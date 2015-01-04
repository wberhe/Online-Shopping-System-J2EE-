package com.mandrill.clients.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.MandrillMessageRequest;
import com.mandrill.clients.model.MandrillTemplatedMessageRequest;
import com.mandrill.clients.model.ServiceMethods;
import com.mandrill.clients.model.response.BaseMandrillAnonymousListResponse;
import com.mandrill.clients.model.response.BaseMandrillResponse;
import com.mandrill.clients.model.response.message.MessageResponse;
import com.mandrill.clients.model.response.message.SendMessageResponse;

/**
 * This class holds various functions for the Mandrill Messages API
 * @author Brian Cribbs
 *
 */
public class MandrillMessagesRequest {
	
	MandrillRESTRequest request;
	TypeReference<List<MessageResponse>> messageResponseListReference = new TypeReference<List<MessageResponse>>(){};
	
	/**
	 * Send a new transactional message through Mandrill
	 * @param messageRequest a populated @see com.cribstechnologies.clients.mandrill.model.MandrillMessageRequest
	 * @throws RequestFailedException
	 */
	@SuppressWarnings("unchecked")
	public SendMessageResponse sendMessage(MandrillMessageRequest messageRequest) throws RequestFailedException {
		SendMessageResponse response = new SendMessageResponse();
		BaseMandrillResponse baseMandrillResponse = request.postRequest(messageRequest, ServiceMethods.Messages.SEND, SendMessageResponse.class, messageResponseListReference);
		BaseMandrillAnonymousListResponse<MessageResponse> baseMandrillAnonymousListResponse = (BaseMandrillAnonymousListResponse<MessageResponse>)baseMandrillResponse;
		List<MessageResponse> messageResponseList = baseMandrillAnonymousListResponse.getList();
		response.setList(messageResponseList);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public SendMessageResponse sendTemplatedMessage(MandrillTemplatedMessageRequest templateMessage) throws RequestFailedException {
		SendMessageResponse response = new SendMessageResponse();
		response.setList(((BaseMandrillAnonymousListResponse<MessageResponse>) request.postRequest(templateMessage, ServiceMethods.Messages.SEND_TEMPLATE, SendMessageResponse.class, messageResponseListReference)).getList());
		return response;
	}

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}
	
}
