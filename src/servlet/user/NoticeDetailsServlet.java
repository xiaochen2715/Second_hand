package servlet.user;

import model.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static dao.BaseDao.noticeDetails;
import static dao.BaseDao.toFindAllNotice;

/**
 * @author User: 鹏
 * @version 创建时间：2020/4/29 13:06
 * @description 描述：Ajax 返回公告详情
 */
@WebServlet("/noticeDetailsServlet")
public class NoticeDetailsServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        int noticeID = Integer.parseInt(req.getParameter("noticeID"));
        Notice n = noticeDetails(noticeID);
        String ntitle = n.getNtitle();
        String ncontent = n.getNcontent();
        String ntime = n.getNtime();

        String result = ntitle+"#*#"+ntime+"#*#"+ncontent;
        //将响应结果输出
        resp.getWriter().print(result);
    }
}
