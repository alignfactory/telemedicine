package com.tmc.client.app.psc;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.psc.model.StudentModel;
import com.tmc.client.app.psc.model.StudyClassModel;
import com.tmc.client.app.sys.Lookup_UserImage;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.LongField;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
 
public class TabPage_Student extends ContentPanel implements Editor<StudentModel>, InterfaceTabPage, InterfaceLookupResult, InterfaceGridOperate {

	interface EditDriver extends SimpleBeanEditorDriver<StudentModel, TabPage_Student> {}
	EditDriver editDriver = GWT.create(EditDriver.class);
	Grid<StudentModel> grid; 
	Image image = new Image(); 
	StudentModel studentModel ; 
	
	TextField studentNo = new TextField();
	TextField korName 	= new TextField();
	TextField ctzNo 	= new TextField();
	TextField engName 	= new TextField(); 
	TextField chnName 	= new TextField(); 
	TextField genderCode = new TextField(); 
	ComboBoxField genderName = new ComboBoxField("GenderCode");
	
	DateField birthday 	= new DateField();
	
	TextField tel01 	= new TextField();
	TextField tel02 	= new TextField();
	TextField email 	= new TextField();
	TextField zipCode 	= new TextField();
	TextField zipAddress= new TextField();
	TextField detailAddress = new TextField();
	TextArea childNote = new TextArea(); // 특기사항
	TextField character 	= new TextField(); // 성격
	TextField speciality 	= new TextField(); // 특기
	TextField habit = new TextField(); // 교정할 버릇 
	TextField likeFood = new TextField(); // 좋아하는 음식
	TextField hateFood = new TextField(); // 싫어하는 음식 
	TextField choiceSchool = new TextField(); // 희망학교 
	TextField career = new TextField(); // 입학전 학력 

	TextField dadName 	= new TextField();
	DateField dadBirthday 	= new DateField();
	TextField dadJob 	= new TextField();
	TextField dadWorkplace = new TextField();
	TextField dadTel01= new TextField();
	TextField dadTel02= new TextField();
	TextField dadScholar = new TextField();
	TextField dadReligion = new TextField();
	
	TextField momName 	= new TextField();
	DateField momBirthday 	= new DateField();
	TextField momJob 	= new TextField();
	TextField momWorkplace = new TextField();
	TextField momTel01= new TextField();
	TextField momTel02= new TextField();
	TextField momScholar = new TextField();
	TextField momReligion = new TextField();
	
	TextField grandFatherYn = new TextField();
	TextField grandMotherYn = new TextField();
	
	LongField upBrotherCount = new LongField(); 
	LongField downBrotherCount = new LongField();
	LongField upSisterCount = new LongField();
	LongField downSisterCount = new LongField();

	TextField supportYn = new TextField(); 
	LongField supportAmount = new LongField(); 
	
	
	TextField bankCode 		= new TextField();
	ComboBoxField bankName 	= new ComboBoxField("BankCode");
	TextField accountNo 	= new TextField();
	TextField accountHolder	= new TextField();

	@Path("classStudentModel.assignDate")
	DateField assignDate = new DateField();
	
	@Path("classStudentModel.studyClassId")
	LongField studyClassId = new LongField(); 
	
	@Path("classStudentModel.studyClassModel.studyClassName") // data path 설정하기  
	LookupTriggerField studyClassName = new LookupTriggerField() ; 
	
    private Radio radioGrandFatherYes = new Radio();
    private Radio radioGrandFatherNo = new Radio();
    private Radio radioGrandMotherYes = new Radio();
    private Radio radioGrandMotherNo = new Radio();
	private CheckBox checkBoxSupportYn = new CheckBox();
	private Lookup_StudyClass lookupWindow = new Lookup_StudyClass(getThis());
	
	private TabPage_Student getThis(){
		return this;
	}
	
