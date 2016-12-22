package com.tmc.client.app.emp;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.code.Lookup_LicenseCode;
import com.tmc.client.app.emp.model.LicenseModel;
import com.tmc.client.app.emp.model.LicenseModelProperties;
import com.tmc.client.app.sys.model.LicenseCodeModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class TabPage_License extends ContentPanel  implements InterfaceTabPage, InterfaceGridOperate, InterfaceLookupResult {
	
	private LicenseModelProperties properties = GWT.create(LicenseModelProperties.class);
	private Grid<LicenseModel> grid = this.buildGrid(); 
	private Long userId = null;
	
	public TabPage_License() {
		
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
	
	private Grid<LicenseModel> buildGrid(){

		GridBuilder<LicenseModel> gridBuilder = new GridBuilder<LicenseModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.licenseCode(), 120, "자격면허코드") ;
		
		LookupTriggerField lookupLicense = new LookupTriggerField();
		lookupLicense.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				new Lookup_LicenseCode(getThis()).show(); 
			}
			
		}); 
		gridBuilder.addText(properties.licenseName(), 200, "자격면허명", lookupLicense) ;
		gridBuilder.addDate(properties.issueDate(), 120, "발급일", new DateField()) ;
		gridBuilder.addText(properties.issueOrgName(), 180, "발급기관명") ;
		gridBuilder.addDate(properties.expirationDate(), 120, "만료일", new DateField()) ;
		gridBuilder.addText(properties.note(), 200, "비고", new TextField() );

		return gridBuilder.getGrid();
	}

	private TabPage_License getThis(){
		return this; 
	}
	
	@Override
	public void setLookupResult(Object result) {
		LicenseCodeModel licenseCodeModel = (LicenseCodeModel)result;
		
		LicenseModel data = grid.getSelectionModel().getSelectedItem(); 
		grid.getStore().getRecord(data).addChange(properties.licenseCode(), licenseCodeModel.getLicenseCode());
		grid.getStore().getRecord(data).addChange(properties.licenseName(), licenseCodeModel.getLicenseName());
		grid.getStore().getRecord(data).addChange(properties.issueOrgName(), licenseCodeModel.getIssueOrgName());
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

	@Override
	public void retrieve(){
		if(this.userId != null){
			GridRetrieveData<LicenseModel> service = new GridRetrieveData<LicenseModel>(grid.getStore());
			service.addParam("userId", userId);
			service.retrieve("emp.License.selectByUserId");
		}
		else {
			grid.getStore().clear();
		}
	}
	
	@Override
	public void update(){
		GridUpdateData<LicenseModel> service = new GridUpdateData<LicenseModel>(); 
		service.update(grid.getStore(), "emp.License.update"); 
	}
	
	@Override
	public void insertRow(){
		if(this.userId == null){
			new SimpleMessage("사용자가 선택되어 있지 않습니다"); 
			return; 
		}
		
		GridInsertRow<LicenseModel> service = new GridInsertRow<LicenseModel>(); 
		LicenseModel LicenseModel = new LicenseModel();
		LicenseModel.setUserId(this.userId);
		service.insertRow(grid, LicenseModel);
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<LicenseModel> service = new GridDeleteData<LicenseModel>();
		List<LicenseModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "emp.License.delete");
	}


}