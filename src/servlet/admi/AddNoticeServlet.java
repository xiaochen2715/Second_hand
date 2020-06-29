package servlet.admi;
import	java.sql.SQLException;

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
import static util.Myutil.nowTime;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/28 18:54
 * @description 描述：添加公告
 */
@WebServlet("/addNoticeServlet")
public class AddNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("addN").equals("t")){

            req.setAttribute("modname","mod4_1_2");
            req.getRequestDispatcher("admi-home.jsp").forward(req, resp);
        }

        if (req.getParameter("addN").equals("f")) {
            String ntitle = req.getParameter("ntitle");
            String ncontent = req.getParameter("ncontent");
            String ntime = nowTime();
            Connection con = getConnection();
            PreparedStatement ps = null;
            try{
                ps = con.prepareStatement("insert into notice_tb(ntitle,ncontent,ntime) values(?,?,?)");
                ps.setString(1,ntitle);
                ps.setString(2,ncontent);
                ps.setString(3,ntime);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(null,ps,con);
            }

            resp.sendRedirect("manageNoticeServlet?key=4_1");
        }
    }
}
