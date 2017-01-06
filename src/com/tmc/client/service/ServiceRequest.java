package com.tmc.client.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmc.client.ui.AbstractDataModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class ServiceRequest implements IsSerializable {

	private String serviceName;
//	private Map<String, AbstractDataModel> requestModel = new HashMap<String, AbstractDataModel>() ;
	private Map<String, Object> param = new HashMap<String, Object>();
	private List<AbstractDataModel> list = new ArrayList<AbstractDataModel>();
	
	public ServiceRequest(){
	}
	
	public ServiceRequest(String serviceName){
		this.setServiceName(serviceName);
	}
	
	public List<AbstractDataModel> getList() {
		return list;
	}
	
	public void setList(List<AbstractDataModel> list) {
		this.list = list;
	}

	public String getServiceName() {
		return serviceName;
	}
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Map<String, Object> getParam(){
		return this.param; 
	}
	
	public void add(String key, String value){
		this.param.put(key, value); 
	}

	public void add(String key, Long value){
		this.param.put(key, value); 
	}

	public void add(String key, Boolean value){
		this.param.put(key, value); 
	}

	public void add(String key, Date value){
		this.param.put(key, value); 
	}
	
	public void add(String key, AbstractDataModel value){
		this.param.put(key, value); 
	}

	public void add(String key, Object value){
		this.param.put(key, value); 
	}

	public void setParam(Map<String, Object> param){
		this.param = param ;  
	}

	
	public String getString(String key){
		if(this.param.get(key) !=  null ){
			return this.param.get(key).toString();
		}
		else {
			return null; 
		}
	}
	
	public Long getLong(String key){
		if(param.get(key) != null) {
			Long data = (Long)this.param.get(key);
			return data ; 
		}
		else {
			return null; 
		}
	}

	public Date getDate(String key){
		if(param.get(key) != null) {
			return (Date)this.param.get(key) ; 
		}
		else {
			return null; 
		}
	}

	
	public Boolean getBoolean(String key){
		if(param.get(key) != null) {
			return (Boolean)param.get(key);
		}
		else {
			return null; 
		}
	}
	
	public AbstractDataModel getModel(String key){
		return (AbstractDataModel)this.param.get(key);
	}

}

/*
public String insertData(String tableName, AbstractDataModel data){
	this.setServiceName("common.DataHandler.insertData");
	this.add("tableName", tableName);
	this.add("data", data);
	return "insertData"; 
}

public String updateData(String tableName, AbstractDataModel data){
	this.setServiceName("common.DataHandler.updateData");
	this.add("tableName", tableName);
	this.add("data", data);
	return "updateData"; 
}

public String deleteData(String tableName, Long dataId){
	this.setServiceName("common.DataHandler.deleteData");
	this.add("tableName", tableName);
	this.add("dataId", dataId);
	return "deleteData"; 
}



*/