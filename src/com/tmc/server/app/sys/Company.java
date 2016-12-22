package com.tmc.server.app.sys;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Company {

	private String mapperName = "sys01_company"; 
	
	public void selectByAll(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		List<AbstractDataModel> list = sqlSession.selectList("sys01_company.selectByAll");
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByClientName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String clientName = request.getString("companyName"); 
		List<AbstractDataModel> list = sqlSession.selectList("sys01_company.selectByName",  clientName);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectByNotAssignedCompany(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		Long userId = request.getLong("userId"); 
		
		System.out.println("user id is " + userId); 
		
		List<AbstractDataModel> userRoleList = sqlSession.selectList(mapperName + ".selectByNotAssignedCompany", userId) ;
		result.setRetrieveResult(1, "사용자별 미등록 고객조회", userRoleList);
	}

	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<CompanyModel> updateModel = new UpdateDataModel<CompanyModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<CompanyModel> updateModel = new UpdateDataModel<CompanyModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
