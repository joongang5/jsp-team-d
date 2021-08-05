package bbs.member.command;






import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.auth.model.User;

import bbs.member.model.Member;

import bbs.member.service.MemberNotFoundException;


import bbs.member.service.ReadMyPageService;
import bbs.mvc.command.CommandHandler;


public class MyPageHandler extends CommandHandler {

	private ReadMyPageService readService = new ReadMyPageService();


	@Override
	protected String getFormViewName() {
		return "/WEB-INF/view/myPage.jsp";
	}

	// 마이페이지에 진입했을 때 보여줄 데이터에 대한 처리를 합니다.
	// ReadArticleHandler의 process(HttpServletRequest req, HttpServletResponse res)
	// 함수 참조
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

	// 정보 수정하기와 같은 처리를 합니다.
	// myPage.jsp에서 수정하기 form의 action을 post방식으로 처리
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		//mail server 설정
		String host = "smtp.naver.com";
		String admin = "elites3";
		String password = "Sanggi0214*";
		
		Member member = readService.getMember(userId);
		
		String to = req.getParameter("newEmail"); //메일 받을 주소
		
		//SMTP 서버 정보
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		
		//인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int randomIndex = random.nextInt(3);
			switch(randomIndex) {
			case 0:
				temp.append((char) ((int) (random.nextInt(26))+97));
				 break;
            case 1:
                // A-Z
                temp.append((char) ((int) (random.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((random.nextInt(10)));
                break;
			}
		}
         String key = temp.toString(); //인증키
         System.out.println(key);
         
         Session session = Session.getDefaultInstance(props, new Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(admin,password);
             }
         });
         
         //email 전송
         try {
             MimeMessage msg = new MimeMessage(session);
             msg.setFrom(new InternetAddress(admin, "My Favorits"));
             msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
             
             //메일 제목
             msg.setSubject("영화를 사랑하는 사람들의 모임, My Favorits 이메일 변경 인증 메일입니다.");
             //메일 내용
             msg.setText("인증 번호는 :"+temp);
             
             Transport.send(msg);
             System.out.println("이메일 전송");
           
             
         }catch (Exception e) {
             e.printStackTrace();
             System.out.println("이메일 전송 실패");
         }
         HttpSession keyWasSaved = req.getSession();
         keyWasSaved.setAttribute("AuthenticationKey", key);
         
         return getFormViewName();
        
			}
		

		
	
	}

