package bbs.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {

	public abstract int selectCount(Connection conn) throws SQLException;
	public abstract List<T> select(Connection conn, int startRow, int size) throws SQLException;
	// 특정 페이지만 필요할 경우 condition에 조건을 입력합니다.  
	public abstract int selectCount(Connection conn, String condition) throws SQLException;
	public abstract List<T> select(Connection conn, int startRow, int size, String condition) throws SQLException;
}
