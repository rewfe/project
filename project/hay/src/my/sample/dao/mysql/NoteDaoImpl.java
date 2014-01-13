package my.sample.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import my.sample.dao.NoteDao;
import my.sample.db.ConnectionManager;
import my.sample.entity.Note;
import my.sample.entity.Role;
import my.sample.entity.User;

public class NoteDaoImpl implements NoteDao {
	private ConnectionManager connectionManager;
	
	public NoteDaoImpl(ConnectionManager manager ) {
		connectionManager = manager; 
	}

	public List<Note> getNotesOrderByBirthday() throws SQLException {
		String theQuery = 
			"SELECT note.id, note.message, user.id, user.name, role.name " +
			"FROM note LEFT JOIN user ON note.user_id = user.id LEFT JOIN role ON user.role_id = role.id ORDER BY birthday";
		Statement theStatement = connectionManager.getConnection().createStatement();
		
		ResultSet theResultSet = theStatement.executeQuery(theQuery);
		List<Note> newsList = new ArrayList<Note>();
		while (theResultSet.next()) {
			Note theNote = new Note();
			theNote.setId(theResultSet.getInt(1));
			theNote.setMessage(theResultSet.getString(2));
			User theUser = null;
			int userId = theResultSet.getInt(3);
			if (userId > 0) {
				theUser = new User();
				theUser.setId(userId);
				theUser.setName(theResultSet.getString(4));
				theUser.setRole(Role.lookUp(theResultSet.getString(5)));
			}
			theNote.setUser(theUser);
			newsList.add(theNote);
		}

		return newsList;
	}

	public List<Note> getNotesOrderByBirthday(int top) throws SQLException {
		String theQuery =
			"SELECT n.id AS id, n.message AS message, user.id AS userid, user.name AS username, role.name AS userrole " +
			"FROM (SELECT * FROM note ORDER BY birthday DESC LIMIT ?) AS n LEFT JOIN user ON n.user_id = user.id LEFT JOIN role ON user.role_id = role.id ORDER BY n.birthday";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, top);
		
		ResultSet theResultSet = theStatement.executeQuery();
		List<Note> newsList = new ArrayList<Note>();
		while (theResultSet.next()) {
			Note theNote = new Note();
			theNote.setId(theResultSet.getInt(1));
			theNote.setMessage(theResultSet.getString(2));
			User theUser = null;
			int userId = theResultSet.getInt(3);
			if (userId > 0) {
				theUser = new User();
				theUser.setId(userId);
				theUser.setName(theResultSet.getString(4));
				theUser.setRole(Role.lookUp(theResultSet.getString(5)));
			}
			theNote.setUser(theUser);
			newsList.add(theNote);
		}

		return newsList;
	}

	public int saveNote(Note note) throws SQLException {
		String theQuery =
			"INSERT INTO note(message, user_id, birthday) " +
			"VALUES (?, ?, NOW())";		
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setString(1, note.getMessage());
		
		User theUser = note.getUser();
		if(theUser != null){
			theStatement.setInt(2, note.getUser().getId());
		}else{
			theStatement.setNull(2, Types.INTEGER);
		}
		
		int count = theStatement.executeUpdate();
		
		ResultSet theResultSet = theStatement.getGeneratedKeys();
		if(theResultSet.next()){
			note.setId(theResultSet.getInt(1));
		}
		
		return count;
	}

	public int updateNote(Note note) throws SQLException {
		//TODO
		throw new SQLException("This method not implimented.");
	}
}
