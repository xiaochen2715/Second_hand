package servlet.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dao.BaseDao.close;
import static dao.BaseDao.getConnection;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/22 23:16
 * @description 描述：注册
 */
@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection con = getConnection();
        PreparedStatement ps = null;

        try{
            ps = con.prepareStatement("insert into user_tb(uemail,upwd,uname,uclass,ucontentway) values(?,?,?,?,?)");
            ps.setString(1,req.getParameter("uemail"));
            ps.setString(2,req.getParameter("upwd"));
            ps.setString(3,req.getParameter("uname"));
            ps.setString(4,req.getParameter("uclass"));
            ps.setString(5,req.getParameter("ucontentway"));
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(null,ps,con);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("注册成功！点击<a href='userlogin.jsp'>前往登录</a>或者<a href='home'>前往首页</a>");
    }
}
