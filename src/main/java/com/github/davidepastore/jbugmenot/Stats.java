package com.github.davidepastore.jbugmenot;

/**
 * Stats on age & validity of an account
 * 
 * @author <a href="https://github.com/mdewilde">Marceau Dewilde</a>
 */
public class Stats {

	private final int percentage;
	private final int votes;
	private final String age;

	public Stats(int percentage, int votes, String age) {
		this.percentage = percentage;
		this.votes = votes;
		this.age = age;
	}

	public int getPercentage() {
		return percentage;
	}

	public int getVotes() {
		return votes;
	}

	public String getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Stats [percentage=" + percentage + ", votes=" + votes + ", age=" + age + "]";
	}

}
