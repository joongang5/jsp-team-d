package bbs.review.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.review.service.ModifyRequest;
import bbs.review.service.ModifyReviewService;
import bbs.review.service.PermissionDeniedException;
import bbs.review.service.ReadReviewService;
import bbs.review.service.ReviewData;
import bbs.review.service.ReviewNotFoundException;



public class ModifyReviewHandler extends CommandHandler {
	
	private ReadReviewService readService = new ReadReviewService();
	private ModifyReviewService modifyService = new ModifyReviewService(); 
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/reviewmodifyForm.jsp";
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
		
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
				req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		modReq.validate(errors);
		if (!errors.isEmpty()) {
			return getFormViewName();
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/reviewmodifySuccess.jsp";
		} catch (ReviewNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}