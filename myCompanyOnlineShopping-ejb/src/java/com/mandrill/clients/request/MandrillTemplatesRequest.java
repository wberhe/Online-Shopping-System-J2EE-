package com.mandrill.clients.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import com.mandrill.clients.exception.RequestFailedException;
import com.mandrill.clients.model.BaseMandrillRequest;
import com.mandrill.clients.model.MandrillRequestWithCode;
import com.mandrill.clients.model.MandrillRequestWithName;
import com.mandrill.clients.model.ServiceMethods;
import com.mandrill.clients.model.response.BaseMandrillAnonymousListResponse;
import com.mandrill.clients.model.response.templates.TemplateListResponse;
import com.mandrill.clients.model.response.templates.TemplateResponse;

public class MandrillTemplatesRequest {
	
	MandrillRESTRequest request;
	
	TypeReference<List<TemplateResponse>> templatesListReference = new TypeReference<List<TemplateResponse>>(){};
	
	public TemplateResponse addTemplate(MandrillRequestWithCode addRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(addRequest, ServiceMethods.Templates.ADD, TemplateResponse.class);
	}
	
	public TemplateResponse getTemplateInfo(MandrillRequestWithName infoRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(infoRequest, ServiceMethods.Templates.INFO, TemplateResponse.class);
	}
	
	public TemplateResponse updateTemplate(MandrillRequestWithCode updateRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(updateRequest, ServiceMethods.Templates.UPDATE, TemplateResponse.class);
	}
	
	public TemplateResponse deleteTemplate(MandrillRequestWithName deleteRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(deleteRequest, ServiceMethods.Templates.DELETE, TemplateResponse.class);
	}
	
	@SuppressWarnings("unchecked")
	public TemplateListResponse getTemplates(BaseMandrillRequest listRequest) throws RequestFailedException {
		TemplateListResponse response = new TemplateListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<TemplateResponse>)request.postRequest(listRequest, ServiceMethods.Templates.LIST, TemplateResponse.class, templatesListReference)).getList());
		return response;
	}

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}

}
