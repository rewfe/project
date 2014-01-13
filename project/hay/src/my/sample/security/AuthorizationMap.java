package my.sample.security;

import my.sample.entity.Role;

/**
 * 
 * 
 * 
 *
 */
public interface AuthorizationMap {
	/**
	 * 
	 * @param path
	 * @param role
	 * @return
	 */
	public boolean isAuthorize(String path, Role role);
}
