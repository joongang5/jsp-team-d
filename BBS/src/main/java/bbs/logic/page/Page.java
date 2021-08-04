package bbs.logic.page;

import java.util.List;

public class Page<T> {

	private int total;
	private int currentPage;
	private List<T> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	private int size;

	public Page(int total, int currentPage, int size, List<T> content) {
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

	public int getTotal() {
		return total;
	}
	
	public boolean hasContent() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public List<T> getContent() {
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
}
