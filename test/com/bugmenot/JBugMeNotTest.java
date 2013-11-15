/**
 * 
 */
package com.bugmenot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JBugMeNot testing class.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class JBugMeNotTest {

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
	 * Test method for {@link com.bugmenot.JBugMeNot#getAllAccounts(java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testGetAllAccountsString() throws IOException {
		ArrayList<Account> accounts = JBugMeNot.getAllAccounts("nypost.com");
		assertFalse("No accounts found for nypost.com", accounts.isEmpty());
	}
	
	/**
	 * Test to get all the attributes of an account.
	 * @throws IOException
	 */
	@Test
	public void testGetAllAccountsAndReadAttributes() throws IOException {
		ArrayList<Account> accounts = JBugMeNot.getAllAccounts("nypost.com");
		Account firstAccount = accounts.get(0);
		String username = firstAccount.getUsername();
		String password = firstAccount.getPassword();
		String other = firstAccount.getOther();
		String stats = firstAccount.getStats();
		//System.out.printf("Username: %s\nPassword: %s\nOther: %s\nStats: %s", username, password, other, stats);
		assertNotNull("The username is null.", username);
		assertNotNull("The password is null.", password);
		assertNotNull("The other field is null.", other);
		assertNotNull("The stats field is null.", stats);
	}

	/**
	 * Test method for {@link com.bugmenot.JBugMeNot#getAllAccounts(java.lang.String, java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testGetAllAccountsStringString() throws IOException {
		ArrayList<Account> accounts = JBugMeNot.getAllAccounts("nypost.com", "Explorer");
		assertFalse("No accounts found for nypost.com", accounts.isEmpty());
	}

}
