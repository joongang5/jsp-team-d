package bbs.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.member.dao.MemberDao;

@WebServlet("/joinEmailCheck")
public class JoinEmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinEmailCheck() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		MemberDao dao = new MemberDao();
		int result = 1;// 있다 = 1 = 무조건 1로 만들기
		

		result = dao.emailCheck(email);
		PrintWriter pw = response.getWriter();
		pw.print(result);

	}

}
