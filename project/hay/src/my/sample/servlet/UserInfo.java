package my.sample.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.sample.constant.Attributs;
import my.sample.constant.Links;
import static my.sample.constant.Params.*;
import my.sample.dao.UserDao;
import my.sample.dao.mysql.UserDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.User;

/**
 * Servlet implementation class UserRoom
 */
public class UserInfo extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		userDao = new UserDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Integer userId = new  Integer(request.getParameter(ID));
		if(getUser(request).getId()==userId.intValue()){
			request.getRequestDispatcher(Links.USER_ROOM_URI).forward(request, response);
			return;
		}
		if(ACCEPT.equals(request.getParameter(MESSAGE))){
			request.setAttribute(Attributs.MESSAGE, "alert.messageSent");
		}
		User theUser = userDao.getById(userId);
		request.setAttribute("user", theUser);
		request.getRequestDispatcher(Links.USER_INFO_JSP).forward(request, response);
	}
}
