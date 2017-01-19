package com.tmc.client.service;

import java.util.HashMap;
import java.util.Map;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class GridRetrieveJson<T> implements InterfaceServiceCall{
	
	private ListStore<T> listStore ; 
	private Map<String, Object> param = new HashMap<String, Object>(); 
	private InterfaceCallback callBack; 
	private String jsonResult; 
	
	
	public GridRetrieveJson(ListStore<T> listStore){
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
	
	
	public void addCallback(InterfaceCallback callBack){
		this.callBack = callBack;
	}
	
	
// 	@SuppressWarnings("unchecked")
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}
			
		this.setJsonResult(result.getJsonResult());
		//listStore.replaceAll((List<? extends T>) result.getResult());
		
		if(callBack != null){
			callBack.callback();
		}
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
}
