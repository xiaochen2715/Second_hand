package servlet.user;

import model.Demand;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static dao.BaseDao.demandDetails;
import static dao.BaseDao.toFindUser;


/**
 * @author User: 鹏
 * @version 创建时间：2020/4/29 20:45
 * @description 描述：Ajax 返回需求详情
 */
@WebServlet("/demandDetailsServlet")
public class DemandDetailsServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        int demandID = Integer.parseInt(req.getParameter("demandID"));
        Demand d = demandDetails(demandID);
        String dtitle = d.getDtitle();
        String dcontent = d.getDcontent();
        String dtime = d.getDtime();
        int user_uid = d.getUser_uid();
        //通过id查询用户资料
        User u = toFindUser(user_uid);
        String ucontentway = u.getUcontentway();

        String result = dtitle+"#*#"+dtime+"#*#"+ucontentway+"#*#"+dcontent;
        //将响应结果输出
        resp.getWriter().print(result);
    }
}