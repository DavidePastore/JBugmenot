/**
 * 
 */
package com.github.davidepastore.jbugmenot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.github.davidepastore.jbugmenot.Account;
import com.github.davidepastore.jbugmenot.JBugmenot;

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
		ArrayList<Account> accounts = JBugmenot.getAllAccounts("nypost.com");
		assertFalse("No accounts found for nypost.com", accounts.isEmpty());
	}
	
	/**
	 * Test to get all the attributes of an account.
	 * @throws IOException
	 */
	@Test
	public void testGetAllAccountsAndReadAttributes() throws IOException {
		ArrayList<Account> accounts = JBugmenot.getAllAccounts("nypost.com");
		Account firstAccount = accounts.get(0);
		String username = firstAccount.getUsername();
		String password = firstAccount.getPassword();
		String other = firstAccount.getOther();
		String stats = firstAccount.getStats();
		long votes = firstAccount.getVotes();
		long id = firstAccount.getId();
		long site = firstAccount.getSite();
		Date addingDate = firstAccount.getAddingDate();
		System.out.printf("Username: %s\nPassword: %s\nOther: %s\nStats: %s\nVotes: %s\nId: %s\nSite: %s\nAdding Date: %s\n",
				username, password, other, stats, votes, id, site, addingDate);
		assertNotNull("The username is null.", username);
		assertNotNull("The password is null.", password);
		assertNotNull("The other field is null.", other);
		assertNotNull("The stats field is null.", stats);
		assertNotNull("The votes field is null.", votes);
		assertNotNull("The id field is null.", id);
		assertNotNull("The site field is null.", site);
		assertNotNull("The addingDate field is null.", addingDate);
	}

	/**
	 * Test method for {@link com.github.davidepastore.jbugmenot.JBugmenot#getAllAccounts(java.lang.String, java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testGetAllAccountsStringString() throws IOException {
		ArrayList<Account> accounts = JBugmenot.getAllAccounts("nypost.com", "Explorer");
		assertFalse("No accounts found for nypost.com", accounts.isEmpty());
	}
	
	/**
	 * Test method for {@link com.github.davidepastore.jbugmenot.JBugmenot#getAllAccounts(java.lang.String)}
	 * with minimum success rate test.
	 * @throws IOException 
	 */
	@Test
	public void testGetAllAccountsAndReadAttributesWithMinimum() throws IOException {
		int minimumSuccessRate = 50;
		JBugmenot.setMinimumSuccessRate(minimumSuccessRate);
		ArrayList<Account> accounts = JBugmenot.getAllAccounts("nypost.com");
		for (Account account : accounts) {
			if(Integer.parseInt(account.getStats().replaceAll("\\D+", "")) < minimumSuccessRate){
				fail("Minimum success rate is not respected.");
			}
		}
	}
	
	/**
	 * Test method for {@link com.github.davidepastore.jbugmenot.JBugmenot#vote(com.github.davidepastore.jbugmenot, boolean)}.
	 * This method is ignored because it works only the first time it is called. I guess it is because the request comes
	 * from the same IP address.
	 * @throws IOException 
	 */
	@Test
	@Ignore
	public void testVote() throws IOException {
		String site = "corriere.it";
		ArrayList<Account> accounts = JBugmenot.getAllAccounts(site);
		Account lastAccount = accounts.get(accounts.size() - 1);
		long oldVotes = lastAccount.getVotes();
		JBugmenot.vote(lastAccount, false);
		
		accounts = JBugmenot.getAllAccounts(site);
		lastAccount = accounts.get(accounts.size() - 1);
		long newVotes = lastAccount.getVotes();
		assertEquals("Vote number is different", oldVotes + 1, newVotes);
	}

}
