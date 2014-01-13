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
import my.sample.dao.UserDao;
import my.sample.dao.mysql.UserDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.User;

public class UserList extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		userDao = new UserDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<User> theUserList = userDao.getUsersOrderByName();
		request.setAttribute("users", theUserList);
		request.getRequestDispatcher(Links.USER_LIST_JSP).forward(request, response);
	}
}
