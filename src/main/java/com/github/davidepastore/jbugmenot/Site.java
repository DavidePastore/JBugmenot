package com.github.davidepastore.jbugmenot;

/**
 * Website name and internal BugMeNot id
 * 
 * @author <a href="https://github.com/mdewilde">Marceau Dewilde</a>
 */
public class Site {

	private final long id;

	private final String name;

	public Site(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", name=" + name + "]";
	}

}
