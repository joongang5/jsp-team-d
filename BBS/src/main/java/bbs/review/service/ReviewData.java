package bbs.review.service;

import bbs.review.model.Review;
import bbs.review.model.ReviewContent;

/*
 *  reviewdata 클래스는 readreviewservice클래스에 구현할 getreview() 메서드의 리턴 타입으로
 *  review객체와 reviewcontent 객체를 한 객체에 담기위한 용도로 사용.
 */

public class ReviewData {

	private Review review;
	private ReviewContent content;

	public ReviewData(Review review, ReviewContent content) {
		this.review = review;
		this.content = content;
	}

	public Review getReview() {
		return review;
	}

	public String getContent() {
		return content.getContent();
	}

}
