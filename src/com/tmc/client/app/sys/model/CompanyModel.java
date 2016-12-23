package com.tmc.client.app.sys.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class CompanyModel extends AbstractDataModel { 

	private Long 	companyId ; 	
	private String 	companyName ;
	private String 	companyTypeCode ;
	private String 	companyTypeName ;
	private String 	bizNo ;
	private String 	telNo01 ;
	private String 	telNo02 ;
	private String 	faxNo01 ;
	private String 	zipCode ;
	private String 	zipAddress ;
	private String 	zipDetail ;
	private String 	locationName ;
	private Date 	annvDate ;
	private String 	ceoPersonId ;
	private String 	managerPersonId ;
	private String 	note ;
	
	@Override
	public void setKeyId(Long id) {
		this.companyId = id;
	}

	@Override
	public Long getKeyId() {
		return this.getCompanyId(); 
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyTypeCode() {
		return companyTypeCode;
	}

	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}
	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getTelNo01() {
		return telNo01;
	}

	public void setTelNo01(String telNo01) {
		this.telNo01 = telNo01;
	}

	public String getTelNo02() {
		return telNo02;
	}

	public void setTelNo02(String telNo02) {
		this.telNo02 = telNo02;
	}

	public String getFaxNo01() {
		return faxNo01;
	}

	public void setFaxNo01(String faxNo01) {
		this.faxNo01 = faxNo01;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipAddress() {
		return zipAddress;
	}

	public void setZipAddress(String zipAddress) {
		this.zipAddress = zipAddress;
	}

	public String getZipDetail() {
		return zipDetail;
	}

	public void setZipDetail(String zipDetail) {
		this.zipDetail = zipDetail;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Date getAnnvDate() {
		return annvDate;
	}

	public void setAnnvDate(Date annvDate) {
		this.annvDate = annvDate;
	}

	public String getCeoPersonId() {
		return ceoPersonId;
	}

	public void setCeoPersonId(String ceoPersonId) {
		this.ceoPersonId = ceoPersonId;
	}

	public String getManagerPersonId() {
		return managerPersonId;
	}

	public void setManagerPersonId(String managerPersonId) {
		this.managerPersonId = managerPersonId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	} 
}
