package com.tmc.client.app.psc.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StudentModelProperties extends PropertyAccess<StudentModel> {
	
	ModelKeyProvider<StudentModel> keyId();
	
	ValueProvider<StudentModel, Long>  	studentId() ; 
	ValueProvider<StudentModel, Long> 	companyId() ; 
	ValueProvider<StudentModel, String> studentNo() ; 
	ValueProvider<StudentModel, String> korName(); 
	ValueProvider<StudentModel, String> engName(); 
	ValueProvider<StudentModel, String> chnName();
	ValueProvider<StudentModel, String> ctzNo();
	ValueProvider<StudentModel, Date> 	birthday();
	ValueProvider<StudentModel, String> genderCode();
	ValueProvider<StudentModel, String> genderName();
	ValueProvider<StudentModel, String> zipCode();
	ValueProvider<StudentModel, String> zipAddress();
	ValueProvider<StudentModel, String> detailAddress();
	ValueProvider<StudentModel, String> tel01();
	ValueProvider<StudentModel, String> tel02();
	ValueProvider<StudentModel, String> email();
	ValueProvider<StudentModel, String> career();
	ValueProvider<StudentModel, String> childNote();
	ValueProvider<StudentModel, String> character();
	ValueProvider<StudentModel, String> speciality();
	ValueProvider<StudentModel, String> habit();
	ValueProvider<StudentModel, String> likeFood();
	ValueProvider<StudentModel, String> hateFood();
	ValueProvider<StudentModel, String> choiceSchool();
	ValueProvider<StudentModel, String> dadName();
	ValueProvider<StudentModel, Date> 	dadBirthday();
	ValueProvider<StudentModel, String> dadJob();
	ValueProvider<StudentModel, String> dadWorkplace();
	ValueProvider<StudentModel, String> dadTel01();
	ValueProvider<StudentModel, String> dadTel02();
	ValueProvider<StudentModel, String> dadScholar();
	ValueProvider<StudentModel, String> dadReligion();
	ValueProvider<StudentModel, String> momName();
	ValueProvider<StudentModel, Date> 	momBirthday();
	ValueProvider<StudentModel, String> momJob();
	ValueProvider<StudentModel, String> momWorkplace();
	ValueProvider<StudentModel, String> momTel01();
	ValueProvider<StudentModel, String> momTel02();
	ValueProvider<StudentModel, String> momScholar();
	ValueProvider<StudentModel, String> momReligion();
	ValueProvider<StudentModel, String> grandFatherYn();
	ValueProvider<StudentModel, String> grandMotherYn();
	ValueProvider<StudentModel, Long> 	upBrotherCount();
	ValueProvider<StudentModel, Long> 	downBrotherCount();
	ValueProvider<StudentModel, Long> 	upSisterCount();
	ValueProvider<StudentModel, Long> 	downSisterCount();
	ValueProvider<StudentModel, String> familyNote();
	ValueProvider<StudentModel, String> supportYn();
	ValueProvider<StudentModel, Long> supportAmount();
	ValueProvider<StudentModel, String> bankCode();
	ValueProvider<StudentModel, String> bankName();
	ValueProvider<StudentModel, String> accountNo();
	ValueProvider<StudentModel, String> accountHolder();
	ValueProvider<StudentModel, String> evidenceYn();
	ValueProvider<StudentModel, String> note();
	}
