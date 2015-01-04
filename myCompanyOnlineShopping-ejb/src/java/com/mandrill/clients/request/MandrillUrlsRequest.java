package com.mandrill.clients.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.BaseMandrillRequest;
import com.mandrill.clients.model.MandrillRequestWithQuery;
import com.mandrill.clients.model.MandrillRequestWithUrl;
import com.mandrill.clients.model.ServiceMethods;
import com.mandrill.clients.model.response.BaseMandrillAnonymousListResponse;
import com.mandrill.clients.model.response.urls.UrlListResponse;
import com.mandrill.clients.model.response.urls.UrlResponse;

public class MandrillUrlsRequest {

	MandrillRESTRequest request;
	
	TypeReference<List<UrlResponse>> urlsListReference = new TypeReference<List<UrlResponse>>(){};
	
	@SuppressWarnings("unchecked")
	public UrlListResponse getList(BaseMandrillRequest listRequest) throws RequestFailedException {
		UrlListResponse response = new UrlListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<UrlResponse>)request.postRequest(listRequest, ServiceMethods.Urls.LIST, UrlListResponse.class, urlsListReference)).getList());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public UrlListResponse doSearch(MandrillRequestWithQuery searchRequest) throws RequestFailedException {
		UrlListResponse response = new UrlListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<UrlResponse>)request.postRequest(searchRequest, ServiceMethods.Urls.SEARCH, UrlListResponse.class, urlsListReference)).getList());
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public UrlListResponse getTimeSeries(MandrillRequestWithUrl seriesRequest) throws RequestFailedException {
		UrlListResponse response = new UrlListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<UrlResponse>)request.postRequest(seriesRequest, ServiceMethods.Urls.TIME_SERIES, UrlListResponse.class, urlsListReference)).getList());
		return response;
	} 

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}
	
}
