package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CheckupModelProperties  extends PropertyAccess<CheckupModel> {

	 ModelKeyProvider<CheckupModel> keyId();

	 ValueProvider<CheckupModel, Long > checkupId();
	 ValueProvider<CheckupModel, Long > requestId();	 
	 ValueProvider<CheckupModel, String > checkupCode();
	 ValueProvider<CheckupModel, String > checkupName();
	 ValueProvider<CheckupModel, String > checkupOrder();
	 
	 ValueProvider<CheckupModel, String > processCode();
	 ValueProvider<CheckupModel, String > processName();
	 ValueProvider<CheckupModel, String> checkupResult();
	 ValueProvider<CheckupModel, Date > checkupDate();
	 
	 ValueProvider<CheckupModel, Long > checkupUserId();
	 
	 ValueProvider<CheckupModel, String > fileUpload();
	 
	 @Path("userModel.korName")
	 ValueProvider<CheckupModel, String> userKorName();
	 
	 @Path("requestModel.treatDate")
	 ValueProvider<CheckupModel, Date> treatDate();

	 @Path("requestModel.treatUserModel.treatKorName")
	 ValueProvider<CheckupModel, String> treatKorName();

	 @Path("requestModel.treatStateName")
	 ValueProvider<CheckupModel, String> treatStateName();

	 @Path("requestModel.requestNote")
	 ValueProvider<CheckupModel, String> requestNote();

	 @Path("patientModel.korName")
	 ValueProvider<CheckupModel, String> patientKorName();


	 
//	 @Path("requestModel.requestDate")
//	 ValueProvider<CheckupModel, Date> requestDate();
//
//	 @Path("requestModel.requestDate")
//	 ValueProvider<CheckupModel, Date> requestDate();
//
//	 
//		gridBuilder.addDate(checkupModelProperties.treatDate(), 85, "진료일"); //, new DateField());
//		gridBuilder.addText(checkupModelProperties.treatKorName(), 80, "진료의"); //, lookUpTreatUserField) ;
//
//		gridBuilder.addText(checkupModelProperties.treatStateName(), 80, "상태구분"); 
//		gridBuilder.addText(checkupModelProperties.insNo(), 80, "보험번호"); //, new TextField()) ;
//		gridBuilder.addText(checkupModelProperties.patientKorName(), 80, "환자명"); //, lookupPatientField) ;
//		gridBuilder.addText(checkupModelProperties.korName(), 80, "보건의"); //, lookupRequestUserField);
//		gridBuilder.addText(checkupModelProperties.requestNote(), 200, "진료요청내용"); //, new TextField()) ;

		
		
	 
}