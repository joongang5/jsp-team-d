package bbs.review.command;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.review.service.DeleteReviewService;
import bbs.review.service.ModifyRequest;
import bbs.review.service.PermissionDeniedException;
import bbs.review.service.ReadReviewService;
import bbs.review.service.ReviewData;
import bbs.review.service.ReviewNotFoundException;

public class DeleteReviewHandler extends CommandHandler {

	private DeleteReviewService deleteReview = new DeleteReviewService();
	private ReadReviewService readService = new ReadReviewService();
	
	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/deleteReviewForm.jsp";
	}
	
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			ReviewData reviewData = readService.getReview(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			
			if (!canModify(authUser, reviewData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
					reviewData.getReview().getTitle(),
					reviewData.getContent());
			
			req.setAttribute("modReq", modReq);
			return getFormViewName();
		} catch (ReviewNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canModify(User authUser, ReviewData reviewData) {
		String writerId = reviewData.getReview().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		try {
			deleteReview.deleteReview(no);
			return "/WEB-INF/view/deleteReviewSuccess.jsp";
		} catch (ReviewNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			System.out.println("실패");
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			System.out.println("실패");
			return null;
		}  
	}
		 
	}


