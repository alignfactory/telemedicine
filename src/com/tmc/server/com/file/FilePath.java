package com.tmc.server.com.file;

public class FilePath {

	// folder는 반드시 직접 만들어 주어야 한다. 
	public String getFilePath(String fileId){
		return "C:\\WebFiles\\" + (Long.parseLong(fileId)/100) ;	
	}
	
}
