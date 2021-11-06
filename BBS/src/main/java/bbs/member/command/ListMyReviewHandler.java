package bbs.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.review.dao.PageReviewDao;
import bbs.review.model.Review;
import dev.core.command.CommandHandler;

public class ListMyReviewHandler extends CommandHandler {
	
	private PageListService<Review> listService;
	
	public ListMyReviewHandler() {
		String tableName = "integrate_review_view";
		String orderRule = "review_no DESC";
		PageReviewDao<Review> dao = new PageReviewDao<Review>(tableName, orderRule);
		listService = new PageListService<Review>(dao);
	}
	
	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		User user = (User)req.getSession().getAttribute("authUser");
		String condition = String.format("writer_id='%s'", user.getId());
		Page<Review> page = listService.getPage(pageNo, condition);
		req.setAttribute("page", page);
		
		return "/WEB-INF/view/listMyReview.jsp";
	}
	
}
