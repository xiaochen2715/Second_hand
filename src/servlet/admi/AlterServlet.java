package servlet.admi;

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
import static util.Myutil.nowTime;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/28 17:28
 * @description 描述：管理员修改公告
 */
@WebServlet("/alterServlet")
public class AlterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("noticeID") != null) {
            int noticeID = Integer.parseInt(req.getParameter("noticeID"));
            String ntitle = req.getParameter("ntitle");
            String ncontent = req.getParameter("ncontent");
            String ntime = nowTime();
            Connection con = getConnection();
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement("update notice_tb set ntitle=?,ncontent=?,ntime=? where id=?");
                ps.setString(1, ntitle);
                ps.setString(2, ncontent);
                ps.setString(3, ntime);
                ps.setInt(4, noticeID);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close(null, ps, con);
            }

            System.out.println("编号为"+noticeID+"的公告修改成功！");
            resp.sendRedirect("manageNoticeServlet?key=4_1");
        }
    }
}
