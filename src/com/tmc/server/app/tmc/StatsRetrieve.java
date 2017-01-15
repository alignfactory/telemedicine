package com.tmc.server.app.tmc;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;

public class StatsRetrieve {

	private String mapperName = "tmc99_retrieve"; 
	
	public void selectByRetrieve(SqlSession sqlSession, ServiceRequest request, ServiceResult result){
		Date fromDate = request.getDate("fromDate");
		Date toDate = request.getDate("toDate");
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("fromDate", fromDate); 
		param.put("toDate", toDate);
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByRetrieve", param);
		result.setRetrieveResult(1, "select ok", list);
	}
	
}
