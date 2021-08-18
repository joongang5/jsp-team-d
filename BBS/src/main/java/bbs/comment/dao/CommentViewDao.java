package bbs.comment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.comment.model.CommentView;
import bbs.logic.dao.BasePagingDao;

public class CommentViewDao<T extends CommentView> extends BasePagingDao<T> {

	public CommentViewDao(String tableName) {
		super(tableName, null);
	}
	
	public CommentViewDao(String tableName, String orderRule) {
		super(tableName, orderRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convert(ResultSet rs) throws SQLException {
		
		CommentView commentView = new CommentView(
				rs.getInt("no"),
				rs.getInt("article_no"),
				rs.getString("content"),
				rs.getDate("reg_date"),
				rs.getString("id"),
				rs.getString("name"));
		return (T)commentView;
	}
}
