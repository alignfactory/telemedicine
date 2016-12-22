package com.tmc.client.app.psc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class PDF_Student_backup extends Window  {
	
	public interface HWPTemplate extends XTemplates {
	    @XTemplate("<iframe id='pdf' border=0 src='PdfDownload?file=4' width='100%' height='99%'/>")
	    SafeHtml getTemplate();
	}

	
	// Call iFrmae JavaScript 
	private native void resize(String height) /*-{
		$doc.getElementById("pdfReader").contentWindow.resize(height);
	}-*/;
	
	private native void newFile() /*-{
		$doc.getElementById("pdfReader").contentWindow.newFile("KKK");
	}-*/;


	public PDF_Student_backup(){
		
		this.setResizable(false);
		
		this.setHeading("원생 정보 출력");
		this.setModal(true);
		this.setPixelSize(700, 700);
		this.setLayoutData(new MarginData(0));
		
		HWPTemplate hwp = GWT.create(HWPTemplate.class);
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(hwp.getTemplate());
		
		htmlLayoutContainer.setBorders(false);
		
		ContentPanel panel = new ContentPanel();
		panel.setBorders(false);
		panel.add(htmlLayoutContainer, new MarginData(0));
		this.add(htmlLayoutContainer);

		
		TextButton downButton = new TextButton("확인");
		downButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				newFile(); 
			}
		});
		this.addButton(downButton);
		
		TextButton cancelButton = new TextButton("취소");
		cancelButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		});
		this.addButton(cancelButton);
		this.setButtonAlign(BoxLayoutPack.CENTER); // 버튼을 가운데로 


	}
	
	public void retrieve(){
//		GridRetrieveData<HireModel> service = new GridRetrieveData<HireModel>(listStore);
//		service.addParam("companyId", LoginUser.getLoginCompany());
//		service.addParam("korName", userNameField.getText());
//		service.retrieve("emp.Hire.selectByKorName");
	}
	
}
