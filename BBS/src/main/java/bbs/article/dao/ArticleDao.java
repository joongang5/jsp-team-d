package bbs.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bbs.article.model.Article;
import bbs.article.model.Writer;
import dev.jdbc.JdbcUtil;

public class ArticleDao {
	
	public Article insert(Connection conn, Article article) throws SQLException {
		PreparedStatement insertPstmt = null;
		PreparedStatement resultPstmt = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO article (title, regdate, moddate, readcnt, writer_id) values (?,?,?,0,?)";
			insertPstmt = conn.prepareStatement(sql);
			insertPstmt.setString(1, article.getTitle());
			insertPstmt.setTimestamp(2, toTimestamp(article.getRegDate()));
			insertPstmt.setTimestamp(3, toTimestamp(article.getModifiedDate()));
			insertPstmt.setString(4, article.getWriter().getId());
			int insertedCount = insertPstmt.executeUpdate();
			
			if (insertedCount > 0) {
				resultPstmt = conn.prepareStatement("SELECT last_insert_id() from article");
				rs = resultPstmt.executeQuery();
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					article.setNo(newNo);
					return article;
				}
			}
			return null;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(resultPstmt);
			JdbcUtil.close(insertPstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT count(*) FROM article";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Article> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM article_view ORDER BY no DESC limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<Article>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Article selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM article_view where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if (rs.next()) {
				article = convertArticle(rs);
			}
			return article;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, int no, String title) throws SQLException {
		String sql = "UPDATE article set title=?, moddate=now() WHERE no=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		String sql = "UPDATE article set readcnt = readcnt + 1 WHERE no=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			pstmt.executeQuery();
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	private Article convertArticle(ResultSet rs) throws SQLException {
		Writer writer = new Writer(
				rs.getString("writer_id"),
				rs.getString("writer_name"));
		
		return new Article(
				rs.getInt("no"),
				writer,
				rs.getString("title"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("readcnt"));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}
