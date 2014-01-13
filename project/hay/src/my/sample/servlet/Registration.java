package my.sample.servlet;

import static my.sample.constant.Params.EVENT;
import static my.sample.constant.Params.LOGIN;
import static my.sample.constant.Params.PASSWORD;
import static my.sample.constant.Params.REGISTRATION;
import static my.sample.constant.Params.REPASSWORD;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.sample.constant.Attributs;
import my.sample.constant.Links;
import my.sample.dao.UserDao;
import my.sample.dao.mysql.UserDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.Role;
import my.sample.entity.User;

public class Registration extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		userDao = new UserDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		User theUser = (User) request.getSession(true).getAttribute(Attributs.USER);
		if (theUser != null) {
			request.getRequestDispatcher(Links.REGISTRATION_SUCCESS_JSP).forward(request, response);
			return;
		}
		if (REGISTRATION.equals(request.getParameter(EVENT))) {
			String theLogin = request.getParameter(LOGIN);
			String thePassword = request.getParameter(PASSWORD);
			String theRePassword = request.getParameter(REPASSWORD);

			if (theLogin == null || theLogin.trim().length() == 0) {
				request.setAttribute(Attributs.ERROR, "alert.loginIsEmpty");
				request.getRequestDispatcher(Links.REGISTRATION_JSP).forward(request, response);
				return;
			}
			if (thePassword == null || thePassword.trim().length() == 0) {
				request.setAttribute(Attributs.ERROR, "alert.passwordIsEmpty");
				request.getRequestDispatcher(Links.REGISTRATION_JSP).forward(request, response);
				return;
			}
			if (!thePassword.equals(theRePassword)) {
				request.setAttribute(Attributs.ERROR, "alert.passwordIsntRepassword");
				request.getRequestDispatcher(Links.REGISTRATION_JSP).forward(request, response);
				return;
			}
			if (userDao.isExists(theLogin)) {
				request.setAttribute(Attributs.ERROR, "alert.loginIsOccupied");
				request.getRequestDispatcher(Links.REGISTRATION_JSP).forward(request, response);
				return;
			}
			User user = new User();
			user.setName(theLogin);
			user.setPassword(thePassword);
			user.setRole(Role.USER);
			userDao.saveUser(user);

			request.getSession(true).setAttribute(Attributs.USER, user);
			request.getRequestDispatcher(Links.REGISTRATION_SUCCESS_JSP).forward(request, response);
			return;
		}

		request.getRequestDispatcher(Links.REGISTRATION_JSP).forward(request, response);
	}
}
