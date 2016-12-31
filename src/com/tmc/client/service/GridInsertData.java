package com.tmc.client.service;

import java.util.ArrayList;
import java.util.List;

import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class GridInsertData<T extends AbstractDataModel> implements InterfaceServiceCall{
	
	private ListStore<T> listStore ; 
	// private T insertModel ; 
	private InterfaceCallback callBack; 
	
	public void insertData(ListStore<T> listStore, String serviceName, List<T> list ){

		this.listStore = listStore;
		List<AbstractDataModel> dataList = new ArrayList<AbstractDataModel>(); 
		
		dataList.addAll(list); 

		ServiceRequest request = new ServiceRequest(serviceName);
		request.setList(dataList);
		
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

		for (AbstractDataModel model: result.getResult()) {
			// 저장된 후 add한다. 
			listStore.add((T) model);
		}
		
		if(this.callBack != null){
			this.callBack.callback();
		}
	}
	
	public void addCallback(InterfaceCallback callBack){
		this.callBack = callBack;
	}
}
