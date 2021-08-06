package bbs.boxoffice.model;

public class BoxOfficeView {

	private String movieNm;
	private int rank;
	private String image;

	public BoxOfficeView(String movieNm, int rank, String image) {
		this.movieNm = movieNm;
		this.rank = rank;
		this.image = image;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public int getRank() {
		return rank;
	}

	public String getImage() {
		return image;
	}
}
