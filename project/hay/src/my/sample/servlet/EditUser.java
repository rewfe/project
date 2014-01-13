package my.sample.servlet;

import static my.sample.constant.Params.EVENT;
import static my.sample.constant.Params.ID;
import static my.sample.constant.Params.LOGIN;
import static my.sample.constant.Params.PASSWORD;
import static my.sample.constant.Params.ROLE;
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
import my.sample.entity.Role;
import my.sample.entity.User;

/**
 * Servlet implementation class EditUser
 */
public class EditUser extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDao userDao;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		userDao = new UserDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Integer userId = new Integer(request.getParameter(ID));
		User user = userDao.getById(userId);
		if(UPDATE.equals(request.getParameter(EVENT))){
			String theLogin = request.getParameter(LOGIN);
			String thePassword = request.getParameter(PASSWORD);
			Role theRole = Role.lookUp(request.getParameter(ROLE));
			if(!user.getName().equals(theLogin)&& userDao.isExists(theLogin)){
				request.setAttribute(Attributs.ERROR, "alert.loginIsOccupied");
			}else{
				user.setName(theLogin);			
				user.setPassword(thePassword);
				user.setRole(theRole);
				
				userDao.updateUser(user);
				request.setAttribute(Attributs.MESSAGE, "alert.changesApplied");
			}
		}
		request.setAttribute("user", user);
		request.setAttribute("roles", Role.realValues());
		request.getRequestDispatcher(Links.EDIT_USER_JSP).forward(request, response);
	}

}
