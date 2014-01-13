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
import my.sample.dao.MailDao;
import my.sample.dao.mysql.MailDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.Mail;

public class ShowMail extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private MailDao mailDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		mailDao = new MailDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Integer mailId = new Integer(request.getParameter(ID));
		Mail mail = mailDao.getByRecipientAndId(getUser(request), mailId);
		mailDao.setAsDelivered(getUser(request), mailId);
		request.setAttribute("mail", mail);
		
		request.getRequestDispatcher(Links.SHOW_MAIL_JSP).forward(request, response);
	}
}
