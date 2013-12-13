/**
 * 
 */
package com.github.davidepastore.jbugmenot;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The main class of the library. It contains static methods to access to all the accounts of a website and to set other things.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a> 
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
	private static final String BASE_URL = "http://www.bugmenot.com/view/";
	
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
		Document doc = Jsoup.connect("http://www.bugmenot.com/view/" + website).userAgent(userAgent).get();
		Elements account_elements = doc.getElementsByClass("account");
		for(Element account_element : account_elements)	{
			Element tbody = account_element.select("table tbody").first();
			Account account=new Account();
			Elements tr_elements=tbody.children();
			for(Element tr:tr_elements){
				String th = tr.getElementsByTag("th").text();
				String td = tr.getElementsByTag("td").text();
				FieldName fieldName = FieldName.valueOf(th.toLowerCase());
				
				switch(fieldName){
					case username:
						account.setUsername(td);
						break;
					case password:
						account.setPassword(td);
						break;
					case other:
						account.setOther(td);
						break;
					case stats:
						account.setStats(td.substring(0, 3));
						break;
				}
				
				/*
				switch(th.toLowerCase()){
					case "username"	:	account.setUsername(td);break;
					case "password"	:	account.setPassword(td);break;
					case "other"	:	account.setOther(td);break;
					case "stats"	:	account.setStats(td.substring(0, 3));break;
				}
				*/
			}
			accounts.add(account);
		}
		return accounts;
	}
		

}
