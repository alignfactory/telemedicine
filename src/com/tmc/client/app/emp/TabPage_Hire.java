package com.tmc.client.app.emp;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.emp.model.HireModel;
import com.tmc.client.app.emp.model.HireModelProperties;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class TabPage_Hire extends ContentPanel implements InterfaceTabPage, InterfaceGridOperate {
	
	private HireModelProperties properties = GWT.create(HireModelProperties.class);
	private Grid<HireModel> grid = this.buildGrid(); 
	
	private Long userId = null; 
	
	public TabPage_Hire() {
		
		this.setBorders(false); 
		this.setHeaderVisible(false);
		this.add(this.grid);
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		//searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();

		this.getButtonBar().add(searchBarBuilder.getSearchBar()); // buttonBar에 search Bar를 얹는다.  
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.getButtonBar().setLayoutData(new Margins(0));
	}
	
	@Override
	public void retrieve(Map<String, Object> param) {
		if(param != null){
			UserModel user = (UserModel)param.get("UserModel"); 
			this.setUserId(user.getUserId());
			this.retrieve();
		}
		else {
			this.userId = null; 
			grid.getStore().clear();
		}
	}

	public void setUserId(long userId){
		this.userId = userId; 
	}
	
	private Grid<HireModel> buildGrid(){

		GridBuilder<HireModel> gridBuilder = new GridBuilder<HireModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addDate(properties.hireDate(), 100, "채용일", new DateField()) ;
		
		final ComboBoxField hireCodeComboBox = new ComboBoxField("HireCode");  
		hireCodeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				HireModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.hireCode(), hireCodeComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.hireName(), 100, "채용구분", hireCodeComboBox) ;
		gridBuilder.addText(properties.hireReason(), 200, "채용사유상세", new TextField()) ;
		gridBuilder.addDate(properties.retireDate(), 100, "퇴직일", new DateField()) ;
		
		final ComboBoxField retireCodeComboBox = new ComboBoxField("RetireCode");  
		retireCodeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				HireModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.retireCode(), retireCodeComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.retireName(), 100, "퇴직구분", retireCodeComboBox) ;
		gridBuilder.addText(properties.retireReason(), 200, "퇴직사유상세", new TextField()) ;
		gridBuilder.addText(properties.note(), 200, "비고", new TextField() );

		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve() {
		if(this.userId != null){
			GridRetrieveData<HireModel> service = new GridRetrieveData<HireModel>(grid.getStore());
			service.addParam("userId", userId);
			service.retrieve("emp.Hire.selectByUserId");
		}
		else {
			grid.getStore().clear();
		}
	}

	@Override
	public void update() {
		GridUpdateData<HireModel> service = new GridUpdateData<HireModel>(); 
		service.update(grid.getStore(), "emp.Hire.update"); 
	}

	@Override
	public void insertRow() {
		if(this.userId == null){
			new SimpleMessage("사용자가 선택되어 있지 않습니다"); 
			return; 
		}
		GridInsertRow<HireModel> service = new GridInsertRow<HireModel>(); 
		HireModel hireModel = new HireModel();
		hireModel.setUserId(this.userId);
		service.insertRow(grid, hireModel);
	}

	@Override
	public void deleteRow() {
		GridDeleteData<HireModel> service = new GridDeleteData<HireModel>();
		List<HireModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "emp.Hire.delete");
	}
}