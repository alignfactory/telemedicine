package com.tmc.client.app.emp.model;

import java.util.Date;

import com.tmc.client.app.sys.model.LicenseCodeModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class LicenseModel extends AbstractDataModel {
	private Long 	licenseId;
	private Long	userId;
	private String 	licenseCode ;
	private Date	issueDate ;
	private Date 	expirationDate ;
	private String 	note;
	
	private UserModel userModel = new UserModel(); 
	private LicenseCodeModel licenseCodeModel = new LicenseCodeModel();
	
	@Override
	public void setKeyId(Long id) {
		this.setLicenseId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getLicenseId(); 
	}

	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public LicenseCodeModel getLicenseCodeModel() {
		return licenseCodeModel;
	}

	public void setLicenseCodeModel(LicenseCodeModel licenseCodeModel) {
		this.licenseCodeModel = licenseCodeModel;
	}
	
	public String getFindCell(){
		return null; 
	}
	
	public void setFindCell(String cell){
	}

}
