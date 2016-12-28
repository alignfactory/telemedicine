package com.tmc.server.app.tmc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.tmc.model.RequestModel;
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
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", companyId);

		
		System.out.println("start log"); 
		for(AbstractDataModel data : list){
			RequestModel requestModel = (RequestModel)data;
			System.out.println("request user id is " + requestModel.getRequestUserId());
			System.out.println("request user Model id is " + requestModel.getRegUserModel().getKorName());
			
		}
		System.out.println("close log"); 

		
		
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		List<AbstractDataModel> list = request.getList(); 

		
		UpdateDataModel<AbstractDataModel> updateModel = new UpdateDataModel<AbstractDataModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<AbstractDataModel> updateModel = new UpdateDataModel<AbstractDataModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
