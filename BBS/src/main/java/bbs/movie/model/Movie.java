package bbs.movie.model;

public class Movie {

	private String movieCd; 	// 영화코드를 출력합니다.
	private String movieNm; 	// 영화명(국문)을 출력합니다.
	private String movieNmEn; 	// 영화명(영문)을 출력합니다.
	private String prdtYear; 	// 제작연도를 출력합니다.
	private String openDt; 		// 개봉일을 출력합니다.
	private String typeNm; 		// 영화유형을 출력합니다.
	private String prdtStatNm; 	// 제작상태를 출력합니다.
	private String nationAlt; 	// 제작국가(전체)를 출력합니다.
	private String genreAlt; 	// 영화장르(전체)를 출력합니다.
	private String repNationNm; // 대표 제작국가명을 출력합니다.
	private String repGenreNm; 	// 대표 장르명을 출력합니다.
	private String directors; 	// 자열 영화감독을 나타냅니다.
	private String peopleNm; 	// 영화감독명을 출력합니다.
	private String companys; 	// 제작사를 나타냅니다.
	private String companyCd; 	// 제작사 코드를 출력합니다.
	private String companyNm; 	// 제작사명을 출력합니다.

	public Movie(String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm,
			String prdtStatNm, String nationAlt, String genreAlt, String repNationNm, String repGenreNm,
			String directors, String peopleNm, String companys, String companyCd, String companyNm) {
		this.movieCd = movieCd;
		this.movieNm = movieNm;
		this.movieNmEn = movieNmEn;
		this.prdtYear = prdtYear;
		this.openDt = openDt;
		this.typeNm = typeNm;
		this.prdtStatNm = prdtStatNm;
		this.nationAlt = nationAlt;
		this.genreAlt = genreAlt;
		this.repNationNm = repNationNm;
		this.repGenreNm = repGenreNm;
		this.directors = directors;
		this.peopleNm = peopleNm;
		this.companys = companys;
		this.companyCd = companyCd;
		this.companyNm = companyNm;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public String getMovieNm() {
		return movieNm;
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

	public String getRepNationNm() {
		return repNationNm;
	}

	public String getRepGenreNm() {
		return repGenreNm;
	}

	public String getDirectors() {
		return directors;
	}

	public String getPeopleNm() {
		return peopleNm;
	}

	public String getCompanys() {
		return companys;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public String getCompanyNm() {
		return companyNm;
	}
}
