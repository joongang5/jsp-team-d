package bbs.member.command;




import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import bbs.auth.model.User;


import bbs.member.service.MemberNotFoundException;


import bbs.member.service.ReadMyReviewService;


import bbs.mvc.command.CommandHandler;
import bbs.review.model.Review;



//내 리뷰 보기 

public class MyReviewHandler extends CommandHandler { //현
	
	
	

	private ReadMyReviewService service = new ReadMyReviewService();
	private List<Review> review = new ArrayList<Review>();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/readMyReview.jsp";
	}


	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {



		Object obj =  req.getSession().getAttribute("authUser");
		User user = (User)obj; 
		String memberId = user.getId();

		
	
		try {
			List<Review> review = service.getMyReview(memberId); 
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
