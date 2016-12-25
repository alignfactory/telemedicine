package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class PatientModel extends AbstractDataModel {

	private Long 	patientId;
	private String	korName;
	private String zipCode;
	private String address;
	private String tel1;
	private String tel2;
	private String guardianName;
	private String guardianTel1;
	private String guardianTel2;
	private String guardianRelationName;
	private String viewPoint;
	private Date 	birthday;
	private String genderCode;
	private String genderName;
	private String insNo;
	private Long 	companyId;
	private String note;
	
	private CompanyModel companyModel = new CompanyModel(); 

	@Override
	public void setKeyId(Long id) {
		this.setPatientId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getPatientId();
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getKorName() {
		return korName;
	}

	public void setKorName(String korName) {
		this.korName = korName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianTel1() {
		return guardianTel1;
	}

	public void setGuardianTel1(String guardianTel1) {
		this.guardianTel1 = guardianTel1;
	}

	public String getGuardianTel2() {
		return guardianTel2;
	}

	public void setGuardianTel2(String guardianTel2) {
		this.guardianTel2 = guardianTel2;
	}

	public String getGuardianRelationName() {
		return guardianRelationName;
	}

	public void setGuardianRelationName(String guardianRelationName) {
		this.guardianRelationName = guardianRelationName;
	}

	public String getViewPoint() {
		return viewPoint;
	}

	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getInsNo() {
		return insNo;
	}

	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CompanyModel getCompanyModel() {
		return companyModel;
	}

	public void setCompanyModel(CompanyModel companyModel) {
		this.companyModel = companyModel;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

}

