package servlet.user;

import model.Goods;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static dao.BaseDao.toFindMyGoods;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/24 14:39
 * @description 描述：查看个人已发布物品
 */
@WebServlet("/releasedGoodsServlet")
public class ReleasedGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = new User();
        user = (User)session.getAttribute("user");
//        ArrayList<Goods> myGoods = new ArrayList<Goods> ();
//        myGoods = toFindMyGoods(user.getUid());
        //将我的所有发布过的物品存入
        req.setAttribute("myGoods",toFindMyGoods(user.getUid()));
        req.getRequestDispatcher("goodsissue.jsp").forward(req, resp);
    }
}
