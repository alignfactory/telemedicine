package com.tmc.server.app.emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.tmc.client.app.emp.model.HireModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Hire {

	private String mapperName  = "emp01_hire"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long hireId = request.getLong("hireId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", hireId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByUserId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long userId = request.getLong("userId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByUserId", userId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByPayTarget(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("korName", request.getString("korName")); 
		param.put("companyId", request.getLong("companyId")); 
		param.put("onDate", request.getDate("onDate"));
		param.put("offDate", request.getDate("offDate"));
		
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByPayTarget", param);
		result.setRetrieveResult(1, "select ok", list);
	}


	public void selectByKorName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("korName", request.getString("korName")); 
		param.put("companyId", request.getLong("companyId")); 

		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByKorName", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	
	public void selectByUserName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String userName = request.getString("userName"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByUserName", userName);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<HireModel> updateModel = new UpdateDataModel<HireModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		// 사용중인 코드인지 체크 로직 추가 필요.  
		UpdateDataModel<HireModel> updateModel = new UpdateDataModel<HireModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
