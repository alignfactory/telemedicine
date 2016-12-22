package com.tmc.client.app.sys.model;

import com.tmc.client.ui.AbstractDataModel;

public class UserCompanyModel extends AbstractDataModel {

	private Long userCompanyId;
	private Long userId;
	private Long companyId;
	private String note; 
	private CompanyModel companyModel = new CompanyModel(); 
	
	@Override
	public void setKeyId(Long id) {
		this.setUserCompanyId(id);;
	}
	@Override
	public Long getKeyId() {
		return this.getUserCompanyId();
	}
	public Long getUserCompanyId() {
		return userCompanyId;
	}
	public void setUserCompanyId(Long userCompanyId) {
		this.userCompanyId = userCompanyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
}
