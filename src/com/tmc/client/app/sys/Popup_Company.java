package com.tmc.client.app.sys;

import java.util.ArrayList;
import java.util.List;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.CompanyModelProperties;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.SimpleMessage;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;

public class Popup_Company extends Window  {

	private CompanyModelProperties properties = GWT.create(CompanyModelProperties.class);
	private ListStore<CompanyModel> listStore = new ListStore<CompanyModel>(properties.keyId()); 
	private Grid<CompanyModel> grid ; 
	
	private TextField companyName = new TextField();
	
	public Popup_Company(Tab_User personTab){
		
		//this.personTab = personTab; 
		
		this.setModal(true);
		//this.center();
		
		this.setHeading("고객 찾기");
		
		this.addShowHandler(new ShowHandler(){
			@Override
			public void onShow(ShowEvent event) {
				setPixelSize(600, 500);
				center();
			}
		}); 
		
		List<ColumnConfig<CompanyModel, ?>> columnList = new ArrayList<ColumnConfig<CompanyModel, ?>>();
		RowNumberer<CompanyModel> rowNum = new RowNumberer<CompanyModel>();
		columnList.add(rowNum);

		// check box column setting 
		IdentityValueProvider<CompanyModel> identity = new IdentityValueProvider<CompanyModel>();
		CheckBoxSelectionModel<CompanyModel> checkBox = new CheckBoxSelectionModel<CompanyModel>(identity);
		checkBox.setSelectionMode(SelectionMode.MULTI);
		
		columnList.add(checkBox.getColumn()); 
		columnList.add(new ColumnConfig<CompanyModel, String>(properties.companyName(), 200, "고객명"));
		columnList.add(new ColumnConfig<CompanyModel, String>(properties.bizNo(), 200, "법인번호"));
		
		ColumnModel<CompanyModel> columnModel = new ColumnModel<CompanyModel>(columnList);
		
		for(int i=0; i<columnModel.getColumns().size(); i++){
			columnModel.getColumns().get(i).setMenuDisabled(true);
		}

		this.grid = new Grid<CompanyModel>(listStore, columnModel);
		this.grid.setSelectionModel(checkBox);
		this.grid.setBorders(false);
		this.grid.getView().setStripeRows(true);
		this.grid.getView().setColumnLines(true); 

		rowNum.initPlugin(this.grid);  

		this.grid.addRowDoubleClickHandler(new RowDoubleClickHandler(){

			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				setCompany(); 
			}
		}); 

		BorderLayoutContainer borderLayout = new BorderLayoutContainer(); 
		borderLayout.setBorders(false);
		borderLayout.setNorthWidget(this.getSearchBar(), new BorderLayoutData(40));
		borderLayout.setCenterWidget(this.grid); 
		
		this.add(borderLayout);
	}
	
	public ButtonBar getSearchBar(){
		
		ButtonBar buttonBar = new ButtonBar();
		
		FieldLabel comapanyNameLabel = new FieldLabel(companyName, "고객명"); 
		comapanyNameLabel.setWidth(200);
		comapanyNameLabel.setLabelWidth(50);
		buttonBar.add(comapanyNameLabel); 
		
		TextButton retrieveButton = new TextButton("조회"); 
		retrieveButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				retrieve();
			}
		}); 
		buttonBar.add(retrieveButton);

	    TextButton confirmButton = new TextButton("확인");
	    confirmButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				setCompany(); 
			}
		});
	    buttonBar.add(confirmButton);

		TextButton cancelButton = new TextButton("취소");
		// cancelButton.setWidth(60);
		cancelButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		});
		
		buttonBar.add(cancelButton);
		
		return buttonBar; 
		
	}
	
	public void retrieve(){
		GridRetrieveData<CompanyModel> service = new GridRetrieveData<CompanyModel>(listStore);
		service.retrieveAll("sys.Company.selectByAll");
	}
	
	private void setCompany(){
		CompanyModel clientModel = grid.getSelectionModel().getSelectedItem(); 
		
		if(clientModel != null){
			//personTab.setCompanyInfo(clientModel.getCompanyName(), clientModel.getCompanyId());
			hide(); 
		}
		else {
			new SimpleMessage("선택된 고객이 없습니다");
		}
	}
}
