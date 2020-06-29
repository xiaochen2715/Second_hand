package servlet.user;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static dao.BaseDao.*;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/17 22:31
 * @description 描述： 用户登录
 */
@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        User user = new User();
        user.setUemail(req.getParameter("uemail"));
        user.setUpwd(req.getParameter("upwd"));

        if (userValidate(user) ){
            System.out.println("用户登录成功");
            System.out.println("Email："+user.getUemail());
            System.out.println("密码："+user.getUpwd());

            //把当前用户的所有信息存入session
            user = toFindUser(currentUserID(user.getUemail()));
            session.setAttribute("user",user);
            //resp.sendRedirect("user/home.jsp");
            //转发至→首页
            //req.getRequestDispatcher("user/home.jsp").forward(req,resp);
            resp.sendRedirect("home");
            //req.getRequestDispatcher("homeServlet").forward(req, resp);
        }else {
            System.out.println("用户登录失败");
            out.println("登录失败，3秒后返回登录页面。或者选择<a href='registration.jsp'>点击这里</a>前往注册页面。");
            resp.setHeader("refresh","3;url=\"userlogin.jsp\"");
        }

    }
}
