package bbs.movie.service;

public class NaverMovie {

	private String title;
	private String link;
	private String image;
	private String pubdate;
	private String userRating;

	public NaverMovie(String title, String link, String image, String pubdate, String userRating) {
		this.title = title;
		this.link = link;
		this.image = image;
		this.pubdate = pubdate;
		this.userRating = userRating;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getImage() {
		return image;
	}

	public String getPubdate() {
		return pubdate;
	}

	public String getUserRating() {
		return userRating;
	}
}
