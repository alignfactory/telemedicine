package com.tmc.client;

import com.tmc.client.main.Login;
import com.google.gwt.core.client.EntryPoint;

public class TeleMedicine implements EntryPoint {

	public void onModuleLoad() {
		Login login = new Login();
		login.open(); //로그인 오픈 
	}
}
