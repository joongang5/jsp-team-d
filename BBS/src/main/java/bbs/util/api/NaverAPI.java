package bbs.util.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bbs.movie.service.NaverMovie;

public class NaverAPI {
	private String searchMovieUrl;
	private String clientId;
	private String clientSecret;

	public NaverAPI(String searchMovieUrl, String clientId, String clientSecret) {
		this.searchMovieUrl = searchMovieUrl;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public ArrayList<NaverMovie> requestMovieList(String query) {
		String response = requestSearchMovie(query);
		if (response == null)
			return null;
		
		return parseSearchMovieJson(response);
	}

	private String generateSearchMovieUrl(String query) throws UnsupportedEncodingException {
		query = URLEncoder.encode(query, "UTF-8");
		String params = String.format("query=%s", query);

		return String.format("%s?%s", searchMovieUrl, params);
	}

	private String requestSearchMovie(String query) {
		try {
			String reqUrl = generateSearchMovieUrl(query);
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
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
			System.out.println(response.toString());
			return response.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<NaverMovie> parseSearchMovieJson(String json) {
		ArrayList<NaverMovie> list = new ArrayList<NaverMovie>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONArray items = (JSONArray) rootObj.get("items");
			Iterator<JSONObject> iter = items.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();
				
				NaverMovie movie = new NaverMovie(
						(String) obj.get("title"),
						(String) obj.get("link"),
						(String) obj.get("image"),
						(String) obj.get("pubdate"),
						(String) obj.get("userRating"));
				
				list.add(movie);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
