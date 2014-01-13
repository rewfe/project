package my.sample.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.sample.dao.MailDao;
import my.sample.db.ConnectionManager;
import my.sample.entity.Mail;
import my.sample.entity.Role;
import my.sample.entity.User;

public class MailDaoImpl implements MailDao {
	private ConnectionManager connectionManager;

	public MailDaoImpl(ConnectionManager manager) {
		connectionManager = manager;
	}

	public List<Mail> getMailOrderByBirthday(User recipient) throws SQLException {
		String theQuery = 
			"SELECT mail.id, mail.subject, mail.birthday, mail.delivered, user.id, user.name, user.password, role.name " +
			"FROM mail LEFT JOIN user ON mail.sender_id = user.id LEFT JOIN role ON user.role_id = role.id " +
			"WHERE recipient_id = ? ORDER BY mail.birthday DESC";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, recipient.getId());

		ResultSet theResultSet = theStatement.executeQuery();
		List<Mail> newsList = new ArrayList<Mail>();
		while (theResultSet.next()) {
			Mail theMail = new Mail();
			theMail.setId(theResultSet.getInt(1));
			theMail.setSubject(theResultSet.getString(2));
			theMail.setBirthday(theResultSet.getDate(3));
			theMail.setDelivered(theResultSet.getBoolean(4));

			User sender = new User();
			sender.setId(theResultSet.getInt(5));
			sender.setName(theResultSet.getString(6));
			sender.setPassword(theResultSet.getString(7));
			sender.setRole(Role.lookUp(theResultSet.getString(8)));

			theMail.setSender(sender);
			theMail.setRecipient(recipient);
			newsList.add(theMail);
		}

		return newsList;
	}

	public int getUnreadMailCount(int userId) throws SQLException {
		String theQuery = 
			"SELECT COUNT(*) AS count FROM mail " +
			"WHERE recipient_id=? AND NOT delivered=1";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, userId);
		ResultSet theResultSet = theStatement.executeQuery();
		
		theResultSet.next();
		return theResultSet.getInt(1);
	}

	public int setAsDelivered(User recepient, int mailId) throws SQLException {
		String theQuery = 
			"UPDATE mail SET mail.delivered = 1 " +
			"WHERE mail.id = ? AND mail.recipient_id = ?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, mailId);
		theStatement.setInt(2, recepient.getId());
		
		return theStatement.executeUpdate();
	}

	public Mail getByRecipientAndId(User recipient, int mailId) throws SQLException {
		String theQuery = 
			"SELECT mail.id, mail.subject, mail.message, mail.birthday, mail.delivered, user.id, user.name, user.password, role.name " +
			"FROM mail LEFT JOIN user ON mail.sender_id = user.id LEFT JOIN role ON user.role_id = role.id " +
			"WHERE mail.recipient_id = ? AND mail.id=?";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, recipient.getId());
		theStatement.setInt(2, mailId);
		
		ResultSet theResultSet = theStatement.executeQuery();
		Mail theMail = null;
		if (theResultSet.next()) {
			theMail = new Mail();
			theMail.setId(theResultSet.getInt(1));
			theMail.setSubject(theResultSet.getString(2));
			theMail.setMessage(theResultSet.getString(3));
			theMail.setBirthday(theResultSet.getDate(4));
			theMail.setDelivered(theResultSet.getBoolean(5));

			User sender = new User();
			sender.setId(theResultSet.getInt(6));
			sender.setName(theResultSet.getString(7));
			sender.setPassword(theResultSet.getString(8));
			sender.setRole(Role.lookUp(theResultSet.getString(9)));

			theMail.setSender(sender);
			theMail.setRecipient(recipient);
		}
		return theMail;
	}

	public int setAsDelivered(User recipient, String[] mailIds) throws SQLException {
		StringBuilder theQuery = new StringBuilder();
		theQuery.append("UPDATE mail SET mail.delivered = 1 WHERE mail.id IN (");
		for (int i = 0; i < mailIds.length - 1; i++) {
			theQuery.append(mailIds[i]).append(", ");
		}
		theQuery.append(mailIds[mailIds.length - 1]);
		theQuery.append(") AND mail.recipient_id = ?");

		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery.toString());
		theStatement.setInt(1, recipient.getId());

		return theStatement.executeUpdate();
	}

	public int saveMail(Mail mail) throws SQLException {
		String theQuery = 
			"INSERT INTO mail (sender_id, recipient_id, birthday, subject, message, delivered) " +
			"VALUES(?, ?, NOW(), ?, ?, 0)";
		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery);
		theStatement.setInt(1, mail.getSender().getId());
		theStatement.setInt(2, mail.getRecipient().getId());
		theStatement.setString(3, mail.getSubject());
		theStatement.setString(4, mail.getMessage());
	
		int count = theStatement.executeUpdate();
	
		ResultSet theResultSet = theStatement.getGeneratedKeys();
		if (theResultSet.next()) {
			int id = theResultSet.getInt(1);
			mail.setId(id);
		}
		return count;
	}

	public int deleteMails(User recipient, String[] mailIds) throws SQLException {
		StringBuilder theQuery = new StringBuilder();
		theQuery.append("DELETE FROM mail WHERE mail.id IN (");
		for (int i = 0; i < mailIds.length - 1; i++) {
			theQuery.append(mailIds[i]).append(", ");
		}
		theQuery.append(mailIds[mailIds.length - 1]);
		theQuery.append(") AND mail.recipient_id = ?");

		PreparedStatement theStatement = connectionManager.getConnection().prepareStatement(theQuery.toString());
		theStatement.setInt(1, recipient.getId());

		return theStatement.executeUpdate();
	}
}
