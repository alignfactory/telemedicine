package com.tmc.client.app.sys.model;

import java.util.List;

import com.tmc.client.ui.AbstractDataModel;

public class MenuModel extends AbstractDataModel {
	private Long 	menuId;
	private Long 	parentId ;
	private String 	useYn;
	private String 	menuName; 
	private String 	className;
	private String 	seq;
	private String 	note;
	
	private List<AbstractDataModel> childList;
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public MenuModel(){
	}
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public List<AbstractDataModel> getChildList() {
		return childList;
	}
	
	public void setChildList(List<AbstractDataModel> childList) {
		this.childList = childList;
	}

	@Override
	public void setKeyId(Long id) {
		this.setMenuId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getMenuId();
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Boolean getUseYnFlag() {
		return "true".equals(useYn) ; 
	} 
	public void setUseYnFlag(Boolean useYnFlag) {
		this.useYn = useYnFlag.toString();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
