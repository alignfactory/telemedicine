package com.tmc.client.app.acc.model;

import com.tmc.client.ui.AbstractDataModel;

public class ClientModel extends AbstractDataModel {
	
	private Long clientId;
	private Long companyId;
	private String bizNo;
	private String clientName;
	private String printName;
	private String ceoName;
	private String ctzNo;
	private String tel1;
	private String tel2;
	private String fax1;
	private String accountNo;
	private String bankCode;
	private String bankName;
	private String accountOwner;
	private String zipCode;
	private String zipAddress;
	private String zipDetail;
	private String empName;
	private String empTel1;
	private String enpTel2;
	private String empEmail;
	private String useYn;
	private String note;
	private String industry;
	private String bizType;
	
	
	
	@Override
	public void setKeyId(Long id) {
		this.setClientId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getClientId(); 
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getCtzNo() {
		return ctzNo;
	}
	public void setCtzNo(String ctzNo) {
		this.ctzNo = ctzNo;
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
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpTel1() {
		return empTel1;
	}
	public void setEmpTel1(String empTel1) {
		this.empTel1 = empTel1;
	}
	public String getEnpTel2() {
		return enpTel2;
	}
	public void setEnpTel2(String enpTel2) {
		this.enpTel2 = enpTel2;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Boolean getUseYnBoolean() {
		return "true".equals(useYn) ;
	}
	public void setUseYnBoolean(Boolean useYnBoolean) {
		this.useYn = useYnBoolean.toString();
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	
}
