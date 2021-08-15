package bbs.member.command;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;

import bbs.member.model.Member;

import bbs.member.service.MemberNotFoundException;

import bbs.member.service.ReadMyPageService;
import bbs.member.service.ValidEmailService;

import bbs.mvc.command.CommandHandler;


public class MyPageHandler extends CommandHandler { //占쏙옙

	private ReadMyPageService readService = new ReadMyPageService();
	private ValidEmailService validService = new ValidEmailService();

	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/myPage.jsp";
	}

	// 留덉씠�럹�씠吏��뿉 吏꾩엯�뻽�쓣 �븣 蹂댁뿬以� �뜲�씠�꽣�뿉 ���븳 泥섎━瑜� �빀�땲�떎.
	// ReadArticleHandler�쓽 process(HttpServletRequest req, HttpServletResponse res)
	// �븿�닔 李몄“
	@Override
	protected String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {

	
	
		// 20210802
		
		
		//String memberId ="hyuna";
		Object obj =  req.getSession().getAttribute("authUser");
		User user = (User)obj; 
		String memberId = user.getId();
		
		try {
			Member member = readService.getMember(memberId); //
			req.setAttribute("member", member);
			return getFormViewName();
		} catch (MemberNotFoundException e) {
			req.getServletContext().log("no member", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	// �젙蹂� �닔�젙�븯湲곗� 媛숈� 泥섎━瑜� �빀�땲�떎.
	// myPage.jsp�뿉�꽌 �닔�젙�븯湲� form�쓽 action�쓣 post諛⑹떇�쑝濡� 泥섎━
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		
		//硫붿씪 �씤利� �썑 �닔�젙 援ы쁽
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		
		String to = req.getParameter("newEmail"); //硫붿씪 諛쏆쓣 二쇱냼
		//System.out.println(to); �옒 �뱾�뼱�샂
		
	 //  validService.validEmailService(to) = �씠硫붿씪 �씤利앸쾲�샇 蹂대궡�뒗 �꽌鍮꾩뒪 
		

		HttpSession keyWasSaved = req.getSession(); //�꽭�뀡�뿉 ���옣
        keyWasSaved.setAttribute("AuthenticationKey", validService.validEmailService(to)); //�씠由� 吏��젙
        keyWasSaved.setAttribute("newEmail2", to);
         
		
		         
         return  "/WEB-INF/view/sendKeySuccess.jsp";
        
			}
		

		
	
	}

