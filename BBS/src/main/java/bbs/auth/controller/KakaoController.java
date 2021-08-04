package bbs.auth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/oauth/kakao.do")
public class KakaoController extends HttpServlet {
	private final static String TAG = "KakaoController : ";
	private static final long serialVersionUID = 1L;
       
    public KakaoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		System.out.println(TAG+"router : "+code);
		Action action = router(code);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8000/blog/user?code=join
		String code = request.getParameter("code");
		System.out.println(TAG+"router : "+code);
		Action action = router(code);
		action.execute(request, response);
	}
	
	public Action router(String code) {
		if(code != null) {
			// 홈페이지로 이동
			return new KakaocallbackAction();
		}else if(code.equals("joinProc")) {
			// 홈페이지로 이동
			//return new KakaoJoinProcAction(); //Board의 목록
			System.out.println("joinProc로 넘어옴");
			
		}
		return null;
		
	}
	

}
