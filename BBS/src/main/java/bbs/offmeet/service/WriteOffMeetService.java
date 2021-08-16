package bbs.offmeet.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import bbs.jdbc.ConnectionProvider;
import bbs.jdbc.JdbcUtil;
import bbs.offmeet.dao.OffMeetContentDao;
import bbs.offmeet.dao.OffMeetDao;
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.model.OffMeetContent;
import bbs.offmeet.model.kakao;

public class WriteOffMeetService {
	
	private OffMeetDao offMeetDao = new OffMeetDao();
	private OffMeetContentDao contentDao = new OffMeetContentDao();
	
	
	//write() 메서드는 WriteRequest 타입의 req 파라미터를 이용해서 게시글을 등록하고, 결과로 게시글 번호를 리턴
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			//conn = ConnectionProvider.getConnection();
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			OffMeet offmeet = toOffmeet(req);
			System.out.println(req.getContent());
			OffMeet savedOffMeet = offMeetDao.insert(conn, offmeet);
			//kakao savekakao = offMeetDao.kakaoin(conn, kakao);
//			
			//null 일시 테이블에 삽입한 값이 없으므로 에러 발생
			if(savedOffMeet == null) {
				throw new RuntimeException("fail to insert offmeet");
			}
//			OffMeetContent content = new OffMeetContent(savedOffMeet.getNumber(),req.getContent());
//			OffMeetContent savedContent = contentDao.insert(conn, content);
//			if(savedContent == null) {
//				throw new RuntimeException("fail to insert offmeet_content");
//			}
			
			conn.commit();
			
			return savedOffMeet.getNumber();
		} catch (SQLException e) {
			JdbcUtil.rollBack(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollBack(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	
	
	private OffMeet toOffmeet(WriteRequest req) {
		Date now = new Date();
		String content = req.getContent() + req.getJuso() + req.getSangho() + req.getTel();
		return new OffMeet(null, content, req.getWriter(), req.getTitle(), now, now, req.getJuso(), req.getSangho(), req.getTel(), 0);
	}
	
}
