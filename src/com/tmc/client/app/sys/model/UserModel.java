package com.tmc.client.app.sys.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class UserModel extends AbstractDataModel {

	private Long userId ;
	private Long companyId;
	private String korName; 
	private String ctzNo; 
	private String engName; 
	private String chnName; 
	private String genderCode; 
	private String genderName;
	private String nationCode; 
	private String nationName;
	private Date birthday; 
	private String email; 
	private String telNo01; 
	private String telNo02; 
	private String faxNo; 
	private String zipCode; 
	private String zipAddress; 
	private String zipDetail; 
	private String schoolName; 
	private String graduateYear;
	private String mainMajor;
	private String subMajor;
	private String militaryTypeCode;
	private String militaryTypeName;
	private String scholar;
	
	private String loginYn; 
	
	private String career; 
	private String loginId; 
	private String passwd; 
	private String managerYn;
	private String closeYn;
	private String note;
	private String adminYn; 
	
	private String bankCode; 
	private String bankName;
	private String accountNo; 
	private String accountHolder; 
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public String getAdminYn() {
		return adminYn;
	}

	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}

	public UserModel(){
	}

	@Override
	public void setKeyId(Long id) {
		this.setUserId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getUserId();
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
	public String getKorName() {
		return korName;
	}
	public void setKorName(String korName) {
		this.korName = korName;
	}
	public String getCtzNo() {
		return ctzNo;
	}
	public void setCtzNo(String ctzNo) {
		this.ctzNo = ctzNo;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getChnName() {
		return chnName;
	}
	public void setChnName(String chnName) {
		this.chnName = chnName;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	public String getNationName() {
		return nationName;
	}
	public void setNationName(String nationName) {
		this.nationName = nationName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
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
	public String getScholar() {
		return scholar;
	}
	public void setScholar(String scholar) {
		this.scholar = scholar;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getManagerYn() {
		return managerYn;
	}
	public void setManagerYn(String managerYn) {
		this.managerYn = managerYn;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getCloseYn() {
		return closeYn;
	}

	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}
	
	public Boolean getCloseYnBoolean() {
		return "true".equals(this.closeYn); 
	}
	
	public void setCloseYnBoolean(Boolean closeYnBoolean) {
		this.closeYn = closeYnBoolean.toString(); 
	}
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	public String getMainMajor() {
		return mainMajor;
	}

	public void setMainMajor(String mainMajor) {
		this.mainMajor = mainMajor;
	}

	public String getSubMajor() {
		return subMajor;
	}

	public void setSubMajor(String subMajor) {
		this.subMajor = subMajor;
	}

	public String getMilitaryTypeCode() {
		return militaryTypeCode;
	}

	public void setMilitaryTypeCode(String militaryTypeCode) {
		this.militaryTypeCode = militaryTypeCode;
	}

	public String getMilitaryTypeName() {
		return militaryTypeName;
	}

	public void setMilitaryTypeName(String militaryTypeName) {
		this.militaryTypeName = militaryTypeName;
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

	public String getLoginYn() {
		return loginYn;
	}

	public void setLoginYn(String loginYn) {
		this.loginYn = loginYn;
	}

}

