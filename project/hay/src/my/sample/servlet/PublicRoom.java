package my.sample.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.sample.constant.Attributs;
import my.sample.constant.Links;
import my.sample.dao.NoteDao;
import my.sample.dao.mysql.NoteDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.Note;

/**
 * Servlet implementation class PublicRoom
 */
public class PublicRoom extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private NoteDao noteDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		noteDao = new NoteDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Note> noteList = noteDao.getNotesOrderByBirthday(20);
		request.setAttribute("notes", noteList);
		
		request.getRequestDispatcher(Links.PUBLIC_ROOM_JSP).forward(request, response);
	}
}
