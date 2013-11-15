/**
 * 
 */
package com.bugmenot;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a> 
 * @author <a href="https://github.com/JBassx">Giuseppe Ciullo</a>
 *
 */
public class JBugMeNot {
	
	/**
	 * Version of the library.
	 */
	private static final String VERSION = "0.1.0";
	
	
	
	/**
	 * Base url of the site.
	 */
	private static final String BASE_URL = "http://www.bugmenot.com/view/";
	
	
	
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
	
	public static ArrayList<Account> getAllAccounts(String website) throws IOException
	{
		return getAllAccounts(website,"Mozilla");
	}
	
	public static ArrayList<Account> getAllAccounts(String website,String userAgent) throws IOException{
		ArrayList<Account> accounts=null;
		Document doc = Jsoup.connect("http://www.bugmenot.com/view/"+website).userAgent(userAgent).get();
		Elements account_elements = doc.getElementsByClass("account");
		for(Element account_element : account_elements)	{
			Element tbody = account_element.select("table tbody").first();
			if(Integer.parseInt(tbody.child(3).getElementsByTag("td").text().substring(0, 2))>=50){
				Account account=new Account();
				Elements tr_elements=tbody.children();
				for(Element tr:tr_elements){
					String th = tr.getElementsByTag("th").text();
					String td = tr.getElementsByTag("td").text();
					switch(th.toLowerCase()){
						case "username"	:	account.setUsername(td);break;
						case "password"	:	account.setPassword(td);break;
						case "other"	:	account.setOther(td);break;
						case "stats"	:	account.setStats(td.substring(0, 3));break;
					}
				}
			}
			else break;
		}
		return accounts;
	}
		

}
