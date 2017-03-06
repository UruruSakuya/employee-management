package jp.ne.naokiur.em.controller.register;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.Site;
import jp.ne.naokiur.em.dto.EmployeeDto;
import jp.ne.naokiur.em.model.RegisterModel;

@WebServlet(name = "RegisterCompleteController", urlPatterns = {"/user/register/complete"})
public class RegisterCompleteController extends HttpServlet {

    /** Default serialize ID */
    private static final long serialVersionUID = 1L;

    /** ServletContext */
    private ServletContext context;

    private final RegisterModel registerModel = new RegisterModel();

    public void init() {
        context = getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        EmployeeDto employee = new EmployeeDto();
        employee.setUserId((String) session.getAttribute("user_id"));
        employee.setFirstName((String) session.getAttribute("first_name"));
        employee.setLastName((String) session.getAttribute("last_name"));
        employee.setPostCode((String) session.getAttribute("post_code"));
        employee.setAge(Integer.valueOf((String) session.getAttribute("age")));
        try {
            employee.setEnterDate(new Timestamp(new SimpleDateFormat("yyyy-mm-dd")
                    .parse(String.valueOf(session.getAttribute("enter_date"))).getTime()));
        } catch (ParseException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        registerModel.register(employee);

        context.getRequestDispatcher(Site.REGISTER_COMPLETE.getJspPath()).forward(req, res);
    }
}
