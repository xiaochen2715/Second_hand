package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/17 18:09
 * @description 描述：
 */
@WebFilter(filterName = "characterEncoding", urlPatterns = { "/*" })
public class SetCharacterEncodingFilter implements Filter {
    public SetCharacterEncodingFilter() {
    }
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req, resp);

    }
    public void init(FilterConfig fConfig) throws ServletException {
    }
}