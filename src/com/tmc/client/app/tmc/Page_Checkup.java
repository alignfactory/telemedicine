package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.CheckupModel;
import com.tmc.client.app.tmc.model.CheckupModelProperties;
import com.tmc.client.app.tmc.model.RequestModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Page_Checkup extends ContentPanel  {
	
	private CheckupModelProperties properties = GWT.create(CheckupModelProperties.class);
	private Grid<CheckupModel> grid = this.buildGrid();
	private RequestModel requestModel; 
	
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
		gridBuilder.addText(properties.checkupName(), 100, "검사종류", checkupTypeComboBox) ;
		
		gridBuilder.addText(properties.checkupOrder(), 200, "검사요청사항", new TextField()) ;

		
		final ComboBoxField checkupProcessComboBox = new ComboBoxField("CheckupProcessCode");  
		checkupProcessComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				CheckupModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.processCode(), checkupProcessComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.processName(), 80, "상태구분"); // , checkupProcessComboBox) ;

		gridBuilder.addText(properties.checkupResult(), 250, "검사결과"); //, new DateField()) ;
		gridBuilder.addDate(properties.checkupDate(), 100, "검사일"); // , new DateField()) ;
		gridBuilder.addText(properties.userKorName(), 80, "검사담당") ;
		
		return gridBuilder.getGrid(); 
	}

	public void retrieve(RequestModel requestModel){
		this.grid.getStore().clear(); 
		this.requestModel = requestModel;
		GridRetrieveData<CheckupModel> service = new GridRetrieveData<CheckupModel>(grid.getStore());
		service.addParam("requestId", this.requestModel.getRequestId());
		service.retrieve("tmc.Checkup.selectByRequestId");
	}

	public void insert(){
		if(this.requestModel == null){
			new SimpleMessage("선택된 진료요청 내역이 없습니다."); 
			return ; 
		}
		
		GridInsertRow<CheckupModel> service = new GridInsertRow<CheckupModel>(); 
		CheckupModel checkupModel = new CheckupModel();
		
		// 초기 데이터 설정 
		// checkupModel.setCheckupDate(new Date());
		checkupModel.setProcessCode("10");
		checkupModel.setRequestId(this.requestModel.getRequestId());
		service.insertRow(grid, checkupModel);
	}
	
	public void delete(){
		GridDeleteData<CheckupModel> service = new GridDeleteData<CheckupModel>();
		List<CheckupModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "tmc.Checkup.delete");

	}
	
	public void update(){
		GridUpdateData<CheckupModel> service = new GridUpdateData<CheckupModel>(); 
		service.update(grid.getStore(), "tmc.Checkup.update"); 
	}
	
	public int getRowCount() {
		return grid.getStore().getAll().size(); 
	}
}

