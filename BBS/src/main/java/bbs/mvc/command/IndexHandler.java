package bbs.mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.service.ArticlePage;
import bbs.article.service.ListArticleService;

public class IndexHandler extends CommandHandler {
	
	private ListArticleService listService = new ListArticleService();
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/index.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return processGetOrPost(req, res);
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return processGetOrPost(req, res);
	}

	private String processGetOrPost(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		ArticlePage articlePage = listService.getArticlePage(pageNo);
		req.setAttribute("articlePage", articlePage);
		return getFormViewName();
	}
}
