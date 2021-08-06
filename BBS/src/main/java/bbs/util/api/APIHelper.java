package bbs.util.api;

import java.io.StringReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class APIHelper implements ServletContextListener {

	public static KobisAPI kobis;
	public static NaverAPI naver;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		kobis = new KobisAPI();
		Properties kobisProp = loadProperty(sce, "kobisMovie");
		kobis.boxOfficeUrl = kobisProp.getProperty("boxOfficeUrl");
		kobis.movieListUrl = kobisProp.getProperty("movieListUrl");
		kobis.key = kobisProp.getProperty("key");
		
		naver = new NaverAPI();
		Properties naverProp = loadProperty(sce, "naverMovie");
		naver.searchMovieUrl = naverProp.getProperty("searchMovieUrl");
		naver.clientId = naverProp.getProperty("clientId");
		naver.clientSecret = naverProp.getProperty("clientSecret");
	}

	private Properties loadProperty(ServletContextEvent sce, String paramName) {
		String values = sce.getServletContext().getInitParameter(paramName);
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(values));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
