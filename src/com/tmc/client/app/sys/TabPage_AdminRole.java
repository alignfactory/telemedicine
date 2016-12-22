package com.tmc.client.app.sys;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.sys.model.RoleModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.sys.model.UserRoleModel;
import com.tmc.client.app.sys.model.UserRoleModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceCallback;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class TabPage_AdminRole extends ContentPanel implements InterfaceTabPage, InterfaceGridOperate, InterfaceLookupResult {
	
	 
	private UserRoleModelProperties properties = GWT.create(UserRoleModelProperties.class);
	private Grid<UserRoleModel> grid = this.buildGrid();
	private Long userId = null;

	private TabPage_AdminRole getThis(){
		return this; 
	}
	
	public TabPage_AdminRole() {
		this.setHeaderVisible(false); 
		this.add(this.grid);
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addDeleteButton();

		this.getButtonBar().add(searchBarBuilder.getSearchBar()); 
		this.setButtonAlign(BoxLayoutPack.CENTER);
	}

	public Grid<UserRoleModel> buildGrid(){
		GridBuilder<UserRoleModel> gridBuilder = new GridBuilder<UserRoleModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.MULTI);
		
		gridBuilder.addText(properties.roleName(), 250, "권한명") ;
		gridBuilder.addText(properties.seq(), 80, "순서", new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}

	@Override
	public void retrieve(Map<String, Object> param) {
		this.grid.getStore().clear();
		
		if(param != null){
			UserModel userModel = (UserModel)param.get("UserModel"); 
			this.userId = userModel.getUserId(); 
			this.retrieve();
		}
		else {
			this.userId = null; 
		}
	}

	@Override
	public void setLookupResult(Object result) {

		@SuppressWarnings("unchecked")
		List<RoleModel> roleList = (List<RoleModel>)result;  
		
		for(final RoleModel roleModel  : roleList){
			
			final UserRoleModel userRoleModel = new UserRoleModel();
			userRoleModel.setUserId(this.userId);
			userRoleModel.setRoleId(roleModel.getRoleId());
			
			GridInsertRow<UserRoleModel> service = new GridInsertRow<UserRoleModel>();
			service.addCallback(new InterfaceCallback(){
				@Override
				public void callback() {
					// role이 등록된것을 보여주기 위하여 따로 등록한다. 
					grid.getStore().getRecord(userRoleModel).addChange(properties.roleName(), roleModel.getRoleName());
				}
			});
			service.insertRow(this.grid, userRoleModel);
		}
	}

	@Override
	public void retrieve() {
		if(this.userId != null){
			GridRetrieveData<UserRoleModel> service = new GridRetrieveData<UserRoleModel>(grid.getStore());
			service.addParam("userId", userId);
			service.retrieve("sys.UserRole.selectByUserId");
		}
	}

	@Override
	public void update() {
		GridUpdateData<UserRoleModel> service = new GridUpdateData<UserRoleModel>(); 
		service.update(grid.getStore(), "sys.UserRole.update"); 
	}

	@Override
	public void insertRow() {
		
		if(userId != null){
			new Lookup_AdminRole(this.getThis(), this.userId).show();
		}
	}

	@Override
	public void deleteRow() {
		GridDeleteData<UserRoleModel> service = new GridDeleteData<UserRoleModel>();
		List<UserRoleModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.UserRole.delete");
	}
	
}