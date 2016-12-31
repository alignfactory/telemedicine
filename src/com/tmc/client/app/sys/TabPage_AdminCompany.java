package com.tmc.client.app.sys;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.UserCompanyModel;
import com.tmc.client.app.sys.model.UserCompanyModelProperties;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.service.InterfaceCallback;
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

public class TabPage_AdminCompany extends ContentPanel implements InterfaceTabPage, InterfaceGridOperate, InterfaceLookupResult {
	
	private UserCompanyModelProperties properties = GWT.create(UserCompanyModelProperties.class);
	private Grid<UserCompanyModel> grid = this.buildGrid();
	private Long userId = null;
	
	private TabPage_AdminCompany getThis(){
		return this; 
	}
	
	public TabPage_AdminCompany() {
		
		this.setHeaderVisible(false); 
		this.add(this.grid);
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addDeleteButton();

		this.getButtonBar().add(searchBarBuilder.getSearchBar()); 
		this.setButtonAlign(BoxLayoutPack.CENTER);
	}
	
	public Grid<UserCompanyModel> buildGrid(){
		
		GridBuilder<UserCompanyModel> gridBuilder = new GridBuilder<UserCompanyModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addText(properties.companyName(), 250, "고객명") ;
		gridBuilder.addText(properties.bizNo(), 100, "사업자번호") ;
		gridBuilder.addText(properties.telNo01(), 100, "대표전화번호") ;
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
	public void retrieve() {
		if(this.userId != null){
			GridRetrieveData<UserCompanyModel> service = new GridRetrieveData<UserCompanyModel>(grid.getStore());
			service.addParam("userId", this.userId);
			service.retrieve("sys.UserCompany.selectByUserId");
		}
	}
	

	@Override
	public void update() {
		GridUpdateData<UserCompanyModel> service = new GridUpdateData<UserCompanyModel>(); 
		service.update(grid.getStore(), "sys.UserCompany.update"); 
	}

	@Override
	public void insertRow() {
		if(userId != null){
			new Lookup_AdminCompany(this.getThis(), this.userId).show();
		}
	}

	@Override
	public void deleteRow() {
		GridDeleteData<UserCompanyModel> service = new GridDeleteData<UserCompanyModel>();
		List<UserCompanyModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.UserCompany.delete");
	}

	@Override
	public void setLookupResult(Object result) {
		
		@SuppressWarnings("unchecked")
		List<CompanyModel> companyList = (List<CompanyModel>)result;  
		
		for(final CompanyModel companyModel:companyList){

			final UserCompanyModel userCompanyModel = new UserCompanyModel();
			userCompanyModel.setCompanyId(companyModel.getCompanyId());
			userCompanyModel.setUserId(this.userId);
			
			GridInsertRow<UserCompanyModel> service = new GridInsertRow<UserCompanyModel>();
			
			service.addCallback(new InterfaceCallback(){
				@Override
				public void callback() {
					String companyName = companyModel.getCompanyName() ;  
					String bizNo = companyModel.getBizNo() ; 
					String telNo = companyModel.getTelNo01() ; 
					
					grid.getStore().getRecord(userCompanyModel).addChange(properties.companyName(), companyName);
					grid.getStore().getRecord(userCompanyModel).addChange(properties.bizNo(), bizNo);
					grid.getStore().getRecord(userCompanyModel).addChange(properties.telNo01(), telNo);
				}
				
			});
			service.insertRow(grid, userCompanyModel);
		}
	}
}