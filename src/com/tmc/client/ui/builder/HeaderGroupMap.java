package com.tmc.client.ui.builder;

import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;

public class HeaderGroupMap {

	private int row; 
	private int col; 
	private HeaderGroupConfig config; 
	
	public HeaderGroupMap(int row, int col, HeaderGroupConfig config){
		this.setRow(row);
		this.setCol(col); 
		this.setConfig(config); 
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public HeaderGroupConfig getConfig() {
		return config;
	}
	public void setConfig(HeaderGroupConfig config) {
		this.config = config;
	}
}
