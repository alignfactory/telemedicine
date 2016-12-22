package com.tmc.client.app.sys;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class Lookup_UserImage extends Window  {
	
	private Image 	image ; // = new Image(); 
	private Long 	userId ; 
	private FormPanel formPanel = new FormPanel();
	
	public Lookup_UserImage(Image image, Long userId){
		
		this.setHeading("파일업로드");
		this.setModal(true);
		this.setResizable(false);
		this.setPixelSize(450, 150);
		
		this.image = image; 
		this.userId = userId; 
		
		final FileUploadField file = new FileUploadField();
		file.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent arg0) {
				Info.display("File Changed", "You selected " + file.getValue());
			}
		});

		file.setName("uploadedfile"); // file name 
		file.setAllowBlank(false);

		FieldLabel fileSearchLable = new FieldLabel(file, "파일선택");  
		fileSearchLable.setWidth(200);
		fileSearchLable.setLabelWidth(60);
		buttonBar.add(fileSearchLable) ; 
		
		TextField fileIdField = new TextField();
		fileIdField.setText(userId.toString()); // userId setting 
		fileIdField.setName("fileId");
		// fileIdField.hide();
		
		FieldLabel fileIdLabel = new FieldLabel(fileIdField, "파일ID") ; 
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(fileSearchLable, new VerticalLayoutData(1, -1, new Margins(15)));
		vlc.add(fileIdLabel) ; //, new VerticalLayoutData(1, -1, new Margins(20)));
		
		fileIdLabel.hide();
		
//		formPanel.setAction("FileUpload"); // File upload servelt call - web.xml 참조 
		formPanel.setEncoding(Encoding.MULTIPART);
		formPanel.setMethod(Method.POST);
		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler(){
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// image.setUrl("FileDownload?file=" + userId);
				uploadMessage(event.getResults().toString()); 
			}
		});
		
		formPanel.add(vlc);

		HorizontalLayoutData hld = new HorizontalLayoutData(); 
		hld.setMargins( new Margins(10, 10, 0, 0));
		this.add(formPanel, hld);
		
		TextButton submitButton = new TextButton("파일등록");
		submitButton.setWidth(60);
		
		submitButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {

				if (!formPanel.isValid()) {
					return;
				}
				submit(); 
			}
		});

		TextButton closeButton = new TextButton("닫기");
		closeButton.setWidth(60);
		closeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
				//image.setUrl("FileDownload?file=" + userId);
			}
		});
		
		this.addButton(submitButton);
		this.addButton(closeButton);
	}
	
	private void submit(){
		if(userId != null){
			//String actionUrl = "FileUpload?uploadType=image&"; 
			String actionUrl = "FileUpload?uploadType=image&fileId=" + this.userId.toString();
			formPanel.setAction(actionUrl);
			formPanel.submit();
		}
	}
	
	public void uploadMessage(String message){
		// 이미지가 버퍼에 있어 다시 불러오지 않는 경우  System.currentTimeMillis() 을 붙여 
		// 시스템이 다은 URL호출이라 인지할 수 있도록 한다. 
		this.image.setUrl("FileDownload?fileId=" + this.userId + "&time" +  System.currentTimeMillis());
		MessageBox box = new MessageBox("파일등록(" + this.userId + ")", new HTML(message).getText());
        box.setIcon(MessageBox.ICONS.info());
        box.show();
	}
	
}
