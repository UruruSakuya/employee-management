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
import jp.ne.naokiur.em.exception.ModelValidatorException;
import jp.ne.naokiur.em.model.EmployeeModel;

@WebServlet(name = "RegisterCompleteController", urlPatterns = {"/user/register/complete"})
public class RegisterCompleteController extends HttpServlet {

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

            EmployeeModel<Object> model = new EmployeeModel<>(session.getAttribute("user_id"),
                    session.getAttribute("first_name"), session.getAttribute("last_name"), session.getAttribute("post_code"),
                    session.getAttribute("age"), session.getAttribute("enter_date"));

            model.register();

        } catch (ModelValidatorException e) {
            e.printStackTrace();
        }

        context.getRequestDispatcher(Site.REGISTER_COMPLETE.getJspPath()).forward(req, res);
    }
}
