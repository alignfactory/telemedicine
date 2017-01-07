package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.RequestModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
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
	TextField patientNote = new TextField(); // 특기사항
	
	TextField treatStateName = new TextField(); // 상태구분
	
	DateField requestDate = new DateField();
	DateField treatDate = new DateField(); // 진료일
	
	@Path("requestUserModel.note")
	TextField korName 	= new TextField();
	TextArea requestNote = new TextArea(); // 특기사항

	@Path("treatUserModel.korName")
	TextField treatKorName 	= new TextField();
	TextArea treatNote = new TextArea(); // 진료내역
	
	public Page_Treat(){
		editDriver.initialize(this);
		this.setHeaderVisible(false);
		this.add(this.getEditor());
		
		TextButton checkupInsert = new TextButton("검사오더 등록");
		checkupInsert.setWidth(100);
		this.addButton(checkupInsert);
		
		TextButton checkupDelete = new TextButton("검사오더 삭제");
		checkupDelete.setWidth(100);
		this.addButton(checkupDelete);
		
		TextButton treateUpdate = new TextButton("저장");
		treateUpdate.setWidth(100);
		this.addButton(treateUpdate);
		
		this.setButtonAlign(BoxLayoutPack.CENTER);
		//this.getButtonBar().setVerticalSpacing(20);
		this.getButtonBar().setPadding(new Padding(0, 0, 15, 0));
		
//		.setLayoutData(new Margins(0, 0, 30, 0));
	}

	private FormPanel getEditor(){
		
	    HorizontalLayoutData rowLayout = new HorizontalLayoutData(180, -1); // 컬럼크기 
	    rowLayout.setMargins(new Margins(0, 20, 0, 0)); // 컬럼간의 간격조정 
	    
    	HorizontalLayoutContainer row00 = new HorizontalLayoutContainer();
    	row00.add(new FieldLabel(insNo, "보험번호"), rowLayout);
    	row00.add(new FieldLabel(patientKorName, "환자명"), rowLayout);
    	row00.add(new FieldLabel(genderName, "성별"), rowLayout);
    	row00.add(new FieldLabel(birthday, "생일"), rowLayout);
    	insNo.setReadOnly(true);
    	patientKorName.setReadOnly(true);
    	genderName.setReadOnly(true);
    	birthday.setReadOnly(true);
    	birthday.setHideTrigger(true);
    	
    	HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
    	row01.add(new FieldLabel(patientNote, "특기사항"), new HorizontalLayoutData(1, 30));
    	patientNote.setReadOnly(true);
    	
    	HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
    	row02.add(new FieldLabel(requestDate, "진료예정일"), rowLayout);
    	row02.add(new FieldLabel(treatStateName, "상태구분"), rowLayout);
    	row02.add(new FieldLabel(korName, "담당보건의"), rowLayout);
    	row02.add(new FieldLabel(treatDate, "진료일"), rowLayout);
    	row02.add(new FieldLabel(treatKorName, "진료전문의"), rowLayout);
    	
    	treatStateName.setReadOnly(true);
    	requestDate.setReadOnly(true);
    	requestDate.setHideTrigger(true);
    	korName.setReadOnly(true);
    	treatDate.setReadOnly(true);
    	treatDate.setHideTrigger(true);
    	treatKorName.setReadOnly(true);
    	
    	HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
    	row03.add(new FieldLabel(requestNote, "요청내역"), new HorizontalLayoutData(1, 68));
    	requestNote.setReadOnly(true);
    	
    	HorizontalLayoutContainer row04 = new HorizontalLayoutContainer();
    	Page_Checkup pageCheckup = new Page_Checkup(); 
    	pageCheckup.setBorders(true);
    	row04.add(new FieldLabel(pageCheckup, "검사오더"), new HorizontalLayoutData(1, 160));
    	
    	HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
    	row05.add(new FieldLabel(treatNote, "처방내용"), new HorizontalLayoutData(1, 1));
    	
	    VerticalLayoutContainer layout = new VerticalLayoutContainer(); 
	    layout.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
	    
	    layout.add(row00, new VerticalLayoutData(1, -1, new Margins(18)));
	    layout.add(row01, new VerticalLayoutData(1, -1, new Margins(18))); 
	    layout.add(row02, new VerticalLayoutData(1, -1, new Margins(18)));
	    layout.add(row03, new VerticalLayoutData(1, 76, new Margins(18)));
	    layout.add(row04, new VerticalLayoutData(1, 170, new Margins(18)));
	    layout.add(row05, new VerticalLayoutData(1, 1, new Margins(18)));
	    
	    // form setting 
		FormPanel form = new FormPanel(); 
    	form.setBorders(false);
	    form.setWidget(layout);
	    form.setLabelWidth(70); // 모든 field 적용 후 설정한다. 
	    return form;
	}
	
	public void retrieve(RequestModel requestModel){
		
		if(requestModel != null){
			this.requestModel = requestModel;
		}
		else {
			this.requestModel = new RequestModel();
		}
		
		editDriver.edit(requestModel);
	}
}
