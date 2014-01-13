package my.sample.dao;

import java.sql.SQLException;
import java.util.List;

import my.sample.entity.User;

public interface UserDao {
	/**
	 *
	 * @param name
	 *            
	 * @param password
	 *           
	 * @throws SQLException
	 */
	User getByNameAndPassword(String name, String password) throws SQLException;

	
	User getById(Integer id) throws SQLException;

	/**
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int saveUser(User user) throws SQLException;

	/**
	 * @return
	 * @throws SQLException
	 */
	List<User> getUsersOrderByName() throws SQLException;

	/**
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int updateUser(User user) throws SQLException;

	/**
	 * 
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	boolean isExists(String name) throws SQLException;
}
