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

	// ������������ �������� �� ������ �����Ϳ� ���� ó���� �մϴ�.
	// ReadArticleHandler�� process(HttpServletRequest req, HttpServletResponse res)
	// �Լ� ����
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

	// ���� �����ϱ�� ���� ó���� �մϴ�.
	// myPage.jsp���� �����ϱ� form�� action�� post������� ó��
	@Override
	protected String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getId(); 	
		
		//mail server ����
		String host = "smtp.naver.com";
		String admin = "elites3";
		String password = "Sanggi0214*";
		
		Member member = readService.getMember(userId);
		
		String to = req.getParameter("newEmail"); //���� ���� �ּ�
		
		//SMTP ���� ����
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		
		//���� ��ȣ ������
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
         String key = temp.toString(); //����Ű
         System.out.println(key);
         
         Session session = Session.getDefaultInstance(props, new Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(admin,password);
             }
         });
         
         //email ����
         try {
             MimeMessage msg = new MimeMessage(session);
             msg.setFrom(new InternetAddress(admin, "My Favorits"));
             msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
             
             //���� ����
             msg.setSubject("��ȭ�� ����ϴ� ������� ����, My Favorits �̸��� ���� ���� �����Դϴ�.");
             //���� ����
             msg.setText("���� ��ȣ�� :"+temp);
             
             Transport.send(msg);
             System.out.println("�̸��� ����");
           
             
         }catch (Exception e) {
             e.printStackTrace();
             System.out.println("�̸��� ���� ����");
         }
         HttpSession keyWasSaved = req.getSession();
         keyWasSaved.setAttribute("AuthenticationKey", key);
         
         return getFormViewName();
        
			}
		

		
	
	}

