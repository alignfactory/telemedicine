package com.tmc.client.service;

import java.util.List;

import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class TreeGridDeleteRow<T> implements InterfaceServiceCall{
	
	TreeStore<T> treeStore ; 
	
	public TreeGridDeleteRow(){
	} 
	
	
	@SuppressWarnings("unchecked")
	public void deleteRow(TreeStore<T> treeStore, List<T> checkedList, String serviceName){

		this.treeStore = treeStore; 
		
		ServiceRequest request = new ServiceRequest(serviceName);
		request.setList((List<AbstractDataModel>)checkedList);
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
			treeStore.remove(treeStore.findModelWithKey(model.getKeyId() + ""));
		}
	}
}
