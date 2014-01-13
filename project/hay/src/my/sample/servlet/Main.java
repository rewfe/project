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
import my.sample.dao.NewsDao;
import my.sample.dao.mysql.NewsDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.News;

/**
 * Servlet implementation class Main
 */
public class Main extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private NewsDao newsDao;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
    	newsDao = new NewsDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<News> theNewsList = newsDao.getNewsOrderByBirthday();
		request.setAttribute("newsList", theNewsList);
		
		request.getRequestDispatcher(Links.MAIN_JSP).forward(request, response);
	}
}
