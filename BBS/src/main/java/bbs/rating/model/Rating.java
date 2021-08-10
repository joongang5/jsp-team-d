package bbs.rating.model;

public class Rating {
	
	private int ratingID;
	private String userID;
	private String movieName;
	private String directorName;
	private int movieYear;
	private String agegroupDivide;
	private String genreDivide;
	private String ratingTitle;
	private String ratingContent;
	private String totalScore;
	private String immersionScore;
	private String visualbeautyScore;
	private String messageScore;
	private int likeCount;

	public Rating(int ratingID, String userID, String movieName, String directorName, int movieYear,
			String agegroupDivide, String genreDivide, String ratingTitle, String ratingContent, String totalScore,
			String immersionScore, String visualbeautyScore, String messageScore, int likeCount) {
		super();
		this.ratingID = ratingID;
		this.userID = userID;
		this.movieName = movieName;
		this.directorName = directorName;
		this.movieYear = movieYear;
		this.agegroupDivide = agegroupDivide;
		this.genreDivide = genreDivide;
		this.ratingTitle = ratingTitle;
		this.ratingContent = ratingContent;
		this.totalScore = totalScore;
		this.immersionScore = immersionScore;
		this.visualbeautyScore = visualbeautyScore;
		this.messageScore = messageScore;
		this.likeCount = likeCount;
	}

	public int getRatingID() {
		return ratingID;
	}

	public String getUserID() {
		return userID;
	}

	public String getMovieName() {
		return movieName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public int getMovieYear() {
		return movieYear;
	}

	public String getAgegroupDivide() {
		return agegroupDivide;
	}

	public String getGenreDivide() {
		return genreDivide;
	}

	public String getRatingTitle() {
		return ratingTitle;
	}

	public String getRatingContent() {
		return ratingContent;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public String getImmersionScore() {
		return immersionScore;
	}

	public String getVisualbeautyScore() {
		return visualbeautyScore;
	}

	public String getMessageScore() {
		return messageScore;
	}

	public int getLikeCount() {
		return likeCount;
	}
}
