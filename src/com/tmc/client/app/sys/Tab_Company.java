package com.tmc.client.app.sys;

import java.util.HashMap;
import java.util.Map;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.CompanyModelProperties;
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

public class Tab_Company extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private CompanyModelProperties properties = GWT.create(CompanyModelProperties.class);
	private Grid<CompanyModel> grid = this.buildGrid();
	private TextField companyName = new TextField(); 
	private PlainTabPanel tabPanel = new PlainTabPanel();

	public Tab_Company() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(companyName, "고객명", 150, 50, true); 
		searchBarBuilder.addRetrieveButton(); 
		this.setNorthWidget(searchBarBuilder.getSearchBar(), new BorderLayoutData(40));

		this.setCenterWidget(grid);
		this.grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<CompanyModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<CompanyModel> event) {
				retrieveTabPage();
			} 
		}); 
		
		tabPanel.add(new TabPage_Company(this.grid), "상세정보"); 
		
		ContentPanel dummyPanel = new ContentPanel(); 
		dummyPanel.setHeaderVisible(false);
		tabPanel.add(dummyPanel, "계약내역");
		
		tabPanel.addSelectionHandler(new SelectionHandler<Widget> (){
			@Override
			public void onSelection(SelectionEvent<Widget> arg0) {
				retrieveTabPage();
			}
		}); 
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(tabPanel, new VerticalLayoutData(1, 1, new Margins(5, 0, 10, 1)));
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.add(vlc);
		
		BorderLayoutData southLayoutData = new BorderLayoutData(0.4);
		southLayoutData.setMargins(new Margins(2, 0, 0, 0)); 
		southLayoutData.setSplit(true);
		southLayoutData.setMaxSize(1000); // TODO: BorderLayoutContainer set max size

		this.setSouthWidget(panel, southLayoutData);
	}
	
	private Grid<CompanyModel> buildGrid(){

		GridBuilder<CompanyModel> gridBuilder = new GridBuilder<CompanyModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		gridBuilder.addText(properties.companyName(), 150, "고객명");
		gridBuilder.addText(properties.bizNo(), 100, "사업자번호");
		gridBuilder.addText(properties.telNo01(), 120, "전화번호1");
		gridBuilder.addText(properties.telNo02(), 120, "전화번호2");
		gridBuilder.addText(properties.faxNo01(), 120, "팩스번호");
		gridBuilder.addDate(properties.annvDate(), 100, "창립기념일");
		gridBuilder.addText(properties.zipCode(), 100, "우편번호");
		gridBuilder.addText(properties.zipAddress(), 300, "우편번호주소");
		gridBuilder.addText(properties.zipDetail(), 300, "상세주소");
		gridBuilder.addText(properties.note(), 400, "비고");
	
		return gridBuilder.getGrid(); 
	}
	
	public void retrieveTabPage(){

		InterfaceTabPage selectedPage = (InterfaceTabPage)tabPanel.getActiveWidget();
		CompanyModel companyModel = this.grid.getSelectionModel().getSelectedItem(); 
		
		if(companyModel != null){
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("companyModel", companyModel); 
			selectedPage.retrieve(param);
		}
		else {
			selectedPage.retrieve(null);
		}
	}

	@Override
	public void retrieve() {
		GridRetrieveData<CompanyModel> service = new GridRetrieveData<CompanyModel>(grid.getStore()); 
		service.retrieveAll("sys.Company.selectByAll");
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