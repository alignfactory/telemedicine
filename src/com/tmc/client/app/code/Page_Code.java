package com.tmc.client.app.code;

import java.util.List;

import com.tmc.client.app.sys.model.CodeModel;
import com.tmc.client.app.sys.model.CodeModelProperties;
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

public class Page_Code extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private CodeModelProperties properties = GWT.create(CodeModelProperties.class);
	private Grid<CodeModel> grid = this.buildGrid();
	private Long codeKindId; 
	
	public Page_Code() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		this.add(this.grid, new VerticalLayoutData(1, 1));
	}
	
	private Grid<CodeModel> buildGrid(){
		
		GridBuilder<CodeModel> gridBuilder = new GridBuilder<CodeModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.code(), 60, "코드", new TextField());
		gridBuilder.addText(properties.name(), 120, "코드명", new TextField()) ;
		gridBuilder.addDate(properties.applyDate(), 100, "적용일", new DateField()) ;
		gridBuilder.addBoolean(properties.closeYnFlag(), 60, "종료") ;
		gridBuilder.addText(properties.seq(), 80, "조회순서", new TextField());
		gridBuilder.addText(properties.note(), 400, "상세설명", new TextField());
		return gridBuilder.getGrid(); 
	}

	
	public void retrieveCode(Long codeKindId){
		this.codeKindId = codeKindId;
		this.retrieve();
	}

	@Override
	public void insertRow(){
		GridInsertRow<CodeModel> service = new GridInsertRow<CodeModel>(); 
		CodeModel codeModel = new CodeModel();
		codeModel.setCodeKindId(this.codeKindId);
		service.insertRow(grid, codeModel);
	}
	@Override
	public void update(){
		GridUpdateData<CodeModel> service = new GridUpdateData<CodeModel>(); 
		service.update(this.grid.getStore(), "sys.Code.update"); 
	}
	@Override
	public void deleteRow(){
		GridDeleteData<CodeModel> service = new GridDeleteData<CodeModel>();
		List<CodeModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(this.grid.getStore(), checkedList, "sys.Code.delete");
	}

	@Override
	public void retrieve() {
		grid.getStore().clear();
		if(this.codeKindId != null){
			GridRetrieveData<CodeModel> service = new GridRetrieveData<CodeModel>(grid.getStore());
			service.addParam("codeKindId", this.codeKindId);
			service.retrieve("sys.Code.selectByCodeKindId");
		}
	} 
	
}