package servlet.admi;

import model.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dao.BaseDao.*;
import static util.Myutil.nowTime;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/28 16:24
 * @description 描述：公告管理，分页
 */
@WebServlet("/manageNoticeServlet")
public class ManageNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("key") != null){
            String key = req.getParameter("key");
            if (key.equals("4_1")){
                ArrayList<Notice> allNotice = toFindAllNotice();
                req.setAttribute("allNotice",allNotice);
                int totalCount = allNotice.size();    //记录总数
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
                System.out.println("查询到"+ totalCount +"条公告记录。");
                req.setAttribute("modname","mod4_1");
            }
        }

        if (req.getParameter("noticeID") != null) {
            int noticeID = Integer.parseInt(req.getParameter("noticeID"));
            //查询goodsID对应的物品详情
            Notice n = noticeDetails(noticeID);
            req.setAttribute("noticeDetails",n);
            req.setAttribute("modname","mod4_1_1");

        }
        req.getRequestDispatcher("admi-home.jsp").forward(req, resp);
    }
}
