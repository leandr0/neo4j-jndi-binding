/**
 * 
 */
package com.lrgoncalves.neo4j.jndi.binding;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

/**
 * @author leandro_2
 *
 */
public class Neo4jBasicAuthentication implements Serializable,ObjectFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3785736497329542905L;

	private final ConnectionAttribute attribute 	= new ConnectionAttribute();
	
	/**
	 * 
	 */
	public Neo4jBasicAuthentication() {}

	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)throws Exception {
		
		attribute.fillAttributes(environment).validateParameters();
		
		return createURLConnection(encodeAuthentication());
	}
	
	private String encodeAuthentication(){
		
		String authString = attribute.getUsernameInformation() + ":" + attribute.getKeyInformation();

		byte[] authEncBytes =   Base64.getEncoder().encode(authString.getBytes());
		
		return new String(authEncBytes);	
	}
	
	private /*URLConnection*/ Properties createURLConnection(final String authEncoded) throws IOException{
	
		//URL url = new URL(attribute.getHostInformation());
		
		//URLConnection urlConnection = url.openConnection();
		//urlConnection.setRequestProperty("Authorization", "Basic " + authEncoded);
		
		//return urlConnection;
		Properties properties = new Properties();
		
		properties.put("Authorization", "Basic " + authEncoded);
		properties.put("Host", attribute.getHostInformation());
		
		return properties;
	}
	
	public static void main(String[] args) {
		try {

			Neo4jBasicAuthentication that = new Neo4jBasicAuthentication();
			
			Hashtable<String, String> environment = new Hashtable<String, String>();
			
			environment.put("host", "http://localhost:7474/db/data/");
			environment.put("username", "neo4j");
			environment.put("password", "123456");
			
			
			
			URLConnection urlConnection = (URLConnection) that.getObjectInstance(null, null, null, environment);
			
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			String result = sb.toString();

			System.out.println("*** BEGIN ***");
			System.out.println(result);
			System.out.println("*** END ***");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
