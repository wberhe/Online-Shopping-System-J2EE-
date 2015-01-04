package com.mandrill.clients.model;

public class MandrillRequestWithDomain extends BaseMandrillRequest {

	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
