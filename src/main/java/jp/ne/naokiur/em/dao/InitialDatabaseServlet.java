package jp.ne.naokiur.em.dao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(loadOnStartup = Integer.MIN_VALUE)
public class InitialDatabaseServlet extends HttpServlet {

    /** Default serialize ID */
    private static final long serialVersionUID = 1L;

    public void init() {
        DatabaseAccessor.INSTANCE.connect();
    }
}
