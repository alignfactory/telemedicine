package com.tmc.client.app.sys.model;

import com.tmc.client.ui.AbstractDataModel;

public class UserRoleModel extends AbstractDataModel {

	private Long userRoleId;
	private Long userId;
	private Long roleId;
	private String seq;
	private String note; 
	
	private RoleModel roleModel = new RoleModel(); 
	
	@Override
	public void setKeyId(Long id) {
		this.setUserRoleId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getUserRoleId();
	}
	
	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

}
