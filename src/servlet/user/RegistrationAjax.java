package servlet.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/22 18:00
 * @description 描述：请求Ajax后返回验证码
 */
@WebServlet("/registrationAjax")
public class RegistrationAjax extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        System.out.println("请求Ajax后返回验证码："+session.getAttribute("rand"));
        resp.getWriter().print(session.getAttribute("rand"));
    }
}
