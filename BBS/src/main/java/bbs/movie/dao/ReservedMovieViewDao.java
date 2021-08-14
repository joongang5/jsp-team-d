package bbs.movie.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bbs.logic.dao.BasePagingDao;
import bbs.movie.model.ReservedMovieView;

public class ReservedMovieViewDao<T extends ReservedMovieView> extends BasePagingDao<T> {

	public ReservedMovieViewDao() {
		super("reserved_movie_view", null);
	}
	
	public ReservedMovieViewDao(String tableName, String orderRule) {
		super(tableName, orderRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convert(ResultSet rs) throws SQLException {

		ReservedMovieView reservedMovieView = new ReservedMovieView(
				changeOpenDtFormat(rs.getString("open_dt")),
				rs.getString("movie_nm"),
				rs.getString("image"));
		
		return (T)reservedMovieView;
	}
	
	private String changeOpenDtFormat(String openDt) {
		String result = openDt.substring(5);
		if (result.indexOf(0) == '0')
			result.substring(1);
		
		return result.replace('-', '.');
	}
}
