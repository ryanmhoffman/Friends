package com.software.rmh.friends;

/**
 * Created by Ryan Hoffman on 8/2/16. Serves as a Model to store data as a single object to be
 * easily retrievable when needed.
 */

public class Contact {

	private String NAME;
	private String NUMBER;

	/**
	 * Dangerous to use this constructor with no parameters due to that fact that it will create the contact in memory,
	 * and if the "getter" methods are called before their respective "setter" methods it will return null since the
	 * variables have not been initialized at this point.
	 */
	public Contact(){
		// Empty constructor, use with caution.
	}

	public Contact(String name, String number){
		this.NAME = name;
		this.NUMBER = number;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public void setNUMBER(String NUMBER) {
		this.NUMBER = NUMBER;
	}

	public String getNAME() {
		return NAME;
	}

	public String getNUMBER() {
		return NUMBER;
	}
}
