package servlet.user;
import	java.sql.SQLException;
import	java.sql.ResultSet;

import model.Focus;
import model.Goods;
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

import static dao.BaseDao.close;
import static dao.BaseDao.getConnection;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/30 19:59
 * @description 描述：查看个人关注的物品
 */
@WebServlet("/myFocusServlet")
public class MyFocusServlet extends HttpServlet {
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

        ArrayList<Goods> myGoods = new ArrayList<> ();

        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement("select * from goods_tb where id in (select goods_id from focus_tb where user_uid=?)");
            ps.setInt(1,user_uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods g = new Goods();
                g.setId(rs.getInt("id"));
                g.setGpicture(rs.getString("gpicture"));
                g.setGname(rs.getString("gname"));
                g.setGprice(rs.getDouble("gprice"));
                g.setGstate(rs.getString("gstate"));
                myGoods.add(g);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,con);
        }

        Collections.reverse(myGoods);
        req.setAttribute("myGoods",myGoods);

        req.getRequestDispatcher("myfocuspage.jsp").forward(req,resp);
    }
}
