package com.tmc.client.app.tmc.model;

import com.tmc.client.ui.AbstractDataModel;

public class StatsRetrieveModel extends AbstractDataModel {

	private Long companyId;
	private String companyName;
	private Long patientCount;
	private Long p10Count;
	private Long p20Count;
	private Long p30Count;
	private Long p40Count;
	private Long p50Count;
	private Long p60Count;
	
	@Override
	public void setKeyId(Long id) {
		this.setCompanyId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getCompanyId();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getPatientCount() {
		return patientCount;
	}

	public void setPatientCount(Long patientCount) {
		this.patientCount = patientCount;
	}

	public Long getP10Count() {
		return p10Count;
	}

	public void setP10Count(Long p10Count) {
		this.p10Count = p10Count;
	}

	public Long getP20Count() {
		return p20Count;
	}

	public void setP20Count(Long p20Count) {
		this.p20Count = p20Count;
	}

	public Long getP30Count() {
		return p30Count;
	}

	public void setP30Count(Long p30Count) {
		this.p30Count = p30Count;
	}

	public Long getP40Count() {
		return p40Count;
	}

	public void setP40Count(Long p40Count) {
		this.p40Count = p40Count;
	}

	public Long getP50Count() {
		return p50Count;
	}

	public void setP50Count(Long p50Count) {
		this.p50Count = p50Count;
	}

	public Long getP60Count() {
		return p60Count;
	}

	public void setP60Count(Long p60Count) {
		this.p60Count = p60Count;
	}
	
}