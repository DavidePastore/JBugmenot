/**
 * 
 */
package com.github.davidepastore.jbugmenot;

import java.io.IOException;
import java.util.Date;

/**
 * An account entity.
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Account {

	private String username;
	private String password;
	private String other;
	private String stats;
	private long votes;
	private long id;
	private long site;
	private Date addingDate;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
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
	 * @param password
	 *            the password to set
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
	 * @param other
	 *            the other to set
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
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(String stats) {
		this.stats = stats;
	}

	/**
	 * @return the votes
	 */
	public long getVotes() {
		return votes;
	}

	/**
	 * @param votes
	 *            the votes to set
	 */
	public void setVotes(long votes) {
		this.votes = votes;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the site
	 */
	public long getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Vote this account using a positive or negative vote.
	 * 
	 * @param vote
	 *            The vote. True to mark this as good login, false otherwise.
	 * @throws IOException
	 */
	public void vote(boolean vote) throws IOException {
		JBugmenot.vote(this, vote);
	}

	/**
	 * @return the addingDate
	 */
	public Date getAddingDate() {
		return addingDate;
	}

	/**
	 * @param addingDate
	 *            the addingDate to set
	 */
	public void setAddingDate(Date addingDate) {
		this.addingDate = addingDate;
	}

}
