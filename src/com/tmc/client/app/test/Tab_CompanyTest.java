package com.tmc.client.app.test;

import com.tmc.client.app.sys.model.CompanyModel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_CompanyTest extends BorderLayoutContainer {
	
	Grid_CompanyTest gridCompany = new Grid_CompanyTest(); 
	Form_CompanyTest formCompany = new Form_CompanyTest(gridCompany.getGrid().getStore());
	
	public Tab_CompanyTest() {

		this.setBorders(false);
		this.setNorthWidget(this.getToolBar(), new BorderLayoutData(40));

		// grid setting 
		gridCompany.getGrid().setLayoutData(new MarginData(0,0,1,0)); // 아래에 리사이즈바를 넣는다. 
		this.setCenterWidget(gridCompany.getGrid());

		gridCompany.getGrid().getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<CompanyModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<CompanyModel> event) {
				if(event.getSelection().size() > 0 ){
					CompanyModel company = event.getSelection().get(0);
					formCompany.retrieve(company);
				}
			}
		}); 
		
		BorderLayoutData bottom = new BorderLayoutData(400);
		bottom.setMargins(new Margins(1, 0, 0, 0)); 
		bottom.setSplit(true);
		
//		ContentPanel panel = new ContentPanel(); 
//		panel.setHeaderVisible(false);
//		panel.add(formCompany);
		
		this.setSouthWidget(formCompany, bottom);
	}
	
	private ButtonBar getToolBar(){
		
		ButtonBar buttonBar = new ButtonBar();
		buttonBar.setEnableOverflow(true);

		TextField companyName = new TextField();
		companyName.setWidth(120);
		
		FieldLabel comapanyNameLabel = new FieldLabel(companyName, "고객명"); 
		comapanyNameLabel.setWidth(200);
		comapanyNameLabel.setLabelWidth(50);
		buttonBar.add(comapanyNameLabel); 
		
		TextButton retrieveButton = new TextButton("조회"); 
		retrieveButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				gridCompany.retrieve();
			}
		}); 
		buttonBar.add(retrieveButton);

		TextButton updateButton = new TextButton("저장"); 
		updateButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				gridCompany.update();
			}
		}); 
		buttonBar.add(updateButton);

		TextButton insertButton = new TextButton("입력"); 
		insertButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				gridCompany.insertRow();
			}
		}); 
		buttonBar.add(insertButton);

		TextButton deleteButton = new TextButton("삭제"); 
		deleteButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				gridCompany.deleteRow();
			}
		}); 
		buttonBar.add(deleteButton);
		
		return buttonBar; 
	}
}