package my.sample.servlet;

import static my.sample.constant.Params.EVENT;
import static my.sample.constant.Params.HEADLINE;
import static my.sample.constant.Params.ID;
import static my.sample.constant.Params.MESSAGE;
import static my.sample.constant.Params.UPDATE;

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

/**
 * <pre>
 * 
 * </pre>
 * 
 * 
 *
 */
public class EditNews extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private  NewsDao newsDao;
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
    	newsDao = new NewsDaoImpl(manager);
    }
 	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Integer theNewsId = Integer.valueOf(request.getParameter(ID));
		
		if(UPDATE.equals(request.getParameter(EVENT))){			
			String theHeadline = request.getParameter(HEADLINE);
			String theMessage = request.getParameter(MESSAGE);
			
			News theNews = new News();
			theNews.setId(theNewsId);
			theNews.setHeadline(theHeadline);
			theNews.setMessage(theMessage);
			newsDao.updateNews(theNews);
			
			request.setAttribute(Attributs.MESSAGE, "alert.newsAdded");
			response.sendRedirect(request.getContextPath() + Links.MAIN_URI);
			return;
		}
		
		News theNews = newsDao.getById(theNewsId);
		
		request.setAttribute("news", theNews);		
		request.getRequestDispatcher(Links.EDIT_NEWS_JSP).forward(request, response);
	}
}
