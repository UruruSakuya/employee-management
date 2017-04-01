package jp.ne.naokiur.em.controller.register;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.AttributeKey;
import jp.ne.naokiur.em.code.Messages;
import jp.ne.naokiur.em.code.Site;
import jp.ne.naokiur.em.dao.PostAccessorImpl;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.exception.ModelValidatorException;
import jp.ne.naokiur.em.model.RegisterModel;

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
        EmployeeDto employee = new EmployeeDto();

        employee.setUserId(String.valueOf(session.getAttribute("user_id")));
        employee.setFirstName(String.valueOf(session.getAttribute("first_name")));
        employee.setLastName(String.valueOf(session.getAttribute("last_name")));
        employee.setPostCode(String.valueOf(session.getAttribute("post_code")));
        employee.setAge(Integer.valueOf((String) session.getAttribute("age")));

        try {
            employee.setEnterDate(new Timestamp(new SimpleDateFormat("yyyy-mm-dd")
                    .parse(String.valueOf(session.getAttribute("enter_date"))).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RegisterModel model = new RegisterModel();
        try {
            model.validate(employee);
        } catch (ModelValidatorException e) {
            req.setAttribute(AttributeKey.MESSAGE_LIST.getKey(), new ArrayList<Messages>() {
                /** Default serialize id */
                private static final long serialVersionUID = 1L;

                {
                    add(e.getMessages());
                }
            });

            context.getRequestDispatcher(Site.REGISTER_INIT.getJspPath()).forward(req, res);

            return;
        }


        session.setAttribute("user_id", employee.getUserId());
        session.setAttribute("first_name", employee.getFirstName());
        session.setAttribute("last_name", employee.getLastName());
        session.setAttribute("post_code", employee.getPostCode());
        session.setAttribute("post_name",
                PostAccessorImpl.INSTANCE.selectPostNameByCode(employee.getPostCode()));
        session.setAttribute("age", employee.getAge());
        session.setAttribute("enter_date", employee.getEnterDate());

        context.getRequestDispatcher(Site.REGISTER_CONFIRM.getJspPath()).forward(req, res);
    }
}
