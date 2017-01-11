package jp.ne.naokiur.em.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IndexController", urlPatterns = { "/" })
public class IndexController extends HttpServlet {

    /** Default serialize ID */
    private static final long serialVersionUID = 1L;

    /** ServletContext */
    private ServletContext context;

    /** JSP file path */
    private final String jspFile = "/WEB-INF/views/index.jsp";

    public void init() {
        context = getServletContext();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("welcome", "Welcome employee-management!");
        context.getRequestDispatcher(jspFile).forward(req, res);
    }
}
