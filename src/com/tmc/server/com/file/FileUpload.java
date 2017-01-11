package com.tmc.server.com.file;
 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.ibatis.session.SqlSession;

import com.tmc.client.app.sys.model.FileModel;
import com.tmc.server.com.DatabaseFactory;
import com.tmc.server.com.data.IsNewData;

public class FileUpload implements javax.servlet.Servlet {
	
	private FilePath filePath = new FilePath(); 
	
	
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8"); // encoding 해주어야 한글메세지가 보인다. 
		SqlSession sqlSession = DatabaseFactory.openSession();
		
		try{

			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(1 * 1024 * 1024); // 1 MB
			ServletFileUpload serveltFileUpload = new ServletFileUpload(factory);
			FileItem fileItem = serveltFileUpload.parseRequest(request).get(0); // 처음 하나의 파일만 가져온다.  

			String uploadType = request.getParameter("uploadType"); // image or file 
			String fileId = request.getParameter("fileId");
			
			if("file".equals(uploadType)){

				FileModel fileModel = new FileModel();
				String fileName = fileItem.getName();	// file upload시에만 사용된다.
				
				if(fileId == null || "null".equals(fileId)){
					// get file id 
					IsNewData isNewData = new IsNewData(); 
					fileId = isNewData.getSeq(sqlSession).toString();
					
					fileModel.setFileId(Long.parseLong(fileId));
					fileModel.setParentId(Long.parseLong(request.getParameter("parentId"))); 
					fileModel.setFileName(fileName); 
					fileModel.setRegDate(new Date()); // 데이터베이스 시간으로 변경해야 한다.  
					fileModel.setServerPath(filePath.getFilePath(fileId)); //100개씩 잘라 보관한다.
					
					Double size = Double.parseDouble((fileItem.getSize()/1024) + ""); 
					fileModel.setSize(size); 
					
					fileModel.setDelDate(null); 
					fileModel.setNote(null);
					
					sqlSession.insert("sys10_file.insert", fileModel); 
				}
			}  // 이미지의 경우 테이블에 저장하지 않는다. 
			 
			File subDir  = new File(this.filePath.getFilePath(fileId));
	        if(!subDir.exists()) {
	        	subDir.mkdir(); 
	        }

	        File file = new File(this.filePath.getFilePath(fileId), fileId);
    	    file.deleteOnExit(); // 있으면 먼저 지워라.
            fileItem.write(file);
            
            sqlSession.commit();
            sqlSession.close();
            
            setResult(response, "파일을 성공적으로 등록하였습니다.");
            
		} 
		catch (FileUploadException e) {
			sqlSession.rollback();
			sqlSession.close();
			e.printStackTrace();
			setResult(response, "Error encountered while parsing the request");
		} 
		catch (Exception e) {
			sqlSession.rollback();
			sqlSession.close();
			e.printStackTrace();
			setResult(response, "Error encountered while uploading file");
		}
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
		System.out.println("file upload start"); 
		this.upload((HttpServletRequest)arg0, (HttpServletResponse)arg1); 
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