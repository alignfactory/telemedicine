package com.tmc.server.app.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.pay.model.PayModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.data.UpdateDataModel;

public class Pay {

	private String mapperName  = "pay02_pay"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long paydayId = request.getLong("payId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectById", paydayId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByPaydayId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long paydayId = request.getLong("paydayId"); 
		List<AbstractDataModel> list = sqlSession.selectList(mapperName + ".selectByPaydayId", paydayId);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void copyPay(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		System.out.println("start copy pay"); 
		
		Long fromPaydayId = request.getLong("fromPaydayId"); 
		Long toPaydayId = request.getLong("toPaydayId");
		
		System.out.println("fromPaydayId is " + fromPaydayId); 
		System.out.println("toPaydayId is " + toPaydayId);
		
		Map<String, Long> param = new HashMap<String, Long>(); 
		
		param.put("fromPaydayId", fromPaydayId); 
		param.put("toPaydayId", toPaydayId); 
		
		try{
			sqlSession.delete(mapperName + ".deleteByPaydayId", toPaydayId); 
			sqlSession.commit(true);
			
			System.out.println("delete from data ok");

			try{
				sqlSession.insert(mapperName + ".copyByPaydayId", param); 
				System.out.println("copy pay data sucess");
				result.setMessage("copy pay data sucess ");
				result.setStatus(1); 
				return; 

			}
			catch(Exception e){
				System.out.println(e.getMessage());
				result.setMessage("copy pay data error");
				result.setStatus(-1); 
				return ; 
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage()); 
			result.setMessage("delete from data error");
			result.setStatus(-1); 
			return ; 
		}
		

		
		// 계좌정보는 현재 계좌정보로 변경? 아니면 입금내역 생성 시 변경? 어디서 하지? 
		// delete 
	}
	
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		System.out.println("update"); 
		
		UpdateDataModel<PayModel> updateModel = new UpdateDataModel<PayModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<PayModel> updateModel = new UpdateDataModel<PayModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}


}
