package com.tmc.client.app.psc;

import com.tmc.client.app.emp.model.HireModel;
import com.tmc.client.app.emp.model.HireModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.AbstractLookupWindow;
import com.tmc.client.ui.builder.GridBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Lookup_User extends AbstractLookupWindow {

	private HireModelProperties properties = GWT.create(HireModelProperties.class);
	private Grid<HireModel> grid = this.buildGrid();
	private InterfaceLookupResult lookupParent ;
	private TextField userNameField = new TextField();
	 
	public Lookup_User(InterfaceLookupResult lookupParent){

		this.lookupParent = lookupParent; 
		this.setInit("교원 찾기", 600, 350); 
		this.addLabel(userNameField, "교원명", 150, 50, true) ;

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(this.getSearchBar(), new VerticalLayoutData(1, 40)); // , new Margins(0, 0, 0, 5)));
		vlc.add(grid, new VerticalLayoutData(1, 1));
		this.add(vlc);
		this.retrieve();
	}

	private Grid<HireModel> buildGrid(){
		GridBuilder<HireModel> gridBuilder = new GridBuilder<HireModel>(properties.keyId());
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.korName(), 80, "교원명") ;
		gridBuilder.addDate(properties.hireDate(), 90, "입사일") ;
		gridBuilder.addText(properties.hireName(), 120, "입사구분") ;
		gridBuilder.addText(properties.telNo1(), 120, "연락처") ;
		//gridBuilder.addText(properties.ctzNo(), 100, "주민번호");
		gridBuilder.addDate(properties.retireDate(), 90, "퇴직일") ;
		gridBuilder.addText(properties.note(), 200, "비고" );
	
		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve(){
		GridRetrieveData<HireModel> service = new GridRetrieveData<HireModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginUser().getCompanyId());
		service.addParam("korName", userNameField.getText());
		service.retrieve("emp.Hire.selectByKorName");
	}

	@Override
	public void confirm() {
		HireModel hireModel = grid.getSelectionModel().getSelectedItem(); 
		if(hireModel != null){
			lookupParent.setLookupResult(hireModel.getUserModel());
			hide(); 
		}
		else {
			new SimpleMessage("선택된 교원이 없습니다.");
		}
	}

	@Override
	public void cancel() {
	}
}
