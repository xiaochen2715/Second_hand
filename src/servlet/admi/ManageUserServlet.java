package servlet.admi;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dao.BaseDao.toFindAllusers;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/28 12:47
 * @description 描述：
 */
@WebServlet("/manageUserServlet")
public class ManageUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("key");

        if (key.equals("2_1")) {
            ArrayList<User> allUsers = toFindAllusers();
            req.setAttribute("allUsers",allUsers);

            int totalCount = allUsers.size();    //记录总数
            int totalPage ;  //总页数
            if (totalCount == 0) {
                totalPage = 0;
            }else {
                totalPage = (int)Math.ceil((double)totalCount/10);  //返回大于或等于这个数的最小整数
            }
            String pageCurstr = req.getParameter("pageCur");  //当前页(字符串表示的)
            if (pageCurstr == null) {
                pageCurstr = "1";
            }
            int pageCur = Integer.parseInt(pageCurstr); //当前页（转为整数型）
            int beginIndex = (pageCur-1)*10;    //迭代的开始
            int endIndex = pageCur*10-1;        //迭代的结尾
            req.setAttribute("beginIndex",beginIndex);
            req.setAttribute("endIndex",endIndex);

            req.setAttribute("totalCount",totalCount);
            req.setAttribute("totalPage",totalPage);
            req.setAttribute("pageCur",pageCur);
            System.out.println("user_tb总共发现"+totalCount+"条记录。");

            req.setAttribute("modname","mod2_1");
        }

        req.getRequestDispatcher("admi-home.jsp").forward(req, resp);
    }
}
