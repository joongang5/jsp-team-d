package bbs.boxoffice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.boxoffice.model.BoxOfficeView;
import bbs.logic.dao.BasePagingDao;

public class BoxOfficeViewDao<T extends BoxOfficeView> extends BasePagingDao<T> {

	public BoxOfficeViewDao() {
		super("box_office_view", null);
	}
	
	public BoxOfficeViewDao(String tableName, String orderRule) {
		super(tableName, orderRule);
	}

	@SuppressWarnings("unchecked")
	protected T convert(ResultSet rs) throws SQLException {
		
		BoxOfficeView boxOfficeView = new BoxOfficeView(
				rs.getString("movie_nm"),
				rs.getString("movie_cd"),
				rs.getInt("rank"),
				rs.getInt("audi_acc"),
				rs.getString("image"));
		
		return (T)boxOfficeView;
	}
}
