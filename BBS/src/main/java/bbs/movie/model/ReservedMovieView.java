package bbs.movie.model;

public class ReservedMovieView {
	
	private String openDt;
	private String movieCd;
	private String movieNm;
	private String image;

	public ReservedMovieView(String openDt, String movieCd, String movieNm, String image) {
		this.openDt = openDt;
		this.movieCd = movieCd;
		this.movieNm = movieNm;
		this.image = image;
	}

	public String getOpenDt() {
		return openDt;
	}
	
	public String getMovieCd() {
		return movieCd;
	}
	
	public String getMovieNm() {
		return movieNm;
	}

	public String getImage() {
		return image;
	}
}
