package bbs.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.member.dao.MemberDao;

@WebServlet("/joinIdCheck")
public class JoinIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinIdCheck() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		int result = 1;// 있다 = 1 = 무조건 1로 만들기

		// response.getWriter().write(1);//아이디가 있으면 1
		// System.out.println(id);
		// DAO에게 결과값 받아오게 일 시키기
		result = dao.idCheck(id);// 아이디가 없다면 0

		PrintWriter pw = response.getWriter();
		pw.print(result);

	}

}
