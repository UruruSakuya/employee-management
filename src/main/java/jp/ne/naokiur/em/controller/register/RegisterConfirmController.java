package jp.ne.naokiur.em.controller.register;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.Site;
import jp.ne.naokiur.em.dao.PostAccessorImpl;

@WebServlet(name = "RegisterConfirmController", urlPatterns = {"/user/register/confirm"})
public class RegisterConfirmController extends HttpServlet {

    /** Default serialize ID */
    private static final long serialVersionUID = 1L;

    /** ServletContext */
    private ServletContext context;

    public void init() {
        context = getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user_id", req.getParameter("user_id"));
        session.setAttribute("first_name", req.getParameter("first_name"));
        session.setAttribute("last_name", req.getParameter("last_name"));
        session.setAttribute("post_code", req.getParameter("post_code"));
        session.setAttribute("post_name", PostAccessorImpl.INSTANCE.selectPostNameByCode(req.getParameter("post_code")));
        session.setAttribute("age", req.getParameter("age"));
        session.setAttribute("entry_date", req.getParameter("entry_date"));

        context.getRequestDispatcher(Site.REGISTER_CONFIRM.getJspPath()).forward(req, res);
    }
}
