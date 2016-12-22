package com.tmc.server.com.data
;

public class ColumnModel {

	private String 	tableName; 
	private String 	columnName;
	private String 	dataType;
	private String 	nullable;
	private int 	pkYn; 
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public int getPkYn() {
		return pkYn;
	}
	public void setPkYn(int pkYn) {
		this.pkYn = pkYn;
	}
}
