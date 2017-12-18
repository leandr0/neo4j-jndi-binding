package com.lrgoncalves.neo4j.jndi.binding;

import java.io.Serializable;
import java.util.Hashtable;

public class ConnectionAttribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3588363388990456679L;
	
	private static final String NEO4J_ADDRESS 	= "host";
	private static final String NEO4J_PASSWORD 	= "password";
	private static final String NEO4J_USERNAME 	= "username";


	private String host 	= "host";
	private String password	= "password";
	private String username	= "username";

	public ConnectionAttribute() {
	}

	public final ConnectionAttribute fillAttributes(final Hashtable<?, ?> environment){

		host 		= (String) environment.get(NEO4J_ADDRESS);
		password 	= (String) environment.get(NEO4J_PASSWORD);
		username	= (String) environment.get(NEO4J_USERNAME);

		return this;
	}

	public final ConnectionAttribute validateParameters()throws RuntimeException{

		if (host == null || host.isEmpty()) {
			throw new RuntimeException(NEO4J_ADDRESS + " resource property is empty");
		}
		else if (password == null || password.isEmpty()) {
			throw new RuntimeException(NEO4J_PASSWORD + " resource property is empty");
		}
		else if (username == null || username.isEmpty()) {
			throw new RuntimeException(NEO4J_USERNAME + " resource property is empty");
		}

		return this;
	}	
	
	public String getUsernameInformation(){
		return username;
	}
	
	public String getKeyInformation(){
		return password;
	}
	
	public String getHostInformation(){
		return host;
	}
}