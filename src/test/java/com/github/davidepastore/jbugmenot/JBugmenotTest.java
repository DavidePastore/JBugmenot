/**
 * 
 */
package com.github.davidepastore.jbugmenot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
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
		//System.out.printf("Username: %s\nPassword: %s\nOther: %s\nStats: %s", username, password, other, stats);
		assertNotNull("The username is null.", username);
		assertNotNull("The password is null.", password);
		assertNotNull("The other field is null.", other);
		assertNotNull("The stats field is null.", stats);
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

}
