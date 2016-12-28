package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.PatientModel;
import com.tmc.client.app.tmc.model.PatientModelProperties;
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

public class Lookup_Patient extends AbstractLookupWindow {
	
	private PatientModelProperties properties = GWT.create(PatientModelProperties.class);	
	private Grid<PatientModel> grid = this.buildGrid(); 
	private InterfaceLookupResult lookupParent; 
	private TextField patientName = new TextField();
	
	public Lookup_Patient(InterfaceLookupResult lookupParent){
		
		this.lookupParent = lookupParent; 
		this.setInit("환자검색", 600, 350); 
		this.addLabel(patientName, "환자명", 150, 50, true) ;

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(this.getSearchBar(), new VerticalLayoutData(1, 40)); // , new Margins(0, 0, 0, 5)));
		vlc.add(grid, new VerticalLayoutData(1, 1));
		this.add(vlc);
		
		// this.retrieve();
	}
	
	private Grid<PatientModel> buildGrid(){
		GridBuilder<PatientModel> gridBuilder = new GridBuilder<PatientModel>(properties.keyId());
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.insNo(), 100, "보험번호") ;
		gridBuilder.addText(properties.korName(), 150, "성명") ;
		gridBuilder.addText(properties.guardianName(), 100, "보호자") ;
		gridBuilder.addText(properties.genderCode(), 100, "성별");
		gridBuilder.addText(properties.note(), 200, "비고" );
	
		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve() {
		GridRetrieveData<PatientModel> service = new GridRetrieveData<PatientModel>(grid.getStore());
		service.addParam("patientName", patientName.getValue());
		service.addParam("companyId", Long.parseLong("2000940")) ;
		service.retrieve("tmc.Patient.selectByName");
	}

	@Override
	public void confirm() {
		PatientModel patientModel = grid.getSelectionModel().getSelectedItem(); 
		if(patientModel != null){
			// 선택한 반 정보를 돌려준다. interfaceLookupResult를 상속받은 클래스여야 한다. 
			lookupParent.setLookupResult(patientModel);
			this.hide(); 
		}
		else {
			new SimpleMessage("선택된 반이 없습니다.");
		}
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}
	
}
