package my.sample.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.sample.constant.Attributs;
import my.sample.dao.MailDao;
import my.sample.dao.mysql.MailDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.Role;
import my.sample.entity.User;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 3183086530705524729L;

	private MailDao mailDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		mailDao = new MailDaoImpl(manager);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			beforeExecute(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			beforeExecute(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void beforeExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		User theUser = getUser(request);
		if (theUser != null && theUser.getRole()!=Role.GUEST) {
			int unreadMailCount = mailDao.getUnreadMailCount(theUser.getId());
			if (unreadMailCount > 0) {
				request.setAttribute("unreadMailCount", unreadMailCount);
			}
		}
		execute(request, response);
	}

	/**
	 *
	 * @param request
	 *            -
	 * @param response
	 *            -
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	protected abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;

	protected User getUser(HttpServletRequest request) {
		HttpSession theSession = request.getSession();
		return (User) (theSession == null ? null : theSession.getAttribute(Attributs.USER));
	}
}
