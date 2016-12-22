package com.tmc.client.app.psc.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ClassStudentModelProperties extends PropertyAccess<ClassStudentModel> {
	
	ModelKeyProvider<ClassStudentModel> keyId();
	
	ValueProvider<ClassStudentModel, Long> classStudentId();  
	ValueProvider<ClassStudentModel, Long> studyClassId();
	ValueProvider<ClassStudentModel, Long> studentId();
	ValueProvider<ClassStudentModel, Date> assignDate();
	ValueProvider<ClassStudentModel, String> note();
	
	@Path("studyClassModel.studyClassName")	
	ValueProvider<ClassStudentModel, String> studyClassName();

	@Path("studyClassModel.studyClassTypeName")	
	ValueProvider<ClassStudentModel, String> studyClassTypeName();
}
