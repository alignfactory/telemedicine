package com.tmc.server.app.psc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.tmc.client.app.psc.model.StudyClassModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class StudyClass {

	private String mapperName  = "psc01_study_class"; 
	
	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", companyId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByName", request.getParam());
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<StudyClassModel> updateModel = new UpdateDataModel<StudyClassModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		// 사용중인 코드인지 체크 로직 추가 필요.  
		UpdateDataModel<StudyClassModel> updateModel = new UpdateDataModel<StudyClassModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
