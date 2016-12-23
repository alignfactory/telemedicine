package com.tmc.server.app.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.RoleModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.sys.model.MenuModel;
import com.tmc.client.app.sys.model.UserRoleModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Menu { 
	
	String mapperName = "sys05_menu"; 
	
	public void selectByRoleId(SqlSession sqlSession, ServiceRequest request,
			com.tmc.client.service.ServiceResult result) {

		Long roleId = request.getLong("roleId"); 
		List<AbstractDataModel> roleObjectList = getChild(sqlSession, roleId); 
		result.setRetrieveResult(roleObjectList.size(), "하위메뉴 조회", roleObjectList);
	}
	
	public void selectByUserId(SqlSession sqlSession, ServiceRequest request,
			com.tmc.client.service.ServiceResult result) {

		Long userId = request.getLong("userId");
		
		System.out.println("user id is " + userId); 
		
		List<AbstractDataModel> roleObjectList = new ArrayList<AbstractDataModel>(); 
		List<UserRoleModel> roleList = new ArrayList<UserRoleModel>(); // sqlSession.selectList("sys06_user_role.selectByUserId", userId);
		//roleList = sqlSession.selectList("sys06_user_role.selectByUserId", userId);
		
		UserModel userModel = sqlSession.selectOne("sys02_user.selectById", userId);

		Long roleId = Long.parseLong("0"); 
		
		if("10".equals(userModel.getCompanyModel().getCompanyTypeCode())){
			// 보건소
			roleId = Long.parseLong("1000394"); 
		}

		if("20".equals(userModel.getCompanyModel().getCompanyTypeCode())){
			// 병원 
			roleId = Long.parseLong("2000192"); 
		}

		if("90".equals(userModel.getCompanyModel().getCompanyTypeCode())){
			// 센터
			roleId = Long.parseLong("1000277"); 
		}
		
		UserRoleModel userRoleModel = new UserRoleModel(); 
		userRoleModel.setUserId(userId);
		userRoleModel.setRoleId(roleId);
		
		roleList.add(userRoleModel); 
		
		for(UserRoleModel userRole : roleList){
			List<AbstractDataModel> childList = getChild(sqlSession, userRole.getRoleId());
			roleObjectList.addAll(childList); 
		}
		
		result.setRetrieveResult(roleObjectList.size(), "하위메뉴 조회", roleObjectList);
	}
	
	private List<AbstractDataModel> getChild(SqlSession sqlSession, Long parentId ){
		
		// System.out.println("parent id is " + parentId); 
		
		List<AbstractDataModel> roleObjectList 
			= sqlSession.selectList(mapperName + ".selectByParentId", parentId);

		for(AbstractDataModel child : roleObjectList){
			
			MenuModel menuModel = (MenuModel)child;
			
			List<AbstractDataModel> childList = getChild(sqlSession, menuModel.getMenuId());  
			menuModel.setChildList(childList); 	

		}

		return roleObjectList ; 
	}

//	public void insert(SqlSession sqlSession, ServiceRequest request,
//			com.tmc.client.svc.ServiceResult result) {
//
//		try{
//			RoleObjectModel roleObject = (RoleObjectModel)request.getModel("roleObjectModel");	 
//
//			sqlSession.insert("sys05_role_object.insert", roleObject); 
//			result.setModel(1, "메뉴구성을 등록하였습니다.", (AbstractDataModel)roleObject);
//			
//		}
//		catch(Exception e) {
//			System.out.println(e.toString()); 
//			result.fail(-1, "메뉴구성 등록 오류: " + e.getMessage());
//		}
//	}

//	public void insertObject(SqlSession sqlSession, ServiceRequest request,
//			ServiceResult result) {
//	
//		try{
//			Long parentId = request.getLong("parentId"); 
//			List<AbstractDataModel> list = request.getList(); 
//			List<AbstractDataModel> objectList = new ArrayList<AbstractDataModel>(); 
//					
//			for(AbstractDataModel model : list){
//				ObjectModel object = (ObjectModel)model;
//
//				RoleObjectModel roleObject = new RoleObjectModel(); // Empty roleObjectModel create  
//				roleObject.setParentId(parentId);
//				roleObject.setObjectId(object.getObjectId()); 
//				roleObject.setApplyName(object.getObjectName());
//				roleObject.setSeq(object.getObjectName());
//				
//				sqlSession.insert("sys05_role_object.insert", roleObject);
//
//				//System.out.println("inserted object id is " + roleObject.getRoleObjectId()); 
//				
//				AbstractDataModel insertObject = sqlSession.selectOne("sys05_role_object.selectById", roleObject.getRoleObjectId()); 
//				objectList.add(insertObject); 
//			}
//			
//			result.setRetrieveResult(1, "하위 메뉴를 등록하였습니다.", objectList);
//		}
//		catch(RuntimeSqlException e ){
//			result.fail(-1, "하위 메뉴 등록 오류: " + e.getMessage());
//		    e.printStackTrace();
//		}
//	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<MenuModel> updateModel = new UpdateDataModel<MenuModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<RoleModel> updateModel = new UpdateDataModel<RoleModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
