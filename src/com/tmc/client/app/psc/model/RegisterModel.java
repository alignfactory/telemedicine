package com.tmc.client.app.psc.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class RegisterModel extends AbstractDataModel {

	private Long registerId; 
	private Long studentId; 
	private String entranceCode; 
	private String entranceName; 
	private Date entranceDate; 
	private String entranceNote; 
	private String graduateCode; 
	private String graduateName; 
	private Date graduateDate; 
	private String graduateNote; 
	private String afterAction; 
	private String note; 
	
	@Override
	public void setKeyId(Long id) {
		this.setRegisterId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getRegisterId(); 
	}
	public Long getRegisterId() {
		return registerId;
	}
	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getEntranceCode() {
		return entranceCode;
	}
	public void setEntranceCode(String entranceCode) {
		this.entranceCode = entranceCode;
	}
	public String getEntranceName() {
		return entranceName;
	}
	public void setEntranceName(String entranceName) {
		this.entranceName = entranceName;
	}
	public Date getEntranceDate() {
		return entranceDate;
	}
	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}
	public String getEntranceNote() {
		return entranceNote;
	}
	public void setEntranceNote(String entranceNote) {
		this.entranceNote = entranceNote;
	}
	public String getGraduateCode() {
		return graduateCode;
	}
	public void setGraduateCode(String graduateCode) {
		this.graduateCode = graduateCode;
	}
	public String getGraduateName() {
		return graduateName;
	}
	public void setGraduateName(String graduateName) {
		this.graduateName = graduateName;
	}
	public Date getGraduateDate() {
		return graduateDate;
	}
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}
	public String getGraduateNote() {
		return graduateNote;
	}
	public void setGraduateNote(String graduateNote) {
		this.graduateNote = graduateNote;
	}
	public String getAfterAction() {
		return afterAction;
	}
	public void setAfterAction(String afterAction) {
		this.afterAction = afterAction;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
