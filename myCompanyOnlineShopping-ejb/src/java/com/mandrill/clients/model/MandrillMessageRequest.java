package com.mandrill.clients.model;

public class MandrillMessageRequest extends BaseMandrillRequest {
	
	private MandrillHtmlMessage message;

	public MandrillMessage getMessage() {
		return message;
	}

	public void setMessage(MandrillHtmlMessage message) {
		this.message = message;
	}
	
	

}
