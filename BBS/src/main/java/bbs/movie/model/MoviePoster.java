package bbs.movie.model;

public class MoviePoster {
	
	private String movieCd;
	private String image;

	public MoviePoster(String movieCd, String image) {
		this.movieCd = movieCd;
		this.image = image;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public String getImage() {
		return image;
	}
}
