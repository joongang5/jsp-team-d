package bbs.article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.service.ArticleContentNotFoundException;
import bbs.article.service.ArticleData;
import bbs.article.service.ArticleNotFoundException;
import bbs.article.service.ReadArticleService;
import bbs.mvc.command.CommandHandler;

public class ReadArticleHandler extends CommandHandler {

	private ReadArticleService readService = new ReadArticleService();
	
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
		String noVal = req.getParameter("no");
		int articleNo = Integer.parseInt(noVal);
		try {
			ArticleData articleData = readService.getArticle(articleNo, true);
			req.setAttribute("articleData", articleData);
			return "/WEB-INF/view/readArticle.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
