package servlet.user;

import model.Goods;
import model.Message;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static dao.BaseDao.*;
import static util.Myutil.nowTime;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/19 15:33
 * @description 描述：商品详情
 */
@WebServlet("/goodsDetailsServlet")
public class GoodsDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String isFocus = "";    //关注情况

        Connection con =getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> goodsDetails = null;
        Goods g = new Goods();  //实例化Goods实体模型
        User u = new User();    //实例化User实体模型

        ArrayList<Goods> otherGoods = new ArrayList<> ();  //装“推荐物品”的集合
        ArrayList<Message> allMessage = new ArrayList<> (); //装“留言”的集合
        int goodsID = Integer.parseInt(req.getParameter("goodsID"));
        //查询goodsID对应的物品详情
        g = goodsDetails(goodsID);
        //获取浏览量
        int gpv = g.getGpv();
        try {
            //查某商品id对应的主人信息
            ps = con.prepareStatement("select uid,uemail,uname,uclass,ucontentway,udesc,usex from user_tb where uid in (select user_uid from goods_tb where id=?);");
            ps.setInt(1,goodsID);
            rs = ps.executeQuery();
            while (rs.next()){
                u.setUid(rs.getInt("uid"));
                u.setUemail(rs.getString("uemail"));
                u.setUname(rs.getString("uname"));
                u.setUclass(rs.getString("uclass"));
                u.setUcontentway(rs.getString("ucontentway"));
                u.setUdesc(rs.getString("udesc"));
                u.setUsex(rs.getString("usex"));
            }

            //查某商品id对应的该类型相同且已上架的其他商品的id,名字与价格  结果随机排序
            ps = con.prepareStatement("select id,gname,gprice from goods_tb where id not in(?) and gputaway=1 and gtype in(select gtype from goods_tb where id=?)  order by rand();");
            ps.setInt(1,goodsID);
            ps.setInt(2,goodsID);
            rs = ps.executeQuery();
            while (rs.next()){
                Goods g1 = new Goods();  //实例化Goods实体模型
                g1.setId(rs.getInt("id"));
                g1.setGname(rs.getString("gname"));
                g1.setGprice(rs.getDouble("gprice"));
                otherGoods.add(g1);
            }

            //判断该商品是否被关注
            HttpSession session = req.getSession();
            User user = (User)session.getAttribute("user");
            int user_uid = user.getUid();
            req.setAttribute("loginUser",user_uid);
            ps = con.prepareStatement("select * from focus_tb where goods_id=? and user_uid=?");
            ps.setInt(1,goodsID);
            ps.setInt(2,user_uid);
            rs = ps.executeQuery();
            String s = null;
            while (rs.next()){
                s = rs.getString("id");
            }
            if (s!=null){
                isFocus = "已关注";
            }else{
                isFocus = "未关注";
            }

            //如果不是本人的物品
            if(u.getUid() != user_uid){
                //让此物品的浏览量+1
                ps = con.prepareStatement("update goods_tb set gpv=? where id=?");
                ps.setInt(1,gpv+1);
                ps.setInt(2,goodsID);
                ps.executeUpdate();
            }

            //接收key
            if (req.getParameter("key") != null){
                String key = req.getParameter("key");
                //接收到key=fb，发表留言
                if(key.equals("fb")){
                    //发表留言
                    ps = con.prepareStatement("insert into message_tb(mcontent,goods_id,user_uid,mtime) values(?,?,?,?)");
                    ps.setString(1,req.getParameter("mcontent"));
                    ps.setInt(2,goodsID);
                    ps.setInt(3,user_uid);
                    ps.setString(4,nowTime());
                    ps.executeUpdate();
                }
                //接收到key=sc，删除留言
                if(key.equals("sc")){
                    //发表留言
                    int messageID = Integer.parseInt(req.getParameter("messageID"));
                    ps = con.prepareStatement("delete from message_tb where id=?");
                    ps.setInt(1,messageID);
                    ps.executeUpdate();
                }
            }
            //显示留言
            ps = con.prepareStatement("select * from message_tb where goods_id=?");
            ps.setInt(1,goodsID);
            rs = ps.executeQuery();
            while (rs.next()){
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setMcontent(rs.getString("mcontent"));
                m.setGoods_id(rs.getInt("goods_id"));
                m.setUser_uid(rs.getInt("user_uid"));
                m.setMtime(rs.getString("mtime"));
                User u1 = toFindUser(m.getUser_uid());
                m.setUsername(u1.getUname());
                allMessage.add(m);
            }
            Collections.reverse(allMessage);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,con);
        }

        System.out.println(isFocus);

        req.setAttribute("goodsDetails",g);
        req.setAttribute("owner",u);
        req.setAttribute("otherGoods",otherGoods);
        req.setAttribute("isFocus",isFocus);
        req.setAttribute("allMessage",allMessage);
        req.getRequestDispatcher("showgoodsdetails.jsp").forward(req,resp);
    }
}
//查某商品id对应的主人信息
//select uname,uclass,ucontentway,udesc from user_tb where uid in (select user_uid from goods_tb where id=?);
//查某商品id对应的该类型相同且已上架的其他商品的名字与价格
//select gname,gprice from goods_tb where id not in(?) and gputaway=1 and gtype in(select gtype from goods_tb where id=?);