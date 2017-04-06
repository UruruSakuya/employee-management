package jp.ne.naokiur.em.controller.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.Site;
import jp.ne.naokiur.em.dao.PostAccessorImpl;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.dto.PostDto;
import jp.ne.naokiur.em.model.EmployeeModel;

@WebServlet(name = "SearchController", urlPatterns = {"/user/search/*"})
public class SearchInitController extends HttpServlet {

    /** Default serialize ID */
    private static final long serialVersionUID = 1L;

    /** ServletContext */
    private ServletContext context;

    public void init() {
        context = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("title", Site.SEARCH_INIT.getTitle());

        List<PostDto> postList = PostAccessorImpl.INSTANCE.selectAllPost();
        // 検索条件用
        postList.add(0, new PostDto("", ""));

        session.setAttribute("postList", postList);
        context.getRequestDispatcher(Site.SEARCH_INIT.getJspPath()).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        List<EmployeeDto> resultList = new EmployeeModel().search(req.getParameter("user_id"),
                req.getParameter("fitst_name"), req.getParameter("last_name"), req.getParameter("post_code"),
                req.getParameter("from_age"), req.getParameter("to_age"), req.getParameter("from_enter_date"),
                req.getParameter("to_enter_date"));
        session.setAttribute("resultList", resultList);
        session.setAttribute("title", Site.SEARCH_INIT.getTitle());

        context.getRequestDispatcher(Site.SEARCH_INIT.getJspPath()).forward(req, res);
    }
}
