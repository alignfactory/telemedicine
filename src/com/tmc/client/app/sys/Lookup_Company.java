package com.tmc.client.app.sys;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.CompanyModelProperties;
import com.tmc.client.app.sys.model.UserCompanyModel;
import com.tmc.client.app.sys.model.UserCompanyModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.AbstractLookupWindow;
import com.tmc.client.ui.builder.GridBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Lookup_Company extends AbstractLookupWindow {

	private CompanyModelProperties properties = GWT.create(CompanyModelProperties.class); // 계약정보로 대체되어야 한다.
	private InterfaceLookupResult lookupParent; 
	private Grid<CompanyModel> grid = this.buildGrid(); 
	 
	
	public Lookup_Company(InterfaceLookupResult lookupParent){
		
		// callback parent setting 
		this.lookupParent = lookupParent;
		
		this.setInit("기관을 선택해 주세요.", 600, 350);

		this.add(this.grid); 
		this.grid.addRowDoubleClickHandler(new RowDoubleClickHandler(){
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				confirm();
			}
		}); 

		this.retrieve(); 
	}

	
	private Grid<CompanyModel> buildGrid(){
		GridBuilder<CompanyModel> gridBuilder = new GridBuilder<CompanyModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.companyTypeName(), 100, "구분") ;
		gridBuilder.addText(properties.companyName(), 150, "기관명") ;
		//gridBuilder.addText(properties.bizNo(), 100, "사업자번호") ;
		gridBuilder.addText(properties.telNo01(), 100, "대표전화") ;
		gridBuilder.addText(properties.note(), 400, "비고");
		return gridBuilder.getGrid(); 
	}
	
	
	@Override
	public void retrieve(){
		GridRetrieveData<CompanyModel> service = new GridRetrieveData<CompanyModel>(grid.getStore());
		service.retrieve("sys.Company.selectByAll");
	}
	
	@Override
	public void confirm() {
		
		CompanyModel companyModel = this.grid.getSelectionModel().getSelectedItem();

		if(companyModel != null){
			lookupParent.setLookupResult(companyModel);
			this.hide();
		}
		else {
			new SimpleMessage("유치원 확인", "유치원이 선택되지 않았습니다.");
			return; 
		}
		return ; 
	}

	@Override
	public void cancel() {
		this.hide(); 
	}
}
