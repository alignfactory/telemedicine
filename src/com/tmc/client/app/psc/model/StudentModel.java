package com.tmc.client.app.psc.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class StudentModel extends AbstractDataModel {
	private Long  studentId ; 
	private Long companyId; 
	private String studentNo; 
	private String korName; 
	private String engName; 
	private String chnName;
	private String ctzNo;
	private Date birthday;
	private String genderCode;
	private String genderName;
	private String zipCode;
	private String zipAddress;
	private String detailAddress;
	private String tel01;
	private String tel02;
	private String email;
	private String career;
	private String childNote;
	private String character;
	private String speciality;
	private String habit;
	private String likeFood;
	private String hateFood;
	private String choiceSchool;
	private String dadName;
	private Date dadBirthday;
	private String dadJob;
	private String dadWorkplace;
	private String dadTel01;
	private String dadTel02;
	private String dadScholar;
	private String dadReligion;
	private String momName;
	private Date momBirthday;
	private String momJob;
	private String momWorkplace;
	private String momTel01;
	private String momTel02;
	private String momScholar;
	private String momReligion;
	private String grandFatherYn;
	private String grandMotherYn;
	private Long 	upBrotherCount;
	private Long 	downBrotherCount;
	private Long 	upSisterCount;
	private Long 	downSisterCount;
	private String familyNote;
	private String supportYn;
	private Long supportAmount;
	private String bankCode;
	private String bankName;
	private String accountNo;
	private String accountHolder;
	private String evidenceYn;
	private String note;
	
	private ClassStudentModel classStudentModel = new ClassStudentModel(); 
	
	@Override
	public void setKeyId(Long id) {
		this.setStudentId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getStudentId(); 
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getKorName() {
		return korName;
	}
	public void setKorName(String korName) {
		this.korName = korName;
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
	public String getCtzNo() {
		return ctzNo;
	}
	public void setCtzNo(String ctzNo) {
		this.ctzNo = ctzNo;
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
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getTel01() {
		return tel01;
	}
	public void setTel01(String tel01) {
		this.tel01 = tel01;
	}
	public String getTel02() {
		return tel02;
	}
	public void setTel02(String tel02) {
		this.tel02 = tel02;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getChildNote() {
		return childNote;
	}
	public void setChildNote(String childNote) {
		this.childNote = childNote;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHabit() {
		return habit;
	}
	public void setHabit(String habit) {
		this.habit = habit;
	}
	public String getLikeFood() {
		return likeFood;
	}
	public void setLikeFood(String likeFood) {
		this.likeFood = likeFood;
	}
	public String getHateFood() {
		return hateFood;
	}
	public void setHateFood(String hateFood) {
		this.hateFood = hateFood;
	}
	public String getChoiceSchool() {
		return choiceSchool;
	}
	public void setChoiceSchool(String choiceSchool) {
		this.choiceSchool = choiceSchool;
	}
	public String getDadName() {
		return dadName;
	}
	public void setDadName(String dadName) {
		this.dadName = dadName;
	}
	public Date getDadBirthday() {
		return dadBirthday;
	}
	public void setDadBirthday(Date dadBirthday) {
		this.dadBirthday = dadBirthday;
	}
	public String getDadJob() {
		return dadJob;
	}
	public void setDadJob(String dadJob) {
		this.dadJob = dadJob;
	}
	public String getDadWorkplace() {
		return dadWorkplace;
	}
	public void setDadWorkplace(String dadWorkplace) {
		this.dadWorkplace = dadWorkplace;
	}
	public String getDadTel01() {
		return dadTel01;
	}
	public void setDadTel01(String dadTel01) {
		this.dadTel01 = dadTel01;
	}
	public String getDadTel02() {
		return dadTel02;
	}
	public void setDadTel02(String dadTel02) {
		this.dadTel02 = dadTel02;
	}
	public String getDadScholar() {
		return dadScholar;
	}
	public void setDadScholar(String dadScholar) {
		this.dadScholar = dadScholar;
	}
	public String getDadReligion() {
		return dadReligion;
	}
	public void setDadReligion(String dadReligion) {
		this.dadReligion = dadReligion;
	}
	public String getMomName() {
		return momName;
	}
	public void setMomName(String momName) {
		this.momName = momName;
	}
	public Date getMomBirthday() {
		return momBirthday;
	}
	public void setMomBirthday(Date momBirthday) {
		this.momBirthday = momBirthday;
	}
	public String getMomJob() {
		return momJob;
	}
	public void setMomJob(String momJob) {
		this.momJob = momJob;
	}
	public String getMomWorkplace() {
		return momWorkplace;
	}
	public void setMomWorkplace(String momWorkplace) {
		this.momWorkplace = momWorkplace;
	}
	public String getMomTel01() {
		return momTel01;
	}
	public void setMomTel01(String momTel01) {
		this.momTel01 = momTel01;
	}
	public String getMomTel02() {
		return momTel02;
	}
	public void setMomTel02(String momTel02) {
		this.momTel02 = momTel02;
	}
	public String getMomScholar() {
		return momScholar;
	}
	public void setMomScholar(String momScholar) {
		this.momScholar = momScholar;
	}
	public String getMomReligion() {
		return momReligion;
	}
	public void setMomReligion(String momReligion) {
		this.momReligion = momReligion;
	}
	public String getGrandFatherYn() {
		return grandFatherYn;
	}
	public void setGrandFatherYn(String grandFatherYn) {
		this.grandFatherYn = grandFatherYn;
	}
	public String getGrandMotherYn() {
		return grandMotherYn;
	}
	public void setGrandMotherYn(String grandMotherYn) {
		this.grandMotherYn = grandMotherYn;
	}
	public Long getUpBrotherCount() {
		return upBrotherCount;
	}
	public void setUpBrotherCount(Long upBrotherCount) {
		this.upBrotherCount = upBrotherCount;
	}
	public Long getDownBrotherCount() {
		return downBrotherCount;
	}
	public void setDownBrotherCount(Long downBrotherCount) {
		this.downBrotherCount = downBrotherCount;
	}
	public Long getUpSisterCount() {
		return upSisterCount;
	}
	public void setUpSisterCount(Long upSisterCount) {
		this.upSisterCount = upSisterCount;
	}
	public Long getDownSisterCount() {
		return downSisterCount;
	}
	public void setDownSisterCount(Long downSisterCount) {
		this.downSisterCount = downSisterCount;
	}
	public String getFamilyNote() {
		return familyNote;
	}
	public void setFamilyNote(String familyNote) {
		this.familyNote = familyNote;
	}
	public String getSupportYn() {
		return supportYn;
	}
	public void setSupportYn(String supportYn) {
		this.supportYn = supportYn;
	}
	public Long getSupportAmount() {
		return supportAmount;
	}
	public void setSupportAmount(Long supportAmount) {
		this.supportAmount = supportAmount;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
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
	public String getEvidenceYn() {
		return evidenceYn;
	}
	public void setEvidenceYn(String evidenceYn) {
		this.evidenceYn = evidenceYn;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public ClassStudentModel getClassStudentModel() {
		return classStudentModel;
	}
	public void setClassStudentModel(ClassStudentModel classStudentModel) {
		this.classStudentModel = classStudentModel;
	}

}
