package bbs.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.service.ArticlePage;
import bbs.article.service.ListArticleService;
import bbs.mvc.command.CommandHandler;

public class ListArticleHandler extends CommandHandler {

	private ListArticleService listService = new ListArticleService();
	
	@Override
	protected String getFormViewName() {
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return null;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		ArticlePage articlePage = listService.getArticlePage(pageNo);
		req.setAttribute("articlePage", articlePage);
		return "/WEB-INF/view/listArticle.jsp";
	}
}
