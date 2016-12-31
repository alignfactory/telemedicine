package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.RequestModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

public class Page_Treat extends ContentPanel implements Editor<RequestModel> {
	
	//private RequestModelProperties properties = GWT.create(RequestModelProperties.class);	
	//private Grid<RequestModel> grid = this.buildGrid(); 
//	private RequestModel requestModel ;

	
	interface EditDriver extends SimpleBeanEditorDriver<RequestModel, Page_Treat> {}
	EditDriver editDriver = GWT.create(EditDriver.class);
// 	Grid<RequestModel> grid; 
	Image image = new Image(); 
	RequestModel requestModel ; 
	
	@Path("patientModel.insNo")
	TextField insNo = new TextField();
	
	@Path("patientModel.korName")
	TextField patientKorName 	= new TextField();
	
	@Path("patientModel.genderName")
	TextField genderName 	= new TextField();
	
	@Path("patientModel.birthday")
	DateField birthday 	= new DateField();
	
	@Path("patientModel.note")
	TextArea patientNote = new TextArea(); // 특기사항
	
	DateField requestDate = new DateField();
	@Path("requestUserModel.note")
	TextField korName 	= new TextField();
	TextArea requestNote = new TextArea(); // 특기사항

	public Page_Treat(){
		editDriver.initialize(this);
		this.setHeaderVisible(false);
		this.add(this.getEditor());
		this.addButton(new TextButton("저장"));
		this.setButtonAlign(BoxLayoutPack.CENTER);
	}
	

	private FormPanel getEditor(){
		
	    HorizontalLayoutData rowLayout = new HorizontalLayoutData(180, -1); // 컬럼크기 
	    rowLayout.setMargins(new Margins(0, 20, 0, 0)); // 컬럼간의 간격조정 
	    
    	HorizontalLayoutContainer row00 = new HorizontalLayoutContainer();
    	row00.add(new FieldLabel(insNo, "보험번호"), rowLayout);
    	row00.add(new FieldLabel(patientKorName, "환자명"), rowLayout);
    	row00.add(new FieldLabel(genderName, "  성별"), rowLayout);
    	
    	row00.add(new FieldLabel(birthday, "생일"), rowLayout);
    	
    	HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
    	row01.add(new FieldLabel(patientNote, "특기사항"), new HorizontalLayoutData(1, 60, new Margins(0, 0, 0, 0)));

    	HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
    	row02.add(new FieldLabel(requestDate, "요청일"), rowLayout);
    	row02.add(new FieldLabel(korName, "보건의"), rowLayout);

    	HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
    	row03.add(new FieldLabel(requestNote, "진료요청"), new HorizontalLayoutData(1, 80, new Margins(0, 0, 0, 0)));
    	
	    VerticalLayoutContainer layout = new VerticalLayoutContainer(); 
	    layout.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
	    
	    layout.add(row00, new VerticalLayoutData(1, -1, new Margins(16)));
	    layout.add(row01, new VerticalLayoutData(1, 64, new Margins(16))); 
	    layout.add(row02, new VerticalLayoutData(1, -1, new Margins(16)));
	    layout.add(row03, new VerticalLayoutData(1, 84, new Margins(16)));
	    
	    // form setting 
		FormPanel form = new FormPanel(); 
    	form.setBorders(false);
	    form.setWidget(layout);
	    form.setLabelWidth(60); // 모든 field 적용 후 설정한다. 
	    return form;
	}
	
	public void retrieve(RequestModel requestModel){
		
		if(requestModel != null){
			this.requestModel = requestModel;
		}
		else {
			this.requestModel = new RequestModel();
		}
	}
}
