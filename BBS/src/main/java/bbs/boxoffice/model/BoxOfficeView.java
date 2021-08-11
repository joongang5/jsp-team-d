package bbs.boxoffice.model;

public class BoxOfficeView {

	private String movieNm;
	private int rank;
	private int audiAcc;
	private String image;

	public BoxOfficeView(String movieNm, int rank, int audiAcc, String image) {
		this.movieNm = movieNm;
		this.rank = rank;
		this.audiAcc = audiAcc;
		this.image = image;
	}

	public String getMovieNm() {
		return movieNm;
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
