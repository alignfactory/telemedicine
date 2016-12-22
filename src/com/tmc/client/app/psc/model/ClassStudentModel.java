package com.tmc.client.app.psc.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class ClassStudentModel extends AbstractDataModel {
	private Long 	classStudentId;
	private Long	studyClassId;
	private Long	studentId;
	private Date	assignDate;
	private String 	note;
	
	private StudyClassModel studyClassModel = new StudyClassModel(); 
	
	public void setKeyId(Long id) {
		this.setClassStudentId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getClassStudentId(); 
	}
	public Long getClassStudentId() {
		return classStudentId;
	}
	public void setClassStudentId(Long classStudentId) {
		this.classStudentId = classStudentId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public StudyClassModel getStudyClassModel() {
		return studyClassModel;
	}
	public void setStudyClassModel(StudyClassModel studyClassModel) {
		this.studyClassModel = studyClassModel;
	}
	public Long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}
}
