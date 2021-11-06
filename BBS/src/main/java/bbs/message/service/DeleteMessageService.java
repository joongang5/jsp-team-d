package bbs.message.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.message.dao.MessageDao;
import bbs.message.model.Message;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;

public class DeleteMessageService {

	private static DeleteMessageService instance = new DeleteMessageService();
	
	public static DeleteMessageService getInstance() {
		return instance;
	}
	
	private DeleteMessageService() {
	}
	
	public void deleteMessage(int messageId, String password) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MessageDao messageDao = MessageDao.getInstance();
			Message message = messageDao.select(conn, messageId);
			if (message == null) {
				throw new MessageNotFoundException("�޽��� ����");
			}
			if (!message.matchPassword(password)) {
				throw new InvalidPasswordException("bad password");
			}
			messageDao.delete(conn, messageId);
			
			conn.commit();
		} catch (SQLException ex) {
			JdbcUtil.rollBack(conn);
			throw new ServiceException("���� ����:" + ex.getMessage(), ex);
		} catch (InvalidPasswordException | MessageNotFoundException ex) {
			JdbcUtil.rollBack(conn);
			throw ex;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
