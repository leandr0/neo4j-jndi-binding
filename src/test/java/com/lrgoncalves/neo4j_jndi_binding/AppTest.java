package com.lrgoncalves.neo4j_jndi_binding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
extends TestCase
{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( AppTest.class );
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp()
	{
		assertTrue( true );
	}

	public static void main(String[] args) {


		String[] pathSegments = {"foo","..",".." ,"bar", "se/sa",".."};
		
		ArrayList<String> results = new ArrayList<String>(); 
		
		String result = "/";
		
		final String RELATIVE_PATH = "..";
		
		for (int i = 0; i < pathSegments.length; i++) {
			
			String content = pathSegments[i];
			
			String[] splitContent =  content.split("/");
			
			if (splitContent!= null && splitContent.length > 1){
				for (String contentFromSplit : splitContent) {
					results.add(contentFromSplit);
				}
			}else{
				results.add(content);
			}
			
		}
		
		for (int index = 0; index < results.size(); index ++){
				
			if(RELATIVE_PATH.equals(results.get(index))){
				results.remove(index);
				
				try{
					results.remove(index-1);
				}catch(Exception e){
					System.err.println(e.getMessage());
				}
			}
		}
		
		
		for (String string : results) {
			System.out.println(string);
		}
		//System.out.println("/" + Arrays.asList(pathSegments).stream().collect(Collectors.joining("/")));



	}

	private static boolean modFive(int number){

		if( (number % 5) == 0 )
			return true;

		return false;

	}

	private static boolean modThree(int number){

		if( (number % 3) == 0 )
			return true;

		return false;

	}
}
