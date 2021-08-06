package bbs.message.service;

import java.sql.Connection;
import java.sql.SQLException;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.message.dao.MessageDao;
import bbs.message.model.Message;


//싱글톤 패턴을 구현한다.
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
			//insert메서드를 이용해서 메시지를 테이블에 추가한다.
			
		} catch (SQLException e) {
			throw new ServiceException("메시지 등록 실패:" + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
