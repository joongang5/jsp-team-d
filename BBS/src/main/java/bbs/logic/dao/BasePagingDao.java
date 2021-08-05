package bbs.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BasePagingDao<T> {

	public int selectCount(Connection conn, String condition) throws SQLException;
	public List<T> select(Connection conn, int startRow, int size, String condition) throws SQLException;
}
