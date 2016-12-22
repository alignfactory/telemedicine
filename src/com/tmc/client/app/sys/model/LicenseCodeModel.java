package com.tmc.client.app.sys.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class LicenseCodeModel extends AbstractDataModel {

	private Long	licenseCodeId;
	private String 	licenseCode;
	private String 	licenseName ;
	private String  issueOrgName;
	private Date	applyDate;
	private String 	closeYn;
	private String 	note;

	@Override
	public void setKeyId(Long id) {
		this.setLicenseCodeId(id);
	}
	
	@Override
	public Long getKeyId() {
		return getLicenseCodeId();
	}

	public Long getLicenseCodeId() {
		return licenseCodeId;
	}
	public void setLicenseCodeId(Long licenseCodeId) {
		this.licenseCodeId = licenseCodeId;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}
	public String getIssueOrgName() {
		return issueOrgName;
	}
	public void setIssueOrgName(String issueOrgName) {
		this.issueOrgName = issueOrgName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getCloseYn() {
		return this.closeYn;
	}
	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn ;  
	}

	public Boolean getCloseYnFlag() {
		return "true".equals(this.closeYn); 
	}
	public void setCloseYnFlag(Boolean closeYnFlag) {
		this.closeYn = closeYnFlag.toString();
	}
}
