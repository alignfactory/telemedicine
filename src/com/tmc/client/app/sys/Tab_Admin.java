package com.tmc.client.app.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.sys.model.UserModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_Admin extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private UserModelProperties properties = GWT.create(UserModelProperties.class);
	private Grid<UserModel> grid = this.buildGrid();
	private TextField userNameField = new TextField();
	private PlainTabPanel  tabPanel = new PlainTabPanel ();
	
	public Tab_Admin() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addTextField(userNameField, "관리자명", 180, 56, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		this.setBorders(false);
		this.setNorthWidget(searchBarBuilder.getSearchBar(), new BorderLayoutData(40)); 
		this.setCenterWidget(this.grid, new BorderLayoutData(0.4));

		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<UserModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<UserModel> event) {
				retrieveTabpage(); 
			}
		});
		 
		tabPanel.add(new TabPage_AdminRole(), "메뉴권한"); 
		tabPanel.add(new TabPage_AdminCompany(), "고객권한");
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
		
		gridBuilder.addText(properties.korName(), 80, "한글이름", new TextField()) ;
		gridBuilder.addText(properties.ctzNo(), 120, "주민번호", new TextField()) ;
		
		gridBuilder.addText(properties.loginId(), 180, "아이디", new TextField()) ;
		ColumnConfig<UserModel, String> password = gridBuilder.addText(properties.passwd(), 120, "패스워드", new PasswordField());

		password.setCell(new AbstractCell<String>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context arg0, String arg1, SafeHtmlBuilder arg2) {
				arg2.appendHtmlConstant("********");   
			}
		});
		
		final ComboBoxField genderComboBox = new ComboBoxField("GenderCode");  
		genderComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				UserModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.genderCode(), genderComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.genderName(), 60, "성별", genderComboBox) ;
		
		final ComboBoxField nationComboBox = new ComboBoxField("NationCode");  
		genderComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				UserModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.nationCode(), nationComboBox.getCode());
			}
		}); 

		gridBuilder.addText(properties.nationName(), 80, "국적", nationComboBox) ;
		gridBuilder.addText(properties.engName(), 120, "영문명", new TextField()) ;
		gridBuilder.addDate(properties.birthday(), 100, "생일", new DateField()) ;
		gridBuilder.addText(properties.telNo01(), 120, "전화번호1", new TextField()) ;
		gridBuilder.addText(properties.telNo02(), 120, "전화번호2", new TextField()) ;
		gridBuilder.addText(properties.zipCode(), 80, "우편번호", new TextField()) ;
		gridBuilder.addText(properties.zipAddress(), 250, "우편번호주소", new TextField()) ;
		gridBuilder.addText(properties.zipDetail(), 250, "상세주소", new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

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
		service.retrieve("sys.User.selectAdminUserByUserName");
	}

	@Override
	public void update() {
		GridUpdateData<UserModel> service = new GridUpdateData<UserModel>(); 
		service.update(grid.getStore(), "sys.User.updateAdminUser"); // 업데이트 시 회사정보가 null이면 강제로 넣어준다. 
	}

	@Override
	public void insertRow() {
		GridInsertRow<UserModel> service = new GridInsertRow<UserModel>();
		service.insertRow(grid, new UserModel());
	}

	@Override
	public void deleteRow() {
		GridDeleteData<UserModel> service = new GridDeleteData<UserModel>();
		List<UserModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.User.deleteAdminUser");
	}
}
