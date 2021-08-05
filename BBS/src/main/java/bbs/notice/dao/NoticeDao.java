package bbs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bbs.article.model.Article;
import bbs.article.model.Writer;
import bbs.jdbc.JdbcUtil;
import bbs.notice.model.Notice;
import bbs.notice.model.NoticeContent;
import bbs.review.model.Review;

public class NoticeDao {
	public List<Notice> selectList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> list = null;
		try {
			String sql = "SELECT * FROM noticeview";
			pstmt = conn.prepareStatement(sql);

			// pstmt.setInt(1, startRow);
			// pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			list = new ArrayList<Notice>();
			if (rs != null) {
				while (rs.next()) {
					/* log_no log_ip log_date log_target log_id log_etc */
					// HashMap<String, Object> map = new HashMap<String, Object>();
					// totalcount�� ������ view�� ������ּ���.
					Notice notice = new Notice();
					notice.setNno(rs.getInt("nno"));
					notice.setId(rs.getString("id"));
					notice.setName(rs.getString("name"));
					notice.setNtitle(rs.getString("ntitle"));
					notice.setNcontent(rs.getString("ncontent"));
					notice.setNdate(rs.getString("ndate"));
					notice.setNcount(rs.getInt("ncount"));

					list.add(notice);
				}
			}
			return list;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public static Notice selectById(Connection conn, int noticeNno) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM noticeview where nno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNno);
			rs = pstmt.executeQuery();
			Notice notice = null;
			if (rs.next()) {
				notice = convertNotice(rs);
			}
			return notice;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}


	private static Notice convertNotice(ResultSet rs) throws SQLException {
		Notice notice = new Notice();
		notice.setNno(rs.getInt("nno"));
		notice.setId(rs.getString("id"));
		notice.setName(rs.getString("name"));
		notice.setNcontent(rs.getString("ncontent"));
		notice.setNtitle(rs.getString("ntitle"));
		notice.setNdate(rs.getString("ndate"));
		notice.setNcount(rs.getInt("ncount"));
		return notice;
	}

	public void increaseReadCount(Connection conn, int noticeNno) {
		// TODO Auto-generated method stub
		
	}






	
}
