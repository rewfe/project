package my.sample.servlet;

import static my.sample.constant.Params.CREATE;
import static my.sample.constant.Params.EVENT;
import static my.sample.constant.Params.MESSAGE;

import java.io.IOException;
import java.sql.SQLException;

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
import my.sample.entity.User;

/**
 * Servlet implementation class Registration
 */
public class CreateNote extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private NoteDao noteDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		noteDao = new NoteDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if (CREATE.equals(request.getParameter(EVENT))) {
			String theMessage = request.getParameter(MESSAGE);
			Note theNote = new Note();
			theNote.setMessage(theMessage);
			theNote.setUser((User) request.getSession().getAttribute(Attributs.USER));
			noteDao.saveNote(theNote);
		}

		response.sendRedirect(request.getContextPath()+(Links.PUBLIC_ROOM_LAST_URI));
	}
}
