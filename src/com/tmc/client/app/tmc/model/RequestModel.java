package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class RequestModel extends AbstractDataModel {

	private Long requestId;
	private Long patientId;
	
	private Long 	requestUserId;
	private String 	requestTypeCode;
	private Date 	requestDate;
	private String 	requestNote;

	private Date 	treatDate;
	private Long 	treatUserId;
	private String 	treatNote;
	private String 	treatStateCode; 
	private String 	treatStateName; 
	
	private Long 	regUserId;
	private Date 	regDate;
	private String	note; 
	private String 	resultNote; 
	
	private PatientModel patientModel = new PatientModel(); 
	private UserModel requestUserModel = new UserModel(); 
	private UserModel treatUserModel = new UserModel();
	private UserModel regUserModel = new UserModel();
	
	
	@Override
	public void setKeyId(Long id) {
		this.setRequestId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getRequestId();
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getRequestUserId() {
		return requestUserId;
	}

	public void setRequestUserId(Long requestUserId) {
		this.requestUserId = requestUserId;
	}

	public String getRequestTypeCode() {
		return requestTypeCode;
	}

	public void setRequestTypeCode(String requestTypeCode) {
		this.requestTypeCode = requestTypeCode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestNote() {
		return requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public Date getTreatDate() {
		return treatDate;
	}

	public void setTreatDate(Date treatDate) {
		this.treatDate = treatDate;
	}

	public Long getTreatUserId() {
		return treatUserId;
	}

	public void setTreatUserId(Long treatUserId) {
		this.treatUserId = treatUserId;
	}

	public String getTreatNote() {
		return treatNote;
	}

	public void setTreatNote(String treatNote) {
		this.treatNote = treatNote;
	}

	public Long getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(Long regUserId) {
		this.regUserId = regUserId;
	}

	public UserModel getRequestUserModel() {
		return requestUserModel;
	}

	public void setRequestUserModel(UserModel requestUserModel) {
		this.requestUserModel = requestUserModel;
	}

	public UserModel getTreatUserModel() {
		return treatUserModel;
	}

	public void setTreatUserModel(UserModel treatUserModel) {
		this.treatUserModel = treatUserModel;
	}

	public UserModel getRegUserModel() {
		return regUserModel;
	}

	public void setRegUserModel(UserModel regUserModel) {
		this.regUserModel = regUserModel;
	}

	public PatientModel getPatientModel() {
		return patientModel;
	}

	public void setPatientModel(PatientModel patientModel) {
		this.patientModel = patientModel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTreatStateCode() {
		return treatStateCode;
	}

	public void setTreatStateCode(String treatStateCode) {
		this.treatStateCode = treatStateCode;
	}

	public String getTreatStateName() {
		return treatStateName;
	}

	public void setTreatStateName(String treatStateName) {
		this.treatStateName = treatStateName;
	}

	public String getResultNote() {
		return resultNote;
	}

	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}
	
}

