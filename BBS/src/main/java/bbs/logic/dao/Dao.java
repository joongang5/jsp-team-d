package bbs.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {

	public abstract int selectCount(Connection conn) throws SQLException;
	public abstract List<T> select(Connection conn, int startRow, int size) throws SQLException;
}
