package bbs.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnUtil {

	public static final String KOBIS_KEY = "e2d60f300b52b6c426228f8f7da7d521";
	
	public static String request(String url, String params) {
		try {
			String apiUrl = String.format("%s?%s", url, params);
			URL reqUrl = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) reqUrl.openConnection();
			con.setRequestMethod("GET");
			// post request
			con.setDoOutput(true);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
