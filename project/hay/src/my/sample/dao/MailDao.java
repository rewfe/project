package my.sample.dao;

import java.sql.SQLException;
import java.util.List;

import my.sample.entity.Mail;
import my.sample.entity.Note;
import my.sample.entity.User;

/**
 * DAO для {@link Note}.
 * 
 * 
 * 
 */
public interface MailDao {
	
	/**
	 * @param recipient
	 * @param mailIds
	 * @return
	 * @throws SQLException
	 */
	int setAsDelivered(User recipient, String[]mailIds) throws SQLException;
	
	int deleteMails(User recipient, String[]mailIds) throws SQLException;
	
	Mail getByRecipientAndId(User recipient, int mailId) throws SQLException;
	
	int setAsDelivered(User recepient, int mailId) throws SQLException;
	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<Mail> getMailOrderByBirthday(User recipient) throws SQLException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	int getUnreadMailCount(int userId) throws SQLException;
	
	/**
	 * 
	 * 
	 * @param note
	 * @return
	 * @throws SQLException
	 */
	int saveMail(Mail note) throws SQLException;	
}
