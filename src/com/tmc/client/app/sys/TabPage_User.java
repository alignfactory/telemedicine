package com.tmc.client.app.sys;


import java.util.List;
import java.util.Map;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class TabPage_User extends ContentPanel implements InterfaceTabPage, Editor<UserModel>, InterfaceGridOperate {
	
	interface EditDriver extends SimpleBeanEditorDriver<UserModel, TabPage_User> {}
	private EditDriver editDriver = GWT.create(EditDriver.class);
	private Grid<UserModel> grid ; 
	private Image image = new Image(); 
	private UserModel userModel; 
	
	public TextField korName 	= new TextField();
	public TextField ctzNo 	= new TextField();
	public TextField engName 	= new TextField(); 
	public TextField chnName 	= new TextField(); 
	
	
	public TextField genderCode = new TextField();
	public ComboBoxField genderName = new ComboBoxField("GenderCode");
	
	public TextField nationCode= new TextField();
	public ComboBoxField nationName = new ComboBoxField("NationCode");
	
	public DateField birthday 	= new DateField(); 
	public TextField email 	= new TextField();
	public TextField faxNo 	= new TextField();
	public TextField telNo01 	= new TextField();
	public TextField telNo02 	= new TextField();
	public TextField zipCode 	= new TextField();
	public TextField zipAddress= new TextField();
	public TextField zipDetail = new TextField();
	public TextArea scholar 	= new TextArea();
	
	public TextField schoolName 	= new TextField();
	public TextField graduateYear	= new TextField();
	public TextField mainMajor 	= new TextField();
	public TextField subMajor 		= new TextField();
	public TextField militaryTypeCode 	= new TextField();
	public ComboBoxField militaryTypeName = new ComboBoxField("MilitaryTypeCode");
	
	public TextField bankCode = new TextField(); 
	public ComboBoxField bankName = new ComboBoxField("BankCode");  
	
	public TextField accountNo 	= new TextField();
	public TextField accountHolder	= new TextField();
	
	
	public TextArea career 	= new TextArea();
	public TextArea note 		= new TextArea();
	
	public TextField loginId 	= new TextField();
	public PasswordField passwd 	= new PasswordField();
	
	public TabPage_User(Grid<UserModel> grid){
		
		this.setHeaderVisible(false);
		this.editDriver.initialize(this);
		this.grid = grid ; 
	    
		// image setting 
	    ContentPanel imagePanel = new ContentPanel();
	    imagePanel.setHeaderVisible(false);
	    image.setPixelSize(120,  160); // 고정 Pixel 
	    image.setUrl("FileDownload?fileId=0");
	    imagePanel.add(image);
	    
	    TextButton addImageButton = new TextButton("사진등록");
	    addImageButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				addUserImage(); 
			}
		});
		imagePanel.addButton(addImageButton);
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
		if(userModel != null && userModel.getUserId() != null){
			Lookup_UserImage lookupUserImage = new Lookup_UserImage(image, userModel.getUserId());
			lookupUserImage.show();
		}
		else {
			Info.display("교원확인", "사진을 등록할 사용자을 먼저 선택하여 주세요.");
			return ; 
		}
	}
	
	@Override
	public void retrieve(Map<String, Object> param) {
		this.userModel = (UserModel)param.get("UserModel");
		this.setEnabled(true);
		this.retrieve();
	}
	
	
	private FormPanel getEditor(){
		
	    HorizontalLayoutData rowLayout = new HorizontalLayoutData(250, -1); // 컬럼크기 
	    rowLayout.setMargins(new Margins(0, 50, 5, 0)); // 컬럼간의 간격조정 
	    
    	HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
    	row01.add(new FieldLabel(korName, "한글이름"), rowLayout);
	    row01.add(new FieldLabel(ctzNo, "주민번호"), rowLayout);

    	row01.add(new FieldLabel(genderName, "남녀구분"), rowLayout);
		genderName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				genderCode.setValue(genderName.getCode());
			}
		}); 

    	
    	row01.add(new FieldLabel(nationName, "국적"), rowLayout);
		nationName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				nationCode.setValue(nationName.getCode());
			}
		}); 

    	HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
    	row02.add(new FieldLabel(telNo01, "헨드폰"), rowLayout);
	    row02.add(new FieldLabel(telNo02, "집전화"), rowLayout);
    	row02.add(new FieldLabel(email, "이메일"), rowLayout);
	    row02.add(new FieldLabel(faxNo, "팩스"), rowLayout);

    	HorizontalLayoutContainer row11 = new HorizontalLayoutContainer();

    	row11.add(new FieldLabel(bankName, "은행명"), rowLayout);
    	bankName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				bankCode.setValue(bankName.getCode());
			}
		}); 
    	
	    row11.add(new FieldLabel(accountNo, "계좌번호"), rowLayout);
    	row11.add(new FieldLabel(accountHolder, "예금주"), rowLayout);
    	
	    HorizontalLayoutContainer row09 = new HorizontalLayoutContainer();
	    row09.add(new FieldLabel(loginId, "아이디"), rowLayout);
	    row09.add(new FieldLabel(passwd, "패스워드"),  rowLayout);
	    
	    HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
    	row03.add(new FieldLabel(zipCode, "우편번호"),rowLayout);
	    row03.add(new FieldLabel(zipAddress, "우편주소"), new HorizontalLayoutData(750, -1, new Margins(0, 50, 0, 0)));
	    
	    HorizontalLayoutContainer row04 = new HorizontalLayoutContainer();
	    row04.add(new FieldLabel(zipDetail, "상세주소"), new HorizontalLayoutData(1000,  -1, new Margins(0, 50, 0, 0)));

	    HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
	    row05.add(new FieldLabel(birthday, "생일"), rowLayout);
	    row05.add(new FieldLabel(engName, "영문명"), rowLayout);
	    row05.add(new FieldLabel(chnName, "한자명"), rowLayout);
	    row05.add(new FieldLabel(militaryTypeName, "병역구분"), rowLayout);
	    militaryTypeName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				militaryTypeCode.setValue(militaryTypeName.getCode());
			}
		}); 

	    HorizontalLayoutContainer row10 = new HorizontalLayoutContainer();
	    row10.add(new FieldLabel(schoolName, "최종학교"), rowLayout);
	    row10.add(new FieldLabel(graduateYear, "졸업년도"), rowLayout);
	    row10.add(new FieldLabel(mainMajor, "전공(1)"), rowLayout);
	    row10.add(new FieldLabel(subMajor, "전공(2)"), rowLayout);
	    
    	HorizontalLayoutContainer row06 = new HorizontalLayoutContainer();
    	row06.add(new FieldLabel(scholar, "기타학력"), new HorizontalLayoutData(1000, 56, new Margins(0, 50, 0, 0)));

    	HorizontalLayoutContainer row07 = new HorizontalLayoutContainer();
    	row07.add(new FieldLabel(career, "경력사항"), new HorizontalLayoutData(1000, 56, new Margins(0, 50, 0, 0)));
    	
    	HorizontalLayoutContainer row08 = new HorizontalLayoutContainer();
    	row08.add(new FieldLabel(note, "기타사항"), new HorizontalLayoutData(1000, 56, new Margins(0, 50, 0, 0)));
	    
	    VerticalLayoutContainer layout = new VerticalLayoutContainer(); 
	    layout.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
	    
	    layout.add(row01, new VerticalLayoutData(1, -1, new Margins(15))); 
	    layout.add(row02, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row11, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row09, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row03, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row04, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row05, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row10, new VerticalLayoutData(1, -1, new Margins(15)));
	    layout.add(row06, new VerticalLayoutData(1, 60, new Margins(15)));
	    layout.add(row07, new VerticalLayoutData(1, 60, new Margins(15)));
	    layout.add(row08, new VerticalLayoutData(1, 60, new Margins(15)));
	    
		// form setting 
		FormPanel form = new FormPanel(); 
    	form.setBorders(false);
	    form.setWidget(layout);
	    form.setLabelWidth(60); // 모든 field 적용 후 설정한다. 
	    return form;
	}

	@Override
	public void retrieve() {
		editDriver.edit(this.userModel);
		image.setUrl("FileDownload?fileId=" + userModel.getUserId() + "&time=" +  System.currentTimeMillis()); // 사진 가져오기
	}

	@Override
	public void insertRow() {
		GridInsertRow<UserModel> service = new GridInsertRow<UserModel>(); 
		UserModel userModel = new UserModel(); 
		userModel.setCompanyId(LoginUser.getLoginUser().getCompanyId()); 
		service.insertRow(grid, userModel);
	}
	
	@Override 
	public void update(){
		this.grid.getStore().update(editDriver.flush());
		GridUpdateData<UserModel> service = new GridUpdateData<UserModel>(); 
		service.update(this.grid.getStore(), editDriver.flush(), "sys.User.update"); 
	}

	@Override
	public void deleteRow() {
		GridDeleteData<UserModel> service = new GridDeleteData<UserModel>();
		List<UserModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.User.delete");
		this.setEnabled(false);
	}
}