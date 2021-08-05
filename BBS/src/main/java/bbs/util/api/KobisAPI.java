package bbs.util.api;

public class KobisAPI {
	public String boxOfficeUrl;
	public String movieListUrl;
	public String key;
	
	public String generateBoxOfficeUrl(String targetDt) {
		String params = String.format("key=%s&targetDt=%s", key, targetDt);

		return String.format("%s?%s", boxOfficeUrl, params);
	}
	
	
	public String generateMovieListUrl(String openStartDt) {
		String params = String.format("key=%s&openStartDt=%s&itemPerPage=100", key, openStartDt);

		return String.format("%s?%s", movieListUrl, params);
	}
}
