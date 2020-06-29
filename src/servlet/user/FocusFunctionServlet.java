package servlet.user;
import	java.sql.SQLException;

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

import static dao.BaseDao.close;
import static dao.BaseDao.getConnection;
import static util.Myutil.nowTime;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/30 17:17
 * @description 描述：ajax 添加和取消 关注
 */
@WebServlet("/focusFunctionServlet")
public class FocusFunctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        String result = "";
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        int user_uid = user.getUid();
        int goodsID = Integer.parseInt(req.getParameter("goodsID"));
        String ftime = nowTime();

        Connection con = getConnection();
        PreparedStatement ps = null;
        if (req.getParameter("key").equals("add")){
            //添加关注
            try {
                ps = con.prepareStatement("insert into focus_tb(goods_id,user_uid,ftime) values(?,?,?);");
                ps.setInt(1,goodsID);
                ps.setInt(2,user_uid);
                ps.setString(3,ftime);
                ps.executeUpdate();
                result = "已成功收藏！";
                System.out.println("已成功收藏！");
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(null,ps,con);
            }
        }
        if (req.getParameter("key").equals("del")){
            //取消关注
            try {
                ps = con.prepareStatement("delete from focus_tb where goods_id=? and user_uid=?;");
                ps.setInt(1,goodsID);
                ps.setInt(2,user_uid);
                ps.executeUpdate();
                result = "已取消收藏！";
                System.out.println("已取消收藏！");
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(null,ps,con);
            }
        }

        resp.getWriter().println(result);
    }
}
