package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public interface PatientModelProperties  extends PropertyAccess<PatientModel> {

	ModelKeyProvider<PatientModel> keyId();
	
	ValueProvider<PatientModel, Long>	patientId();
	ValueProvider<PatientModel, String> korName();
	ValueProvider<PatientModel, String> zipCode();
	ValueProvider<PatientModel, String> address();
	ValueProvider<PatientModel, String> tel1();
	ValueProvider<PatientModel, String> tel2();
	ValueProvider<PatientModel, String> guardianName();
	ValueProvider<PatientModel, String> guardianTel1();
	ValueProvider<PatientModel, String> guardianTel2();
	ValueProvider<PatientModel, String> guardianRelationName();
	ValueProvider<PatientModel, String> viewPoint();
	ValueProvider<PatientModel, Date> 	birthday();
	ValueProvider<PatientModel, String> genderCode();
	ValueProvider<PatientModel, String> insNo();
	ValueProvider<PatientModel, Long> 	companyId();
	ValueProvider<PatientModel, String> note();
	ValueProvider<PatientModel, String> genderName();
	ValueProvider<PatientModel, Date> 	firstDate();

}
