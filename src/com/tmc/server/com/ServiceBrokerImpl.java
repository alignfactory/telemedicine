package com.tmc.server.com;

import java.lang.reflect.Method;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.apache.ibatis.session.SqlSession;

import com.tmc.client.service.InterfaceServiceBroker;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.server.com.data.IsNewData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class ServiceBrokerImpl extends RemoteServiceServlet implements InterfaceServiceBroker {

	private static final long serialVersionUID = 1L;

	public ServiceBrokerImpl(){
	}
	
	@Override
	public ServiceResult serviceCall(ServiceRequest request)
			throws IllegalArgumentException {
		
		System.out.println("request service : " + request.getServiceName());
		
		SqlSession sqlSession = null;

		ServiceResult result = new ServiceResult(); 
	    result.setServiceName(request.getServiceName());
	    
		try { // Database session create  
		    sqlSession = DatabaseFactory.openSession();
		}
		catch(RuntimeSqlException e ){
			result.fail(-1, "RuntimeSqlException fail: " + request.getServiceName());
		    e.printStackTrace();
		}
		catch(Exception e ){
			result.fail(-1, "Database session fail: " + request.getServiceName());
		    e.printStackTrace();

		    if(sqlSession != null){
				sqlSession.clearCache();
			    sqlSession.close();
		    }
		    return result ;
		}
		
		try { 
			// execute service & method 
			String serviceName = request.getServiceName(); 
			
			if("getSeq".equals(serviceName)) { // seq 가져오기 
				IsNewData isNewData = new IsNewData(); 
				Long seq = isNewData.getSeq(sqlSession); 
				result.setSeq(seq);
			}
			else { 
				String className = serviceName.substring(0,  serviceName.lastIndexOf("."));  
				String methodName =  serviceName.substring(serviceName.lastIndexOf(".") + 1);
	
				Class<?> loadClass = Class.forName("com.tmc.server.app." + className);
				Object executor = (Object)loadClass.newInstance(); 
	
				Method method 
			    	= executor.getClass().getMethod(methodName, new Class[]{SqlSession.class, ServiceRequest.class, ServiceResult.class}); 
				method.invoke(executor, sqlSession, request, result);
	
			    if(result.getStatus() < 0){
	 				sqlSession.rollback(true);
	 			}
	 			else {
	 				sqlSession.commit(true);
	 			}
			} 

			return result ;
		}
	    catch(ClassNotFoundException e){
	    	result.fail(-1, "service not found:" + request.getServiceName());
	    	e.printStackTrace();
			return result ;
	    }
		catch(Exception e ){
		    e.printStackTrace();
			return result ;
		}
		finally {
			if(sqlSession != null){
				sqlSession.rollback(true);
				sqlSession.clearCache();
			    sqlSession.close();
		    }
		} 
	}
}
