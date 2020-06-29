package servlet.user;
import	java.sql.SQLException;
import	java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static dao.BaseDao.close;
import static dao.BaseDao.getConnection;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/22 22:28
 * @description 描述：验证邮箱唯一性
 */
@WebServlet("/checkEmailAjax")
public class CheckEmailAjax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uemail = req.getParameter("email");
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        try {
            ps = con.prepareStatement("select uemail from user_tb where uemail=?");
            ps.setString(1,uemail);
            rs = ps.executeQuery();
            String s = null;
            while (rs.next()) {
                s = rs.getString("uemail");
                System.out.println("S:"+s);
            }
            if (s == null){
                result = "true";
            }else {
                result = "false";
            }
            if (!uemail.contains("@")){
                result = "false";
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,con);
        }
        System.out.println("请求Ajax后返回邮箱唯一性(false代表已使用)："+result);
        resp.getWriter().print(result);
    }
}
