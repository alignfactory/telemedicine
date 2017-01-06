package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.CheckupModel;
import com.tmc.client.app.tmc.model.CheckupModelProperties;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Page_Checkup extends ContentPanel implements InterfaceGridOperate {
	
	private CheckupModelProperties properties = GWT.create(CheckupModelProperties.class);
	private Grid<CheckupModel> grid = this.buildGrid();

	public Page_Checkup(){
		this.setHeaderVisible(false);
		this.add(grid);
	}
	
	public Grid<CheckupModel> buildGrid(){

		GridBuilder<CheckupModel> gridBuilder = new GridBuilder<CheckupModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		final ComboBoxField checkupTypeComboBox = new ComboBoxField("CheckupTypeCode");  
		checkupTypeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				CheckupModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.checkupCode(), checkupTypeComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.checkupCode(), 100, "검사종류", checkupTypeComboBox) ;
		gridBuilder.addText(properties.checkupName(), 120, "검사확인사항", new TextField()) ;

		
		final ComboBoxField checkupProcessComboBox = new ComboBoxField("CheckupProcessCode");  
		checkupProcessComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				CheckupModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.processCode(), checkupProcessComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.checkupName(), 120, "상태구분", checkupProcessComboBox) ;

		gridBuilder.addText(properties.checkupResult(), 200, "검사결과", new DateField()) ;
		gridBuilder.addDate(properties.checkupDate(), 100, "검사일", new DateField()) ;
		gridBuilder.addText(properties.userKorName(), 80, "검사담당") ;
		
		return gridBuilder.getGrid(); 
	}

	@Override
	public void retrieve() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertRow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRow() {
		// TODO Auto-generated method stub
		
	}

}

