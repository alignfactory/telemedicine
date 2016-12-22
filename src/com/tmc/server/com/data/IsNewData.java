package com.tmc.server.com.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class IsNewData {
	
	public Long getSeq(SqlSession sqlSession) {
		
		Long seq = sqlSession.selectOne("dbConfig.getSeq");
		System.out.println("seq is " + seq); 
		return seq; 
	}
	
	public Boolean isNewData(SqlSession sqlSession, String tableName, Long id) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("tableName", tableName);
		param.put("pkName", tableName + "_id");
		param.put("seqId", id);
		
		Long tableId = sqlSession.selectOne("dbConfig.isNewData", param);
		
		if(tableId < 1 ){
			return true; // 자료가 이미 있다. 
		}
		
		return false ; // 자료가 없다. 
	}
	
}
