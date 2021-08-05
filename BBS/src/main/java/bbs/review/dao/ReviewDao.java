package bbs.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bbs.jdbc.JdbcUtil;
import bbs.review.model.Review;
import bbs.review.model.Writer;

public class ReviewDao {

	public Review insert(Connection conn, Review review) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("INSERT INTO review (writer_id, writer_name, title, regdate, moddate, read_cnt) values(?,?,?,?,?,0)");

			pstmt.setString(1, review.getWriter().getId());
			pstmt.setString(2, review.getWriter().getName());
			pstmt.setString(3, review.getTitle());
			pstmt.setTimestamp(4, toTimeStamp(review.getRegDate()));
			pstmt.setTimestamp(5, toTimeStamp(review.getModifiedDate()));
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT last_insert_id() from review");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Review(newNum, review.getWriter(), review.getTitle(), review.getRegDate(),
							review.getModifiedDate(), 0);
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);

		}
	}

	private Timestamp toTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}

	// 게시글의 개수를 구하기 위한 selectcount()메서드를 구현
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) FROM review");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// selectcount()메소드는 review 테이블의 전체 레코드수를 리턴한다.
	// 지정한 범위의 게시글을 읽어오기 위한 select()메서드는 아래와 같이 구현한다.

	public List<Review> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from review order by review_no desc limit ?,?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			java.util.List<Review> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertReview(rs));
			}
			return result;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Review convertReview(ResultSet rs) throws SQLException {
		return new Review(rs.getInt("review_no"), new Writer(rs.getString("writer_id"), rs.getString("writer_name")),
				rs.getString("title"), toDate(rs.getTimestamp("regdate")), toDate(rs.getTimestamp("moddate")),
				rs.getInt("read_cnt"));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public Review selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM review WHERE review_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Review review = null;
			if (rs.next()) {
				review = convertReview(rs);
			}
			return review;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("UPDATE review set read_cnt = read_cnt + 1 " + "WHERE review_no = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}

	public int update(Connection conn, int no, String title) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update review set title = ?, moddate = now()" +
						"where review_no = ?")) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	//하단 delete 코드 추가
	
	public int delete(Connection conn, int no) throws SQLException {
		try(PreparedStatement pstmt =
				conn.prepareStatement("delete from review where review_no = ?")){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//여기까지 delete 코드
}