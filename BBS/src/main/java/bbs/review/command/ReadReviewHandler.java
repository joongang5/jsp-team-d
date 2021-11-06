package bbs.review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.review.service.ReviewContentNotFoundException;
import bbs.review.service.ReviewData;
import bbs.review.service.ReviewNotFoundException;
import dev.core.command.CommandHandler;
import bbs.review.service.ReadReviewService;

public class ReadReviewHandler extends CommandHandler {

	private ReadReviewService readService = new ReadReviewService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/readReview.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int reviewNo = Integer.parseInt(noVal);
		try {
			ReviewData reviewData = readService.getReview(reviewNo, true);
			req.setAttribute("reviewData", reviewData);
			return getFormViewName();
		} catch (ReviewNotFoundException | ReviewContentNotFoundException e) {
			req.getServletContext().log("no review", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
