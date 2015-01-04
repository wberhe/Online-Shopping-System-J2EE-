package com.mandrill.clients.model;

public class MandrillRequestWithEmail extends BaseMandrillRequest {

	String email;

	public String getEmail() {
		return email;
	}

	public  void setEmail(String email) {
		this.email = email;
	}
	
}
