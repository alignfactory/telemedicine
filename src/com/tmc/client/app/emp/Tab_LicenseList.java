package com.tmc.client.app.emp;

import com.tmc.client.app.emp.model.LicenseModel;

import com.tmc.client.app.emp.model.LicenseModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_LicenseList extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private LicenseModelProperties properties = GWT.create(LicenseModelProperties.class);
	private Grid<LicenseModel> grid = this.buildGrid(); 
	
	private TextField licenseName = new TextField();
	
	public Tab_LicenseList() {
		this.setBorders(false); 
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(licenseName, "자격면허명", 200, 80, true); 
		searchBarBuilder.addRetrieveButton(); 
		
		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		this.add(grid, new VerticalLayoutData(1, 1));
	}

	
	public Grid<LicenseModel> buildGrid(){
		
		GridBuilder<LicenseModel> gridBuilder = new GridBuilder<LicenseModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.korName(),80, "교원명") ;
		gridBuilder.addText(properties.telNo01(), 120, "연락처") ;
		gridBuilder.addText(properties.licenseCode(), 100, "자격면허코드") ;
		
		// grouping
		gridBuilder.addGrouping(gridBuilder.addText(properties.licenseName(), 150, "자격면허명")) ;
		
		gridBuilder.addDate(properties.issueDate(), 100, "발급일") ;
		gridBuilder.addText(properties.issueOrgName(), 150, "발급기관명") ;
		gridBuilder.addDate(properties.expirationDate(), 100, "만료일") ;
		gridBuilder.addText(properties.note(), 400, "비고");
		
		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve() {
		GridRetrieveData<LicenseModel> service = new GridRetrieveData<LicenseModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.retrieve("emp.License.selectByToday");
	}
	@Override
	public void update() {
	}
	@Override
	public void insertRow() {
	}
	@Override
	public void deleteRow() {
	}
	
}