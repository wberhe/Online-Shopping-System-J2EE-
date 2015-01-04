package com.mandrill.clients.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.BaseMandrillRequest;
import com.mandrill.clients.model.MandrillRequestWithDomain;
import com.mandrill.clients.model.MandrillRequestWithEmail;
import com.mandrill.clients.model.ServiceMethods;
import com.mandrill.clients.model.response.BaseMandrillAnonymousListResponse;
import com.mandrill.clients.model.response.BaseMandrillResponse;
import com.mandrill.clients.model.response.BaseMandrillStringResponse;
import com.mandrill.clients.model.response.users.DisableResponse;
import com.mandrill.clients.model.response.users.MandrillSender;
import com.mandrill.clients.model.response.users.UsersInfoResponse;
import com.mandrill.clients.model.response.users.UsersSendersResponse;
import com.mandrill.clients.model.response.users.VerifyResponse;

/**
 * 
 * This class holds various functions for the Mandrill Users API.
 * 
 * @author Brian Cribbs, brian@cribbstechnologies.com
 *
 */
public class MandrillUsersRequest {
	
	MandrillRESTRequest request;

	TypeReference<List<MandrillSender>> usersListReference = new TypeReference<List<MandrillSender>>(){};
	/**
	 * Return the information about the API-connected user
	 * @param infoRequest a populated @see com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest
	 * @throws RequestFailedException
	 */
	public UsersInfoResponse getInfo(BaseMandrillRequest infoRequest) throws RequestFailedException {
		BaseMandrillResponse response = request.postRequest(infoRequest, ServiceMethods.Users.INFO, UsersInfoResponse.class);
		return (UsersInfoResponse) response;
	}
	
	/**
	 * Validate an API key and respond to a ping
	 * @param pingRequest a populated @see com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest
	 * @throws RequestFailedException
	 */
	public BaseMandrillStringResponse performPing(BaseMandrillRequest pingRequest) throws RequestFailedException {
		BaseMandrillStringResponse response = (BaseMandrillStringResponse) request.postRequest(pingRequest, ServiceMethods.Users.PING, null);
		return response;
	}
	
	/**
	 * Return the senders that have tried to use this account, both verified and unverified
	 * @param sendersRequest a populated @see com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest
	 * @throws RequestFailedException
	 */
	@SuppressWarnings("unchecked")
	public UsersSendersResponse getSenders(BaseMandrillRequest sendersRequest) throws RequestFailedException {
		UsersSendersResponse response = new UsersSendersResponse();
		response.setList(((BaseMandrillAnonymousListResponse<MandrillSender>) request.postRequest(sendersRequest, ServiceMethods.Users.SENDERS, UsersSendersResponse.class, usersListReference)).getList());
		return response;
	}
	
	/**
	 * Disable a sender from being able to send
	 * @param disableRequest a populated @see com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithEmail
	 * @throws RequestFailedException
	 */
	public DisableResponse disableSender(MandrillRequestWithDomain disableRequest) throws RequestFailedException {
		return (DisableResponse) request.postRequest(disableRequest, ServiceMethods.Users.DISABLE_SENDER, DisableResponse.class);
	}
	
	/**
	 * Send an email to the given address to verify that it is an accepted sender for your Mandrill account
	 * @param verifyRequest a populated @see com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithEmail
	 * @throws RequestFailedException
	 */
	public VerifyResponse verifySender(MandrillRequestWithEmail verifyRequest) throws RequestFailedException {
		return (VerifyResponse)request.postRequest(verifyRequest, ServiceMethods.Users.VERIFY_SENDER, VerifyResponse.class);
	}

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}

	public TypeReference<List<MandrillSender>> getUsersListReference() {
		return usersListReference;
	}
	
}
