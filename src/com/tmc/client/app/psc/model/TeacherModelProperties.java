package com.tmc.client.app.psc.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TeacherModelProperties extends PropertyAccess<TeacherModel> {

	ModelKeyProvider<TeacherModel> keyId();
	
	ValueProvider<TeacherModel, Long> 	teacherId();
	ValueProvider<TeacherModel, Long> 	studyClassId();
	ValueProvider<TeacherModel, Long> 	userId();
	ValueProvider<TeacherModel, String> 	teacherTypeCode();
	ValueProvider<TeacherModel, String> 	teacherTypeName();
	ValueProvider<TeacherModel, Date> 	applyDate();
	ValueProvider<TeacherModel, Boolean> closeYnFlag();
	ValueProvider<TeacherModel, String> 	note();

	@Path("userModel.korName")
	ValueProvider<TeacherModel, String>	korName();

	@Path("userModel.ctzNo")
	ValueProvider<TeacherModel, String>	ctzNo();

	@Path("userModel.genderName")
	ValueProvider<TeacherModel, String>	genderName();

	@Path("userModel.telNo01")
	ValueProvider<TeacherModel, String>	telNo01();

	ValueProvider<TeacherModel, String> findCell();	
	
}
