package bbs.offmeet.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.article.command.PermissionDeniedException;
import bbs.auth.model.User;
import bbs.offmeet.service.ModifyOffMeetService;
import bbs.offmeet.service.ModifyRequest;
import bbs.offmeet.service.OffMeetData;
import bbs.offmeet.service.OffMeetNotFoundException;
import bbs.offmeet.service.ReadOffMeetService;
import dev.core.command.CommandHandler;

public class ModifyOffMeetHandler extends CommandHandler {

	@Override
	protected String getFormViewName() {
		// TODO Auto-generated method stub
		return "/WEB-INF/view/OffMeetmodifyForm.jsp";
	}	
	
	private static final String FORM_VIEW = "/WEB-INF/view/OffMeetmodifyForm.jsp";
	private ReadOffMeetService readService = new ReadOffMeetService();
	private ModifyOffMeetService modifyService = new ModifyOffMeetService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if (req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		}else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		
	}
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			OffMeetData offmeetData = readService.getOffMeet(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if(!canModify(authUser, offmeetData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			System.out.println(offmeetData.getOffMeet().getNumber());
			ModifyRequest modReq = new ModifyRequest(
					offmeetData.getOffMeet().getWriter().getId(),
					offmeetData.getOffMeet().getNumber(),
					offmeetData.getOffMeet().getTitle(),
					offmeetData.getOffMeet().getContent());
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (OffMeetNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User authUser, OffMeetData offmeetData) {
		String writerId = offmeetData.getOffMeet().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User authUser = (User)req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
				req.getParameter("title"), req.getParameter("content"));
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return"/WEB-INF/view/OffMeetmodifySuccess.jsp";
		} catch (OffMeetNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
