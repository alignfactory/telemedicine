package com.tmc.client.service;

import com.tmc.client.ui.SimpleMessage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;

public class ServiceCall implements IsSerializable {
	
	public ServiceCall(){
	}
	
	public void execute(final ServiceRequest request, final InterfaceServiceCall target){

		InterfaceServiceBrokerAsync serviceBroker = GWT.create(InterfaceServiceBroker.class);
		
		serviceBroker.serviceCall(request, 
			new AsyncCallback<ServiceResult>() {
			
				@Override // TODO : override 치는게 맞는지? 
				public void onFailure(Throwable caught) {
					new SimpleMessage("network async fail : " + request.getServiceName());
				}
				
				@Override
				public void onSuccess(ServiceResult result) {
					target.getServiceResult(result);
				}
		});
	}

}
