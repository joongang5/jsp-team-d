package bbs.message.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.message.dao.MessageDao;
import bbs.message.model.Message;
import dev.jdbc.ConnectionProvider;
import dev.jdbc.JdbcUtil;


//�̱��� ������ �����Ѵ�.
public class WriteMessageService {
	private static WriteMessageService instance = new WriteMessageService();
	
	public static WriteMessageService getInstance() {
		return instance;
	}
	
	private WriteMessageService() {
	}
	
	public void write(Message message) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			MessageDao messageDao = MessageDao.getInstance();
			messageDao.insert(conn, message);
			//insert�޼��带 �̿��ؼ� �޽����� ���̺� �߰��Ѵ�.
			
		} catch (SQLException e) {
			throw new ServiceException("�޽��� ��� ����:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
