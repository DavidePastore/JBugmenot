package com.github.davidepastore.jbugmenot;

/**
 * An account entity.
 * 
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 */
public class Account {

	private final long id;
	private final Credentials credentials;
	private final String note;
	private final Stats stats;
	private final Site site;

	public Account(long id, Credentials credentials, String note, Stats stats, Site site) {
		this.id = id;
		this.credentials = credentials == null ? new Credentials(null, null) : credentials;
		this.note = note;
		this.stats = stats == null ? new Stats(-1, -1, null) : stats;
		this.site = site == null ? new Site(-1, null) : site;
	}

	public long getId() {
		return id;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public String getNote() {
		return note;
	}

	public Stats getStats() {
		return stats;
	}

	public Site getSite() {
		return site;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", credentials=" + credentials + ", note=" + note + ", stats=" + stats + ", site=" + site + "]";
	}

}
