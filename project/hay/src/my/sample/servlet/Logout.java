package my.sample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.sample.constant.Links;

public class Logout extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).invalidate();
		response.sendRedirect(request.getContextPath()+Links.MAIN_URI);
	}
}
