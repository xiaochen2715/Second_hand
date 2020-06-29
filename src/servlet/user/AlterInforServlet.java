package servlet.user;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static dao.BaseDao.toAddition;
import static dao.BaseDao.toFindUser;


/**
 * @author User: 鹏
 * @version 创建时间：2020/4/23 21:16
 * @description 描述：对用户个人信息的操作
 */
@WebServlet("/alterInforServlet")
public class AlterInforServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        User user = new User();
        //获得原始个人信息
        user = (User)session.getAttribute("user");

        String uname = req.getParameter("uname");
        String usex = req.getParameter("usex");
        String uclass = req.getParameter("uclass");
        String ucontentway = req.getParameter("ucontentway");
        String udesc = req.getParameter("udesc");

        String sql = "update user_tb set uname=?,usex=?,uclass=?,ucontentway=?,udesc=? where uid="+user.getUid();
        String[] s = {uname,usex,uclass,ucontentway,udesc};
        toAddition(sql,s);

        //将新的个人信息存入session
        session.setAttribute("user",toFindUser(user.getUid()));

        System.out.println("个人信息修改成功！");

        session.setAttribute("tip","个人信息修改成功！");
        resp.sendRedirect("alterinfor.jsp");
    }
}
