package my.sample.servlet;

import static my.sample.constant.Params.EVENT;
import static my.sample.constant.Params.OLD_PASSWORD;
import static my.sample.constant.Params.PASSWORD;
import static my.sample.constant.Params.REPASSWORD;
import static my.sample.constant.Params.UPDATE;

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
import my.sample.entity.User;

/**
 * Servlet implementation class UserRoom
 */
public class UserRoom extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		userDao = new UserDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if (UPDATE.equals(request.getParameter(EVENT))) {
			String theOldPasswod = request.getParameter(OLD_PASSWORD);
			String thePasswod = request.getParameter(PASSWORD);
			String theRePasswod = request.getParameter(REPASSWORD);
			User user = (User) request.getSession().getAttribute(Attributs.USER);
			if (user.getPassword().equals(theOldPasswod) && checkPass(thePasswod) && thePasswod.equals(theRePasswod)) {
				user.setPassword(thePasswod);
				userDao.updateUser(user);
				request.setAttribute(Attributs.MESSAGE, "alert.passwordChanged");
			} else {
				request.setAttribute(Attributs.ERROR, "alert.passwordDontChanged");
			}
		}
		request.getRequestDispatcher(Links.USER_ROOM_JSP).forward(request, response);
	}
	
	private boolean checkPass(String pass){
		if(pass==null){
			return false;
		}
		if(pass.trim().length()==0){
			return false;
		}
		return true;
	}
}
