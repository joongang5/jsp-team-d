package bbs.offmeet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.mvc.command.CommandHandler;
import bbs.offmeet.model.OffMeetContent;
import bbs.offmeet.service.OffMeetData;
import bbs.offmeet.service.OffMeetNotFoundException;
import bbs.offmeet.service.ReadOffMeetService;

public class ReadOffMeetHandler extends CommandHandler {
	
		private ReadOffMeetService readService = new ReadOffMeetService();
		
		@Override
		protected String getFormViewName() {
			return "/WEB-INF/view/readOffMeet.jsp";
		}

		@Override
		protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
			return null;
		}
		
		@Override
		public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
			String noVal = req.getParameter("no"); 
			int offmeetNo = Integer.parseInt(noVal);
			try {
				OffMeetData offmeetData = readService.getOffMeet(offmeetNo, true);
				System.out.println(offmeetData.getOffMeet());
				req.setAttribute("offmeetData", offmeetData.getOffMeet());
				return "/WEB-INF/view/readOffMeet.jsp";
			}catch (OffMeetNotFoundException e) {
				req.getServletContext().log("no offmeet", e);
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				return super.process(req, res);
				
			}
			
		}


}
