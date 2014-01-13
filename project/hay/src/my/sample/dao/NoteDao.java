package my.sample.dao;

import java.sql.SQLException;
import java.util.List;

import my.sample.entity.News;
import my.sample.entity.Note;

/**
 * DAO для {@link Note}.
 * 
 * 
 * 
 */
public interface NoteDao {
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<Note> getNotesOrderByBirthday() throws SQLException;

	/**
	 * 
	 * @param top
	 * 
	 * @return
	 * @throws SQLException
	 */
	List<Note> getNotesOrderByBirthday(int top) throws SQLException;

	/**
	 *
	 * 
	 * @param note
	 * @return
	 * @throws SQLException
	 */
	int saveNote(Note note) throws SQLException;

	/**
	 *
	 * @param note
	 *  
	 * @return
	 * @throws SQLException
	 */
	int updateNote(Note note) throws SQLException;
}
