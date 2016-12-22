package com.tmc.client.service;

import java.util.List;

import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class GridDeleteData<T> implements InterfaceServiceCall{
	
	ListStore<T> listStore ; 
	
	public GridDeleteData(){
	} 
	
	
	@SuppressWarnings("unchecked")
	public void deleteRow(ListStore<T> listStore, List<T> checkedList, String serviceName){
		this.listStore = listStore; 
		ServiceRequest request = new ServiceRequest(serviceName);
		request.setList((List<AbstractDataModel>) checkedList);
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}
	
	@Override
	public void getServiceResult(ServiceResult result) {
		
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}

		for (AbstractDataModel model: result.getResult()) {
			// remove할 때는 key id로 model을 찾아서 삭제한다. 
			listStore.remove(listStore.findModelWithKey(model.getKeyId() + ""));
		}
	}
}
