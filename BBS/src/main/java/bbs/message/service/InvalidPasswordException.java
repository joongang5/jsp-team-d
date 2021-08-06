package bbs.message.service;

public class InvalidPasswordException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String message) {
		super(message);
	}
}
