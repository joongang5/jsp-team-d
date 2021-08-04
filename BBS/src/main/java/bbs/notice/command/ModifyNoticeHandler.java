package bbs.notice.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.command.PermissionDeniedException;
import bbs.article.service.ArticleData;
import bbs.article.service.ArticleNotFoundException;
import bbs.article.service.ModifyRequest;
import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.util.PermissionChecker;

public class ModifyNoticeHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/modifyForm.jsp";
	}

	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
			return null;
		}
	
	
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

			return null;
		}
	}

