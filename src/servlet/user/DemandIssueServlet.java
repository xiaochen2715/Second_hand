package servlet.user;
import	java.sql.SQLException;

import model.Demand;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;

import static dao.BaseDao.*;
import static util.Myutil.nowTime;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/30 12:44
 * @description 描述：需求发布与删除
 */
@WebServlet("/demandIssueServlet")
public class DemandIssueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        int user_uid = user.getUid();

        //进行添加操作或者删除操作
        if (req.getParameter("key") != null){
            String key = req.getParameter("key");
            //进行添加操作
            if (key.equals("alt")){
                String dtitle = req.getParameter("dtitle");
                String dcontent = req.getParameter("dcontent");
                String dtime = nowTime();
                Connection con = getConnection();
                PreparedStatement ps= null;
                try{
                    ps = con.prepareStatement("insert into demand_tb(dtitle,dcontent,dtime,user_uid) values (?,?,?,?)");
                    ps.setString(1,dtitle);
                    ps.setString(2,dcontent);
                    ps.setString(3,dtime);
                    ps.setInt(4,user_uid);
                    ps.executeUpdate();
                }catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    close(null,ps,con);
                }
                System.out.println("成功发表需求信息！");
            }
            //进行删除操作
            if (key.equals("del")){
                int demandID = Integer.parseInt(req.getParameter("demandID"));
                deleteDemand(demandID);
                System.out.println("需求删除成功！");
            }
        }

        ArrayList<Demand> myDemand = toFindMyDemand(user_uid);
        Collections.reverse(myDemand);  //倒序
        req.setAttribute("myDemand",myDemand);

        //获取联系方式
        String ucontentway = toFindUser(user_uid).getUcontentway();
        req.setAttribute("ucontentway",ucontentway);

        req.getRequestDispatcher("demandissue.jsp").forward(req,resp);
    }
}
