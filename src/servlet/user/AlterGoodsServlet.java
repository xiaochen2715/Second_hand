package servlet.user;
import	java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static dao.BaseDao.close;
import static dao.BaseDao.getConnection;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/24 17:35
 * @description 描述：修改商品的部分信息
 */
@WebServlet("/alterGoodsServlet")
public class AlterGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String gintro = req.getParameter("gintro");
        double gprice = Double.parseDouble(req.getParameter("gprice"));
        String gsite = req.getParameter("gsite");
        String gstate = req.getParameter("gstate");
        Connection con = getConnection();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("update goods_tb set gintro=?,gprice=?,gsite=?,gstate=? where id=?");
            ps.setString(1,gintro);
            ps.setDouble(2,gprice);
            ps.setString(3,gsite);
            ps.setString(4,gstate);
            ps.setInt(5,id);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(null,ps,con);
        }
        System.out.println("编号为 "+id+" 的物品修改成功！");
        HttpSession session = req.getSession();
        session.setAttribute("tip","物品信息修改成功！");
        resp.sendRedirect("releasedGoodsServlet");
    }
}
