package com.tmc.client.app.sys.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface FileModelProperties extends PropertyAccess<FileModel> {
	
	ModelKeyProvider<FileModel> keyId();
	
	ValueProvider<FileModel, Long> 		fileId();
	ValueProvider<FileModel, Long> 		parentId();
	ValueProvider<FileModel, String> 	fileName();
	ValueProvider<FileModel, Date> 		regDate();
	ValueProvider<FileModel, String> 	serverPath();
	ValueProvider<FileModel, Double> 	size();
	ValueProvider<FileModel, Date> 		delDate();
	ValueProvider<FileModel, String> 	note();
	
	ValueProvider<FileModel, String> 	downloadCell();
	ValueProvider<FileModel, String> 	deleteCell();
	
}
