package com.software.rmh.friends;

/**
 * Created by rhoffman on 8/2/16. Serves as a Model to store data as a single object to be
 * easily retrievable when needed.
 */

public class Contact {

	private String NAME;
	private String NUMBER;

	public Contact(){
		// Empty constructor
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
