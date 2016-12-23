package com.tmc.client.app.sys.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public interface UserModelProperties  extends PropertyAccess<UserModel> {
	ModelKeyProvider<UserModel> keyId();
		
	ValueProvider<UserModel, Long> 		userId();
	ValueProvider<UserModel, Long> 		companyId();
	ValueProvider<UserModel, String> 	korName(); 
	ValueProvider<UserModel, String> 	ctzNo(); 
	ValueProvider<UserModel, String> 	engName(); 
	ValueProvider<UserModel, String> 	chnName(); 
	ValueProvider<UserModel, String> 	genderCode(); 
	ValueProvider<UserModel, String> 	genderName();
	ValueProvider<UserModel, String> 	nationCode(); 
	ValueProvider<UserModel, String> 	nationName();
	ValueProvider<UserModel, Date> 		birthday(); 
	ValueProvider<UserModel, String> 	email(); 
	ValueProvider<UserModel, String> 	telNo01(); 
	ValueProvider<UserModel, String> 	telNo02(); 
	ValueProvider<UserModel, String> 	faxNo(); 
	ValueProvider<UserModel, String> 	zipCode(); 
	ValueProvider<UserModel, String> 	zipAddress(); 
	ValueProvider<UserModel, String> 	zipDetail(); 
	ValueProvider<UserModel, String> 	schoolName(); 
	ValueProvider<UserModel, String> 	graduateYear();
	ValueProvider<UserModel, String> 	mainMajor();
	ValueProvider<UserModel, String> 	subMajor();
	ValueProvider<UserModel, String> 	militaryTypeCode();
	ValueProvider<UserModel, String> 	militaryTypeName();
	ValueProvider<UserModel, String> 	scholar();
	ValueProvider<UserModel, String> 	career(); 
	ValueProvider<UserModel, String> 	loginId();
	
	ValueProvider<UserModel, String> 	loginYn();
	
	
	ValueProvider<UserModel, String> 	passwd();
	ValueProvider<UserModel, Boolean> 	closeYnBoolean();
	ValueProvider<UserModel, String> 	managerYn();
	
	ValueProvider<UserModel, String> 	bankCode();
	ValueProvider<UserModel, String> 	bankName();
	ValueProvider<UserModel, String> 	accountNo();
	ValueProvider<UserModel, String> 	accountHolder();
	
	ValueProvider<UserModel, String> 	note();
	
	ValueProvider<UserModel, Date> 		startDate();
	ValueProvider<UserModel, Date> 		closeDate();
	
	// ValueProvider<UserModel, Boolean> 	managerYnBoolean();
}
