package com.tmc.client.app.pay.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class PaydayModel extends AbstractDataModel {
	private Long	paydayId;
	private Long	companyId; 
	private Date	payDate;
	private String 	payTypeCode;
	private String  payTypeName;
	private String 	payName ; 
	private Date 	accountDate ;
	private Date 	onDate ;
	private Date	offDate ;
	private String 	note;	
	
	@Override
	public void setKeyId(Long id) {
		this.setPaydayId(id);
	}
	@Override
	public Long getKeyId() {
		return getPaydayId();
	}
	public Long getPaydayId() {
		return paydayId;
	}
	public void setPaydayId(Long paydayId) {
		this.paydayId = paydayId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public Date getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}
	public Date getOnDate() {
		return onDate;
	}
	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}
	public Date getOffDate() {
		return offDate;
	}
	public void setOffDate(Date offDate) {
		this.offDate = offDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getIconButton() {
		return "iconButton";
	}
	public void setIconButton(Object o) {
	}
	
	public String  getActionCell(){
		return "생성" ;
	}
	public void setActionCell(String s){
	}
	
	public String getCloseActionCell(){
		return "마감"; 
	}
	
	public void setCloseActionCell(String actionCell){
	}
	
}
