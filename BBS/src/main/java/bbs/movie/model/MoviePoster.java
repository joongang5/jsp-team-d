package bbs.movie.model;

public class MoviePoster {
	
	private String movieCd;
	private String image;
	private float userRating;

	public MoviePoster(String movieCd, String image, float userRating) {
		this.movieCd = movieCd;
		this.image = image;
		this.userRating = userRating;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}
	
	public String getMovieCd() {
		return movieCd;
	}

	public String getImage() {
		return image;
	}
	
	public float getUserRating() {
		return userRating;
	}
}
