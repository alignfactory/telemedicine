package com.tmc.client.app.pay.model;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class PayModel extends AbstractDataModel {
	private Long	payId ;  
	private Long 	paydayId;
	private Long 	userId;     
	private Double 	baseAmt; 
	private Double	hireAmt; 
	private Double	benefitAmt; 
	private	Double 	extraAmt;  
	private Double 	etcInAmt; 
	private Double 	incomeTax; 
	private Double 	ctzTax;  
	private Double 	healthIns; 
	private Double	privatePns; 
	private Double	unEmpIns;  
	private Double	nationPns; 
	private Double 	etcOutAmt;  
	private String 	closeYn;   	
	private String	note; 	
	
	private UserModel userModel = new UserModel();  
	
	@Override
	public void setKeyId(Long id) {
		this.setPayId(id);
	}
	@Override
	public Long getKeyId() {
		return getPayId();
	}
	public Long getPayId() {
		return payId;
	}
	public void setPayId(Long payId) {
		this.payId = payId;
	}
	public Long getPaydayId() {
		return paydayId;
	}
	public void setPaydayId(Long paydayId) {
		this.paydayId = paydayId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getBaseAmt() {
		return (baseAmt!=null)? baseAmt : Long.parseLong("0");
	}
	public void setBaseAmt(Double baseAmt) {
		this.baseAmt = baseAmt;
	}
	public Double getHireAmt() {
		return (hireAmt!=null)? hireAmt : Long.parseLong("0"); 
	}
	public void setHireAmt(Double hireAmt) {
		this.hireAmt = hireAmt;
	}
	public Double getBenefitAmt() {
		return (benefitAmt!=null)? benefitAmt : Long.parseLong("0");
	}
	public void setBenefitAmt(Double benefitAmt) {
		this.benefitAmt = benefitAmt;
	}
	public Double getExtraAmt() {
		return (extraAmt!=null)? extraAmt : Long.parseLong("0");
	}
	public void setExtraAmt(Double extraAmt) {
		this.extraAmt = extraAmt;
	}
	public Double getEtcInAmt() {
		return (etcInAmt!=null)? etcInAmt : Long.parseLong("0");
	}
	public void setEtcInAmt(Double etcInAmt) {
		this.etcInAmt = etcInAmt;
	}
	public Double getIncomeTax() {
		return (incomeTax!=null)? incomeTax : Long.parseLong("0");
	}
	public void setIncomeTax(Double incomeTax) {
		this.incomeTax = incomeTax;
	}
	public Double getCtzTax() {
		return (ctzTax!=null)? ctzTax : Long.parseLong("0");
	}
	public void setCtzTax(Double ctzTax) {
		this.ctzTax = ctzTax;
	}
	public Double getHealthIns() {
		return (healthIns!=null)? healthIns : Long.parseLong("0");
	}
	public void setHealthIns(Double healthIns) {
		this.healthIns = healthIns;
	}
	public Double getPrivatePns() {
		return (privatePns!=null)? privatePns : Long.parseLong("0");
	}
	public void setPrivatePns(Double privatePns) {
		this.privatePns = privatePns;
	}
	public Double getUnEmpIns() {
		return (unEmpIns!=null)? unEmpIns : Long.parseLong("0");
	}
	public void setUnEmpIns(Double unEmpIns) {
		this.unEmpIns = unEmpIns;
	}
	public Double getNationPns() {
		return (nationPns!=null)? nationPns : Long.parseLong("0");
	}
	public void setNationPns(Double nationPns) {
		this.nationPns = nationPns;
	}
	public Double getEtcOutAmt() {
		return (etcOutAmt!=null)? etcOutAmt : Long.parseLong("0");
	}
	public void setEtcOutAmt(Double etcOutAmt) {
		this.etcOutAmt = etcOutAmt;
	}
	public String getCloseYn() {
		return closeYn;
	}
	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Double sumPayAmt (){
		return getBaseAmt() + getHireAmt() + getBenefitAmt() + getExtraAmt() + getEtcInAmt() ;   
	}
	
	public Double sumDeduceAmt (){
		return getBaseAmt() + getHireAmt() + getBenefitAmt() + getExtraAmt() + getEtcInAmt() ;   
	}
	
	public Double sumRealPayAmt (){
		return sumPayAmt() + sumDeduceAmt();    
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
