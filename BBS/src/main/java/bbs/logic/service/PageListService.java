package bbs.logic.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.jdbc.ConnectionProvider;
import bbs.logic.dao.Dao;
import bbs.logic.page.Page;

public class PageListService<T> {

	private Dao<T> dao;
	
	private int size = 10;
	
	public PageListService(Dao<T> dao) {
		this.dao = dao;
	}
	
	public Page<T> getPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = dao.selectCount(conn);
			List<T> content = dao.select(conn, (pageNum - 1) * size, size);
			Page<T> page = new Page<T>(total, pageNum, size, content);
			
			return page;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
