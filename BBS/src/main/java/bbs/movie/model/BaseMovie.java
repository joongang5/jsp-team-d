package bbs.movie.model;

public class BaseMovie {
	
	private String movieCd; 	// 영화코드를 출력합니다.
	private String movieNm; 	// 영화명(국문)을 출력합니다.
	
	public BaseMovie() {}
	
	public BaseMovie(String movieCd, String movieNm) {
		this.movieCd = movieCd;
		this.movieNm = movieNm;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public String getMovieNm() {
		return movieNm;
	}
}
