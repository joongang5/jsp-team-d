package bbs.notice.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.auth.model.User;
import bbs.mvc.command.CommandHandler;
import bbs.notice.service.ModifyNoticeService;
import bbs.notice.service.ModifyRequest;
import bbs.notice.service.NoticeData;
import bbs.notice.service.NoticeNotFoundException;
import bbs.notice.service.PermissionDeniedException;
import bbs.notice.service.ReadNoticeService;

public class ModifyNoticeHandler extends CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/noticeModifyForm.jsp";
	
	private ReadNoticeService readService = new ReadNoticeService();
	private ModifyNoticeService modifyService = new ModifyNoticeService();
		
	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/noticeModifyForm.jsp";
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
		
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			NoticeData noticeData = readService.getNotice(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if(!canModify(authUser, noticeData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, noticeData.getNotice().getTitle(), noticeData.getContent());
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch(NoticeNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	
	private boolean canModify(User authUser, NoticeData noticeData) {
		String writerId = noticeData.getNotice().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/noticeModifySuccess.jsp";
		} catch(NoticeNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;			
		} catch(PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	}

