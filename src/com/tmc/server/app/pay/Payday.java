package com.tmc.server.app.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.tmc.client.app.emp.model.HireModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Payday {

	private String mapperName  = "pay01_payday"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long hireId = request.getLong("paydayId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", hireId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		Long paydayId = request.getLong("paydayId"); 
		
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("companyId", companyId);
		param.put("paydayId", paydayId); 
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	
	public void selectByPayYear(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId");
		String payYear = request.getString("payYear");
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", companyId);
		param.put("payYear", payYear); 
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByPayYear", param);
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
