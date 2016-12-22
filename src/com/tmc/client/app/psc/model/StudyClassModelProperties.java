package com.tmc.client.app.psc.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StudyClassModelProperties extends PropertyAccess<StudyClassModel> {

	ModelKeyProvider<StudyClassModel> keyId();
	
	ValueProvider<StudyClassModel, Long> 	studyClassId();
	ValueProvider<StudyClassModel, Long> 	companyId();
	ValueProvider<StudyClassModel, String> 	studyClassCode();
	ValueProvider<StudyClassModel, String> 	studyClassName();
	ValueProvider<StudyClassModel, String> 	studyClassTypeCode();
	ValueProvider<StudyClassModel, String> 	studyClassTypeName();
	ValueProvider<StudyClassModel, String> 	note();
	ValueProvider<StudyClassModel, Boolean> useYnFlag();
}
