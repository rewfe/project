package my.sample.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 **/
public class ConnectionManager {

	private String url;
	private String user;
	private String password;
	private int validTime = 0;
	private String dbName;

	private Map<String, Connection> connectionMap = new HashMap<String, Connection>();

	/**
	 * 
	 * 
	 * 
	 */
	public Connection getConnection() {

		String key = Thread.currentThread().getName();
		Connection connection = connectionMap.get(key);

		try {
			if (connection == null || connection.isClosed() || (validTime > 0 && !connection.isValid(validTime))) {
				connection = DriverManager.getConnection(url, user, password);
				connectionMap.put(key, connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 
	 * @param url
	 *            
	 * @param user
	 *           
	 * @param password
	 *            
	 */
	public ConnectionManager(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
System.out.println(url);
		
		int startIndex = url.indexOf(":") + 1;
		int endIndex = url.indexOf(":", startIndex);
		dbName = url.substring(startIndex, endIndex);
	}

	public int getValidTime() {
		return validTime;
	}

	/**
	 * 
	 * 
	 * @param validTime
	 *           
	 */
	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}

	/**
	 * 
	 * @return 
	 */
	public String getDbName() {
		return dbName;
	}
}
