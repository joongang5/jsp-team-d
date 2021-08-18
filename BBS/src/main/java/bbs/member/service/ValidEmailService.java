package bbs.member.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import bbs.member.model.Member;

public class ValidEmailService {
	
	public String validEmailService(String to){
	//mail server 설정
			String host = "smtp.gmail.com";
			String admin = "hyuna960229";
			String password = "gusdkgusdk*";
			
			
			
		
			
			//SMTP 서버 정보
					Properties props = System.getProperties();
					props.put("mail.smtp.starttls.enable","true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", 465);
					props.put("defaultEncoding", "utf-8");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.ssl.enable", "true");
					props.put("mail.smtp.ssl.trust",host);
					
					
					
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
			         String key = temp.toString();  //인증키
			         System.out.println(key);
			         
			         Session session = Session.getDefaultInstance(props, new Authenticator() {
			             protected PasswordAuthentication getPasswordAuthentication() {
			                 return new PasswordAuthentication(admin,password);
			             }
			         });
			         
			       //email 전송
			         try {
			             MimeMessage msg = new MimeMessage(session);
			             msg.setFrom(new InternetAddress(admin, "D'Movie"));
			             msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
			             
			             //메일 제목
			             msg.setSubject("영화를 사랑하는 사람들의 모임, D'Movie 이메일 변경 인증 메일입니다.");
			             //메일 내용
			             msg.setText("인증 번호는 : "+temp);
			             
			             Transport.send(msg);
			             System.out.println("이메일 전송");
			           
			             
			         }catch (AddressException e) {
			             e.printStackTrace();
			             System.out.println("주소 문제");
			         }catch (MessagingException e) {
			        	 e.printStackTrace();
			        	 System.out.println("메세지 문제");
			         } catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						System.out.println("인코딩문제");
					}
			         return key;
	}
}
