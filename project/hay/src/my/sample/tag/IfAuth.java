package my.sample.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import my.sample.constant.Attributs;
import my.sample.entity.Role;
import my.sample.entity.User;
import my.sample.security.AuthorizationMap;

public class IfAuth extends ConditionalTagSupport {

	private static final long serialVersionUID = -1756188924982950496L;


	private String path; 


	public IfAuth() {
		super();
		init();
	}

	public void release() {
		super.release();
		init();
	}

	protected boolean condition() {
		AuthorizationMap map = (AuthorizationMap) pageContext.getServletContext().getAttribute(my.sample.constant.Attributs.AUTHORIZATION_MAP);
		HttpServletRequest theRequest = (HttpServletRequest) pageContext.getRequest();
		HttpSession theSession = theRequest.getSession();
		User theUser = (User) (theSession != null ? theSession.getAttribute(Attributs.USER) : null);
		Role theRole = (theUser == null ? null : theUser.getRole());

		return map.isAuthorize(path, theRole);
	}

	private void init() {

	}

	public void setPath(String path) {
		this.path = path;
	}
}
