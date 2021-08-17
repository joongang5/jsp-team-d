package bbs.auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;
import bbs.auth.service.KakaoLoginService;
import bbs.auth.service.LoginFailException;
import bbs.auth.service.LoginService;
import bbs.member.model.Member;
import bbs.mvc.command.CommandHandler;
import bbs.util.ErrorUtil;

public class LoginHandler extends CommandHandler {

	private LoginService loginService = new LoginService();
	private KakaoLoginService kakaoLoginService = new KakaoLoginService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/loginForm.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String code = req.getParameter("code"); // 로그인 과정중 얻은 authorization code 값
		if (code == null || code.isEmpty())
			return getFormViewName();

		String access_token = kakaoLoginService.login(code);

		HashMap<String, Object> userInfo = kakaoLoginService.getKakaoUserInfo(access_token);

		String email = userInfo.get("email").toString();

		// 3. 이메일 정보가 DB에도 존재하는지 확인
		Member member = kakaoLoginService.kakaoLoginValid(email);

		HttpSession session = req.getSession();
		// 4. 이미 가입된 회원이라면 그냥 로그인 처리
		if (member != null) {
			// String id = email;

			User user = new User(member.getId(), member.getName(), access_token);
			String snsUser = "kakao";
			
			session.setAttribute("authUser", user);
			session.setAttribute("snsAuthUser", snsUser);
			
			
			
			return "/boxOffice/list.do?login=kakao";
			// 3.2 존재하지 않는다면 카카오 계정을 기반으로 회원가입 시켜야함
		} else {
			// 3.2.1 name필드에 닉네임을 입력하도록 유도
			// 3.2.2 id 필드에 email주소를 입력
			User user = new User(email, access_token);
			session.setAttribute("snsUser", user);
			//return "/boxOffice/list.do?login=kakao";
			res.sendRedirect("./boxOffice/list.do");
			return null;
			// 카카오 간단 회원가입 폼 이동
		}
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String id = trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		if (id.contains("@") == false) {
			ErrorUtil.checkEmpty(errors, id, "id");
		}

		ErrorUtil.checkEmpty(errors, password, "password");

		if (errors.isEmpty() == false)
			return getFormViewName();

		try {
			User user = loginService.login(id, password);

			req.getSession().setAttribute("authUser", user);
			res.sendRedirect(req.getContextPath() + "/boxOffice/list.do");
			return null;
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return getFormViewName();
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}
