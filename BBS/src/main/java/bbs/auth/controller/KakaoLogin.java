package bbs.auth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// http://localhost:8000/blog/board
@WebServlet("/oauth/kakao")
public class KakaoLogin extends HttpServlet {
	private final static String TAG = "KakaoController : ";
	private static final long serialVersionUID = 1L;
       
    public KakaoLogin() {
        super();
    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doProcess(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doProcess(request, response);
//	}
////	
////	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		// http://localhost:8000/blog/user?cmd=join
////		String cmd = request.getParameter("cmd");
////		System.out.println(TAG+"router : "+cmd);
////		Action action = router(cmd);
////		action.execute(request, response);
////	}
////	
////	public Action router(String cmd) {
////		if(cmd.equals("callback")) {
////			// 홈페이지로 이동
////			return new KakaocallbackAction();
////		}
////		return null;
////		
////	}
////	

}