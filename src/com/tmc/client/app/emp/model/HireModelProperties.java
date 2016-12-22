package com.tmc.client.app.emp.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface HireModelProperties extends PropertyAccess<HireModel> {
	
	ModelKeyProvider<HireModel> keyId();	
	
	ValueProvider<HireModel, Long> 		hireId();
	ValueProvider<HireModel, Long> 		UserId();

	ValueProvider<HireModel, Date> 		hireDate();
	ValueProvider<HireModel, String> 	hireCode();
	ValueProvider<HireModel, String> 	hireName();
	ValueProvider<HireModel, String> 	hireReason();
		
	ValueProvider<HireModel, Date> 		retireDate();
	ValueProvider<HireModel, String> 	retireCode();
	ValueProvider<HireModel, String> 	retireName();
	ValueProvider<HireModel, String> 	retireReason();
	
	ValueProvider<HireModel, String> 	note();
	
	@Path("userModel.korName")
	ValueProvider<HireModel, String>	korName();

	@Path("userModel.ctzNo")
	ValueProvider<HireModel, String>	ctzNo();

	@Path("userModel.genderName")
	ValueProvider<HireModel, String>	genderName();

	@Path("userModel.telNo01")
	ValueProvider<HireModel, String>	telNo1();
}
