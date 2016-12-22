package com.tmc.client.app.code;

import java.util.List;

import com.tmc.client.app.sys.model.LicenseCodeModel;
import com.tmc.client.app.sys.model.LicenseCodeModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_LicenseCode extends VerticalLayoutContainer implements InterfaceGridOperate  {

	private LicenseCodeModelProperties properties = GWT.create(LicenseCodeModelProperties.class);
	private Grid<LicenseCodeModel> grid = this.buildGrid();
	private TextField licenseName = new TextField();
	
	public Tab_LicenseCode() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(licenseName, "자격면허명", 180, 70, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		this.add(this.grid, new VerticalLayoutData(1, 1));
		
	}

	private Grid<LicenseCodeModel> buildGrid(){
		
		GridBuilder<LicenseCodeModel> gridBuilder = new GridBuilder<LicenseCodeModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.licenseCode(), 120, "자격면허코드", new TextField());
		gridBuilder.addText(properties.licenseName(), 300, "자격면허명", new TextField()) ;
		gridBuilder.addText(properties.issueOrgName(), 200, "발급기관명", new TextField()) ;
		gridBuilder.addDate(properties.applyDate(), 100, "적용일", new DateField()) ;
		
		gridBuilder.addBoolean(properties.closeYnFlag(), 100, "사용여부") ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());
	
		return gridBuilder.getGrid(); 
	}
	
	
	@Override
	public void retrieve() {
		GridRetrieveData<LicenseCodeModel> service = new GridRetrieveData<LicenseCodeModel>(grid.getStore());
		service.addParam("licenseName", licenseName.getValue());
		service.retrieveAll("sys.LicenseCode.selectByName");
	}

	@Override
	public void update() {
		GridUpdateData<LicenseCodeModel> service = new GridUpdateData<LicenseCodeModel>(); 
		service.update(grid.getStore(), "sys.LicenseCode.update"); 
	}

	@Override
	public void insertRow() {
		GridInsertRow<LicenseCodeModel> service = new GridInsertRow<LicenseCodeModel>(); 
		service.insertRow(grid, new LicenseCodeModel());
	}

	@Override
	public void deleteRow() {
		GridDeleteData<LicenseCodeModel> service = new GridDeleteData<LicenseCodeModel>();
		List<LicenseCodeModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.LicenseCode.delete");
	}
}