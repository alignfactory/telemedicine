package com.tmc.client.app.code;

import java.util.List;

import com.tmc.client.app.sys.model.CodeKindModel;
import com.tmc.client.app.sys.model.CodeKindModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_CodeKind extends BorderLayoutContainer implements InterfaceGridOperate {

	private CodeKindModelProperties properties = GWT.create(CodeKindModelProperties.class);
	private Grid<CodeKindModel> grid = this.buildGrid();
	private TextField codeKindName = new TextField();
	private Page_Code pageCode = new Page_Code(); 
	
	public Tab_CodeKind() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(codeKindName, "코드구분명", 180, 70, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		vlc.add(this.grid, new VerticalLayoutData(1, 1));
		
		BorderLayoutData westLayoutData = new BorderLayoutData(600);
		westLayoutData.setMargins(new Margins(0, 4, 0, 0)); 
		westLayoutData.setSplit(true);
		westLayoutData.setMaxSize(1000);  

		this.setWestWidget(vlc, westLayoutData);
		this.setCenterWidget(pageCode); 
		
		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<CodeKindModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<CodeKindModel> event) {
				CodeKindModel codeKind = grid.getSelectionModel().getSelectedItem(); 
				pageCode.retrieveCode(codeKind.getCodeKindId());
			}
		}); 
	}
	
	private Grid<CodeKindModel> buildGrid(){
		
		GridBuilder<CodeKindModel> gridBuilder = new GridBuilder<CodeKindModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.kindCode(), 120, "구분코드", new TextField());
		gridBuilder.addText(properties.kindName(), 150, "코드구분명", new TextField()) ;
		gridBuilder.addBoolean(properties.sysYnFlag(), 60, "시스템") ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}

	@Override
	public void retrieve() {
		GridRetrieveData<CodeKindModel> service = new GridRetrieveData<CodeKindModel>(grid.getStore()); 
		service.retrieve("sys.CodeKind.selectByAll");
	}

	@Override
	public void update() {
		GridUpdateData<CodeKindModel> service = new GridUpdateData<CodeKindModel>(); 
		service.update(grid.getStore(), "sys.CodeKind.update"); 
	}

	@Override
	public void insertRow() {
		GridInsertRow<CodeKindModel> service = new GridInsertRow<CodeKindModel>(); 
		service.insertRow(grid, new CodeKindModel());
	}

	@Override
	public void deleteRow() {
		GridDeleteData<CodeKindModel> service = new GridDeleteData<CodeKindModel>();
		List<CodeKindModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.CodeKind.delete");
	}
}