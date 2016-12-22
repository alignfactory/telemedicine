package com.tmc.client.service;

import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class TreeGridInsertRow<T> implements InterfaceServiceCall{
	
	TreeStore<T> treeStore ;
	T parentModel; 
	AbstractDataModel insertModel ; 
	
	public TreeGridInsertRow(){
	} 
	
	public void addRoot(TreeStore<T> treeStore, AbstractDataModel insertModel){

		this.treeStore = treeStore;
		this.insertModel = insertModel; 

		ServiceRequest request = new ServiceRequest("getSeq");

		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}

	public void addItem(TreeStore<T> treeStore, T parentModel, AbstractDataModel insertModel){

		
		this.treeStore = treeStore;
		this.parentModel = parentModel; 
		this.insertModel = insertModel; 

		ServiceRequest request = new ServiceRequest("getSeq");

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

		insertModel.setKeyId(result.getSeq());
		
		if(parentModel != null) {
			treeStore.add(parentModel, (T) insertModel);
		}
		else { 
			treeStore.add((T) insertModel);
		} 
	}
}
