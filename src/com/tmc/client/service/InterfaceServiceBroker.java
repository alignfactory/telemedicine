package com.tmc.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("serviceBroker")
public interface InterfaceServiceBroker extends RemoteService {
	
	ServiceResult serviceCall(ServiceRequest request) throws IllegalArgumentException;
}
