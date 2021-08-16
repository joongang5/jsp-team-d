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
		
		Properties kobisProp = loadProperty(sce, "kobisMovie");
		kobis = new KobisAPI(
				kobisProp.getProperty("boxOfficeUrl"),
				kobisProp.getProperty("movieListUrl"),
				kobisProp.getProperty("movieDetailUrl"),
				kobisProp.getProperty("key"));
		
		Properties naverProp = loadProperty(sce, "naverMovie");
		naver = new NaverAPI(
				naverProp.getProperty("searchMovieUrl"),
				naverProp.getProperty("clientId"),
				naverProp.getProperty("clientSecret"));
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
