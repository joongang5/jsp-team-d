package bbs.offmeet.service;

import java.sql.Connection;

import bbs.jdbc.ConnectionProvider;
import bbs.offmeet.dao.OffMeetContentDao;
import bbs.offmeet.dao.OffMeetDao;
import bbs.offmeet.model.OffMeet;
import bbs.offmeet.model.OffMeetContent;

public class ReadOffMeetService {
	private OffMeetDao offmeetDao = new OffMeetDao() ;
	private OffMeetContentDao contentDao = new OffMeetContentDao() ;
	
	public OffMeetData getOffMeet(int offmeetNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			OffMeet offmeet = offmeetDao.selectById(conn, offmeetNum);
			if(offmeet == null) {
				throw new OffMeetNotFoundException();
			}
//			OffMeetContent content = contentDao.selectById(conn, offmeetNum);
//			if(content == null) {
//				throw new OffMeetContentNotFoundException();
//			}
			if(increaseReadCount) {
				offmeetDao.increaseReadCount(conn, offmeetNum);
			}
			return new OffMeetData(offmeet);
					
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
		
		
	
}
