package com.mandrill.clients.model;

public class MandrillRequestWithTag extends BaseMandrillRequest {

	private String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
