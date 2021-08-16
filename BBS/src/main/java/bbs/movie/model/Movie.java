package bbs.movie.model;

public class Movie extends BaseMovie {

	private String movieNmEn; 	// 영화명(영문)을 출력합니다.
	private String prdtYear; 	// 제작연도를 출력합니다.
	private String openDt; 		// 개봉일을 출력합니다.
	private String typeNm; 		// 영화유형을 출력합니다.
	private String prdtStatNm; 	// 제작상태를 출력합니다.
	private String nationAlt; 	// 제작국가(전체)를 출력합니다.
	private String genreAlt; 	// 영화장르(전체)를 출력합니다.
	private String directors; 	// 자열 영화감독을 나타냅니다.
	private String companyCd; 	// 제작사 코드를 출력합니다.

	public Movie(String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm,
			String prdtStatNm, String nationAlt, String genreAlt,
			String directors, String companyCd) {
		super(movieCd, movieNm);
		
		this.movieNmEn = movieNmEn;
		this.prdtYear = prdtYear;
		this.openDt = openDt;
		this.typeNm = typeNm;
		this.prdtStatNm = prdtStatNm;
		this.nationAlt = nationAlt;
		this.genreAlt = genreAlt;
		this.directors = directors;
		this.companyCd = companyCd;
	}

	public String getMovieNmEn() {
		return movieNmEn;
	}

	public String getPrdtYear() {
		return prdtYear;
	}

	public String getOpenDt() {
		return openDt;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public String getPrdtStatNm() {
		return prdtStatNm;
	}

	public String getNationAlt() {
		return nationAlt;
	}

	public String getGenreAlt() {
		return genreAlt;
	}

	public String getDirectors() {
		return directors;
	}

	public String getCompanyCd() {
		return companyCd;
	}
}