	public TabPage_Student(Grid<StudentModel> grid){

		this.grid = grid;
		editDriver.initialize(this);
		this.setHeaderVisible(false);

		ContentPanel imagePanel = new ContentPanel();
		imagePanel.setHeaderVisible(false);
	    image.setPixelSize(160, 200);
	    image.setUrl("FileDownload?fileId=0");
	    imagePanel.add(image);
	    
	    TextButton addImageButton = new TextButton("사진등록");
	    addImageButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				addUserImage(); 
			}
		});

	    TextButton retrieveDetailButton = new TextButton("카드출력");
	    retrieveDetailButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(studentModel != null){
					new PDF_Student(studentModel.getStudentId()).show();
				}
				else {
					new SimpleMessage("원생카드를 출력할 대상 원아를 선택해 주세요."); 
				}
			}
		});

	    imagePanel.addButton(addImageButton);
	    imagePanel.addButton(retrieveDetailButton);
	    imagePanel.setButtonAlign(BoxLayoutPack.CENTER);
	    
	    // button bar setting 
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addDeleteButton();
		
		this.getButtonBar().add(searchBarBuilder.getSearchBar());
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.add(this.getEditor());
	    
	    HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
	    hlc.add(imagePanel, new HorizontalLayoutData(-1, -1, new Margins(16, 10, 10, 10)));
	    hlc.add(getEditor(), new HorizontalLayoutData(1, 1, new Margins(0)));
	    this.add(hlc);
	}

	private void addUserImage(){
		if(this.studentModel != null){
			new Lookup_UserImage(image, studentModel.getStudentId()).show();
		}
		else {
			Info.display("원생확인", "사진을 등록할 원생을 먼저 선택하여 주세요.");
			return ; 
		}
	} 
	
	@Override
	public void setLookupResult(Object result) {
		// 반이름 찾기의 결과를 돌려받는다. 
		StudyClassModel studyClassModel = (StudyClassModel)result; 
		this.studyClassName.setValue(studyClassModel.getStudyClassName());
		this.studyClassId.setValue(studyClassModel.getStudyClassId());
	}
	
	@Override
	public void retrieve(Map<String, Object> param) {
		if(param == null){
			editDriver.edit(new StudentModel());
			return ; 
		}
		
		this.studentModel = (StudentModel)param.get("studentModel"); 

		editDriver.edit(this.studentModel);
		this.assignDate.clearInvalid();
		this.studyClassName.clearInvalid();
		
		// reload를 하려면 parameter를 바꾸어 줘야 한다. 현재시간은 매번 바뀌므로 같이 전달한다. 
		image.setUrl("FileDownload?fileId=" + studentModel.getStudentId() + "&time=" +  System.currentTimeMillis());  
		
		if("true".equals(this.studentModel.getGrandFatherYn())){
		    radioGrandFatherYes.setValue(true, false);
		    radioGrandFatherNo.setValue(false, false); 
		}
		else {
		    radioGrandFatherYes.setValue(false, false);
		    radioGrandFatherNo.setValue(true, false); 
		}
		
		if("true".equals(this.studentModel.getGrandMotherYn())){
		    radioGrandMotherYes.setValue(true, false);
		    radioGrandMotherNo.setValue(false, false); 
		}
		else {
			radioGrandMotherYes.setValue(false, false);
		    radioGrandMotherNo.setValue(true, false); 
		}
		
		// 지원여부 확인 
		if("true".equals(this.studentModel.getSupportYn())) {
			checkBoxSupportYn.setValue(true, false);
		}
		else {
			checkBoxSupportYn.setValue(false, false);
		}
	}
	
	private FormPanel getEditor(){
		
	    HorizontalLayoutData rowLayout = new HorizontalLayoutData(220, -1); // 컬럼크기 
	    rowLayout.setMargins(new Margins(0, 20, 5, 0)); // 컬럼간의 간격조정 
	    
    	HorizontalLayoutContainer row00 = new HorizontalLayoutContainer();
    	row00.add(new FieldLabel(studentNo, "원생번호"), rowLayout);
    	row00.add(new FieldLabel(assignDate, "배정일"), rowLayout);
    	assignDate.setAllowBlank(false);
   	 	
    	row00.add(new FieldLabel(studyClassName, "반이름"), rowLayout);
   	 	studyClassName.setAllowBlank(false);

   	 	studyClassName.addTriggerClickHandler(new TriggerClickHandler(){
   	 		@Override
			public void onTriggerClick(TriggerClickEvent event) {
				if(studentModel != null){
					 
					lookupWindow.show();
				}
			}
   	 	}); 
   	 	
	    HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
    	row01.add(new FieldLabel(korName, "한글이름"), rowLayout);
	    row01.add(new FieldLabel(ctzNo, "주민번호"), rowLayout);
    	row01.add(new FieldLabel(genderName, "성별"), rowLayout);
    	genderName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				genderCode.setValue(genderName.getCode());
			}
    	}); 

	    HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
	    row02.add(new FieldLabel(birthday, "생일"), rowLayout);
	    row02.add(new FieldLabel(engName, "영문명"), rowLayout);
	    row02.add(new FieldLabel(chnName, "한자명"), rowLayout);

    	HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
    	row03.add(new FieldLabel(tel01, "헨드폰"), rowLayout);
	    row03.add(new FieldLabel(tel02, "집전화"), rowLayout);
    	row03.add(new FieldLabel(email, "이메일"), rowLayout);

    	HorizontalLayoutContainer row031 = new HorizontalLayoutContainer();
    	// childNote.setAllowBlank(false);

    	row031.add(new FieldLabel(childNote, "특기사항"), new HorizontalLayoutData(690, 80, new Margins(0, 50, 0, 0)));
    	
    	HorizontalLayoutContainer row032 = new HorizontalLayoutContainer();
    	row032.add(new FieldLabel(character, "성격"), new HorizontalLayoutData(440, -1, new Margins(0, 20, 0, 0)));
	    row032.add(new FieldLabel(speciality, "특기"), rowLayout);

	    HorizontalLayoutContainer row033 = new HorizontalLayoutContainer();
	    row033.add(new FieldLabel(habit, "버릇"), rowLayout);
	    row033.add(new FieldLabel(likeFood, "좋아하는 음식"), rowLayout);
	    row033.add(new FieldLabel(hateFood, "싫어하는 음식"), rowLayout);
	    
    	HorizontalLayoutContainer row034 = new HorizontalLayoutContainer();
    	row034.add(new FieldLabel(choiceSchool, "희망학교"), rowLayout);
	    row034.add(new FieldLabel(career, "이전학력"), new HorizontalLayoutData(440, -1, new Margins(0, 20, 0, 0)));
		
	    HorizontalLayoutContainer row04 = new HorizontalLayoutContainer();
    	row04.add(new FieldLabel(zipCode, "우편번호"),rowLayout);
    	
    	HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
	    row05.add(new FieldLabel(zipAddress, "우편주소"), new HorizontalLayoutData(690, -1, new Margins(0, 50, 0, 0)));
	    
	    
	    HorizontalLayoutContainer row06 = new HorizontalLayoutContainer();
	    row06.add(new FieldLabel(detailAddress, "상세주소"), new HorizontalLayoutData(690,  -1, new Margins(0, 50, 0, 0)));
	    
    	HorizontalLayoutContainer row07 = new HorizontalLayoutContainer();
    	row07.add(new FieldLabel(dadName, "아빠이름"), rowLayout);
	    row07.add(new FieldLabel(momName, "엄마이름"), rowLayout);
    	
	    radioGrandFatherYes.setBoxLabel("유");
	    radioGrandFatherNo.setBoxLabel("무");
	    radioGrandFatherNo.setValue(true);
	 
	    // we can set name on radios or use toggle group
	    ToggleGroup toggleGrandFather = new ToggleGroup();
	    toggleGrandFather.add(radioGrandFatherYes);
	    toggleGrandFather.add(radioGrandFatherNo);
	    toggleGrandFather.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
			@Override
			public void onValueChange(ValueChangeEvent<HasValue<Boolean>> arg0) {
		        ToggleGroup group = (ToggleGroup) arg0.getSource();
		        Radio radio = (Radio) group.getValue();
		        if(radio.getBoxLabel().toString().indexOf("유") > 0 ){
		        	grandFatherYn.setValue("true");
		        }
		        else {
		        	grandFatherYn.setValue("false");
		        }
			}
	    });
	    
	    HorizontalPanel radioPanaleGrandFather = new HorizontalPanel();
	    radioPanaleGrandFather.add(radioGrandFatherYes);
	    radioPanaleGrandFather.add(radioGrandFatherNo);
	    row07.add(new FieldLabel(radioPanaleGrandFather, "조부"),  rowLayout);
	    
    	HorizontalLayoutContainer row08 = new HorizontalLayoutContainer();
    	row08.add(new FieldLabel(dadBirthday, "아빠생일"), rowLayout);
	    row08.add(new FieldLabel(momBirthday, "엄마생일"), rowLayout);
	    
	    radioGrandMotherYes.setBoxLabel("유");
	    radioGrandMotherNo.setBoxLabel("무");
	    radioGrandMotherNo.setValue(true); // default value 

	    ToggleGroup toggleGrandMother = new ToggleGroup();
	    toggleGrandMother.add(radioGrandMotherYes);
	    toggleGrandMother.add(radioGrandMotherNo);
	    toggleGrandMother.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
			@Override
			public void onValueChange(ValueChangeEvent<HasValue<Boolean>> arg0) {
		        ToggleGroup group = (ToggleGroup) arg0.getSource();
		        Radio radio = (Radio) group.getValue();
		        if(radio.getBoxLabel().toString().indexOf("유") > 0 ){
		        	grandMotherYn.setValue("true");
		        }
		        else {
		        	grandMotherYn.setValue("false");
		        }
			}
	    });

	    HorizontalPanel radioPanaleGrandMother = new HorizontalPanel();
	    radioPanaleGrandMother.add(radioGrandMotherYes);
	    radioPanaleGrandMother.add(radioGrandMotherNo);
	    row08.add(new FieldLabel(radioPanaleGrandMother, "조모"),  rowLayout);
	    
    	HorizontalLayoutContainer row09 = new HorizontalLayoutContainer();
    	row09.add(new FieldLabel(dadJob, "아빠직장"), rowLayout);
	    row09.add(new FieldLabel(momJob, "엄마직장"), rowLayout);
	    row09.add(new FieldLabel(upBrotherCount, "형"), new HorizontalLayoutData(100,  -1, new Margins(0, 5, 0, 0)));
	    
	    FieldLabel countLabel = new FieldLabel(null, "명"); 
	    countLabel.setLabelSeparator("");
	    
	    row09.add(countLabel) ; 
	    		
    	HorizontalLayoutContainer row10 = new HorizontalLayoutContainer();
    	row10.add(new FieldLabel(dadWorkplace, "근무지역"), rowLayout);
	    row10.add(new FieldLabel(momWorkplace, "근무지역"), rowLayout);
	    row10.add(new FieldLabel(downBrotherCount, "제"), new HorizontalLayoutData(100,  -1, new Margins(0, 5, 0, 0)));
	    FieldLabel countLabel2 = new FieldLabel(null, "명"); 
	    countLabel2.setLabelSeparator("");

	    row10.add(countLabel2) ; 
	    
    	HorizontalLayoutContainer row11 = new HorizontalLayoutContainer();
    	row11.add(new FieldLabel(dadTel01, "아빠전화"), rowLayout);
	    row11.add(new FieldLabel(momTel01, "엄마전화"), rowLayout);
	    row11.add(new FieldLabel(upSisterCount, "자"), new HorizontalLayoutData(100,  -1, new Margins(0, 5, 0, 0)));
	    FieldLabel countLabel3 = new FieldLabel(null, "명"); 
	    countLabel3.setLabelSeparator("");

	    row11.add(countLabel3) ; 
	    
    	HorizontalLayoutContainer row13 = new HorizontalLayoutContainer();
    	row13.add(new FieldLabel(dadScholar, "아빠학력"), rowLayout);
	    row13.add(new FieldLabel(momScholar, "엄마학력"), rowLayout);
	    row13.add(new FieldLabel(downSisterCount, "매"), new HorizontalLayoutData(100,  -1, new Margins(0, 5, 0, 0)));
	    FieldLabel countLabel4 = new FieldLabel(null, "명"); 
	    countLabel4.setLabelSeparator("");

	    row13.add(countLabel4) ; 
	    
    	HorizontalLayoutContainer row14 = new HorizontalLayoutContainer();
    	row14.add(new FieldLabel(dadReligion, "아빠종교"), rowLayout);
	    row14.add(new FieldLabel(momReligion, "엄마종교"), rowLayout);

    	HorizontalLayoutContainer row20 = new HorizontalLayoutContainer();

    	checkBoxSupportYn.setBoxLabel("지원대상");
    	checkBoxSupportYn.addValueChangeHandler(new ValueChangeHandler<Boolean>(){

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> arg0) {
				supportYn.setValue(arg0.getValue().toString());
			}
    	}); 
    	
    	HorizontalPanel checkBoxPanel = new HorizontalPanel();
    	checkBoxPanel.add(checkBoxSupportYn);
    	    
    	row20.add(new FieldLabel(checkBoxPanel, "지원여부"), rowLayout);
    	row20.add(new FieldLabel(supportAmount, "지원금액"), rowLayout);
    	supportAmount.setFormat(NumberFormat.getFormat("###,###"));
    	supportAmount.setDirection(Direction.RTL);
    	

	    HorizontalLayoutContainer row21 = new HorizontalLayoutContainer();
    	row21.add(new FieldLabel(bankName, "은행명"), rowLayout);
    	bankName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				bankCode.setValue(bankName.getCode());
			}
    	}); 

	    row21.add(new FieldLabel(accountNo, "계좌번호"), rowLayout);
    	row21.add(new FieldLabel(accountHolder, "예금주"), rowLayout);
	    
	    VerticalLayoutContainer layout = new VerticalLayoutContainer(); 
	    layout.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
	    
	    layout.add(row00, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row01, new VerticalLayoutData(1, -1, new Margins(15))); 
	    layout.add(row02, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row03, new VerticalLayoutData(1, -1, new Margins(15)));
	    
	    layout.add(row032, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row033, new VerticalLayoutData(1, -1, new Margins(15)));
	    
	    layout.add(new HorizontalLayoutContainer(), new VerticalLayoutData(1, -1, new Margins(10)));
	    layout.add(row034, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row031, new VerticalLayoutData(1, 82, new Margins(15))); // 특기사항 
	    
	    layout.add(new HorizontalLayoutContainer(), new VerticalLayoutData(1, -1, new Margins(10))); // 가족
	    layout.add(row07, new VerticalLayoutData(1, -1, new Margins(15)));  
	    layout.add(row08, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row09, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row10, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row11, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row13, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row14, new VerticalLayoutData(1, -1, new Margins(15)));

	    layout.add(new HorizontalLayoutContainer(), new VerticalLayoutData(1, -1, new Margins(10))); // 주소
	    layout.add(row04, new VerticalLayoutData(1, -1, new Margins(15)));  
	    layout.add(row05, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row06, new VerticalLayoutData(1, -1, new Margins(15)));

	    
	    layout.add(new HorizontalLayoutContainer(), new VerticalLayoutData(1, -1, new Margins(10)));

	    layout.add(row20, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row21, new VerticalLayoutData(1, -1, new Margins(15)));
	    
		// form setting 
		FormPanel form = new FormPanel(); 
    	form.setBorders(false);
	    form.setWidget(layout);
	    form.setLabelWidth(60); // 모든 field 적용 후 설정한다. 
	    return form;
	}

	@Override
	public void retrieve() {
	}

	@Override
	public void insertRow() {
		GridInsertRow<StudentModel> service = new GridInsertRow<StudentModel>(); 
		StudentModel student = new StudentModel(); 
		student.setCompanyId(LoginUser.getLoginUser().getCompanyId()); 
		service.insertRow(grid, student);
	}

	@Override
	public void update(){
		if(assignDate.getCurrentValue() == null){
			new SimpleMessage("배정일은 반드시 등록하여야 합니다."); 
			return; 
		}
		
		if(studyClassName.getCurrentValue() == null ){
			new SimpleMessage("반이름은 반드시 등록하여야 합니다."); 
			return ; 
		}
		
		grid.getStore().update(editDriver.flush());
		GridUpdateData<StudentModel> service = new GridUpdateData<StudentModel>(); 
		service.update(grid.getStore(), editDriver.flush(), "psc.Student.update");
	}

	@Override
	public void deleteRow() {
		GridDeleteData<StudentModel> service = new GridDeleteData<StudentModel>();
		List<StudentModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(this.grid.getStore(), checkedList, "psc.Student.delete");
	}
}