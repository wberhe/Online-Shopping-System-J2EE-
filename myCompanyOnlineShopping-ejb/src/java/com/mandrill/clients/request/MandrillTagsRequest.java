package com.mandrill.clients.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.BaseMandrillRequest;
import com.mandrill.clients.model.MandrillRequestWithTag;
import com.mandrill.clients.model.ServiceMethods;
import com.mandrill.clients.model.response.tags.BaseTag;
import com.mandrill.clients.model.response.tags.TagListResponse;
import com.mandrill.clients.model.response.tags.TagSeriesResponse;
import com.mandrill.clients.model.response.tags.TagWithTime;
import com.mandrill.clients.model.response.BaseMandrillAnonymousListResponse;

public class MandrillTagsRequest {
	
	MandrillRESTRequest request;
	
	TypeReference<List<TagWithTime>> timeTagReference = new TypeReference<List<TagWithTime>>(){};
	TypeReference<List<BaseTag>> nameTagReference = new TypeReference<List<BaseTag>>(){};

	@SuppressWarnings("unchecked")
	public TagListResponse getList(BaseMandrillRequest tagsRequest) throws RequestFailedException {
		TagListResponse response = new TagListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<BaseTag>) request.postRequest(tagsRequest, ServiceMethods.Tags.LIST, TagListResponse.class, nameTagReference)).getList());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public TagSeriesResponse getTimeSeries(MandrillRequestWithTag tagsRequest) throws RequestFailedException {
		TagSeriesResponse response = new TagSeriesResponse();
		response.setList(((BaseMandrillAnonymousListResponse<TagWithTime>)request.postRequest(tagsRequest, ServiceMethods.Tags.TIME_SERIES, TagSeriesResponse.class, timeTagReference)).getList());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public TagSeriesResponse getAllTimeSeries(BaseMandrillRequest tagsRequest) throws RequestFailedException {
		TagSeriesResponse response = new TagSeriesResponse();
		response.setList(((BaseMandrillAnonymousListResponse<TagWithTime>)request.postRequest(tagsRequest, ServiceMethods.Tags.ALL_TIME_SERIES, TagSeriesResponse.class, timeTagReference)).getList());
		return response;
	}
	
	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}
}
