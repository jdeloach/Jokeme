package net.jtmcgee.project.jokeme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public abstract class Service {
	
	/**
	 * Simple method to simplify all others. Along the lines of One Ring to Rule them All.
	 * @param url URL to lookup
	 * @return a string array of the entire page
	 */
	protected static String[] getURL(String url) {
		try {
	        URL oracle = new URL(url);
	        URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;
	        ArrayList<String> list = new ArrayList<String>();
	
	        while ((inputLine = in.readLine()) != null) 
	        	list.add(inputLine);
	        in.close();
	        
	        return list.toArray(new String[list.size()]);
		} catch (MalformedURLException mue) {
			System.err.println("The url: " + url + " is malformed.");
		} catch (IOException ie) {
			System.err.println("IOExcpeption when fetching url: " + url);
		}
		
		return null;
	}
	
	/**
	 * Returns a certain number of jokes from the service.
	 * @param numJokes Amount of jokes to retrieve.
	 * @return An array of jokes
	 */
	public String[] getJokes(int numJokes) {
		String[] jokes = new String[numJokes];
		for(int i = 0; i < numJokes; i++)
			jokes[i] = getJoke();
		
		return jokes;
	}
	
	/**
	 * Returns a joke from the selected service
	 * @return returns the joke as a String
	 */
	public abstract String getJoke();
	
	/**
	 * Returns a joke from the specific category.
	 * @param category Index of category in String[] from getCategories()
	 * @return a Joke in a string
	 */
	public abstract String getJoke(int category);
	
	/**
	 * Returns categories the site provides
	 * @return String array of different categories
	 */
	public abstract String[] getCategories();
	
	
}
