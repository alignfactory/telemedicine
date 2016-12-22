package com.tmc.server.app.bbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.bbs.model.BoardModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Board {

	private String mapperName  = "bbs01_board"; 
	
	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId");

		String title = request.getString("title");
		if(title != null){
			title = "%" + title + "%"; 
		}
		else {
			title = "%"; 
		}

		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", companyId);
		param.put("title", title); 

		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByTypeCode(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId");
		String boardTypeCode = request.getString("boardTypeCode");
		if(boardTypeCode != null){
			boardTypeCode = "%" + boardTypeCode + "%"; 
		}
		else {
			boardTypeCode = "%"; 
		}
		
		String title = request.getString("title");
		if(title != null){
			title = "%" + title + "%"; 
		}
		else {
			title = "%"; 
		}
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("companyId", companyId);
		param.put("boardTypeCode", boardTypeCode); 
		param.put("title", title); 
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByTypeCode", param);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		System.out.println("board update"); 
		
		UpdateDataModel<BoardModel> updateModel = new UpdateDataModel<BoardModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		// 사용중인 코드인지 체크 로직 추가 필요.  
		UpdateDataModel<BoardModel> updateModel = new UpdateDataModel<BoardModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
