package bbs.auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.mvc.command.CommandHandler;

public class LogoutHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false);
		if (session.getAttribute("snsAuthUser") != null) {

			session.invalidate();

			// 카카오 로그아웃
			String clientId = "188766d70b45863a165fa74d7d8a455b";
			String logout_redirectUri = "http://localhost:8080/BBS//boxOffice/list.do?logout=kakao";
			String logoutUrl = "https://kauth.kakao.com/oauth/logout?client_id=" + clientId + "&logout_redirect_uri="
					+ logout_redirectUri;
			res.sendRedirect(logoutUrl);

		} else {

			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/boxOffice/list.do?logout=auth");

		}

		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
}
