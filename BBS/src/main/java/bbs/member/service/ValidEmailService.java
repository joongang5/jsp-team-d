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
	//mail server ����
			String host = "smtp.gmail.com";
			String admin = "hyuna960229";
			String password = "gusdkgusdk*";
			
			
			
		
			
			//SMTP ���� ����
					Properties props = System.getProperties();
					props.put("mail.smtp.starttls.enable","true");
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", 465);
					props.put("defaultEncoding", "utf-8");
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.ssl.enable", "true");
					props.put("mail.smtp.ssl.trust",host);
					
					
					
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
			           
			             
			         }catch (AddressException e) {
			             e.printStackTrace();
			             System.out.println("�ּ� ����");
			         }catch (MessagingException e) {
			        	 e.printStackTrace();
			        	 System.out.println("�޼��� ����");
			         } catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						System.out.println("���ڵ�����");
					}
			         return key;
	}
}
