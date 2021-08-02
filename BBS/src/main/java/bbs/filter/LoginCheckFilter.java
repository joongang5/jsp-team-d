package bbs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();

		if (wasLogin(session)) {
			chain.doFilter(req, res);
		} else {
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath() + "./login.do");
		}
	}

	@Override
	public void destroy() {
	}

	private boolean wasLogin(HttpSession session) {
		if (session == null)
			return false;

		if (session.getAttribute("authUser") == null)
			return false;

		return true;
	}
}
