package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import bbs.member.service.ReadMyPageService;
import bbs.mvc.command.CommandHandler;

public class MyReviewHandler extends CommandHandler {


	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/myReview.jsp";
	}


	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return null;
	}


}

