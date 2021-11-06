package bbs.util.api;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bbs.boxoffice.model.BoxOffice;
import bbs.movie.model.BaseMovie;
import bbs.movie.model.Movie;
import dev.util.HttpURLConnUtil;

public class KobisAPI {
	private String boxOfficeUrl;
	private String movieListUrl;
	private String movieDetailUrl;
	private String key;

	public KobisAPI(String boxOfficeUrl, String movieListUrl, String movieDetailUrl, String key) {
		this.boxOfficeUrl = boxOfficeUrl;
		this.movieListUrl = movieListUrl;
		this.movieDetailUrl = movieDetailUrl;
		this.key = key;
	}

	public ArrayList<BoxOffice> requestBoxOffice(String targetDt) {
		String url = generateBoxOfficeUrl(targetDt);
		String response = HttpURLConnUtil.request(url);
		if (response == null) {
			return null;
		}
		return parseBoxOfficeJson(response.toString());
	}
	
	public ArrayList<BaseMovie> requestBaseBoxOffice(String targetDt) {
		String url = generateBoxOfficeUrl(targetDt);
		String response = HttpURLConnUtil.request(url);
		if (response == null) {
			return null;
		}
		return parseBoxOfficeJsonToBaseMovie(response.toString());
	}
	
	public ArrayList<BaseMovie> requestMovieList(String openStartDt) {
		String url = generateMovieListUrl(openStartDt);
		String response = HttpURLConnUtil.request(url);
		if (response == null) {
			return null;
		}
		return parseMovieListJsonToBaseMovie(response.toString());
	}

	public Movie requestMovieDetail(String movieCd) {
		String url = generateMovieDetailUrl(movieCd);
		String response = HttpURLConnUtil.request(url);
		if (response == null) {
			return null;
		}
		return parseMovieDetailJson(response.toString());
	}
	
	private String generateBoxOfficeUrl(String targetDt) {
		String params = String.format("key=%s&targetDt=%s", key, targetDt);

		return String.format("%s?%s", boxOfficeUrl, params);
	}
	
	private String generateMovieListUrl(String openStartDt) {
		String params = String.format("key=%s&openStartDt=%s&itemPerPage=100", key, openStartDt);

		return String.format("%s?%s", movieListUrl, params);
	}

	private String generateMovieDetailUrl(String movieCd) {
		String params = String.format("key=%s&movieCd=%s", key, movieCd);

		return String.format("%s?%s", movieDetailUrl, params);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<BaseMovie> parseBoxOfficeJsonToBaseMovie(String json) {
		ArrayList<BaseMovie> list = new ArrayList<BaseMovie>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONObject boxOfficeResult = (JSONObject) rootObj.get("boxOfficeResult");

			JSONArray dailyBoxOfficeList = (JSONArray) boxOfficeResult.get("dailyBoxOfficeList");
			Iterator<JSONObject> iter = dailyBoxOfficeList.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();

				BaseMovie data = new BaseMovie(
						(String) obj.get("movieCd"),
						(String) obj.get("movieNm"));

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
	private ArrayList<BaseMovie> parseMovieListJsonToBaseMovie(String json) {
		ArrayList<BaseMovie> list = new ArrayList<BaseMovie>();
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONObject movieListResult = (JSONObject) rootObj.get("movieListResult");

			JSONArray movieList = (JSONArray) movieListResult.get("movieList");
			Iterator<JSONObject> iter = movieList.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();

				BaseMovie data = new BaseMovie(
						(String) obj.get("movieCd"),
						(String) obj.get("movieNm"));
				
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

	private Movie parseMovieDetailJson(String json) {
		Movie movie = null;
		JSONParser parser = new JSONParser();

		try {
			JSONObject rootObj = (JSONObject) parser.parse(json);
			JSONObject movieInfoResult = (JSONObject) rootObj.get("movieInfoResult");

			JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");
			
			movie = new Movie(
					(String) movieInfo.get("movieCd"),
					(String) movieInfo.get("movieNm"),
					(String) movieInfo.get("movieNmEn"),
					(String) movieInfo.get("prdtYear"),
					(String) movieInfo.get("openDt"),
					(String) movieInfo.get("typeNm"),
					(String) movieInfo.get("prdtStatNm"),
					getJsonElementFromArray((JSONArray)movieInfo.get("nations"), "nationNm"),
					getJsonElementFromArray((JSONArray)movieInfo.get("genres"), "genreNm"),
					getJsonElementFromArray((JSONArray)movieInfo.get("directors"), "peopleNm"),
					getJsonElementFromArray((JSONArray)movieInfo.get("companys"), "companyCd"));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	private String getJsonElementFromArray(JSONArray array, String elementName) {
		if (array.isEmpty())
			return null;
		
		return (String)((JSONObject)(array.get(0))).get(elementName);
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
	
	@SuppressWarnings({ "unchecked", "unused" })
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
						getJsonElementFromArray((JSONArray)obj.get("directors"), "peopleNm"),
						(String) obj.get("companyCd"));
				
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
}
