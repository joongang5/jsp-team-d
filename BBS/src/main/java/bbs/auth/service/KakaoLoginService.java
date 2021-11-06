package bbs.auth.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bbs.member.dao.MemberDao;
import bbs.member.model.Member;
import dev.jdbc.ConnectionProvider;

public class KakaoLoginService {

	private MemberDao memberDao = new MemberDao();

	public String login(String code) {

//		System.out.println("code : " + code);

		final String AUTH_HOST = "https://kauth.kakao.com";
		final String tokenRequestUrl = AUTH_HOST + "/oauth/token";

		String CLIENT_ID = "188766d70b45863a165fa74d7d8a455b"; // 해당 앱의 REST API KEY 정보. 개발자 웹사이트의 대쉬보드에서 확인 가능
		String REDIRECT_URI = "http://1.239.16.47:8080/BBS/login.do"; // 해당 앱의 설정된 uri. 개발자 웹사이트의 대쉬보드에서 확인 및 설정 가능

		HttpsURLConnection conn = null;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		InputStreamReader isr = null;

		try {
			final String params = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s",
					CLIENT_ID, REDIRECT_URI, code);

			final URL url = new URL(tokenRequestUrl);

			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(params);
			writer.flush();

//			final int responseCode = conn.getResponseCode();

//			System.out.println("\nSending 'POST' request to URL : " + tokenRequestUrl);
//			System.out.println("Post parameters : " + params);
//			System.out.println("Response Code : " + responseCode);

			isr = new InputStreamReader(conn.getInputStream());
			reader = new BufferedReader(isr);
			final StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

//			System.out.println(buffer.toString());

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(buffer.toString());

			String access_token = element.getAsJsonObject().get("access_token").getAsString();
			// String refresh_token =
			// element.getAsJsonObject().get("refresh_token").getAsString();

			// System.out.println("access_token : " + access_Token);
			// System.out.println("refresh_token : " + refresh_Token);

			// HashMap<String, Object> userInfo = getKakaoUserInfo(access_token);

			// System.out.println("*****KakaoLoginService login() passed");

			return access_token;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception ignore) {
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception ignore) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (Exception ignore) {
				}
			}
		}
		return null;

	}

	public HashMap<String, Object> getKakaoUserInfo(String access_token) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_token);

//			int responseCode = conn.getResponseCode();
//			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
//			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			// String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			userInfo.put("access_token", access_token);
			userInfo.put("email", email);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// String nickname = (String) userInfo.get("nickname");
		// String email = (String) userInfo.get("email");
//		System.out.println("*****KakaoLoginService getKakaoUserInfo() passed");

		return userInfo;

	}

	public Member kakaoLoginValid(String email) {

		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectByEmail(conn, email);
			if (member != null) {
//				System.out.println("*****KakaoLoginService kakaoLoginValid() true passed");
				return member;
			} else {
//				System.out.println("*****KakaoLoginService kakaoLoginValid() false passed");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
