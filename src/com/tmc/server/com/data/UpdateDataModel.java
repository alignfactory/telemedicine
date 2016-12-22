package com.tmc.server.com.data;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.SqlSession;

import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;

public class UpdateDataModel<T extends AbstractDataModel> {
	
	public UpdateDataModel(){
	}
	
	private Map<String, Object> getDataMap(AbstractDataModel dataModel) { 
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for(java.lang.reflect.Field field:dataModel.getClass().getDeclaredFields()){
			field.setAccessible(true); 
			try {
				dataMap.put(field.getName(), field.get(dataModel));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataMap; 
	}
	
	// MSSQL column info 
	public void updateModel(SqlSession sqlSession, List<AbstractDataModel> list, String tableName, ServiceResult result ) {

		Map<String, String> resultMap = new HashMap<String, String>();

		// MS-SQL Column list 
		List<ColumnModel> columnList = sqlSession.selectList("dbConfig.getColumnListMSSQL", tableName.toUpperCase());

		List<AbstractDataModel> updateList = new ArrayList<AbstractDataModel>(); 
		for(ResultMapping mapping : sqlSession.getConfiguration().getResultMap(tableName + ".mapper").getResultMappings()){
			if(mapping.getColumn() != null){
				// System.out.println(mapping.getColumn().toUpperCase());  
				resultMap.put(mapping.getColumn().toUpperCase(), mapping.getProperty());
			}
		}
		
		for(AbstractDataModel dataModel: list){
			IsNewData isNewData = new IsNewData(); // 신규 입력인지 변경인지를 확인한다. 
			try {  
				if(isNewData.isNewData(sqlSession, tableName, dataModel.getKeyId())) { 
					String insertSql = getInsertSql(dataModel, tableName, resultMap, columnList);
					System.out.println(insertSql);
					SqlRunner runner = new SqlRunner(sqlSession.getConnection());
					runner.insert(insertSql);
				}
				else {
					String updateSql = getUpdateSql(dataModel, tableName, resultMap, columnList); 
					System.out.println(updateSql);
					SqlRunner runner = new SqlRunner(sqlSession.getConnection());
					runner.update(updateSql);
				}					
			} 
			catch (SQLException e) {
				result.fail(-1, "update fail : \n" + e.getMessage());
			    result.setMessage("update fail");
				e.printStackTrace();
			}
			
			T updateModel = sqlSession.selectOne(tableName + ".selectById", dataModel.getKeyId());
			updateList.add(updateModel) ; 
		}
		
		result.setRetrieveResult(1, "update success!", updateList); 
	}
	
	
	private String getDataString(Object data){
		String dataString; 
		if(data !=  null){
			if(data.getClass().getTypeName().indexOf("Date") > 0) {
				Date date = (Date)data;
				
				// oracle 
				// dataString = "to_date('" +  new SimpleDateFormat("yyyyMMdd").format(date) + "', 'yyyymmdd')";
				
				// MSSQL
				dataString = "convert(date, '" +  new SimpleDateFormat("yyyyMMdd").format(date) + "')";
						
			}
			else if(data.getClass().getTypeName().indexOf("String") > 0) {
				dataString = "'" + data.toString() + "'"; 
			}
			else {
				dataString = data.toString();
			}
		}
		else {
			dataString = "null"; 
		}
		return dataString + "\n" ; 
	}
	
	private String getUpdateSql(AbstractDataModel updateModel, String tableName, Map<String, String> resultMap, List<ColumnModel> columnList){

		String keyColumnName = tableName.toUpperCase() + "_ID" ; // Primary Key 칼럼은 반드시 table_name + _id 이어야 한다.
		
		SqlBuilder.RESET();
		SqlBuilder.BEGIN();
		SqlBuilder.UPDATE(tableName); 
		
		Map<String, Object> dataMap = getDataMap(updateModel);
		
		for(ColumnModel columnModel: columnList){
			String propertyName = resultMap.get(columnModel.getColumnName());
			if(propertyName != null){
				String dataString = this.getDataString(dataMap.get(propertyName)); 
				if(columnModel.getColumnName().equals(keyColumnName)) { 
					SqlBuilder.WHERE(columnModel.getColumnName() + " = " +  updateModel.getKeyId()); 
				}
				else {
					SqlBuilder.SET(columnModel.getColumnName() + " = " +  dataString); 
				}
			}
		}
		return SqlBuilder.SQL(); 
	}
	
	private String getInsertSql(AbstractDataModel insertModel, String tableName, Map<String, String> resultMap, List<ColumnModel> columnList){
		
		SqlBuilder.RESET();
		SqlBuilder.BEGIN();
		SqlBuilder.INSERT_INTO(tableName); 

		Map<String, Object>  dataMap = getDataMap(insertModel);
		
		for(ColumnModel columnModel: columnList){
			
			String propertyName = resultMap.get(columnModel.getColumnName());
			if(propertyName != null){
				String dataString = this.getDataString(dataMap.get(propertyName));
				SqlBuilder.VALUES(columnModel.getColumnName() + "\n", dataString); // insert data setting
			}
		}
		return SqlBuilder.SQL();
	}

	public void deleteModel(SqlSession sqlSession, List<AbstractDataModel> list, String tableName, ServiceResult result ){

		for(AbstractDataModel dataModel: list){
			
			SqlBuilder.RESET();
			SqlBuilder.BEGIN();
			SqlBuilder.DELETE_FROM(tableName); 
			SqlBuilder.WHERE(tableName + "_id = " +  dataModel.getKeyId()); // key값으로만 지운다. 
			
			try { // execute delete 
				String deleteSql = SqlBuilder.SQL(); 
				System.out.println(deleteSql);
				SqlRunner runner = new SqlRunner(sqlSession.getConnection());
				runner.delete(deleteSql);
			} catch (SQLException e) {
				result.fail(-1, "delete fail : \n" + e.getMessage());
			    result.setMessage("delete fail");
				e.printStackTrace();
			}
			
			result.setRetrieveResult(1, "delete sucess!", list);
		}
	}
}
