package com.tmc.server.app.acc;
 
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tmc.client.app.psc.model.StudentModel;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.tmc.server.com.pdf.CellLayout;
import com.tmc.server.com.pdf.RetrieveData;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AccountPrint {

	private List<AbstractDataModel> getStudentModel(String studentId){
		
		ServiceRequest request = new ServiceRequest("psc.Student.selectById");
		request.add("studentId", Long.parseLong(studentId));
		
		RetrieveData retrieveData = new RetrieveData(); 
		ServiceResult result = retrieveData.retrieve(request); 

		if(result.getStatus() < 0 ) {
			return null;
		}
		else {
			if(result.getResult().size() > 0 ){
				List<AbstractDataModel> list = result.getResult(); 
				return list;
			}
			else {
				System.out.println("Not found studnet:" + studentId); 
				return null ; 
			}
		}
	}
	
	public void getDocument(BufferedOutputStream bufferedOutputStream, HttpServletRequest request) throws DocumentException, IOException{
		
		
		List<AbstractDataModel> list  = this.getStudentModel(request.getParameter("studentId")); 
		
		Document document = new Document(PageSize.A4, 0, 0, 50, 0);
		PdfWriter.getInstance(document, bufferedOutputStream);
		document.open();
		
		CellLayout cellLayout = new CellLayout(10);
		
		PdfPTable table = new PdfPTable(7); // column count 
		table.setWidthPercentage(90);
		table.setWidths(new int[]{15, 10, 15, 10, 15, 10, 15});
		
		for(AbstractDataModel dataModel:list){
			StudentModel studentModel = (StudentModel)dataModel; 
			
			PdfPCell cell;
			
			cell = createImageCell(studentModel.getStudentId().toString()); 
			cell.setRowspan(7);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cell);
			
			table.addCell(cellLayout.getCell("원생번호"));
			table.addCell(cellLayout.getCell(studentModel.getStudentNo()));
	
			table.addCell(cellLayout.getCell("한글이름")); 
			table.addCell(cellLayout.getCell(studentModel.getKorName()));
			
			table.addCell(cellLayout.getCell("주민번호")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("반이름")); 
			table.addCell(cellLayout.getCell(""));
			
			table.addCell(cellLayout.getCell("배정일"));
			table.addCell(cellLayout.getCell(""));
			
			table.addCell(cellLayout.getCell("남녀구분")); 
			table.addCell(cellLayout.getCell(""));
			
			table.addCell(cellLayout.getCell("생일")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("영문명")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("한자명")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("헨드폰")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("집전화")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("이메일")); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("성격")); 
	
			cell = cellLayout.getCell("아주좋음"); 
			cell.setColspan(3);
	//	    cell.setFixedHeight(20f);
	//	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	//	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    
		    table.addCell(cell);
			
			table.addCell(cellLayout.getCell("특기")); 
			table.addCell(cellLayout.getCell(""));
			
			table.addCell(cellLayout.getCell("버릇")); 
			table.addCell(cellLayout.getCell(""));
			
			cell = cellLayout.getCell("좋아하는\r음식"); 
			cell.setFixedHeight(28f);
			table.addCell(cell); 
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("싫어하는\r음식"));
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("희망학교"));
			table.addCell(cellLayout.getCell(""));
	
			table.addCell(cellLayout.getCell("이전학력")); 
			cell = cellLayout.getCell(""); 
			cell.setColspan(3);
			table.addCell(cell);
			
			cell = cellLayout.getCell("특기사항"); 
			cell.setFixedHeight(42f);
			table.addCell(cell);
			
			
			cell = cellLayout.getCell("");
			cell.setColspan(6);
			table.addCell(cell);
		}
		document.add(table);

        
        Paragraph p = new Paragraph(" ");
        document.add(p); 
          
		PdfPTable table2 = new PdfPTable(8);
		table2.setWidthPercentage(90);

		table2.setWidths(new int[]{10, 15, 10, 15, 10, 15, 10, 15});
		
		table2.addCell(cellLayout.getCell("헨드폰")); 
		table2.addCell(cellLayout.getCell(""));

		table2.addCell(cellLayout.getCell("집전화")); 
		table2.addCell(cellLayout.getCell(""));

		table2.addCell(cellLayout.getCell("이메일")); 
		table2.addCell(cellLayout.getCell(""));
		
		table2.addCell(cellLayout.getCell("이메일"));
		table2.addCell(cellLayout.getCell(""));
		
		
		document.add(table2);
		
		document.close();
	}
	
	private PdfPCell createImageCell(String studentId) throws DocumentException, IOException {
		String filePath = "D:\\WebFiles\\" ;
		String imageFile = filePath + (Long.parseLong(studentId)/100) + "\\"  + studentId ;
		
		Image img = Image.getInstance(imageFile);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setPadding(3);
        return cell;
    }
	

}