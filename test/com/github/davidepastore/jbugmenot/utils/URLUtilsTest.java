/**
 * 
 */
package com.github.davidepastore.jbugmenot.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.davidepastore.jbugmenot.utils.URLUtils;

/**
 * Test for the url utils.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class URLUtilsTest {

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
	 * Test method for {@link com.github.davidepastore.jbugmenot.utils.URLUtils#getRequest(java.lang.String)}.
	 */
	@Test
	public void testGetRequest() {
		String url = "http://httpbin.org/html";
		String content = "";
		try {
			content = URLUtils.getRequest(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("URLUtils.getRequest() problem", content.contains("Herman Melville - Moby-Dick"));
	}

}
