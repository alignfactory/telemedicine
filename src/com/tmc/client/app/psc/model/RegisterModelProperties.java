package com.tmc.client.app.psc.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface RegisterModelProperties extends PropertyAccess<StudyClassModel> {
	
	ModelKeyProvider<RegisterModel> keyId();
	
	ValueProvider<RegisterModel, Long> registerId(); 
	ValueProvider<RegisterModel, Long> studentId(); 
	ValueProvider<RegisterModel, String> entranceCode(); 
	ValueProvider<RegisterModel, String> entranceName(); 
	ValueProvider<RegisterModel, Date> entranceDate(); 
	ValueProvider<RegisterModel, String> entranceNote(); 
	ValueProvider<RegisterModel, String> graduateCode(); 
	ValueProvider<RegisterModel, String> graduateName(); 
	ValueProvider<RegisterModel, Date> graduateDate(); 
	ValueProvider<RegisterModel, String> graduateNote(); 
	ValueProvider<RegisterModel, String> afterAction(); 
	ValueProvider<RegisterModel, String> note(); 

}
