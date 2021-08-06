package bbs.util.api;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bbs.boxoffice.model.BoxOffice;
import bbs.movie.model.Movie;
import bbs.util.HttpURLConnUtil;

public class KobisAPI {
	public String boxOfficeUrl;
	public String movieListUrl;
	public String key;

	public ArrayList<BoxOffice> requestBoxOffice(String targetDt) {
		String url = generateBoxOfficeUrl(targetDt);
		String response = HttpURLConnUtil.request(url);
		if (response == null) {
			return null;
		}
		return parseBoxOfficeJson(response.toString());
	}
	

	public ArrayList<Movie> requestMovieList(String openStartDt) {
		String url = generateMovieListUrl(openStartDt);
		String response = HttpURLConnUtil.request(url);
		if (response == null) {
			return null;
		}
		return parseMovieListJson(response.toString());
	}
	
	private String generateBoxOfficeUrl(String targetDt) {
		String params = String.format("key=%s&targetDt=%s", key, targetDt);

		return String.format("%s?%s", boxOfficeUrl, params);
	}
	
	private String generateMovieListUrl(String openStartDt) {
		String params = String.format("key=%s&openStartDt=%s&itemPerPage=100", key, openStartDt);

		return String.format("%s?%s", movieListUrl, params);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<BoxOffice> parseBoxOfficeJson(String json) {
		ArrayList<BoxOffice> list = new ArrayList<BoxOffice>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONObject boxOfficeResult = (JSONObject) rootObj.get("boxOfficeResult");

			//String boxofficeType = (String) boxOfficeResult.get("boxofficeType");
			//String showRange = (String) boxOfficeResult.get("showRange");
			JSONArray dailyBoxOfficeList = (JSONArray) boxOfficeResult.get("dailyBoxOfficeList");
			Iterator<JSONObject> iter = dailyBoxOfficeList.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();

				BoxOffice data = new BoxOffice(
						null,
						Integer.parseInt((String) obj.get("rnum")),
						Integer.parseInt((String) obj.get("rank")),
						Integer.parseInt((String) obj.get("rankInten")),
						(String) obj.get("rankOldAndNew"),
						(String) obj.get("movieCd"),
						(String) obj.get("movieNm"),
						(String) obj.get("openDt"),
						Long.parseLong((String) obj.get("salesAmt")),
						Float.parseFloat((String) obj.get("salesShare")),
						Integer.parseInt((String) obj.get("salesInten")),
						Float.parseFloat((String) obj.get("salesChange")),
						Long.parseLong((String) obj.get("salesAcc")),
						Integer.parseInt((String) obj.get("audiCnt")),
						Integer.parseInt((String) obj.get("audiInten")),
						Float.parseFloat((String) obj.get("audiChange")),
						Integer.parseInt((String) obj.get("audiAcc")),
						Integer.parseInt((String) obj.get("scrnCnt")),
						Integer.parseInt((String) obj.get("showCnt")));

				list.add(data);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Movie> parseMovieListJson(String json) {
		ArrayList<Movie> list = new ArrayList<Movie>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONObject movieListResult = (JSONObject) rootObj.get("movieListResult");

			JSONArray movieList = (JSONArray) movieListResult.get("movieList");
			Iterator<JSONObject> iter = movieList.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();

				Movie data = new Movie(
						(String) obj.get("movieCd"),
						(String) obj.get("movieNm"),
						(String) obj.get("movieNmEn"),
						(String) obj.get("prdtYear"),
						(String) obj.get("openDt"),
						(String) obj.get("typeNm"),
						(String) obj.get("prdtStatNm"),
						(String) obj.get("nationAlt"),
						(String) obj.get("genreAlt"),
						(String) obj.get("repNationNm"),
						(String) obj.get("repGenreNm"),
						getJsonElementFromArray((JSONArray)obj.get("directors"), "peopleNm"),
						(String) obj.get("peopleNm"),
						getJsonElementFromArray((JSONArray)obj.get("companys"), "companyNm"),
						(String) obj.get("companyCd"),
						(String) obj.get("companyNm"));
				
				list.add(data);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private String getJsonElementFromArray(JSONArray array, String elementName) {
		if (array.isEmpty())
			return null;
		
		return (String)((JSONObject)(array.get(0))).get(elementName);
	}
}
