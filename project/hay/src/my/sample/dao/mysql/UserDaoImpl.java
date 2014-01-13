package my.sample.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.sample.dao.UserDao;
import my.sample.db.ConnectionManager;
import my.sample.entity.Role;
import my.sample.entity.User;

public class UserDaoImpl implements UserDao {
	private ConnectionManager connectionManager;
	
	public UserDaoImpl(ConnectionManager manager) {
		connectionManager = manager;
	}

	public User getByNameAndPassword(String name, String password) throws SQLException {
		String theQuery =
			"SELECT user.id, user.name, user.password, role.name " +
			"FROM user LEFT JOIN role ON user.role_id = role.id " +
			"WHERE user.name = ? AND user.password = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, name);
		theStatement.setString(2, password);
		
		ResultSet theResultSet = theStatement.executeQuery();

		User theUser = null;
		if (theResultSet.next()) {
			theUser = new User();
			theUser.setId(theResultSet.getInt(1));
			theUser.setName(theResultSet.getString(2));
			theUser.setPassword(theResultSet.getString(3));
			theUser.setRole(Role.lookUp(theResultSet.getString(4)));
		}

		return theUser;
	}

	public User getById(Integer id) throws SQLException {
		String theQuery =
			"SELECT user.id, user.name, user.password, role.name " +
			"FROM user LEFT JOIN role ON user.role_id = role.id " +
			"WHERE user.id = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, id);
		
		ResultSet theResultSet = theStatement.executeQuery();
		User theUser = null;
		if (theResultSet.next()) {
			theUser = new User();
			theUser.setId(theResultSet.getInt(1));
			theUser.setName(theResultSet.getString(2));
			theUser.setPassword(theResultSet.getString(3));
			theUser.setRole(Role.lookUp(theResultSet.getString(4)));
		}

		return theUser;
	}

	public boolean isExists(String name) throws SQLException {
		String theQuery = 
			"SELECT (COUNT(*)>0) FROM user WHERE user.name = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, name);
		
		ResultSet theResultSet = theStatement.executeQuery();

		if (theResultSet.next()) {
			return theResultSet.getBoolean(1);
		}
		return false;
	}

	public int saveUser(User user) throws SQLException {
		String theQuery =
			"INSERT INTO user(name, password, role_id) " +
			"VALUES (?, ?, (SELECT id FROM role WHERE role.name = ? ))";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, user.getName());
		theStatement.setString(2, user.getPassword());
		theStatement.setString(3, user.getRole().getName());
		
		int count = theStatement.executeUpdate();

		ResultSet theResultSet = theStatement.getGeneratedKeys();
		if (theResultSet.next()) {
			int userId = theResultSet.getInt(1);
			user.setId(userId);
		}
		
		return count;
	}

	public int updateUser(User user) throws SQLException {
		String theQuery =
			"UPDATE user SET role_id = (SELECT id FROM role WHERE role.name=?), password = ?, name = ? " +
			"WHERE user.id = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, user.getRole().getName());
		theStatement.setString(2, user.getPassword());
		theStatement.setString(3, user.getName());
		theStatement.setInt(4, user.getId());

		return theStatement.executeUpdate();
	}

	public List<User> getUsersOrderByName() throws SQLException {
		String theQuery =
			"SELECT user.id, user.name, user.password, role.name " +
			"FROM user LEFT JOIN role ON user.role_id = role.id  ORDER BY user.name";
		Statement theStatement = connectionManager.getConnection().createStatement();
		ResultSet theResultSet = theStatement.executeQuery(theQuery);

		List<User> userList = new ArrayList<User>();
		while (theResultSet.next()) {
			User theUser = new User();
			theUser.setId(theResultSet.getInt(1));
			theUser.setName(theResultSet.getString(2));
			theUser.setPassword(theResultSet.getString(3));
			theUser.setRole(Role.lookUp(theResultSet.getString(4)));
			userList.add(theUser);
		}

		return userList;
	}

}
