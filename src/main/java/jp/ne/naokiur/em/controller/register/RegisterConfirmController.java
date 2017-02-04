package jp.ne.naokiur.em.controller.register;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ne.naokiur.em.code.Site;

@WebServlet(name = "RegisterConfirmController", urlPatterns = { "/user/register/confirm" })
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

//        res.sendRedirect(req.getContextPath() + Site.REGISTER_CONFIRM.getUrl());
        context.getRequestDispatcher(Site.REGISTER_CONFIRM.getJspPath()).forward(req, res);
    }
}
