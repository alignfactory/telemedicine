package com.tmc.client.app.acc.model;

import com.tmc.client.ui.AbstractDataModel;

public class AccountModel extends AbstractDataModel {

	private Long accountId ;
	private String eduOfficeCode;
	private String eduOfficeName ;
	private Long companyId ;
	private String gwonCode ;
	private String gwonName ;
	private String hangCode ;
	private String hangName ;
	private String gmokCode ;
	private String gmokName ;
	private String smokCode ;
	private String smokName ;
	private String printName ;
	private String sumYn ;
	private String transYn ;
	
	private String inExpCode ;
	private String inExpName ;
	
	private String resolutionYn ;
	
	private String budgetYn ;
	private String useYn ;
	private String note ;
	
	@Override
	public void setKeyId(Long id) {
		this.setAccountId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getAccountId(); 
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getEduOfficeCode() {
		return eduOfficeCode;
	}
	public void setEduOfficeCode(String eduOfficeCode) {
		this.eduOfficeCode = eduOfficeCode;
	}
	public String getEduOfficeName() {
		return eduOfficeName;
	}
	public void setEduOfficeName(String eduOfficeName) {
		this.eduOfficeName = eduOfficeName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getGwonCode() {
		return gwonCode;
	}
	public void setGwonCode(String gwonCode) {
		this.gwonCode = gwonCode;
	}
	public String getGwonName() {
		return gwonName;
	}
	public void setGwonName(String gwonName) {
		this.gwonName = gwonName;
	}
	public String getHangCode() {
		return hangCode;
	}
	public void setHangCode(String hangCode) {
		this.hangCode = hangCode;
	}
	public String getHangName() {
		return hangName;
	}
	public void setHangName(String hangName) {
		this.hangName = hangName;
	}
	public String getGmokCode() {
		return gmokCode;
	}
	public void setGmokCode(String gmokCode) {
		this.gmokCode = gmokCode;
	}
	public String getGmokName() {
		return gmokName;
	}
	public void setGmokName(String gmokName) {
		this.gmokName = gmokName;
	}
	public String getSmokCode() {
		return smokCode;
	}
	public void setSmokCode(String smokCode) {
		this.smokCode = smokCode;
	}
	public String getSmokName() {
		return smokName;
	}
	public void setSmokName(String smokName) {
		this.smokName = smokName;
	}
	public String getPrintName() {
		return printName;
	}
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	public String getSumYn() {
		return sumYn;
	}
	public void setSumYn(String sumYn) {
		this.sumYn = sumYn;
	}
	public Boolean getSumYnFlag(){
		return "true".equals(this.sumYn); 
	}
	public void setSumYnFlag(Boolean sumYnFlag){
		this.setSumYn(sumYnFlag.toString()); 
	}
	
	public String getTransYn() {
		return transYn;
	}
	public void setTransYn(String transYn) {
		this.transYn = transYn;
	}
	public Boolean getTransYnFlag(){
		return "true".equals(this.transYn); 
	}
	public void setTransYnFlag(Boolean transYnFlag){
		this.setTransYn(transYnFlag.toString()); 
	}

	public String getBudgetYn() {
		return budgetYn;
	}
	public void setBudgetYn(String budgetYn) {
		this.budgetYn = budgetYn;
	}

	public Boolean getBudgetYnFlag() {
		return "true".equals(this.budgetYn);
	}
	public void setBudgetYnFlag(Boolean budgetYn) {
		this.setBudgetYn(budgetYn.toString()); 
	}
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Boolean getUseYnFlag() {
		return "true".equals(this.useYn);
	}
	public void setUseYnFlag(Boolean useYn) {
		this.setUseYn(useYn.toString()); 
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInExpCode() {
		return inExpCode;
	}
	public void setInExpCode(String inExpCode) {
		this.inExpCode = inExpCode;
	}
	public String getInExpName() {
		return inExpName;
	}
	public void setInExpName(String inExpName) {
		this.inExpName = inExpName;
	}

	public String getAccountType(){
		
		if(this.getCompanyId()==0){
			return "공통";  
		}
		else {
			return "개별"; 
		}
	}
	
	public String getResolutionYn() {
		return resolutionYn;
	}
	public void setResolutionYn(String resolutionYn) {
		this.resolutionYn = resolutionYn;
	}
	
	public Boolean getResolutionYnFlag() {
		return "true".equals(this.resolutionYn);
	}
	public void setResolutionYn(Boolean resolutionYnFlag) {
		this.resolutionYn = resolutionYnFlag.toString();
	}
	
}
