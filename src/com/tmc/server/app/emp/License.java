package com.tmc.server.app.emp;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.tmc.client.app.emp.model.LicenseModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class License {

	private String mapperName  = "emp02_license"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long licenseId = request.getLong("licenseId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", licenseId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByUserId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long userId = request.getLong("userId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByUserId", userId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByToday(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByToday", companyId);
		result.setRetrieveResult(1, "select ok", list);
	}

	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<LicenseModel> updateModel = new UpdateDataModel<LicenseModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		// 사용중인 코드인지 체크 로직 추가 필요.  
		UpdateDataModel<LicenseModel> updateModel = new UpdateDataModel<LicenseModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
