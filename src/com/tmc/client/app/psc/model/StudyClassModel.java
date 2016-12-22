package com.tmc.client.app.psc.model;

import com.tmc.client.ui.AbstractDataModel;

public class StudyClassModel extends AbstractDataModel {
	private Long 	studyClassId;
	private Long	companyId;
	private String 	studyClassCode ;
	private String	studyClassName;
	private String	studyClassTypeCode;
	private String	studyClassTypeName;
	private String	useYn;
	private String 	note;
	
	@Override
	public void setKeyId(Long id) {
		this.setStudyClassId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getStudyClassId(); 
	}
	public Long getStudyClassId() {
		return studyClassId;
	}
	public void setStudyClassId(Long studyClassId) {
		this.studyClassId = studyClassId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getStudyClassCode() {
		return studyClassCode;
	}
	public void setStudyClassCode(String studyClassCode) {
		this.studyClassCode = studyClassCode;
	}
	public String getStudyClassName() {
		return studyClassName;
	}
	public void setStudyClassName(String studyClassName) {
		this.studyClassName = studyClassName;
	}
	public String getStudyClassTypeCode() {
		return studyClassTypeCode;
	}
	public void setStudyClassTypeCode(String studyClassTypeCode) {
		this.studyClassTypeCode = studyClassTypeCode;
	}
	public String getStudyClassTypeName() {
		return studyClassTypeName;
	}
	public void setStudyClassTypeName(String studyClassTypeName) {
		this.studyClassTypeName = studyClassTypeName;
	}
	public String getUseYn() {
		if(useYn == null){
			return "false"; 
		}
		else {
			return useYn;
		}
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getUseYnFlag(){
		return "true".equals(this.useYn); 
	}
}
