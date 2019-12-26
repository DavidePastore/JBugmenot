package com.github.davidepastore.jbugmenot;

/**
 * Website credentials
 * 
 * @author <a href="https://github.com/mdewilde">Marceau Dewilde</a>
 */
public class Credentials {

	private final String username;
	private final String password;

	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Credentials [username=" + username + ", password=" + password + "]";
	}

}
