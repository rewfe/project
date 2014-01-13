package my.sample.dao;

import java.sql.SQLException;
import java.util.List;

import my.sample.entity.News;

/**
 * DAO для {@link News}.
 * 
 * 
 * 
 */
public interface NewsDao {
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	News getById(Integer id) throws SQLException;
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<News> getNewsOrderByBirthday() throws SQLException;

	/**
	 * 
	 * 
	 * @param news
	 * @return <code>true</code> 
	 * @throws SQLException
	 */
	int saveNews(News news) throws SQLException;

	/**
	 *
	 * @param news
	 *  
	 */
	int updateNews(News news)throws SQLException;
	
	int deleteNews(Integer id)throws SQLException;
}
