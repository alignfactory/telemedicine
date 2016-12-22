package com.tmc.server.com.file;
 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

public class FileUpload2 implements javax.servlet.Servlet {

	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); // encoding 해주어야 한글메세지가 보인다. 
		
		String filePath = "D:\\pic\\" ;
		String fileId = null;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1 * 1024 * 1024); // 1 MB
		ServletFileUpload upload = new ServletFileUpload(factory); 
		
		try{
			// find file name 
            List<FileItem> items = upload.parseRequest(request);
            
            for(FileItem item:items){
            	if("fileId".equals(item.getFieldName())) {
            		fileId = item.getString();
            		filePath = filePath + (Long.parseLong(fileId)/100) + "\\" ;  
        	        File subDir  = new File(filePath);
        	        if(!subDir.exists()) {
        	        	subDir.mkdir(); 
        	        }
            		System.out.println("file path is  " + filePath + fileId);
            		
            		// 파일이 많아질 경우 하나의 디렉토리에서 관리가 안되므로 
            		// 100으로 나누어서 폴더명을 등록하고 파일ID를 저장한다. 
                }
            	
            	if("uploadedfile".equals(item.getFieldName())){
            		// get file name and save file to db  
            	}
            }
            
            if(fileId == null){
            	setResult(response, "파일ID가 없습니다. 파일을 업로드할 수 없습니다.");
            	return ; 
            }

            // save file object 
            for(FileItem item:items){
                if("uploadedfile".equals(item.getFieldName())) {
        	        File file = new File(filePath, fileId);
        	        file.deleteOnExit(); // 있으면 먼저 지워라. 
                    item.write(file);
                }
            }
            
            setResult(response, "파일을 등록하였습니다.");
		} 
		catch (FileUploadException e) {
			e.printStackTrace();
			setResult(response, "Error encountered while parsing the request");
		} 
		catch (Exception e) {
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
		
		// TODO Auto-generated method stub
		System.out.println("file upload start"); 
		this.upload((HttpServletRequest)arg0, (HttpServletResponse)arg1); 
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

