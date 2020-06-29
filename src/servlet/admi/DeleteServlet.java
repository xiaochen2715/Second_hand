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

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/26 17:23
 * @description 描述：
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //删除某物品ID的所有信息
        if (req.getParameter("goodsID") != null){
            int goodsID = Integer.parseInt(req.getParameter("goodsID"));
            deleteGoods(goodsID);

            System.out.println("ID为"+goodsID+"的物品已被删除。");
            String key = req.getParameter("key");
            String pageCur = req.getParameter("pageCur");

            //重定向到原来的位置
            resp.sendRedirect("manageGoodsServlet?key="+key+"&pageCur="+pageCur);
            //req.getRequestDispatcher("manageGoodsServlet").forward(req, resp);
        }

        //删除某公告ID的所有信息
        if (req.getParameter("noticeID") != null){
            int noticeID = Integer.parseInt(req.getParameter("noticeID"));
            String key = req.getParameter("key");
            String pageCur = req.getParameter("pageCur");
            Connection con = getConnection();
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement("delete from notice_tb where id=?");
                ps.setInt(1,noticeID);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(null,ps,con);
            }

            resp.sendRedirect("manageNoticeServlet?key="+key+"&pageCur="+pageCur);
        }

        //删除某需求ID的所有信息
        if (req.getParameter("demandID") != null){
            int demandID = Integer.parseInt(req.getParameter("demandID"));
            deleteDemand(demandID);
            String key = req.getParameter("key");
            String pageCur = req.getParameter("pageCur");

            resp.sendRedirect("manageDemandServlet?key="+key+"&pageCur="+pageCur);
        }

        //删除某用户ID的所有信息
        if (req.getParameter("userID") != null){
            int userID = Integer.parseInt(req.getParameter("userID"));
            String key = req.getParameter("key");
            String pageCur = req.getParameter("pageCur");
            Connection con = getConnection();
            PreparedStatement ps = null;
            try {
                ps = con.prepareStatement("delete from user_tb where uid=?");
                ps.setInt(1,userID);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close(null,ps,con);
            }

            resp.sendRedirect("manageUserServlet?key="+key+"&pageCur="+pageCur);
        }
    }
}
