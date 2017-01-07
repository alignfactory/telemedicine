package com.tmc.client.app.psc;

import java.util.HashMap;
import java.util.Map;

import com.tmc.client.app.psc.model.StudentModel;
import com.tmc.client.app.psc.model.StudentModelProperties;
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

public class Tab_Student extends BorderLayoutContainer implements InterfaceGridOperate {

	private StudentModelProperties properties = GWT.create(StudentModelProperties.class);
	private Grid<StudentModel> grid = this.buildGrid();
	private TextField korName  = new TextField();
	private PlainTabPanel tabPanel = new PlainTabPanel();
	
	public Tab_Student() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addTextField(korName, "원생명", 150, 50, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addDeleteButton();
		
		VerticalLayoutContainer studentContianer = new VerticalLayoutContainer(); 
		studentContianer.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		studentContianer.add(this.grid, new VerticalLayoutData(1, 1));
		
		BorderLayoutData westLayoutData = new BorderLayoutData(400);
		westLayoutData.setMargins(new Margins(0, 2, 0, 0)); 
		westLayoutData.setSplit(true);
		westLayoutData.setMaxSize(1000);  
		this.setWestWidget(studentContianer, westLayoutData);
		
		this.grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<StudentModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<StudentModel> event) {
				if(event.getSelection().size() > 0){
					retrieveTabpage(); 
				}
			} 
		}); 

		tabPanel.add(new TabPage_Student(this.grid), "기본정보");
		tabPanel.add(new TabPage_Register(), "배정정보");
		tabPanel.addSelectionHandler(new SelectionHandler<Widget>(){
			@Override
			public void onSelection(SelectionEvent<Widget> arg0) {
				if(arg0 != null) {
					retrieveTabpage();
				} 
			}
		}); 
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(tabPanel, new VerticalLayoutData(1, 1, new Margins(6, 0, 10, 1)));
		ContentPanel panel = new ContentPanel(); // setMargins이 적용되지 않아 한번 더 감싼다. 
		panel.setHeaderVisible(false);
		panel.add(vlc);
		
		this.setCenterWidget(panel);
	}
	
	private void retrieveTabpage(){

		InterfaceTabPage selectedPage = (InterfaceTabPage)tabPanel.getActiveWidget();
		StudentModel student = this.grid.getSelectionModel().getSelectedItem();
		
		if(student != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("studentModel", student); 
			selectedPage.retrieve(param);
		}
		else {
			selectedPage.retrieve(null); 
		}

	}
	public Grid<StudentModel> buildGrid(){

		GridBuilder<StudentModel> gridBuilder = new GridBuilder<StudentModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addText(properties.studentNo(), 50, "원번") ;
		gridBuilder.addText(properties.korName(), 80, "이름") ;
		gridBuilder.addText(properties.genderName(), 60, "성별") ;
		gridBuilder.addText(properties.ctzNo(), 120, "주민번호") ;
		gridBuilder.addText(properties.note(), 200, "비고");

		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve(){
		this.grid.getStore().clear();
		GridRetrieveData<StudentModel> service = new GridRetrieveData<StudentModel>(this.grid.getStore());
		service.addParam("companyId", LoginUser.getLoginUser().getCompanyId());
		service.retrieve("psc.Student.selectByCompanyId");
	}
	@Override
	public void insertRow() {
	}
	@Override
	public void update() {
	}
	@Override
	public void deleteRow(){
	}

}