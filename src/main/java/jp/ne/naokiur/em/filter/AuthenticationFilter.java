package jp.ne.naokiur.em.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ne.naokiur.em.code.Site;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/user/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) req).getSession();
        String authenticatedName = (String) session.getAttribute("authenticated-user");
        if (authenticatedName != null && !"".equals(authenticatedName)) {
            chain.doFilter(req, res);

        } else {
            ((HttpServletResponse) res).sendRedirect(((HttpServletRequest) req).getContextPath() + Site.LOGIN.getUrl());

        }
    }

    @Override
    public void destroy() {
    }
}
