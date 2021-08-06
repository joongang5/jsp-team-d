package bbs.member.command;








import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;



import bbs.member.service.MemberNotFoundException;


import bbs.member.service.ReadMyReviewService;


import bbs.mvc.command.CommandHandler;
import bbs.review.model.Review;


//�׽�Ʈ

public class MyReviewHandler extends CommandHandler {

	private ReadMyReviewService service = new ReadMyReviewService();


	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/readMyReview.jsp";
	}

	// ������������ �������� �� ������ �����Ϳ� ���� ó���� �մϴ�.
	// ReadArticleHandler�� process(HttpServletRequest req, HttpServletResponse res)
	// �Լ� ����
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 20210806 
		
		
		
		Object obj =  req.getSession().getAttribute("authUser");
		User user = (User)obj; 
		String memberId = user.getId();
		
	
	
		
		
		try {
			Review review = service.getMyReview(memberId); //
			req.setAttribute("myReview", review);
			return getFormViewName();
		} catch (MemberNotFoundException e) {
			req.getServletContext().log("no member", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
