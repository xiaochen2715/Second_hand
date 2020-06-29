package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/17 18:06
 * @description 描述：
 */
@WebFilter(filterName = "loginFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();

       // System.out.println("过滤器执行");

        // 得到用户请求的URI
        String request_uri = req.getRequestURI();
        // 得到web应用程序的上下文路径
        String ctxPath = req.getContextPath();
        // 去除上下文路径，得到剩余部分的路径
        String uri = request_uri.substring(ctxPath.length());
        //默认不拦截页面
//        if (uri.contains("login") || uri.contains("Login") || uri.contains("home") || uri.contains("imgs")
//                || uri.contains("css") || uri.contains("jpg") || uri.contains("png")){
//            filterChain.doFilter(servletRequest, servletResponse);
//        }else {
//            //判断用户是否登录
//            if (session.getAttribute("user") != null){
//                //执行下一个过滤器
//                System.out.println("过滤器：有用户登录");
//                filterChain.doFilter(servletRequest, servletResponse);
//            }else {
//                out.println("您还没有登录，请先登录！3秒钟后前往登录页面。");
//                resp.setHeader("refresh","3;url=" + ctxPath + "/userlogin.jsp");
//            }
//        }

        if (uri.contains("home") || uri.contains("uploadFiles") || uri.contains("imgs") || uri.contains("css") ||uri.contains("js")
        || uri.contains("validateCode") || uri.contains("LoginServlet") || uri.contains("registration") || uri.contains("logout")
        || uri.contains("demandDetailsServlet") || uri.contains("noticeDetailsServlet") || uri.contains("checkEmailAjax")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //判断管理员是否登录
            if (session.getAttribute("admi") != null){
                System.out.println("管理员登录！");
                filterChain.doFilter(servletRequest,servletResponse);
            }else
            //判断用户是否登录
            if (session.getAttribute("user") != null){
                System.out.println("用户登录！");
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                System.out.println("请先登录！");
                resp.sendRedirect("userlogin.jsp");
            }
        }
    }
}
