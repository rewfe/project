package my.sample.servlet;

import static my.sample.constant.Params.MESSAGE;
import static my.sample.constant.Params.RECIPIENT;
import static my.sample.constant.Params.SUBJECT;
import static my.sample.constant.Params.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.sample.constant.Attributs;
import my.sample.constant.Links;
import my.sample.dao.MailDao;
import my.sample.dao.UserDao;
import my.sample.dao.mysql.MailDaoImpl;
import my.sample.dao.mysql.UserDaoImpl;
import my.sample.db.ConnectionManager;
import my.sample.entity.Mail;
import my.sample.entity.User;

public class CreateMail extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private MailDao mailDao;
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ConnectionManager manager = (ConnectionManager) config.getServletContext().getAttribute(Attributs.CONNECTION_MANAGER);
		mailDao = new MailDaoImpl(manager);
		userDao = new UserDaoImpl(manager);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Integer theRecipientId = Integer.valueOf(request.getParameter(RECIPIENT));
		String theSubject = request.getParameter(SUBJECT);
		String theMessage = request.getParameter(MESSAGE);

		User theRecipien = userDao.getById(theRecipientId);
		Mail theMail = new Mail();
		theMail.setSender(getUser(request));
		theMail.setRecipient(theRecipien);
		theMail.setSubject(theSubject);
		theMail.setMessage(theMessage);

		mailDao.saveMail(theMail);
		response.sendRedirect(request.getContextPath() + Links.USER_INFO_URI + 
				"?"+ ID +"="+ theRecipien.getId()+"&"+MESSAGE+"="+ACCEPT);
	}
}
