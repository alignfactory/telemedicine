package com.tmc.client.app.sys;

import java.util.Map;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.ui.InterfaceTabPage;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class Tab_File extends ContentPanel implements InterfaceTabPage, Editor<UserModel> {

	interface EditDriver extends SimpleBeanEditorDriver<UserModel, Tab_File> {}
	EditDriver editDriver = GWT.create(EditDriver.class);
	private FormPanel form;
	 
	public Tab_File(){
		editDriver.initialize(this);
		this.setHeaderVisible(false);

		TextField firstName = new TextField();
		
		final FileUploadField file = new FileUploadField();
		file.setWidth(100);
		
		file.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent arg0) {
				Info.display("File Changed", "You selected " + file.getValue());
			}
		});
		file.setName("uploadedfile"); // file name 
		file.setAllowBlank(false);

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
	
		FieldLabel fieldLable = new FieldLabel(file);  
		
		// label decoration 
		fieldLable.setLabelSeparator("");
		fieldLable.setLabelPad(0);
		fieldLable.setHTML("<div align=right>" + "파일선택:" + " </div>");
		fieldLable.setLabelWidth(70); 
		 
		vlc.add(new FieldLabel(firstName, "Name"), new VerticalLayoutData(1, -1));
		
		Image pic = new Image(); 
		//pic.setUrl("FileDownload?file=201401.jpg");
		pic.setPixelSize(200, 350);

		//pic.setSize("100", "100");
		
		//vlc.add(pic);
		vlc.add(fieldLable, new VerticalLayoutData(500, -1));
		
		form = new FormPanel();
		
		form.setAction("FileUpload"); // File upload servelt call - web.xml 참조 
		
		form.setEncoding(Encoding.MULTIPART);
		form.setMethod(Method.POST);
		form.add(vlc, new MarginData(10));

		this.add(form ); 
		
		form.addSubmitCompleteHandler(new SubmitCompleteHandler(){

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
		          MessageBox box = new MessageBox("File Upload Example", event.getResults().toString());
		          box.setIcon(MessageBox.ICONS.info());
		          box.show();
			}
			
		});
		
		TextButton submitButton = new TextButton("Submit");
	      submitButton.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
		          if (!form.isValid()) {
			            return;
			          }
			          // normally would submit the form but for example no server set up to
			          // handle the post
			          // panel.submit();
		          form.submit();

				
			}
	      });

	      this.addButton(submitButton);
	      
	      
	}

	@Override
	public void retrieve(Map<String, Object> param) {
	}
	
}