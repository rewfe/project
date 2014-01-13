package my.sample.servlet;

import java.io.IOException;
import java.sql.SQLException;

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
import static my.sample.constant.Params.*;

/**
 * <pre>
 * 
 * </pre>
 * 
 * 
 */
public class CreateNews extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    private  NewsDao newsDao;
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
    	newsDao = new NewsDaoImpl(manager);
    }
 
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if(CREATE.equals(request.getParameter(EVENT))){
			String theHeadline = request.getParameter(HEADLINE);
			String theMessage = request.getParameter(MESSAGE);
			
			News theNews = new News();
			theNews.setHeadline(theHeadline);
			theNews.setMessage(theMessage);
			newsDao.saveNews(theNews);
			
			response.sendRedirect(request.getContextPath()+Links.MAIN_URI);
			return;
		}
			
		request.getRequestDispatcher(Links.CERATE_NEWS_JSP).forward(request, response);
	}
}
