package bbs.auth.controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


public class KakaocallbackAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");

		System.out.println("code :"+code);
		//Post요청, x-www-form-urlencoded
		
		String endpoint="https://kauth.kakao.com/oauth/token";
		URL url =new URL(endpoint);
		
		String bodyData="grant_type=authorization_code&";
		bodyData += "client_id=188766d70b45863a165fa74d7d8a455b&";
		bodyData += "redirect_uri=http://localhost:8000/BBS/oauth/kakao.do?";
		bodyData += "code="+code;
		
		//Stream 연결
		HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
		//http header 값 넣기
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		conn.setDoOutput(true);
		//request 하기
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
		bw.write(bodyData);
		bw.flush();
			
		BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "UTF-8")
				);
		String input="";
		StringBuilder sb=new StringBuilder();
		while((input=br.readLine())!=null){
			sb.append(input);
		}
		
		System.out.println(sb.toString());
				
		//Gson으로 파싱
		Gson gson=new Gson();
		
		OAuthToken oAuthToken=gson.fromJson(sb.toString(), OAuthToken.class);
		
		//profile 받기(Resource Server)
		String endpoint2="https://kapi.kakao.com/v2/user/me";
		URL url2 =new URL(endpoint2);
		
		HttpsURLConnection conn2=(HttpsURLConnection)url2.openConnection();
		
		//header 값 넣기
		conn2.setRequestProperty("Authorization", "Bearer "+oAuthToken.getAccess_token());
		conn2.setDoOutput(true);
		
		//request 하기
		BufferedReader br2=new BufferedReader(new InputStreamReader(conn2.getInputStream(),"UTF-8"));
		String input2="";
		StringBuilder sb2=new StringBuilder();
		while((input2=br2.readLine())!=null) {
			sb2.append(input2);
		}
		
		System.out.println("sb2.toString() : "+sb2.toString());
		
		Gson gson2=new Gson();
		KakaoProfile kakaoProfile=gson2.fromJson(sb2.toString(), KakaoProfile.class);
	
		System.out.println(kakaoProfile);
		
		UsersRepository usersRepository = UsersRepository.getInstance();
		Users principal=usersRepository.findByUsername(kakaoProfile.getId()+"_kakao");
		
		HttpSession session=request.getSession();
		
		if(principal!=null) { //기존 회원이면 로그인 진행
			System.out.println("로그인하기");
			session.setAttribute("principal", principal);
			Script.href("카카오 로그인 완료", "index.do", response);
		}else { //기존 회원이 아니면 회원 가입 후 로그인 진행
			//email값이 없으면 추가 회원정보 받으러 이동
			System.out.println("가입하기");
			if(kakaoProfile.getKakao_account().getEmail()==null || kakaoProfile.getKakao_account().getEmail().equals("")) {
				System.out.println("추가정보 기입 요망");
				request.setAttribute("kakaoProfile",kakaoProfile);
				RequestDispatcher dis = request.getRequestDispatcher("/user/oauthjoin.jsp");
				dis.forward(request, response);
			}else { //email 값이 있으면 바로 회원가입 및 로그인 진행
				System.out.println("추가정보 기입 불필요");
				String username=kakaoProfile.getId();
				username+="_kakao";
				Users user = Users.getUsers();
				usersRepository.save(user);
				session.setAttribute("principal", user);
			}
			
			Script.href("카카오 회원가입 및 로그인 완료", "index.do", response);
		}
		
		
		}
		
		
	}
		
