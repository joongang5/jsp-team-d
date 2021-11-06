package bbs.offmeet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.comment.dao.CommentViewDao;
import bbs.comment.model.CommentView;
import bbs.logic.page.Page;
import bbs.logic.service.PageListService;
import bbs.offmeet.service.OffMeetData;
import bbs.offmeet.service.OffMeetNotFoundException;
import bbs.offmeet.service.ReadOffMeetService;
import bbs.util.Util;
import dev.core.command.CommandHandler;

public class ReadOffMeetHandler extends CommandHandler {
	
		private ReadOffMeetService readService = new ReadOffMeetService();
		private PageListService<CommentView> commentListService; 
		
		public ReadOffMeetHandler() {
			CommentViewDao<CommentView> commentDao = new CommentViewDao<CommentView>("comment_offmeet_view"); 
			commentListService = new PageListService<CommentView>(commentDao);
		}
		
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
			int offmeetPageNo = Util.str2Int2(req.getParameter("offmeetPageNo"));
			if (offmeetPageNo == 0)
				offmeetPageNo = 1;
			
			try {
				OffMeetData offmeetData = readService.getOffMeet(offmeetNo, true);
//				System.out.println(offmeetData.getOffMeet());
				req.setAttribute("offmeetData", offmeetData.getOffMeet());
				
				String condition = String.format("article_no=%d", offmeetNo);
				Page<CommentView> commentPage = commentListService.getPage(offmeetPageNo, condition);
				req.setAttribute("commentPage", commentPage);
				req.setAttribute("pageName", "offmeet");
				
				return "/WEB-INF/view/readOffMeet.jsp";
			}catch (OffMeetNotFoundException e) {
				req.getServletContext().log("no offmeet", e);
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				return super.process(req, res);
				
			}
			
		}
		


}
