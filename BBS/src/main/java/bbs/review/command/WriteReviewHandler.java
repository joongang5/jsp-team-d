package bbs.review.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.member.service.MyPointService;
import bbs.mvc.command.CommandHandler;
import bbs.review.model.Writer;
import bbs.review.service.WriteRequest;
import bbs.review.service.WriteReviewService;



public class WriteReviewHandler extends CommandHandler {
	//이현아 추가
	private MyPointService myPointS = new MyPointService();
	////////////////////////////////
	
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/newReviewForm.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		//세션에서 로그인한 사용자 정보를 구한다.
		//user와 httpservletrequest를 이용해서 writerequest 객체를 생성한다.
		//writereq 객체가 유효한지 검사한다.
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		//이현아가 아이디 변수 추가
		String userId = user.getId();
		
		
		//에러가 존재하면 폼을 다시 보여준다.
		if(errors.isEmpty() == false)
			return getFormViewName();

		
		//writereviewservice를 이용해서 게시글을 등록하고, 등록된 게시근의 id를 리턴받는다.
		//새 글의 id를 request의 newreviewid 속성에 저장한다. 처리 결과를 보여줄 jsp는 이 속성값을 사용해서 링크를 생성한다.
		WriteReviewService writeService = new WriteReviewService();
		int newReviewNo = writeService.write(writeReq);
		req.setAttribute("newReviewNo", newReviewNo);
		
		//이현아 추가 - 글 쓰기 완료 했으니 포인트 추가
		myPointS.upgradeMyPoint(userId); 
		/////////////////////////////////////
		
		return"/WEB-INF/view/newReviewSuccess.jsp";
		
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
	}
}