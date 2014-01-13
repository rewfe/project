package my.sample.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import my.sample.constant.Attributs;
import my.sample.entity.Role;
import my.sample.entity.User;
import my.sample.security.AuthorizationMap;
import my.sample.security.XmlAuthorizationMap;

public class AuthorizationFilter implements Filter {

	private AuthorizationMap authorizationMap = null;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest theRequest = (HttpServletRequest) request;
		HttpServletResponse theResponse = (HttpServletResponse) response;

		String thePath = theRequest.getServletPath();
		Role theRole = null;
		HttpSession theSession = theRequest.getSession();
		User theUser = null;
		if (theSession != null) {
			theUser = (User) theSession.getAttribute(Attributs.USER);
			theRole = theUser == null ? null : theUser.getRole();
		}

		if (!authorizationMap.isAuthorize(thePath, theRole)) {
			
			if (theUser == null) {
		
				theResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			} else {
				
				theResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String pathname = fConfig.getServletContext().getRealPath("/WEB-INF/security-config.xml");
		try {
			authorizationMap = new XmlAuthorizationMap(new File(pathname));
			fConfig.getServletContext().setAttribute(Attributs.AUTHORIZATION_MAP, authorizationMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("init authorization failed.", e);
		}
	}

	public void destroy() {
	}

}
