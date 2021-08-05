package bbs.logic.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BasePagingDao<T> {

	public int selectCount(Connection conn) throws SQLException;
	public List<T> select(Connection conn, int startRow, int size) throws SQLException;
	// 조건을 설정해 특정 페이지들만 가져오고 싶은 경우에 사용합니다.
	public int selectCount(Connection conn, String condition) throws SQLException;
	public List<T> select(Connection conn, int startRow, int size, String condition) throws SQLException;
}
