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
import static my.sample.constant.Params.*;
import my.sample.dao.MailDao;
import my.sample.dao.mysql.MailDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.Mail;

public class MailList extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private MailDao mailDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		mailDao = new MailDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if (READ.equals(request.getParameter(EVENT))) {
			String[] mailIds = request.getParameterValues(MAIL_IDS);
			if(mailIds!=null){
				mailDao.setAsDelivered(getUser(request), mailIds);
			}
		}
		if (DELETE.equals(request.getParameter(EVENT))) {
			String[] mailIds = request.getParameterValues(MAIL_IDS);
			if(mailIds!=null){
				mailDao.deleteMails(getUser(request), mailIds);
			}
		}
		List<Mail> theMailList = mailDao.getMailOrderByBirthday(getUser(request));
		
		request.setAttribute("mails", theMailList);
		request.getRequestDispatcher(Links.MAIL_LIST_JSP).forward(request, response);
	}
}
