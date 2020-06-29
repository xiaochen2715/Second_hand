package servlet.user;
import	java.util.ArrayList;

import model.Demand;
import model.Goods;
import model.Notice;

import java.sql.Connection;
import	java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/18 11:32
 * @description 描述：
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection con =getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> allGoods = null;
        int allGsize;   //长度
        try {
            ps = con.prepareStatement("select gpicture,gname,gprice,gdegree,id from goods_tb where gputaway=1 order by id DESC");
            rs = ps.executeQuery();
            allGoods = new ArrayList<> ();
            while (rs.next()) {
                Goods g = new Goods();
                g.setGpicture(rs.getString("gpicture"));
                g.setGname(rs.getString("gname"));
                g.setGprice(rs.getDouble("gprice"));
                g.setGdegree(rs.getString("gdegree"));
                g.setId(rs.getInt("id"));
                allGoods.add(g);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs,ps,con);
        }
        if (allGoods.size() == 0){
            allGsize = 1;
        }else {
            allGsize = allGoods.size();
        }
        req.setAttribute("allGoods",allGoods);
        req.setAttribute("allGoodsSize",allGsize);   //商品数量

        HttpSession session = req.getSession();
        /*   人气排行  */
        ArrayList<Goods> allpvGoods = goodsPV();
        session.setAttribute("allpvGoods",allpvGoods);

        /*   公告栏  */
        ArrayList<Notice> allNotices = toFindAllNotice();
        session.setAttribute("allNotices",allNotices);

        /*   需求栏   */
        ArrayList<Demand> allDemands = toFindAllDemand();
        Collections.shuffle(allDemands);    //随机排序
        session.setAttribute("allDemands",allDemands);

        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }
}
