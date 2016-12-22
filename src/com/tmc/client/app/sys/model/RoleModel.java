package com.tmc.client.app.sys.model;

import com.tmc.client.ui.AbstractDataModel;

public class RoleModel extends AbstractDataModel {
	private Long 	roleId;
	private String 	roleName ;
	private String 	seq; 
	private String 	managerYn; 
	private Boolean managerYnBoolean;
	private String 	note;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	@Override
	public void setKeyId(Long id) {
		this.setRoleId(id);
	}
	@Override
	public Long getKeyId() {
		return this.getRoleId(); 
	}
	public String getManagerYn() {
		return managerYn;
	}
	public void setManagerYn(String managerYn) {
		this.managerYn = managerYn;
		this.managerYnBoolean = "true".equals(managerYn); 
	}
	public Boolean getManagerYnBoolean() {
		return managerYnBoolean;
	}
	public void setManagerYnBoolean(Boolean managerYnBoolean) {
		this.managerYnBoolean = managerYnBoolean;
		this.managerYn = managerYnBoolean.toString(); 
	}
	
}
