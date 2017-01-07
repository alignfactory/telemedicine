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
	
	ValueProvider<RequestModel, Long> 	requestUserId();
	ValueProvider<RequestModel, String> requestTypeCode();
	ValueProvider<RequestModel, Date> 	requestDate();
	ValueProvider<RequestModel, String> requestNote();

	ValueProvider<RequestModel, Date> treatDate();
	ValueProvider<RequestModel, Long> 	treatUserId();
	ValueProvider<RequestModel, String> treatNote();
	ValueProvider<RequestModel, String> treatStateCode();
	ValueProvider<RequestModel, String> treatStateName();
	
	ValueProvider<RequestModel, Long> regUserId();
	ValueProvider<RequestModel, Date> regDate();

	ValueProvider<RequestModel, String> note();

// 환자 모델 
	@Path("patientModel.korName")
	ValueProvider<RequestModel, String> patientKorName();
	
	@Path("patientModel.insNo")
	ValueProvider<RequestModel, String> insNo();

	@Path("patientModel.birthday")
	ValueProvider<RequestModel, Date> birthday();

	@Path("patientModel.genderName")
	ValueProvider<RequestModel, String> genderName();

	@Path("patientModel.note")
	ValueProvider<RequestModel, String> patientNote();

	
// 보건의 모델
	@Path("requestUserModel.korName")
	ValueProvider<RequestModel, String> korName();

// 전문의 모델 
	@Path("treatUserModel.korName")
	ValueProvider<RequestModel, String> treatKorName();

// 등록자 모델
	@Path("regUserModel.korName")
	ValueProvider<RequestModel, String> regKorName();

}
