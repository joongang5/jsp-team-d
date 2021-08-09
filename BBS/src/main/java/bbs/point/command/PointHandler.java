package bbs.point.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.review.model.Writer;
import bbs.review.service.WriteRequest;
import bbs.review.service.WriteReviewService;



public class PointHandler extends CommandHandler {

	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/point.jsp";
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		return null;

	}
}
