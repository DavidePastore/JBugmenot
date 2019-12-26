/**
 * 
 */
package com.github.davidepastore.jbugmenot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JBugMeNot testing class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class JBugmenotTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.github.davidepastore.jbugmenot.JBugmenot#getAllAccounts(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testGetAllAccountsString() throws IOException {
		List<Account> accounts = JBugmenot.getAccounts("nypost.com");
		assertFalse("No accounts found for nypost.com", accounts.isEmpty());
	}
	
	/**
	 * Test to get all the attributes of an account.
	 * @throws IOException
	 */
	@Test
	public void testGetAllAccountsAndReadAttributes() throws IOException {
		List<Account> accounts = JBugmenot.getAccounts("nypost.com");
		Account firstAccount = accounts.get(0);
		String username = firstAccount.getCredentials().getUsername();
		String password = firstAccount.getCredentials().getPassword();
		String other = firstAccount.getNote();
		int percentage = firstAccount.getStats().getPercentage();
		String age = firstAccount.getStats().getAge();
		long votes = firstAccount.getStats().getVotes();
		long id = firstAccount.getId();
		Site site = firstAccount.getSite();
		System.out.printf("Username: %s\nPassword: %s\nOther: %s\nPercentage: %s\nAge: %s\nVotes: %s\nId: %s\nSite: %s\n",
				username, password, other, percentage, age, votes, id, site);
		assertNotNull("The username is null.", username);
		assertNotNull("The password is null.", password);
		assertNotNull("The other field is null.", other);
		assertNotNull("The stpercentageats field is null.", percentage);
		assertNotNull("The votes field is null.", votes);
		assertNotNull("The id field is null.", id);
		assertNotNull("The site field is null.", site);
	}

	/**
	 * Test method for
	 * {@link com.github.davidepastore.jbugmenot.JBugmenot#vote(com.github.davidepastore.jbugmenot, boolean)}.
	 * This method is ignored because it works only the first time it is called. I
	 * guess it is because the request comes from the same IP address.
	 * 
	 * @throws IOException
	 */
	@Test
	@Ignore
	public void testVote() throws IOException {
		String site = "corriere.it";
		List<Account> accounts = JBugmenot.getAccounts(site);
		Account lastAccount = accounts.get(accounts.size() - 1);
		long oldVotes = lastAccount.getStats().getVotes();
		JBugmenot.vote(lastAccount, false);
		
		accounts = JBugmenot.getAccounts(site);
		lastAccount = accounts.get(accounts.size() - 1);
		long newVotes = lastAccount.getStats().getVotes();
		assertEquals("Vote number is different", oldVotes + 1, newVotes);
	}

}
