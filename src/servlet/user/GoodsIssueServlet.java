package servlet.user;
import java.io.PrintWriter;
import	java.sql.SQLException;
import	java.sql.ResultSet;

import model.User;
import util.Myutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/21 10:19
 * @description 描述：上传商品
 */
@WebServlet("/goodsIssueServlet")
@MultipartConfig
public class GoodsIssueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Part对象
        Part part = req.getPart("gpicture");
        String gpicture = "0.png";   //创建一个图片名称的字符串
        String gpicturePrefix = "0";    //创建一个图片前缀名称的字符串
        //指定上传的文件保存到服务器的uploadFiles目录中
        File uploadFileDir = new File(getServletContext().getRealPath("/uploadFiles"));
        if (!uploadFileDir.exists()) {       //判断文件夹是否存在
            uploadFileDir.mkdirs();         //如果不存在，创建子目录
        }
        //获得原始文件名
        String oldName = Myutil.getFileName(part);

        HttpSession session = req.getSession(true);
        String gname = req.getParameter("gname");
        String gdegree = req.getParameter("gdegree");
        double gprice = Double.parseDouble(req.getParameter("gprice"));
        String gtype = req.getParameter("gtype");
        String gsite = req.getParameter("gsite");
        String gintro = req.getParameter("gintro");
        //获取当前登录者的email
        User user = (User)session.getAttribute("user");
        String uemail = user.getUemail();
        int user_uid = currentUserID(uemail);    //当前登录用户的id
        String gtime = Myutil.nowTime();        //获取当前时间


        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            /*添加商品数据*/
            ps = con.prepareStatement("insert into goods_tb(gname,gdegree,gprice,gtype,gsite,gpicture,gintro,gtime,user_uid) values(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,gname);
            ps.setString(2,gdegree);
            ps.setDouble(3,gprice);
            ps.setString(4,gtype);
            ps.setString(5,gsite);
            ps.setString(6,gpicture);
            ps.setString(7,gintro);
            ps.setString(8,gtime);
            ps.setInt(9,user_uid);
            ps.executeUpdate();

            /**/
            //上传时间+用户id可以确定商品id
            ps = con.prepareStatement("select id from goods_tb where user_uid=? and gtime=?");
            ps.setInt(1,user_uid);
            ps.setString(2,gtime);
            rs = ps.executeQuery();
            while (rs.next()){
                gpicturePrefix = String.valueOf(rs.getInt("id"));
            }
            System.out.println("gpicturePrefix = "+ gpicturePrefix);

            if (oldName != null){
                //用新的文件名称替换旧的
                gpicture = gpicturePrefix + oldName.substring(oldName.lastIndexOf("."));
                //将图片上传到服务器的uploadFiles目录中
                part.write(uploadFileDir + File.separator + gpicture);
                System.out.println("图片成功上传至服务器下的uploadFiles" + File.separator + gpicture);
            }

            /*修改该商品id的图片名称*/
            ps = con.prepareStatement("update goods_tb set gpicture=? where id=?");
            ps.setString(1,gpicture);
            ps.setInt(2,Integer.parseInt(gpicturePrefix));
            ps.executeUpdate();

            //后台打印信息
            System.out.println("成功上传数据库！");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-");
            System.out.println("当前用户id："+user_uid+"\n当前用户Email："+uemail+"\n物品名称："+gname+"\n新旧程度："+gdegree
                    +"\n物品类型："+gtype +"\n物品价格："+gprice+"\n上传图片："+gpicture+"\n交易地点："+gsite+"\n物品描述："+gintro+"\n上传时间："+gtime);
            System.out.println("-*-*-*-*-*-*-*-*-*-*-");

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs,ps,con);
        }

        System.out.println("等待管理员审核...");

        session.setAttribute("tip","发布成功，等待管理员审核！");
        resp.sendRedirect("releasedGoodsServlet");
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.println("商品已成功上传，等待审核...<br><a href='home'>点击这里</a>返回首页。");
    }
}
