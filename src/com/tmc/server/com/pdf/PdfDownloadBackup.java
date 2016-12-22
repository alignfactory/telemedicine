package com.tmc.server.com.pdf;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfDownloadBackup implements javax.servlet.Servlet {

	private void fileDownload(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
		
		System.out.println("pdf download:");
		
		String filePath = "D:\\pdf\\cmp_simple_table.pdf" ;
		String fileName = request.getParameter("file");
        File file = new File(filePath); // + (Long.parseLong(fileName)/100) + "\\"  + fileName);
        
        System.out.println("pdf download:" + file.getAbsolutePath());
        
        if (!file.exists()) {
        	System.out.println("file not found ");
        	return; 
        	//throw new FileNotFoundException(file.getAbsolutePath());
        	//file = new File("D:\\pic\\nonamed.jpg"); 
        }
        
        response.setHeader("Content-Type", "application/pdf"); //  image가 아닌경우 어떻하지? 
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        byte[] buf = new byte[1024];
        
        while (true) {
            int length = bufferedInputStream.read(buf);
            if (length == -1)
                break;
            bufferedOutputStream.write(buf, 0, length);
        }
        
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();
        System.out.println("download complete : " + fileName); 
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("file download start"); 
		this.fileDownload((HttpServletRequest)arg0, (HttpServletResponse)arg1); 
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}