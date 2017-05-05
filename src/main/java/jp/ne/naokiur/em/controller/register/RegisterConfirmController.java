package jp.ne.naokiur.em.controller.register;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.AttributeKey;
import jp.ne.naokiur.em.code.Site;
import jp.ne.naokiur.em.dao.PostAccessorImpl;
import jp.ne.naokiur.em.exception.ModelValidatorException;
import jp.ne.naokiur.em.model.EmployeeModel;

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

        try {
            EmployeeModel model = new EmployeeModel(req.getParameter("user_id"), req.getParameter("password"),
                    req.getParameter("password_again"), req.getParameter("first_name"), req.getParameter("last_name"),
                    req.getParameter("post_code"), req.getParameter("age"), req.getParameter("enter_date"));

            model.validate();

            session.setAttribute("user_id", model.getUserId());
            session.setAttribute("password", model.getPassword());
            session.setAttribute("password_again", model.getPasswordAgain());
            session.setAttribute("first_name", model.getFirstName());
            session.setAttribute("last_name", model.getLastName());
            session.setAttribute("post_code", model.getPostCode());
            session.setAttribute("post_name", PostAccessorImpl.INSTANCE.selectPostNameByCode(model.getPostCode()));
            session.setAttribute("age", model.getAge());
            session.setAttribute("enter_date", model.getEnterDate());

        } catch (ModelValidatorException e) {
            req.setAttribute(AttributeKey.MESSAGE_LIST.getKey(), e.getMessagesList());

            context.getRequestDispatcher(Site.REGISTER_INIT.getJspPath()).forward(req, res);

            return;
        }

        context.getRequestDispatcher(Site.REGISTER_CONFIRM.getJspPath()).forward(req, res);
    }
}
