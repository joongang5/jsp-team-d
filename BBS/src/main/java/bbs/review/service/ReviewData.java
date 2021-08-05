package bbs.review.service;

import bbs.review.model.Review;
import bbs.review.model.ReviewContent;

/*
 *  reviewdata Ŭ������ readreviewserviceŬ������ ������ getreview() �޼����� ���� Ÿ������
 *  review��ü�� reviewcontent ��ü�� �� ��ü�� ������� �뵵�� ���.
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
