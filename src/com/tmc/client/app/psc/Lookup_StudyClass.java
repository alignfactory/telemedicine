package com.tmc.client.app.psc;

import com.tmc.client.app.psc.model.StudyClassModel;
import com.tmc.client.app.psc.model.StudyClassModelProperties;
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

public class Lookup_StudyClass extends AbstractLookupWindow {
	
	private StudyClassModelProperties properties = GWT.create(StudyClassModelProperties.class);	
	private Grid<StudyClassModel> grid = this.buildGrid(); 
	private InterfaceLookupResult lookupParent; 
	private TextField studyClassName = new TextField();
	
	public Lookup_StudyClass(InterfaceLookupResult lookupParent){
		
		this.lookupParent = lookupParent; 
		this.setInit("배정반 찾기", 600, 350); 
		this.addLabel(studyClassName, "반이름", 150, 50, true) ;

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(this.getSearchBar(), new VerticalLayoutData(1, 40)); // , new Margins(0, 0, 0, 5)));
		vlc.add(grid, new VerticalLayoutData(1, 1));
		this.add(vlc);
		
		// this.retrieve();
	}
	
	private Grid<StudyClassModel> buildGrid(){
		GridBuilder<StudyClassModel> gridBuilder = new GridBuilder<StudyClassModel>(properties.keyId());
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.studyClassCode(), 100, "반코드") ;
		gridBuilder.addText(properties.studyClassName(), 150, "반 이름") ;
		gridBuilder.addText(properties.studyClassTypeName(), 100, "반 구분") ;
		gridBuilder.addBoolean(properties.useYnFlag(), 100, "사용여부");
		gridBuilder.addText(properties.note(), 200, "비고" );
	
		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve() {
		GridRetrieveData<StudyClassModel> service = new GridRetrieveData<StudyClassModel>(grid.getStore());
		service.addParam("studyClassName", studyClassName.getValue());
		service.addParam("companyId", LoginUser.getLoginUser().getCompanyId());
		service.retrieve("psc.StudyClass.selectByName");
	}

	@Override
	public void confirm() {
		StudyClassModel studyClassModel = grid.getSelectionModel().getSelectedItem(); 
		if(studyClassModel != null){
			// 선택한 반 정보를 돌려준다. interfaceLookupResult를 상속받은 클래스여야 한다. 
			lookupParent.setLookupResult(studyClassModel);
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
