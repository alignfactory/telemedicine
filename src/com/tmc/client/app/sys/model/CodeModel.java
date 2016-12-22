package com.tmc.client.app.sys.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class CodeModel extends AbstractDataModel {
	private Long 	codeId;
	private Long	codeKindId;
	private String 	code ;
	private String 	name ;
	private Date 	applyDate ; 
	private String 	closeYn ;
	private Boolean closeYnFlag ;
	private String 	seq; 
	private String 	note;
	
	@Override
	public void setKeyId(Long id) {
		this.setCodeId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getCodeId(); 
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public Long getCodeKindId() {
		return codeKindId;
	}

	public void setCodeKindId(Long codeKindId) {
		this.codeKindId = codeKindId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getCloseYn() {
		return closeYn;
	}

	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}

	public boolean getCloseYnFlag() {
		this.closeYnFlag = "true".equals(this.closeYn);
		return closeYnFlag; 
	}

	public void setCloseYnFlag(Boolean closeYnFlag) {
		this.closeYnFlag = closeYnFlag;
		this.closeYn = closeYnFlag.toString();  
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
