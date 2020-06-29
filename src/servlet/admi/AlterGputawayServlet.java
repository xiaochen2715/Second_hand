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

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/27 12:29
 * @description 描述：管理员审核物品是否通过
 */
@WebServlet("/alterGputawayServlet")
public class AlterGputawayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("goodsID") != null){
            int goodsID = Integer.parseInt(req.getParameter("goodsID"));
            String gtype = req.getParameter("gtype");
            Connection con = getConnection();
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement("update goods_tb set gputaway=1,gtype=? where id=?");
                ps.setString(1,gtype);
                ps.setInt(2,goodsID);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(null,ps,con);
            }
            System.out.println("ID为"+goodsID+"的物品已通过审核");
            String key = req.getParameter("key");
            String pageCur = req.getParameter("pageCur");
            //重定向到原来的位置
            resp.sendRedirect("manageGoodsServlet?key="+key+"&pageCur="+pageCur);
        }
    }
}
