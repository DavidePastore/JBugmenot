/**
 * 
 */
package com.github.davidepastore.jbugmenot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The main class of the library. It contains static methods to access to all the accounts of a website and to set other things.
 * @author <a href="https://github.com/DavidePastore">Davide Pastore</a> 
 * @author <a href="https://github.com/JBassx">Giuseppe Ciullo</a>
 *
 */
public class JBugmenot {
	
	/**
	 * Version of the library.
	 */
	private static final String VERSION = "0.1.0";
	
	
	/**
	 * Base url of the site.
	 */
	private static final String BASE_URL = "http://bugmenot.com/";
	
	/**
	 * Base url for the view.
	 */
	private static final String BASE_VIEW_URL = BASE_URL + "view/";
	
	/**
	 * Url to vote the website.
	 */
	private static final String VOTE_URL = BASE_URL + "vote.php";
	
	/**
	 * Number of milliseconds in a day.
	 */
	private static final long DAY_IN_MS = 1000 * 60 * 60 * 24;
	
	/**
	 * Default user agent.
	 */
	private static String defaultUserAgent = "Mozilla";
	
	
	/**
	 * Fields names.
	 */
	private enum FieldName {
		username, password, other, stats
	};
	
	/**
	 * The minimum success rate.
	 */
	private static int minimumSuccessRate = 0;

	/**
	 * Get the version number.
	 * @return Returns the version number.
	 */
	public static String getVersion(){
		return VERSION;
	}
	
	/**
	 * Get the base url.
	 * @return Returns the base url.
	 */
	public static String getBaseUrl(){
		return BASE_URL;
	}
	
	/**
	 * @return the defaultUserAgent
	 */
	public static String getDefaultUserAgent() {
		return defaultUserAgent;
	}

	/**
	 * @param defaultUserAgent the defaultUserAgent to set
	 */
	public static void setDefaultUserAgent(String defaultUserAgent) {
		JBugmenot.defaultUserAgent = defaultUserAgent;
	}

	
	/**
	 * Get the minimum success rate.
	 * Accounts will not be added if the success rate will be lower of this value.
	 * @return the minimumSuccessRate
	 */
	public static int getMinimumSuccessRate() {
		return minimumSuccessRate;
	}

	/**
	 * Set the minimum success rate.
	 * Accounts will not be added if the success rate will be lower of this value.
	 * @param minimumSuccessRate the minimumSuccessRate to set
	 */
	public static void setMinimumSuccessRate(int minimumSuccessRate) {
		JBugmenot.minimumSuccessRate = minimumSuccessRate;
	}

	/**
	 * Returns an ArrayList<Account> of all the accounts of a website.
	 * @param website the website.
	 * @return Returns an ArrayList<Account> of all the accounts of a website.
	 * @throws IOException
	 */
	public static ArrayList<Account> getAllAccounts(String website) throws IOException{
		return getAllAccounts(website, defaultUserAgent);
	}
	
	/**
	 * Returns an ArrayList<Account> of all the accounts of a website.
	 * @param website website the website.
	 * @param userAgent the userAgent to use.
	 * @return Returns an ArrayList<Account> of all the accounts of a website.
	 * @throws IOException
	 */
	public static ArrayList<Account> getAllAccounts(String website, String userAgent) throws IOException{
		ArrayList<Account> accounts = new ArrayList<Account>();
		Document doc = Jsoup.connect(BASE_VIEW_URL + website).userAgent(userAgent).get();
		Elements accountElements = doc.getElementsByClass("account");
		Account account;
		for(Element accountElement : accountElements)	{
			account = new Account();
			Elements kbdElements = accountElement.select("dd kbd");
			
			//Get username and password
			String username = kbdElements.get(0).text();
			String password = kbdElements.get(1).text();
			
			//Get stats
			Elements liElements = accountElement.select(".stats li");
			String stats = liElements.get(0).text().substring(0, 3);
			
			//Add the account only if reaches the minimum success rate
			if(getOnlyNumbers(stats) >= minimumSuccessRate){
				long votes = parseVotes(liElements.get(1).text());
				long site = Long.parseLong(accountElement.select("form input[name=site]").val());
				String addingDate = liElements.get(2).text();
				
				//Set the account attributes
				account.setUsername(username);
				account.setPassword(password);
				account.setStats(stats);
				account.setVotes(votes);
				account.setOther(votes + " " + addingDate);
				account.setId(Long.parseLong(accountElement.attr("data-account_id")));
				account.setSite(site);
				account.setAddingDate(parseDate(addingDate));
				accounts.add(account);
			}
			
		}
		return accounts;
	}
	

	/**
	 * Parse the votes String and create the long value.
	 * @param votes The votes String.
	 * @return Returns the votes in long format.
	 */
	private static long parseVotes(String votes){
		return getOnlyNumbers(votes);
	}
	
	
	/**
	 * Parse the date string and transform it in a {@link Date} object.
	 * @param dateString The date string.
	 * @return Returns the parsed {@link Date} object.
	 */
	private static Date parseDate(String dateString){
		long daysAgo = getOnlyNumbers(dateString);
		if(dateString.contains("years") || dateString.contains("year")){
			daysAgo = daysAgo * 365;
		} else if(dateString.contains("months") || dateString.contains("month")){
			daysAgo = daysAgo * 30;
		}
		return new Date(System.currentTimeMillis() - (daysAgo * DAY_IN_MS));
	}
	
	
	/**
	 * Get only the number part of a string.
	 * @param number The number String.
	 * @return Returns the numbers in long format.
	 */
	private static long getOnlyNumbers(String number){
		return Long.parseLong(number.replaceAll("\\D+", ""));
	}
	
	/**
	 * Vote the given account using a positive or negative vote.
	 * @param account The account to vote.
	 * @param vote The vote. True to mark this as good login, false otherwise.
	 * @throws IOException 
	 */
	public static void vote(Account account, boolean vote) throws IOException{
		String voteString = vote ? "Y" : "N";
		System.out.println("VOTE_URL: " + VOTE_URL);
		System.out.println("account: " + Long.toString(account.getId()));
		System.out.println("site: " + Long.toString(account.getSite()));
		System.out.println("voteString: " + voteString);
		Jsoup.connect(VOTE_URL)
			.data("account", Long.toString(account.getId()))
			.data("site", Long.toString(account.getSite()))
			.data("vote", voteString)
			.userAgent(defaultUserAgent)
			.method(Method.POST)
			.execute();
	}
	
}
