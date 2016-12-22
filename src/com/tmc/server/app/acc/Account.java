package com.tmc.server.app.acc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.acc.model.AccountModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Account {

	private String mapperName  = "acc02_account"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long seasionId = request.getLong("accountId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", seasionId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByEduOfficeCode(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String eduOfficeCode = request.getString("eduOfficeCode"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByEduOfficeCode", eduOfficeCode);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	
	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", request.getLong("companyId")); 
		param.put("eduOfficeCode", request.getString("eduOfficeCode")); 
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AccountModel> updateModel = new UpdateDataModel<AccountModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AccountModel> updateModel = new UpdateDataModel<AccountModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
