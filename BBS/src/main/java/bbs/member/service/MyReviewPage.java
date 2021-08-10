package bbs.member.service;

import java.util.List;

import bbs.article.model.Article;
import bbs.review.model.Review;

public class MyReviewPage { //페이징 위해 20210810 생성
	private int total;
	private int currentPage;
	private List<Review> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	private int size;
	public int getTotal() {
		return total;
	}
	
	///////////////////////////////////함수
	public MyReviewPage(int total, int currentPage, int size, List<Review> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.size = size;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0)
				startPage -= 5;
			
			endPage = startPage + 5 - 1;
			if (endPage > totalPages)
				endPage = totalPages;
		}
	}
	///////////////////////////////////
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Review> getContent() {
		return content;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean hasReview() {
		return total > 0;
	}
	
	}


