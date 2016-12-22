package com.tmc.server.com;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseFactory {

	public static SqlSessionFactory sqlSessionFactory;
	
	public static SqlSessionFactory dataFactory() {
		
		String resource = "com/tmc/server/com/ConfigMyBatis.xml";

		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} 
		catch(IOException e) {
			e.printStackTrace(); 
		}
		
		return sqlSessionFactory; 
	}
	
	public static SqlSession openSession() {
		
		if (sqlSessionFactory == null) {
			sqlSessionFactory = dataFactory(); 
		}
		
		return sqlSessionFactory.openSession(); 
	}
	
	public void reload(){
		// Mapper 가 변경된 경우 sqlSessionFactory를 재설정한다. 
	}
}



