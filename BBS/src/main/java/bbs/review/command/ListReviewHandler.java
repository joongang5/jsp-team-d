package bbs.review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.review.service.ListReviewService;
import bbs.review.service.ReviewPage;
import dev.core.command.CommandHandler;

public class ListReviewHandler extends CommandHandler {

	private ListReviewService listService = new ListReviewService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/listReview.jsp";
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
		throws Exception{
		String pageNoVal = req.getParameter("pageNo");
		
	

		int pageNo = 1;
		if(pageNoVal != null){
			pageNo = Integer.parseInt(pageNoVal);
		}
		ReviewPage reviewPage = listService.getReviewPage(pageNo);
		req.setAttribute("reviewPage", reviewPage);
		return getFormViewName();
	}
	
	// get ȣ��
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}
	
	// post ȣ��
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	 
	
}
