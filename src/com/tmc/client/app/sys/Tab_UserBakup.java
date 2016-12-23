package com.tmc.client.app.sys;

import java.util.HashMap;
import java.util.Map;

import com.tmc.client.app.emp.TabPage_Hire;
import com.tmc.client.app.emp.TabPage_License;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.sys.model.UserModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_UserBakup extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private UserModelProperties properties = GWT.create(UserModelProperties.class);
	private Grid<UserModel> grid = this.buildGrid();
	private TextField userNameField = new TextField();
	private PlainTabPanel  tabPanel = new PlainTabPanel ();
	
	public Tab_UserBakup() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(userNameField, "교원명", 150, 46, true); 
		searchBarBuilder.addRetrieveButton(); 
		
		this.setBorders(false);
		this.setNorthWidget(searchBarBuilder.getSearchBar(), new BorderLayoutData(40)); 
		this.setCenterWidget(this.grid, new BorderLayoutData(0.4));

		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<UserModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<UserModel> event) {
				retrieveTabpage(); 
			}
		});
		
		tabPanel.add(new TabPage_User(this.grid), "상세정보"); 
		tabPanel.add(new TabPage_Hire(), "고용정보");
		tabPanel.add(new TabPage_License(), "자격면허"); 
		tabPanel.add(new TabPage_AdminRole(), "권한설정"); 
//		tabPanel.add(new ContentPanel(), "발령");
//		tabPanel.add(new ContentPanel(), "급여");

		tabPanel.addSelectionHandler(new SelectionHandler<Widget>(){
			@Override
			public void onSelection(SelectionEvent<Widget> arg0) {
				if(arg0 != null) {
					retrieveTabpage();
				} 
			}
		}); 
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(tabPanel, new VerticalLayoutData(1, 1, new Margins(5, 0, 10, 1)));
		
		ContentPanel panel = new ContentPanel(); //setMargins이 적용되지 않아 한번 더 감싼다. 
		panel.setHeaderVisible(false);
		panel.add(vlc);
		
		BorderLayoutData southLayoutData = new BorderLayoutData(0.6);
		southLayoutData.setMargins(new Margins(2,0,0,0));
		southLayoutData.setSplit(true);
		southLayoutData.setMaxSize(1000);
		
		this.setSouthWidget(panel, southLayoutData);
	}
	
	public Grid<UserModel> buildGrid(){
		
		GridBuilder<UserModel> gridBuilder = new GridBuilder<UserModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.korName(), 80, "한글이름") ;
		gridBuilder.addText(properties.ctzNo(), 120, "주민번호") ;
		gridBuilder.addText(properties.genderName(), 60, "성별") ;
		gridBuilder.addText(properties.nationName(), 80, "국적") ;
		gridBuilder.addText(properties.engName(), 120, "영문명") ;
		
		gridBuilder.addDate(properties.birthday(), 100, "생일") ;
		gridBuilder.addText(properties.telNo01(), 120, "전화번호1") ;
		gridBuilder.addText(properties.telNo02(), 120, "전화번호2") ;
		gridBuilder.addText(properties.zipCode(), 80, "우편번호") ;
		gridBuilder.addText(properties.zipAddress(), 250, "우편번호주소") ;
		gridBuilder.addText(properties.zipDetail(), 250, "상세주소") ;
		gridBuilder.addText(properties.note(), 400, "비고");

		return gridBuilder.getGrid(); 
	}

	public void retrieveTabpage(){
		InterfaceTabPage selectedPage = (InterfaceTabPage)tabPanel.getActiveWidget();		

		UserModel user = grid.getSelectionModel().getSelectedItem() ; 
		if(user != null){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("UserModel", user); 
			selectedPage.retrieve(param);
		}
		else {
			selectedPage.retrieve(null);
		}
	}

	@Override
	public void retrieve() {
		GridRetrieveData<UserModel> service = new GridRetrieveData<UserModel>(grid.getStore());
		service.addParam("userName", userNameField.getValue());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.retrieve("sys.User.selectByName");
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void insertRow() {
	}
	
	@Override
	public void deleteRow() {
	}
}