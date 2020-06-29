package servlet.admi;
import	java.sql.SQLException;

import model.Goods;

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
import java.util.ArrayList;

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/26 11:22
 * @description 描述：物品管理（全部物品、待审核物品和已售出物品），分页
 */
@WebServlet("/manageGoodsServlet")
public class ManageGoodsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*  对 “全部物品” 的操作    */
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();

        if (req.getParameter("key") != null) {
            String key = req.getParameter("key");

            if (key.equals("1_1")) {
                ArrayList<Goods> allGoods = toFindAllGoods();
                req.setAttribute("allGoods",allGoods);


                //int totalCount = totalCount("goods_tb");    //记录总数
                int totalCount = allGoods.size();    //记录总数
                int totalPage ;  //总页数
                if (totalCount == 0) {
                    totalPage = 0;
                }else {
                    totalPage = (int)Math.ceil((double)totalCount/15);  //返回大于或等于这个数的最小整数
                }
                String pageCurstr = req.getParameter("pageCur");  //当前页(字符串表示的)
                if (pageCurstr == null) {
                    pageCurstr = "1";
                }
                int pageCur = Integer.parseInt(pageCurstr); //当前页（转为整数型）
                int beginIndex = (pageCur-1)*15;    //迭代的开始
                int endIndex = pageCur*15-1;        //迭代的结尾
                req.setAttribute("beginIndex",beginIndex);
                req.setAttribute("endIndex",endIndex);

                req.setAttribute("totalCount",totalCount);
                req.setAttribute("totalPage",totalPage);
                req.setAttribute("pageCur",pageCur);
                System.out.println("goods_tb总共发现"+totalCount+"条记录。");

                req.setAttribute("modname","mod1_1");

            }

            /*  对 “待审核物品” 的操作    */
            if (key.equals("1_2")){

                Connection con = getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ArrayList<Goods> waitGoods = null;
                try {
                    ps = con.prepareStatement("select * from goods_tb where gputaway=0 order by id asc");
                    rs = ps.executeQuery();
                    waitGoods = new ArrayList<Goods> ();
                    while (rs.next()){
                        Goods g = new Goods();
                        goodsAllField(rs,g);
                        waitGoods.add(g);
                    }

                }catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    close(rs,ps,con);
                }

                int totalCount = waitGoods.size();    //记录总数
                int totalPage;  //总页数
                if (totalCount == 0) {
                    totalPage = 0;
                }else {
                    totalPage = (int)Math.ceil((double)totalCount/2);  //返回大于或等于这个数的最小整数
                }
                String pageCurstr = req.getParameter("pageCur");  //当前页(字符串表示的)
                if (pageCurstr == null) {
                    pageCurstr = "1";
                }
                int pageCur = Integer.parseInt(pageCurstr); //当前页（转为整数型）
                int beginIndex = (pageCur-1)*2;    //迭代的开始
                int endIndex = pageCur*2-1;        //迭代的结尾
                req.setAttribute("beginIndex",beginIndex);
                req.setAttribute("endIndex",endIndex);

                req.setAttribute("totalCount",totalCount);
                req.setAttribute("totalPage",totalPage);
                req.setAttribute("pageCur",pageCur);
                System.out.println("发现待审核物品"+totalCount+"条记录。");

                req.setAttribute("waitGoods",waitGoods);
                req.setAttribute("modname","mod1_2");
            }

            /*  对 “已售出物品” 的操作    */
            if (key.equals("1_3")){
                Connection con = getConnection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                ArrayList<Goods> sellGoods = null;
                try {
                    ps = con.prepareStatement("select * from goods_tb where gstate='已售出' order by id asc");
                    rs = ps.executeQuery();
                    sellGoods = new ArrayList<Goods> ();
                    while (rs.next()){
                        Goods g = new Goods();
                        goodsAllField(rs,g);
                        sellGoods.add(g);
                    }

                }catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    close(rs,ps,con);
                }

                int totalCount = sellGoods.size();    //记录总数
                int totalPage;  //总页数
                if (totalCount == 0) {
                    totalPage = 0;
                }else {
                    totalPage = (int)Math.ceil((double)totalCount/3);  //返回大于或等于这个数的最小整数
                }
                String pageCurstr = req.getParameter("pageCur");  //当前页(字符串表示的)
                if (pageCurstr == null) {
                    pageCurstr = "1";
                }
                int pageCur = Integer.parseInt(pageCurstr); //当前页（转为整数型）
                int beginIndex = (pageCur-1)*3;    //迭代的开始
                int endIndex = pageCur*3-1;        //迭代的结尾
                req.setAttribute("beginIndex",beginIndex);
                req.setAttribute("endIndex",endIndex);

                req.setAttribute("totalCount",totalCount);
                req.setAttribute("totalPage",totalPage);
                req.setAttribute("pageCur",pageCur);
                System.out.println("发现已售出物品"+totalCount+"条记录。");

                req.setAttribute("sellGoods",sellGoods);
                req.setAttribute("modname","mod1_3");
            }
        }

        if (req.getParameter("goodsID") != null){
            int goodsID = Integer.parseInt(req.getParameter("goodsID"));
//                Goods g =new Goods();
            //查询goodsID对应的物品详情
            Goods g = goodsDetails(goodsID);
            req.setAttribute("goodsDetails",g);
            req.setAttribute("modname","mod1_1_1");
        }

//        if (req.getParameter("detailsID") != null){
//
//        }

        req.getRequestDispatcher("admi-home.jsp").forward(req, resp);
    }
}
