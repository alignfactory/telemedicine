package com.tmc.client.app.sys;

import java.util.List;

import com.tmc.client.app.sys.model.RoleModel;
import com.tmc.client.app.sys.model.RoleModelProperties;
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

public class Tab_Role extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private RoleModelProperties properties = GWT.create(RoleModelProperties.class);
	private Grid<RoleModel> grid = this.buildGrid();
	private TextField roleName = new TextField();
	private Page_TreeMenu treeMenu = new Page_TreeMenu();
	
	public Tab_Role() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(roleName, "권한명", 150, 50, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		this.setBorders(false);
		VerticalLayoutContainer roleContainer  = new VerticalLayoutContainer(); 
		
		roleContainer.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		roleContainer.add(this.grid, new VerticalLayoutData(1, 1));
		
		BorderLayoutData westLayoutData = new BorderLayoutData(0.3);
		westLayoutData.setMargins(new Margins(0, 2, 0, 0)); 
		westLayoutData.setSplit(true);
		westLayoutData.setMaxSize(1000); // TODO: BorderLayoutContainer set max size
		
		this.setWestWidget(roleContainer, westLayoutData);
		this.setCenterWidget(treeMenu);

		this.grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<RoleModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<RoleModel> event) {
				RoleModel role = grid.getSelectionModel().getSelectedItem();   
				treeMenu.retrieve(role.getRoleId());
			} 
		}); 
	}
	
	public Grid<RoleModel> buildGrid(){
		
		GridBuilder<RoleModel> gridBuilder = new GridBuilder<RoleModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.roleName(), 150, "권한명", new TextField());
		gridBuilder.addText(properties.seq(), 50, "순서", new TextField()) ;
		gridBuilder.addBoolean(properties.managerYnBoolean(), 70, "ADMIN") ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());
	
		return gridBuilder.getGrid(); 
	}

	@Override
	public void retrieve() {
		GridRetrieveData<RoleModel> service = new GridRetrieveData<RoleModel>(grid.getStore()); 
		service.retrieveAll("sys.Role.selectByAll");
	}

	@Override
	public void update() {
		GridUpdateData<RoleModel> service = new GridUpdateData<RoleModel>(); 
		service.update(grid.getStore(), "sys.Role.update"); 
	}

	@Override
	public void insertRow() {
		GridInsertRow<RoleModel> service = new GridInsertRow<RoleModel>(); 
		service.insertRow(grid, new RoleModel());
	}

	@Override
	public void deleteRow() {
		GridDeleteData<RoleModel> service = new GridDeleteData<RoleModel>();
		List<RoleModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.Role.delete");
	}
}