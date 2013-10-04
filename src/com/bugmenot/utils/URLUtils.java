/**
 * 
 */
package com.bugmenot.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * URL utils.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class URLUtils {
	
	/**
	 * Get the content of an url.
	 * @param url the url to read.
	 * @return The content of an url.
	 * @throws IOException
	 */
	public static String getRequest(String url) throws IOException{
		String result = "";
		URL urlObj = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(urlObj.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null){
			result += inputLine;
		}
		in.close();
		return result;
	}

}
