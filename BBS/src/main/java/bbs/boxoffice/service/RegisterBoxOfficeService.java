package bbs.boxoffice.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bbs.boxoffice.dao.BoxOfficeDao;
import bbs.boxoffice.model.BoxOffice;
import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.member.service.DuplicateIdException;
import bbs.util.HttpURLConnUtil;

public class RegisterBoxOfficeService {

	private BoxOfficeDao<BoxOffice> dao = new BoxOfficeDao<BoxOffice>(); 

	private final String SEARCH_BOX_OFFICE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
	
	public void register(String date) {
		ArrayList<BoxOffice> list = requestBoxOffice(date);
		if (list == null)
			return;
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int count = dao.selectCountByTargetDt(conn, date);
			if (count > 0) {
				JdbcUtil.rollBack(conn);
				throw new DuplicateIdException();
			}
			for (BoxOffice boxOffice : list) {
				boxOffice.setTargetDt(date);
				dao.insert(conn, boxOffice);
			}
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private ArrayList<BoxOffice> requestBoxOffice(String date) {
		String params = String.format("key=%s&targetDt=%s", HttpURLConnUtil.KOBIS_KEY, date);
		
		String response = HttpURLConnUtil.request(SEARCH_BOX_OFFICE_URL, params);
		if (response == null) {
			return null;
		}

		return parseJson(response.toString());
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<BoxOffice> parseJson(String json) {
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
}
