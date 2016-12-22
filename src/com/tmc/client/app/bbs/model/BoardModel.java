package com.tmc.client.app.bbs.model;

import java.util.Date;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.AbstractDataModel;

public class BoardModel extends AbstractDataModel {
	private Long boardId; 
	private Long companyId; 
	private String boardTypeCode; 
	private String boardTypeName; 
	private String title; 
	private String content; 
	private Long writeUserId; 
	private Date writeDate; 
	private String note; 
	private UserModel userModel = new UserModel(); 
	
	@Override
	public void setKeyId(Long id) {
		this.setBoardId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getBoardId(); 
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getBoardTypeCode() {
		return boardTypeCode;
	}
	public void setBoardTypeCode(String boardTypeCode) {
		this.boardTypeCode = boardTypeCode;
	}
	public String getBoardTypeName() {
		return boardTypeName;
	}
	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getWriteUserId() {
		return writeUserId;
	}
	public void setWriteUserId(Long writeUserId) {
		this.writeUserId = writeUserId;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}


}
