package com.tmc.server.app.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class User {
	
	private String mapperName = "sys02_user";
	
	public void getLoginInfo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		try{
			UserModel userInfo = sqlSession.selectOne(mapperName + ".selectByLoginInfo", request.getParam()) ;
			
			if(userInfo == null){
				result.fail(-1, "등록된 사용자 정보가 아닙니다. 아이디와 패스워드를 확인하여 주십시요!");
				return ; 
			}

			if(userInfo.getCloseYnBoolean()){ 
				result.fail(-1, "로그인 제한 사용자입니다. 로그인 할 수 없습니다. : " +  userInfo.getCloseYnBoolean());
				return ; 
			} 
			
// if 관리자인지 여부 
// 관리자가 라면 관리하는 회사목록을 보여준다. 
// else 
// 관리자가 아니라면 본인이 속한 personInfo와 companyId를 찾아 줘야 한다. -> LoginUserModel이 있어야 함.
// 로그인 사용자가 이미 퇴사한 사용자인 경우 로그인은 제한된다. 
			
			result.setModel(1, "login OK", (AbstractDataModel)userInfo);
			System.out.println(userInfo.getCloseYnBoolean().toString());
			return ; 
		}
		catch(Exception e) {
			result.fail(-1, "login fail by service");
			e.printStackTrace();
		}
	}
	
	public void selectByAll(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByAll");
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectAdminUserByUserName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String userName = request.getString("userName");
		if(userName != null){
			userName = "%" + userName + "%"; 
		}
		else {
			userName = "%"; 
		}
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectAdminUserByUserName", userName);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long clientId = request.getLong("companyId"); 
		System.out.println("company id is " + clientId); 
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", clientId);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectByName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		
		String userName = request.getString("userName") ; 
		if(userName != null){
			userName = "%" + userName + "%"; 
		}
		else {
			userName = "%"; 
		}
		
		param.put("companyId", request.getLong("companyId"));
		param.put("korName", userName);
		
		
		if(request.getLong("companyId") != null){
			System.out.println("company id is not null"); 
		}
		else {
			System.out.println("company id is null");
		}
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByName", param);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void updateAdminUser(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		Long companyId = Long.parseLong("2000001"); 
		
		UpdateDataModel<UserModel> updateModel = new UpdateDataModel<UserModel>();
		List<AbstractDataModel> updateList = new ArrayList<AbstractDataModel>(); 
		
		for(AbstractDataModel model : request.getList()){
			UserModel userModel = (UserModel)model; 
			if(userModel.getCompanyId() == null){
				userModel.setCompanyId(companyId);
			}
			updateList.add(userModel);
		}
		updateModel.updateModel(sqlSession, updateList, mapperName, result);
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<UserModel> updateModel = new UpdateDataModel<UserModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<UserModel> updateModel = new UpdateDataModel<UserModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
