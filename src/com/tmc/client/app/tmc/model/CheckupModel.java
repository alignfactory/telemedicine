package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class CheckupModel extends AbstractDataModel {

	private Long checkupId;
	private Long requestId;
	
	private String checkupCode;
	private String checkupName;
	
	private String checkupOrder;
	
	private String processCode;
	private String processName;
	
	private String checkupResult;
	private Date checkupDate;
	private Long checkupUserId;
	
	private UserModel userModel = new UserModel(); 
	
	@Override
	public void setKeyId(Long id) {
		this.setCheckupId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getCheckupId();
	}

	public Long getCheckupId() {
		return checkupId;
	}

	public void setCheckupId(Long checkupId) {
		this.checkupId = checkupId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getCheckupCode() {
		return checkupCode;
	}

	public void setCheckupCode(String checkupCode) {
		this.checkupCode = checkupCode;
	}

	public String getCheckupName() {
		return checkupName;
	}

	public void setCheckupName(String checkupName) {
		this.checkupName = checkupName;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getCheckupResult() {
		return checkupResult;
	}

	public void setCheckupResult(String checkupResult) {
		this.checkupResult = checkupResult;
	}

	public Date getCheckupDate() {
		return checkupDate;
	}

	public void setCheckupDate(Date checkupDate) {
		this.checkupDate = checkupDate;
	}

	public Long getCheckupUserId() {
		return checkupUserId;
	}

	public void setCheckupUserId(Long checkupUserId) {
		this.checkupUserId = checkupUserId;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getCheckupOrder() {
		return checkupOrder;
	}

	public void setCheckupOrder(String checkupOrder) {
		this.checkupOrder = checkupOrder;
	}

	
	
}