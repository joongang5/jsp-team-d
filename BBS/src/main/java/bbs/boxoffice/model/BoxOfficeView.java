package bbs.boxoffice.model;

public class BoxOfficeView {

	private String movieNm;
	private String movieCd;
	private int rank;
	private int audiAcc;
	private String image;

	public BoxOfficeView(String movieNm, String movieCd, int rank, int audiAcc, String image) {
		this.movieNm = movieNm;
		this.movieCd = movieCd;
		this.rank = rank;
		this.audiAcc = audiAcc;
		this.image = image;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public String getMovieCd() {
		return movieCd;
	}
	
	public int getRank() {
		return rank;
	}

	public int getAudiAcc() {
		return audiAcc;
	}

	public String getImage() {
		return image;
	}
}
