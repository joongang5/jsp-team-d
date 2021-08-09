package bbs.member.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.member.dao.MemberDao;

@WebServlet("/joinCheck")
public class JoinCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinCheck() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		MemberDao dao = new MemberDao();
		int idResult = 1;// 있다 = 1 = 무조건 1로 만들기
		int emailResult = 1;
		int nameResult = 1;
		
		if (id != null) {
			// response.getWriter().write(1);//아이디가 있으면 1
			// DAO에게 결과값 받아오게 일 시키기
			idResult = dao.idCheck(id);// 아이디가 없다면 0
			PrintWriter pw = response.getWriter();
			pw.print(idResult);

		} else if (name != null) {
			
			nameResult = dao.nameCheck(name);
			PrintWriter pw = response.getWriter();
			pw.print(nameResult);
			
		} else if (email != null) {
			
			emailResult = dao.emailCheck(email);
			PrintWriter pw = response.getWriter();
			pw.print(emailResult);


		}

	}

}
