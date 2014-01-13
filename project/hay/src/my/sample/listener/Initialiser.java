package my.sample.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import my.sample.constant.Attributs;
import my.sample.db.ConnectionManager;

/**
 *
 *
 *
 */
public class Initialiser implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		File config = new File(event.getServletContext().getRealPath("/WEB-INF/coonection-config.properties"));
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(config));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String driver = properties.getProperty("driver.class");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		String url = properties.getProperty("connection.url");
		String user = properties.getProperty("database.user");
		String password = properties.getProperty("database.password");
		Integer validTime = Integer.valueOf(properties.getProperty("valid.time"));
		
		ConnectionManager manager = new ConnectionManager(url, user, password);
		if (validTime != null) {
			manager.setValidTime(validTime.intValue());
		}
		
		event.getServletContext().setAttribute(Attributs.CONNECTION_MANAGER, manager);
	}

	public void contextDestroyed(ServletContextEvent arg0) {}

}
