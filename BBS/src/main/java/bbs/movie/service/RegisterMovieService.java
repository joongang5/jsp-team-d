package bbs.movie.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.service.DuplicateIdException;
import bbs.movie.dao.MovieDao;
import bbs.movie.model.Movie;
import bbs.util.HttpURLConnUtil;

public class RegisterMovieService {
	private MovieDao<Movie> dao = new MovieDao<Movie>(); 

	private final String SEARCH_MOVIE_LIST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
	
	public void register(String date) {
		ArrayList<Movie> list = requestMovies(date);
		if (list == null)
			return;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int count = dao.selectCountByMovieCd(conn, date);
			if (count > 0) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			for (Movie movie : list) {
				dao.insert(conn, movie);
			}
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private ArrayList<Movie> requestMovies(String date) {
		String params = String.format("key=%s&openStartDt=%s&itemPerPage=100", HttpURLConnUtil.KOBIS_KEY, date);
		
		String response = HttpURLConnUtil.request(SEARCH_MOVIE_LIST_URL, params);
		if (response == null) {
			return null;
		}
			
		return parseJson(response.toString());
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Movie> parseJson(String json) {
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
