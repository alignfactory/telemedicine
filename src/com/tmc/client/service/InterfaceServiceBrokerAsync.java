package com.tmc.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InterfaceServiceBrokerAsync {
	
	void serviceCall(ServiceRequest request, AsyncCallback<ServiceResult> callback) throws IllegalArgumentException;
}
