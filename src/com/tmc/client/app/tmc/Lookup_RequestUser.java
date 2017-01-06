package com.tmc.client.app.tmc;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.sys.model.UserModelProperties;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.AbstractLookupWindow;
import com.tmc.client.ui.builder.GridBuilder;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Lookup_RequestUser extends AbstractLookupWindow {

	private UserModelProperties properties = GWT.create(UserModelProperties.class);
	private Grid<UserModel> grid = this.buildGrid();
	private TextField userNameField = new TextField();
	private CompanyModel companyModel = new CompanyModel(); 
	
	public Lookup_RequestUser(){
		
		this.setInit("보건의 찾기", 900, 400); 
		this.addLabel(userNameField, "성명", 150, 50, true) ;

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(this.getSearchBar(), new VerticalLayoutData(1, 40)); // , new Margins(0, 0, 0, 5)));
		vlc.add(grid, new VerticalLayoutData(1, 1));
		this.add(vlc);
		
		this.grid.addRowDoubleClickHandler(new RowDoubleClickHandler(){
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				confirm(); 
			}
		}); 
	}
	
	public void setCompanyModel(CompanyModel companyModel){
		this.companyModel = companyModel; 
	}
	
	private Grid<UserModel> buildGrid(){
		GridBuilder<UserModel> gridBuilder = new GridBuilder<UserModel>(properties.keyId());
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.ctzNo(), 90, "직원번호") ;
		gridBuilder.addText(properties.korName(), 80, "이름") ;
		gridBuilder.addText(properties.mainMajor(), 200, "전공과목") ;
		gridBuilder.addText(properties.genderName(), 60, "성별") ;
		gridBuilder.addDate(properties.startDate(), 120, "근무시작일") ;
		gridBuilder.addDate(properties.closeDate(), 120, "근무종료일") ;
		gridBuilder.addText(properties.note(), 200, "비고" );

		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve(){
		if(this.companyModel != null){
			GridRetrieveData<UserModel> service = new GridRetrieveData<UserModel>(grid.getStore());
			service.addParam("companyId", this.companyModel.getCompanyId());
			service.addParam("userName", userNameField.getText());
			service.retrieve("sys.User.selectByName");
		} 
		else {
			new SimpleMessage("선택된 의료기관이 없습니다.");
			return ; 
		}
	}
	
	@Override
	public void confirm() {
		UserModel userModel = grid.getSelectionModel().getSelectedItem(); 
		if(userModel != null){
			this.getCallback().setLookupResult(userModel);
			hide(); 
		}
		else {
			new SimpleMessage("선택된 보건의가 없습니다.");
		}
	}

	@Override
	public void cancel() {
		this.hide(); 
	}

}
