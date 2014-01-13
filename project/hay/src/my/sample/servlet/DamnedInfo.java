package my.sample.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.sample.constant.Links;

public class DamnedInfo extends BaseServlet {
	private static final long serialVersionUID = 6900446191652563461L;

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.getRequestDispatcher(Links.DAMNED_INFO_JSP).forward(request, response);
	}
}
