package servlet.user;

import model.Demand;
import model.Goods;
import model.Notice;

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

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/23 16:26
 * @description 描述：关键字搜索  和   类型查找
 */
@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String str = null;
        //获得隐藏表单的值
        String hid = req.getParameter("hid");
        ArrayList<Goods> allGoods = new ArrayList<> ();
        if (hid.equals("gnamechip")){
            str = req.getParameter("gnamechip");
            allGoods = searchGoods(hid,str);
        }else if(hid.equals("gtype")){
            str = req.getParameter("gtype");
            allGoods = searchGoods(hid,str);
        }

        int allGsize = 0;   //长度

        if (allGoods.size() == 0){
            allGsize = 1;
        }else {
            allGsize = allGoods.size();
        }
        req.setAttribute("allGoods",allGoods);
        req.setAttribute("allGoodsSize",allGsize);   //商品数量

        HttpSession session = req.getSession();
        /*   公告栏  */
//        ArrayList<Notice> allNotices = toFindAllNotice();
//        session.setAttribute("allNotices",allNotices);
        /*   需求栏   */
//        ArrayList<Demand> allDemands = toFindAllDemand();
//        session.setAttribute("allDemands",allDemands);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
