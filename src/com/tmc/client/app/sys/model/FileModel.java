package com.tmc.client.app.sys.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class FileModel extends AbstractDataModel  {

	private Long fileId; 
	private Long parentId; 
	private String fileName; 
	private Date regDate = new Date(); 
	private String serverPath; 
	private Double size;
	private Date delDate; 
	private String note;
	
	@Override
	public void setKeyId(Long id) {
		this.setFileId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getFileId(); 
	} 

	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	public String getDownloadCell(){
		return "downloadCell"; 
	}
	
	public String getDeleteCell(){
		return "deleteCell"; 
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
}
