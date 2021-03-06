package bbs.logic.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bbs.logic.dao.BasePagingDao;
import bbs.logic.page.Page;
import dev.jdbc.ConnectionProvider;

public class PageListService<T> {

	private BasePagingDao<T> dao;
	
	private int size = 10;
	
	public PageListService(BasePagingDao<T> dao) {
		this.dao = dao;
	}
	
	public Page<T> getPage(int pageNum) throws RuntimeException {
		return getPage(pageNum, null);
	}

	public Page<T> getPage(int pageNum, String condition) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = dao.selectCount(conn, condition);
			List<T> content = dao.select(conn, (pageNum - 1) * size, size, condition);
			Page<T> page = new Page<T>(total, pageNum, size, content);
			
			return page;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
