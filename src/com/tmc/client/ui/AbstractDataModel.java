package com.tmc.client.ui;

import com.google.gwt.user.client.rpc.IsSerializable;

abstract public class AbstractDataModel implements IsSerializable{
	public abstract void setKeyId(Long id);
	public abstract Long getKeyId();
} 
