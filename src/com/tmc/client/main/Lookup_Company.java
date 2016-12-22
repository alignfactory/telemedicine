package com.tmc.client.main;

import com.tmc.client.app.sys.model.UserCompanyModel;
import com.tmc.client.app.sys.model.UserCompanyModelProperties;
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

	private UserCompanyModelProperties properties = GWT.create(UserCompanyModelProperties.class); // 계약정보로 대체되어야 한다.
	private InterfaceLookupResult lookupParent; 
	private Grid<UserCompanyModel> grid = this.buildGrid(); 
	 
	
	public Lookup_Company(InterfaceLookupResult lookupParent){
		
		// callback parent setting 
		this.lookupParent = lookupParent;
		
		this.setInit("작업할 유치원을 선택해 주세요.", 600, 350);

		this.add(this.grid); 
		this.grid.addRowDoubleClickHandler(new RowDoubleClickHandler(){
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				confirm();
			}
		}); 

		this.retrieve(); 
	}

	
	private Grid<UserCompanyModel> buildGrid(){
		GridBuilder<UserCompanyModel> gridBuilder = new GridBuilder<UserCompanyModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		gridBuilder.addText(properties.companyName(), 150, "고객명") ;
		gridBuilder.addText(properties.bizNo(), 100, "사업자번호") ;
		gridBuilder.addText(properties.telNo01(), 100, "대표전화") ;
		gridBuilder.addText(properties.note(), 400, "비고");
		return gridBuilder.getGrid(); 
	}
	
	
	@Override
	public void retrieve(){
		// login user로부터 사용자 정보를 가져온다. 
		Long userId = LoginUser.getLoginUser().getUserId(); 
		GridRetrieveData<UserCompanyModel> service = new GridRetrieveData<UserCompanyModel>(grid.getStore());
		service.addParam("userId", userId);
		service.retrieve("sys.UserCompany.selectByUserId");
	}
	
	@Override
	public void confirm() {
		
		UserCompanyModel userCompanyModel = this.grid.getSelectionModel().getSelectedItem();

		if(userCompanyModel != null){
			lookupParent.setLookupResult(userCompanyModel);
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
