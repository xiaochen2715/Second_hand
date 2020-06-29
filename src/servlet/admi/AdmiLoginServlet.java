package servlet.admi;

import model.Admi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static dao.BaseDao.admiValidate;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/23 10:19
 * @description 描述：管理员登录判定
 */
@WebServlet("/admiLoginServlet")
public class AdmiLoginServlet extends HttpServlet {
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
        Admi admi = new Admi();
        admi.setAid(Integer.parseInt(req.getParameter("aid")));
        admi.setApwd(req.getParameter("apwd"));
        if (admiValidate(admi)){
            session.setAttribute("admi",admi);
            System.out.println("管理员登陆成功");
            System.out.println("账号："+admi.getAid());
            System.out.println("密码："+admi.getApwd());

            //前往管理员首页
            resp.sendRedirect("admiHomeServlet");
        }else{
            System.out.println("管理员登录失败");
            out.println("登录失败，3秒后返回登录页面。或者我不是管理员<a href='userlogin.jsp'>点击这里</a>前往用户登录页面。");
            resp.setHeader("refresh","3;url=\"admilogin.jsp\"");
        }
    }
}
