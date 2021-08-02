package bbs.article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.service.ArticleData;
import bbs.article.service.ArticleNotFoundException;
import bbs.article.service.ModifyArticleService;
import bbs.article.service.ModifyRequest;
import bbs.article.service.ReadArticleService;
import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.util.PermissionChecker;

public class ModifyArticleHandler extends CommandHandler {

	private ReadArticleService readService = new ReadArticleService();
	private ModifyArticleService modifyService = new ModifyArticleService(); 
	
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/modifyForm.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			ArticleData articleData = readService.getArticle(no, false);
			User authUser = (User)req.getSession().getAttribute("authUser");
			if (PermissionChecker.canModify(authUser.getId(), articleData.getArticle()) == false) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, 
					articleData.getArticle().getTitle(),
					articleData.getContent().getContent());
			
			req.setAttribute("modReq", modReq);
			return getFormViewName();
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, 
				req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		modReq.validate(errors);
		if (errors.isEmpty() == false) {
			return getFormViewName();
		}
		
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
