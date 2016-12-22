package com.tmc.client.app.acc.model;

import java.util.Date;

import com.tmc.client.ui.AbstractDataModel;

public class SeasonModel extends AbstractDataModel {
	
	private Long seasonId; 
	private Long companyId;
	private String eduOfficeCode; 
	private String eduOfficeName; 
	private String seasonName; 
	private Date startDate; 
	private Date closeDate; 
	private String seq; 
	private String note; 

	
	@Override
	public void setKeyId(Long id) {
		this.setSeasonId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getSeasonId(); 
	}
	public Long getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
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
	public String getEduOfficeCode() {
		return eduOfficeCode;
	}
	public void setEduOfficeCode(String eduOfficeCode) {
		this.eduOfficeCode = eduOfficeCode;
	}
	public String getEduOfficeName() {
		return eduOfficeName;
	}
	public void setEduOfficeName(String eduOfficeName) {
		this.eduOfficeName = eduOfficeName;
	}

	
	
	
}
