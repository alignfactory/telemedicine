package com.tmc.client.app.code;

import com.tmc.client.app.sys.model.LicenseCodeModel;
import com.tmc.client.app.sys.model.LicenseCodeModelProperties;
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

public class Lookup_LicenseCode extends AbstractLookupWindow {

	private LicenseCodeModelProperties properties = GWT.create(LicenseCodeModelProperties.class);
	private Grid<LicenseCodeModel> grid = this.buildGrid();
	private InterfaceLookupResult lookupParent ;
	private TextField licenseName = new TextField(); //조회조건 
	
	public Lookup_LicenseCode(InterfaceLookupResult lookupParent){

		this.lookupParent = lookupParent; 
		
		this.setInit("자격면허 코드찾기", 600, 500); 
		this.addLabel(licenseName, "자격면허명", 150, 50, true) ;

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(this.getSearchBar(), new VerticalLayoutData(1, 40)); // , new Margins(0, 0, 0, 5)));
		vlc.add(grid, new VerticalLayoutData(1, 1));
		this.add(vlc);
		
		this.retrieve();
	}
	
	private Grid<LicenseCodeModel> buildGrid(){
		GridBuilder<LicenseCodeModel> gridBuilder = new GridBuilder<LicenseCodeModel>(properties.keyId());
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.licenseCode(), 120, "자격면허코드") ;
		gridBuilder.addText(properties.licenseName(), 300, "자격면허명") ;
		gridBuilder.addText(properties.issueOrgName(), 200, "발급기관명") ;
		gridBuilder.addDate(properties.applyDate(), 100, "적용일") ;
		gridBuilder.addBoolean(properties.closeYnFlag(), 100, "사용여부");
		gridBuilder.addText(properties.note(), 200, "비고" );
	
		return gridBuilder.getGrid(); 
	}
	
	public void returnLicenseCodeModel(){
	}
	
	@Override
	public void retrieve(){
		GridRetrieveData<LicenseCodeModel> service = new GridRetrieveData<LicenseCodeModel>(grid.getStore());
		service.addParam("licenseName", licenseName.getText());
		service.retrieve("sys.LicenseCode.selectByName");
	}


	@Override
	public void confirm() {
		LicenseCodeModel licenseCodeModel = grid.getSelectionModel().getSelectedItem(); 
		if(licenseCodeModel != null){
			lookupParent.setLookupResult(licenseCodeModel);
			hide(); 
		}
		else {
			new SimpleMessage("선택된 자격증이 없습니다.");
		}
	}

	@Override
	public void cancel() {
	}
}
