package my.sample.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.sample.dao.NewsDao;
import my.sample.db.ConnectionManager;
import my.sample.entity.News;

public class NewsDaoImpl implements NewsDao {
	private ConnectionManager connectionManager;
	

	public NewsDaoImpl(ConnectionManager manager) {
		connectionManager = manager;
	}

	public List<News> getNewsOrderByBirthday() throws SQLException {
		String theQuery = 
			"SELECT news.id, news.headline, news.message, news.birthday " +
			"FROM news ORDER BY birthday DESC";
		Statement theStatement = connectionManager.getConnection().createStatement();

		ResultSet theResultSet = theStatement.executeQuery(theQuery.toString());

		List<News> newsList = new ArrayList<News>();
		while (theResultSet.next()) {
			News theNews = new News();
			theNews.setId(theResultSet.getInt(1));
			theNews.setHeadline(theResultSet.getString(2));
			theNews.setMessage(theResultSet.getString(3));
			theNews.setBirthday(theResultSet.getDate(4));
			newsList.add(theNews);
		}

		return newsList;
	}

	public int saveNews(News news) throws SQLException {
		String theQuery = 
			"INSERT INTO news(headline, message, birthday) " +
			"VALUES (?, ?, NOW())";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, news.getHeadline());
		theStatement.setString(2, news.getMessage());

		int count = theStatement.executeUpdate();
		
		ResultSet theResultSet = theStatement.getGeneratedKeys();
		if (theResultSet.next()) {
			int newsId = theResultSet.getInt(1);
			news.setId(newsId);
		}

		return count;
	}

	public int updateNews(News news) throws SQLException {
		String theQuery = 
			"UPDATE news SET headline = ?, message = ? " +
			"WHERE news.id = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, news.getHeadline());
		theStatement.setString(2, news.getMessage());
		theStatement.setInt(3, news.getId());
		
		return theStatement.executeUpdate();
	}

	public News getById(Integer id) throws SQLException {
		String theQuery =
			"SELECT news.id, news.headline, news.message, news.birthday " +
			"FROM news n WHERE n.id=?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, id);
		
		ResultSet theResultSet = theStatement.executeQuery();
		
		News theNews = null;
		if (theResultSet.next()) {
			theNews = new News();
			theNews.setId(theResultSet.getInt(1));
			theNews.setHeadline(theResultSet.getString(2));
			theNews.setMessage(theResultSet.getString(3));
			theNews.setBirthday(theResultSet.getDate(4));
		}
		return theNews;
	}

	public int deleteNews(Integer id) throws SQLException {
		String theQuery = "DELETE FROM news WHERE news.id = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, id);

		return theStatement.executeUpdate();
	}

}
