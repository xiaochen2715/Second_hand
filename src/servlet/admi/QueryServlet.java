package servlet.admi;

import model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/28 11:38
 * @description 描述： gname关键字查询查询
 */
@WebServlet("/queryServlet")
public class QueryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("key");

        if (key.equals("mod1_1")){
            String str;
            //获得隐藏表单的值
            String hid = req.getParameter("hid");
            ArrayList<Goods> allGoods = new ArrayList<Goods> ();
            if (hid.equals("gnamechip")){
                str = req.getParameter("gnamechip");
                allGoods = searchGoods(hid,str);
            }else if(hid.equals("gtype")){
                str = req.getParameter("gtype");
                allGoods = searchGoods(hid,str);
            }
            req.setAttribute("allGoods",allGoods);

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
            System.out.println("查询共发现"+totalCount+"条记录。");

            req.setAttribute("modname","mod1_1");

            if (req.getParameter("goodsID") != null){
                int goodsID = Integer.parseInt(req.getParameter("goodsID"));
//                Goods g =new Goods();
                //查询goodsID对应的物品详情
                Goods g = goodsDetails(goodsID);
                req.setAttribute("goodsDetails",g);
                req.setAttribute("modname","mod1_1_1");
            }
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }
}
