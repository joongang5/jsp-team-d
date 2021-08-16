package bbs.movie.model;

public class MovieView extends Movie {

	private String image;
	
	public MovieView(String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm,
			String prdtStatNm, String nationAlt, String genreAlt,
			String directors, String companyCd, String image) {
		super(movieCd, movieNm, movieNmEn, prdtYear, openDt, typeNm, prdtStatNm, nationAlt, genreAlt, directors, companyCd);
		
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
}
