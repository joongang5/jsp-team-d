package bbs.movie.model;

public class MovieView extends Movie {

	private String image;
	private float userRating;
	
	public MovieView(String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm,
			String prdtStatNm, String nationAlt, String genreAlt,
			String directors, String companyCd, String image, float userRating) {
		super(movieCd, movieNm, movieNmEn, prdtYear, openDt, typeNm, prdtStatNm, nationAlt, genreAlt, directors, companyCd);
		
		this.image = image;
		this.userRating = userRating;
	}
	
	public String getImage() {
		return image;
	}
	
	public float getUserRating() {
		return userRating;
	}
}
