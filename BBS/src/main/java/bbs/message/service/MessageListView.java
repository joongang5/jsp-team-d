package bbs.message.service;

import java.util.List;

import bbs.message.model.Message;

public class MessageListView {
	
	
	

	private List<Message> messageList;
	private int messageTotalCount;
	private int currentpageNumber;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	private int endRow;

	public MessageListView(List<Message> messageList, int messageTotalCount, int currentPageNumber, int messageCountPerPage, int startRow, int endRow) {
		this.messageList = messageList;
		this.messageTotalCount = messageTotalCount;
		this.currentpageNumber = currentPageNumber;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = startRow;
		this.endRow = endRow;
		
		calculatePageTotalCount();
	}

	private void calculatePageTotalCount() {
		if (messageTotalCount == 0) {
			pageTotalCount = 0;
			
		} else {
			pageTotalCount = messageTotalCount / messageCountPerPage;
			if (messageTotalCount % messageCountPerPage > 0) {
				pageTotalCount = getPageTotalCount() + 1;
			}
		}
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public int getMessageTotalCount() {
		return messageTotalCount;
	}

	public int getCurrentpageNumber() {
		return currentpageNumber;
	}

	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getEndRow() {
		return endRow;
	}
	

	public int getPageTotalCount() {
		return pageTotalCount;
	}
	
	public boolean isEmpty() {
		return messageTotalCount == 0;
	}
	
	
	
}
