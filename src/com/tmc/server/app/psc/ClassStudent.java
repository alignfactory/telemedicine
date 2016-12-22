package com.tmc.server.app.psc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.psc.model.ClassStudentModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class ClassStudent {

	private String mapperName  = "psc04_class_student"; 
	
	public void selectByStudentId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long studentId = request.getLong("studentId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByStudentId", studentId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<ClassStudentModel> updateModel = new UpdateDataModel<ClassStudentModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		// 사용중인 코드인지 체크 로직 추가 필요.  
		UpdateDataModel<ClassStudentModel> updateModel = new UpdateDataModel<ClassStudentModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
