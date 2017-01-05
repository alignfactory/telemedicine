package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class CheckupModel extends AbstractDataModel {

	private Long checkupId;
	
	private String checkupCode;
	private String checkupName;
	
	private String checkupResult;
	private Date checkupDate;
	private Date regDate;
	private Long regUserId;
	
	private String processCode;
	private String processName;
	
	private Long checkupUserId;
	private Long requestId;
	
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Long getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(Long regUserId) {
		this.regUserId = regUserId;
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

	public Long getCheckupUserId() {
		return checkupUserId;
	}

	public void setCheckupUserId(Long checkupUserId) {
		this.checkupUserId = checkupUserId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	
}