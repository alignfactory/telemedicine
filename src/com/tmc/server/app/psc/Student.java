package com.tmc.server.app.psc;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.psc.model.ClassStudentModel;
import com.tmc.client.app.psc.model.StudentModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.IsNewData;
import com.tmc.server.com.data.UpdateDataModel;

public class Student {

	private String mapperName  = "psc03_student"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result){
		 
		
		Long studentId = request.getLong("studentId");
		
		System.out.println("select By id student -- " + studentId);
		
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", studentId);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	
	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long companyId = request.getLong("companyId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", companyId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		// UpdateModel을 사용하지 않고 새롭게 쓴다. 
//		List<AbstractDataModel> updateList = new ArrayList<AbstractDataModel>(); 
		
		for(AbstractDataModel data : request.getList()){

			List<AbstractDataModel> updateOne = new ArrayList<AbstractDataModel>();  
			updateOne.add(data); 
			
			UpdateDataModel<StudentModel> updateModel = new UpdateDataModel<StudentModel>(); 
			// 한건만 저장한다. 
			updateModel.updateModel(sqlSession, updateOne, mapperName, result);

			// 반 배정 정보 Update 
			StudentModel studentModel = (StudentModel)data;
			ClassStudentModel classStudentModel = studentModel.getClassStudentModel(); 

			if(classStudentModel.getStudyClassId() == null) {
				result.setStatus(-1);
				return; // 배정된 반이 없으면 Skip,. 배정반은 지울 수 없는 자료이다.
			}

			if(classStudentModel.getAssignDate() == null) {
				result.fail(-1,  "반 배정일은 반드시 등록되어야 합니다");
				return; 
				// continue; // 배정일이 없으면 Skip, 배정일은 지울 수 없는 자료이다. 
			}
			
			if(classStudentModel.getStudentId() == null) {
				// 처음등록되는 원아이면 원아ID를 넣어준다. 
				classStudentModel.setStudentId(studentModel.getStudentId()); 
			}
			
			if(classStudentModel.getClassStudentId() == null) {
				// 데이터가 모두 있다. 신규자료이다. insert
				IsNewData newData = new IsNewData(); 
				classStudentModel.setClassStudentId(newData.getSeq(sqlSession)); // sequence를 새로 받는다. 
				
				UpdateDataModel<ClassStudentModel> studentUpdateModel = new UpdateDataModel<ClassStudentModel>();
				List<AbstractDataModel> classStudentList = new ArrayList<AbstractDataModel>();
				classStudentList.add(classStudentModel);
				
				studentUpdateModel.updateModel(sqlSession, classStudentList, "psc04_class_student", result);
				//sqlSession.insert("psc04_class_student.insert", classStudentModel); 
			}
			else {
				// 기존자료와 비교하여 update할지 insert할지 결정해야 한다. 
				// 일자가 동일하면 업데이트 다르면 insert한다. 
				
				ClassStudentModel currentModel 
					= sqlSession.selectOne("psc04_class_student.selectById", classStudentModel.getClassStudentId());
				
				if(currentModel == null) {
					currentModel = new ClassStudentModel(); 
				}
				
				// 일자형 비교는 CompareTo로 한다. 
				if(classStudentModel.getAssignDate().compareTo(currentModel.getAssignDate()) != 0  ){
					
					System.out.println("current model " + currentModel.getAssignDate().toString());
					System.out.println("classStudent model " + classStudentModel.getAssignDate().toString());
					
					// 반 배정일이 다르다. 신규로 등록한다.  
					IsNewData newData = new IsNewData(); 
					classStudentModel.setClassStudentId(newData.getSeq(sqlSession)); // sequence를 새로 받는다. 
//					sqlSession.insert("psc04_class_student.insert", classStudentModel); 

				}
//				else {
//					sqlSession.update("psc04_class_student.update", classStudentModel);
//				}
//				
				UpdateDataModel<ClassStudentModel> studentUpdateModel = new UpdateDataModel<ClassStudentModel>();
				List<AbstractDataModel> classStudentList = new ArrayList<AbstractDataModel>();
				classStudentList.add(classStudentModel);
				
				studentUpdateModel.updateModel(sqlSession, classStudentList, "psc04_class_student", result);
				
			}
			// 저장한 결과를 다시 받아온다. 
//			studentModel = 	sqlSession.selectOne(mapperName + ".selectById", studentModel.getKeyId());
//			updateList.add(studentModel); 
		}
		
//		result.setRetrieveResult(1, "저장성공", updateList);		
		
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<StudentModel> updateModel = new UpdateDataModel<StudentModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
