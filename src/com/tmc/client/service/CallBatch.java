package com.tmc.client.service;

import java.util.HashMap;
import java.util.Map;

import com.tmc.client.ui.InterfaceCallback;
import com.sencha.gxt.widget.core.client.info.Info;

/*
 * 서버의 배치 작업을 실행하기 위한 Service Call Class 
 */

public class CallBatch implements InterfaceServiceCall{
	
	Map<String, Object> param = new HashMap<String, Object>(); 
	InterfaceCallback callback ; 
	
	public CallBatch(InterfaceCallback callback){
		this.callback = callback ; 
	} 
	
	public void addParam(String key, Object data){
		param.put(key, data); 
	}
	
	public void execute(String serviceName){
		ServiceRequest request = new ServiceRequest(serviceName);
		request.setParam(this.param);
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}
	
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}
		else {
			callback.callback();
			Info.display("배치작업 실행", "작업에 성공하였습니다.");
		}
	}
}
