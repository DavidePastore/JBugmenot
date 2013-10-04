/**
 * 
 */
package com.bugmenot;

/**
 * An account entity.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Account {
	
	private String username;
	private String password;
	private String other;
	private String stats;
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}
	
	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}
	
	/**
	 * @return the stats
	 */
	public String getStats() {
		return stats;
	}
	
	/**
	 * @param stats the stats to set
	 */
	public void setStats(String stats) {
		this.stats = stats;
	}

}
