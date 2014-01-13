package my.sample.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.sample.constant.Attributs;
import my.sample.constant.Links;
import static my.sample.constant.Params.*;
import my.sample.dao.UserDao;
import my.sample.dao.mysql.UserDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.User;

/**
 * Servlet implementation class Login
 */
public class Login extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		userDao = new UserDaoImpl(manager);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		User theUser = (User) (session == null ? null : session.getAttribute(Attributs.USER));
		if(theUser!=null){
			request.getRequestDispatcher(Links.LOGIN_SUCCESS_JSP).forward(request, response);
			return;
		}
		
		if(LOGIN.equals(request.getParameter(EVENT))){
			String theLogin = request.getParameter(LOGIN);
			String thePasswod = request.getParameter(PASSWORD);
			theUser = userDao.getByNameAndPassword(theLogin, thePasswod);
			if(theUser==null){
				request.setAttribute(Attributs.ERROR, "alert.dontLoged");
				request.getRequestDispatcher(Links.LOGIN_JSP).forward(request, response);
				return;
			}
			request.getSession(true).setAttribute(Attributs.USER, theUser);
			
			request.getRequestDispatcher(Links.LOGIN_SUCCESS_JSP).forward(request, response);
			return;
		}
		
		request.getRequestDispatcher(Links.LOGIN_JSP).forward(request, response);
	}
}
