package com.tmc.client.app.emp.model;

import java.util.Date;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class HireModel extends AbstractDataModel {
	private Long 	hireId;
	private Long	userId;
	private Date	hireDate ;
	private String 	hireCode ;
	private String	hireName;
	private String	hireReason;
	private Date 	retireDate ;
	private String	retireCode;
	private String	retireName;
	private String 	retireReason; 
	private String 	note;
	
	private UserModel userModel = new UserModel(); 
	
	public Long getHireId() {
		return hireId;
	}

	public void setHireId(Long hireId) {
		this.hireId = hireId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getHireCode() {
		return hireCode;
	}

	public void setHireCode(String hireCode) {
		this.hireCode = hireCode;
	}

	public String getHireName() {
		return hireName;
	}

	public void setHireName(String hireName) {
		this.hireName = hireName;
	}

	public String getHireReason() {
		return hireReason;
	}

	public void setHireReason(String hireReason) {
		this.hireReason = hireReason;
	}

	public Date getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}

	public String getRetireCode() {
		return retireCode;
	}

	public void setRetireCode(String retireCode) {
		this.retireCode = retireCode;
	}

	public String getRetireName() {
		return retireName;
	}

	public void setRetireName(String retireName) {
		this.retireName = retireName;
	}

	public String getRetireReason() {
		return retireReason;
	}

	public void setRetireReason(String retireReason) {
		this.retireReason = retireReason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public void setKeyId(Long id) {
		this.setHireId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getHireId(); 
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	}
