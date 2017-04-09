package com.tmc.server.app.tmc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Request {

	private String mapperName  = "tmc02_request"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result){
		Long selectId = request.getLong("requestId");
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", selectId);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectByPatientId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long patientId = request.getLong("patientId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByPatientId", patientId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		String patientName = request.getString("patientName");
		patientName = "%" + patientName + "%";
		Date requestDate = request.getDate("requestDate");

		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", companyId);
		param.put("patientName", patientName); 
		param.put("requestDate", requestDate); 
		param.put("treatStateCode", request.getString("treatStateCode"));
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", param);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectBySearchList(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		Date startDate = request.getDate("startDate"); 
		Date endDate = request.getDate("endDate"); 
		String patientName = request.getString("patientName");
		patientName = "%" + patientName + "%";
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", companyId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("patientName", patientName); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectBySearchList", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByTreatList(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		Date startDate = request.getDate("startDate"); 
		Date endDate = request.getDate("endDate"); 
		String patientName = request.getString("patientName");
		patientName = "%" + patientName + "%";
		String treatStateCode = request.getString("treatStateCode");
		
		System.out.println("company id is "+companyId); 
		System.out.println("start date is " + startDate);
		System.out.println("end date is " + endDate);
		System.out.println("patientName " + patientName);
		System.out.println("treatStateCode " + treatStateCode);
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", companyId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("patientName", patientName);
		param.put("treatStateCode", treatStateCode);
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByTreatList", param);
		
		System.out.println("list count is "+ list.size()); 
		
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AbstractDataModel> updateModel = new UpdateDataModel<AbstractDataModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AbstractDataModel> updateModel = new UpdateDataModel<AbstractDataModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
