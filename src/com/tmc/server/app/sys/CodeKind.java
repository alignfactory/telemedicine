package com.tmc.server.app.sys;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.CodeKindModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class CodeKind { 
	String mapperName = "sys08_code_kind"; 
	
	public void selectByAll(SqlSession sqlSession, ServiceRequest request,
			ServiceResult result) {
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByAll");
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<CodeKindModel> updateModel = new UpdateDataModel<CodeKindModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		// 하위코드가 있는지 체크 필요.   
		UpdateDataModel<CodeKindModel> updateModel = new UpdateDataModel<CodeKindModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
