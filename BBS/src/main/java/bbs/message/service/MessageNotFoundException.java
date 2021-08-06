package bbs.message.service;

public class MessageNotFoundException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public MessageNotFoundException(String message) {
		super(message);
	}
}
