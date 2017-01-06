package com.tmc.client.app.sys;

import java.util.List;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.sys.model.UserModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_User extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private UserModelProperties properties = GWT.create(UserModelProperties.class);
	private Grid<UserModel> grid = this.buildGrid();

	private CompanyModel companyModel = LoginUser.getLoginUser().getCompanyModel(); 
	private LookupTriggerField lookupCompanyField = this.getLookupCompanyField() ;
	private TextField userNameField = new TextField();
	
	public Tab_User() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "기관명", 250, 48); 
		searchBarBuilder.addLabel(userNameField, "담당자", 150, 46, true); 

		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	
	private LookupTriggerField getLookupCompanyField(){
		
		Lookup_Company lookupCompany = new Lookup_Company();
		lookupCompany.setCallback(new InterfaceLookupResult(){
			@Override
			public void setLookupResult(Object result) {
				companyModel = (CompanyModel)result;// userCompanyModel.getCompanyModel(); 
				lookupCompanyField.setValue(companyModel.getCompanyName());
			}
		});
		
		LookupTriggerField lookupCompanyField = new LookupTriggerField(); 
		lookupCompanyField.setEditable(false);
		lookupCompanyField.setText(this.companyModel.getCompanyName());
		lookupCompanyField.addTriggerClickHandler(new TriggerClickHandler(){
   	 		@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			lookupCompany.show();
			}
   	 	}); 
		return lookupCompanyField; 
	}

	public Grid<UserModel> buildGrid(){
		
		GridBuilder<UserModel> gridBuilder = new GridBuilder<UserModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.ctzNo(), 80, "직원번호", new TextField()) ;		
		gridBuilder.addText(properties.korName(), 80, "이름 ", new TextField()) ;

		final ComboBoxField genderComboBox = new ComboBoxField("GenderCode");  
		genderComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				UserModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.genderCode(), genderComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.genderName(), 60, "성별", genderComboBox) ;
		gridBuilder.addDate(properties.birthday(), 100, "생년월일", new DateField()) ;
		gridBuilder.addText(properties.mainMajor(), 160, "전공과목", new TextField()) ;		
		gridBuilder.addDate(properties.startDate(), 100, "근무시작일", new DateField()) ;
		gridBuilder.addDate(properties.closeDate(), 100, "근무종료일", new DateField()) ;
		gridBuilder.addText(properties.email(), 150, "이메일주소", new TextField()) ;
		gridBuilder.addText(properties.telNo01(), 120, "전화번호(1)", new TextField()) ;
		gridBuilder.addText(properties.telNo02(), 120, "전화번호(2)", new TextField()) ;
		gridBuilder.addText(properties.loginId(), 100, "로그인아이디", new TextField()) ;

		ColumnConfig<UserModel, String> password = gridBuilder.addText(properties.passwd(), 100, "패스워드", new PasswordField());
		password.setCell(new AbstractCell<String>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context arg0, String arg1, SafeHtmlBuilder arg2) {
				arg2.appendHtmlConstant("********");   
			}
		});
		
		gridBuilder.addText(properties.zipCode(), 80, "우편번호", new TextField()) ;
		gridBuilder.addText(properties.zipAddress(), 250, "주소", new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}

	@Override
	public void retrieve() {
		
		if(this.companyModel.getCompanyId() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		GridRetrieveData<UserModel> service = new GridRetrieveData<UserModel>(grid.getStore());
		service.addParam("companyId", this.companyModel.getCompanyId());
		service.addParam("userName", userNameField.getValue());
		service.retrieve("sys.User.selectByName");
	}
	
	@Override
	public void update(){
		GridUpdateData<UserModel> service = new GridUpdateData<UserModel>(); 
		service.update(grid.getStore(), "sys.User.update"); 
	}
	
	@Override
	public void insertRow(){
		if(this.companyModel.getCompanyId() == null){
			new SimpleMessage("기관선택", "등록하고자 하는 담당자의 기관을 먼저 선택하여 주세요"); 
			return ; 
		}
		GridInsertRow<UserModel> service = new GridInsertRow<UserModel>(); 
		UserModel userModel = new UserModel();
		userModel.setCompanyId(this.companyModel.getCompanyId());
		service.insertRow(grid, userModel);
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<UserModel> service = new GridDeleteData<UserModel>();
		List<UserModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.User.delete");
	}
}