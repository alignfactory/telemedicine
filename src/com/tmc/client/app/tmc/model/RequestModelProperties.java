package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public interface RequestModelProperties  extends PropertyAccess<RequestModel> {

	ModelKeyProvider<RequestModel> keyId();
	
	ValueProvider<RequestModel, Long> requestId();

	ValueProvider<RequestModel, Long> patientId();

	@Path("patientModel.korName")
	ValueProvider<RequestModel, String> patientKorName();
	
	@Path("patientModel.insNo")
	ValueProvider<RequestModel, String> insNo();
	
	ValueProvider<RequestModel, Long> 	requestUserId();

	@Path("requestUserModel.korName")
	ValueProvider<RequestModel, String> korName();

	ValueProvider<RequestModel, String> requestTypeCode();
	ValueProvider<RequestModel, Date> 	requestDate();
	ValueProvider<RequestModel, String> requestNote();
	
	ValueProvider<RequestModel, Date> treatDate();
	ValueProvider<RequestModel, Long> 	treatUserId();

	@Path("treatUserModel.korName")
	ValueProvider<RequestModel, String> treatKorName();
	
	ValueProvider<RequestModel, String> treatNote();
	
	ValueProvider<RequestModel, Long> regUserId();

	@Path("regUserModel.korName")
	ValueProvider<RequestModel, String> regKorName();
	
	ValueProvider<RequestModel, Date> regDate();

	ValueProvider<RequestModel, String> note();
}
