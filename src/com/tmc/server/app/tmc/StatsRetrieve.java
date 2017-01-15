package com.tmc.server.app.tmc;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;

public class StatsRetrieve {

	private String mapperName = "tmc99_retrieve"; 
	
	public void selectByRetrieve(SqlSession sqlSession, ServiceRequest request, ServiceResult result){
		String patientName = request.getString("patientName");
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByRetrieve", patientName);
		result.setRetrieveResult(1, "select ok", list);
	}
	
}
