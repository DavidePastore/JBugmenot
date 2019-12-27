package com.github.davidepastore.jbugmenot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The main class of the library. It contains static methods to access to all accounts for a website and to set other things.
 * 
 * @author <a href="https://github.com/DavidePastore">Davide Pastore</a>
 * @author <a href="https://github.com/JBassx">Giuseppe Ciullo</a>
 * @author <a href="https://github.com/mdewilde">Marceau Dewilde</a>
 */
public class JBugmenot {

	private static final Pattern WHITESPACE = Pattern.compile("\\s+");

	/**
	 * Base url for the view.
	 */
	private static final String VIEW_URL = "https://bugmenot.com/view/";

	/**
	 * Url to vote the website.
	 */
	private static final String VOTE_URL = "https://bugmenot.com/vote.php";

	/**
	 * Default user agent.
	 */
	private static String userAgent = "Mozilla/5.0";

	/**
	 * @param website
	 *                the website to get accounts for
	 * @return {@link List} with every parsed {@link Account} for the {@code website}
	 * @throws IOException on network communication issues
	 */
	public static List<Account> getAccounts(String website) throws IOException {
		List<Account> accounts = new ArrayList<Account>();
		Document document = Jsoup.connect(VIEW_URL + website).userAgent(userAgent).get();

		Element h1 = document.select("#content").select("h1").get(0);
		Elements accountElements = document.select(".account");

		Site site = parseSite(h1, accountElements);

		for (Element element : accountElements) {
			accounts.add(parseAccount(site, element));
		}

		return accounts;
	}

	private static Site parseSite(Element h1, Elements accountElements) {
		String name = WHITESPACE.split(h1.text())[0];
		Element siteIdInput = accountElements.select("form").select("input[name=site]").first();
		if (siteIdInput != null) {
			return new Site(Long.valueOf(siteIdInput.val()), name);
		}
		return null;
	}

	private static Account parseAccount(Site site, Element element) {
		long id = parseId(element);
		Elements kbds = element.select("dl").select("dd").select("kbd");
		Credentials credentials = parseCredentials(kbds);
		String note = parseNote(kbds);
		Stats stats = parseStats(element);
		return new Account(id, credentials, note, stats, site);
	}

	private static long parseId(Element element) {
		String id = element.attr("data-account_id");
		try {
			return Long.parseLong(id);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private static Credentials parseCredentials(Elements kbds) {
		String username = kbds.get(0).text();
		String password = kbds.get(1).text();
		return new Credentials(username, password);
	}

	private static String parseNote(Elements kbds) {
		if (kbds.size() < 3) {
			return null;
		}
		return kbds.get(2).text();
	}

	private static Stats parseStats(Element element) {
		Elements lis = element.select(".stats").select("li");
		int percentage = Integer.valueOf(WHITESPACE.split(lis.get(0).text())[0].replaceAll("%", ""));
		int votes = Integer.valueOf(WHITESPACE.split(lis.get(1).text())[0]);
		String age = lis.get(0).text();
		return new Stats(percentage, votes, age);
	}

	/**
	 * Vote the given account using a positive or negative vote.
	 * 
	 * @param account
	 *                {@link Account} object to mark
	 * @param vote
	 *                {@code boolean} vote, {@code true} to mark this as a working login, {@code false} otherwise
	 * @throws IOException on network communication issues
	 */
	public static void vote(Account account, boolean vote) throws IOException {
		Jsoup.connect(VOTE_URL)
				.data("account", String.valueOf(account.getId()))
				.data("site", String.valueOf(account.getSite().getId()))
				.data("vote", vote ? "Y" : "N")
				.userAgent(userAgent)
				.method(Method.POST)
				.execute();
	}

}
