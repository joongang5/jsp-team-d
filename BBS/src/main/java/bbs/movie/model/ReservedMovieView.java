package bbs.movie.model;

public class ReservedMovieView {
	
	private String openDt;
	private String movieNm;
	private String image;

	public ReservedMovieView(String openDt, String movieNm, String image) {
		this.openDt = openDt;
		this.movieNm = movieNm;
		this.image = image;
	}

	public String getOpenDt() {
		return openDt;
	}
	
	public String getMovieNm() {
		return movieNm;
	}

	public String getImage() {
		return image;
	}
}
