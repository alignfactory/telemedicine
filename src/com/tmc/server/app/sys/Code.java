package com.tmc.server.app.sys;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.CodeModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Code {

	private String mapperName  = "sys09_code"; 
	
	public void selectByCodeKindId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long codeKindId = request.getLong("codeKindId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCodeKindId", codeKindId);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectByCodeKind(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String codeKind = request.getString("codeKind"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCodeKind", codeKind);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectByApplyDate(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("codeKindId", request.getLong("codeKindId"));
		param.put("applyDate", Calendar.getInstance().getTime()); // system date 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCodeKindId", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<CodeModel> updateModel = new UpdateDataModel<CodeModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		// 사용중인 코드인지 체크 로직 추가 필요.  
		UpdateDataModel<CodeModel> updateModel = new UpdateDataModel<CodeModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
