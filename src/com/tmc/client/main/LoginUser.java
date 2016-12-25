package com.tmc.client.main;

import java.util.Date;

import com.tmc.client.app.sys.model.UserModel;
import com.google.gwt.i18n.client.DateTimeFormat;

public class LoginUser {

	private static UserModel loginUser;
	private static Long loginCompany; 
	
	public static UserModel getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(UserModel loginUser) {
		LoginUser.loginUser = loginUser;
	}
	
	public static void setLoginCompany(Long companyId){
		loginCompany = companyId; 
	}
	
	public static Long getLoginCompany(){
		return loginCompany; 
	}
	
	public static Boolean isAdmin(){
		
		return "true".equals(loginUser.getAdminYn()); 
	}
	
	public static String getYear(){
		return DateTimeFormat.getFormat( "yyyy" ).format( new Date()) ; 
	}
	
	public static String getToday(){
		return DateTimeFormat.getFormat( "yyyy-mm-dd" ).format(new Date()) ; 
	}
}
