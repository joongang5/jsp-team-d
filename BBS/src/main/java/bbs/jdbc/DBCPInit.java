package bbs.jdbc;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DBCPInit() {
    }

    @Override
    public void init() throws ServletException {
    	loadJDBCDriver();
    	initConnectionPool();
    }
    
    private void loadJDBCDriver() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void initConnectionPool() {
		try {
			String url = "jdbc:mariadb://220.70.33.29:3306/whdtud";
			String id = "whdtud";
			String pw = "01234567";
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, id, pw);
			
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<PoolableConnection>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("bbs", connectionPool);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
