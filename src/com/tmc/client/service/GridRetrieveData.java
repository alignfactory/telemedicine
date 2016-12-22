package com.tmc.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class GridRetrieveData<T> implements InterfaceServiceCall{
	
	ListStore<T> listStore ; 
	Map<String, Object> param = new HashMap<String, Object>(); 
	
	public GridRetrieveData(ListStore<T> listStore){
		this.listStore = listStore;
	} 
	
	public void retrieveAll(String serviceName){
		// retrieve all, 파라미터 전달없이 조회한다. 
		ServiceRequest request = new ServiceRequest(serviceName);
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}
	
	public void addParam(String key, Object data){
		param.put(key, data); 
	}
	
	public void retrieve(String serviceName){
		// retrieve all 
		ServiceRequest request = new ServiceRequest(serviceName);
		request.setParam(this.param);
		
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}
		
		listStore.replaceAll((List<? extends T>) result.getResult());
		
	}
	
}
