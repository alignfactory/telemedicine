package com.tmc.server.com.file;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.FileModel;
import com.tmc.server.com.DatabaseFactory;

public class FileDownload implements javax.servlet.Servlet {

	private void fileDownload(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); // encoding 해주어야 한글메세지가 보인다.
		
		String filePath = "D:\\WebFiles\\" ;
		String fileId = request.getParameter("fileId");
		
		if(fileId == null){
			setResult(response, "다운로드 받을 파일정보가 없습니다.");	
		}
		
		SqlSession sqlSession = DatabaseFactory.openSession();
		FileModel fileModel = sqlSession.selectOne("sys10_file.selectById", Long.parseLong(fileId)); 
		
        File file = new File(filePath + (Long.parseLong(fileId)/100) + "\\"  + fileId);
        System.out.println("file download:" + file.getAbsolutePath());

        if(fileModel == null) {
        	// image file 
	        if (!file.exists()) {
	        	//throw new FileNotFoundException(file.getAbsolutePath());
	        	file = new File("D:\\WebFiles\\nonamed.jpg"); 
	        }
	        response.setHeader("Content-Type", "image/jpeg"); //  image가 아닌경우 어떻하지? 
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        response.setHeader("Content-disposition", "attachment;filename=" + fileId );
        }
        else { 
        	// 문서파일 
	        if (!file.exists()) {
	        	setResult(response, "서버에 등록되 파일정보가 없습니다:" + fileModel.getFileName());
	        	return; 
	        }
	        
	        response.setContentType("application/octet-stream"); // 무조건 다운받는다.
	        response.setContentLength((int)file.length());
	        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileModel.getFileName(),"UTF-8"));
        }

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
        System.out.println("download complete : " + fileId); 
	}

	private void setResult(HttpServletResponse response, String message){
		try {
			PrintWriter out = response.getWriter();
			out.println(message);
			out.flush();
			System.out.println("end of file upload");
		}
		catch(Exception e){
			System.out.println(e.toString()); 
		}
	}
	
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("file download start"); 
		this.fileDownload((HttpServletRequest)arg0, (HttpServletResponse)arg1); 
	}
	@Override
	public void destroy() {
	}
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	@Override
	public String getServletInfo() {
		return null;
	}
	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}
}