package com.tmc.client.app.psc;

import com.tmc.client.app.psc.model.StudyClassModel;
import com.tmc.client.app.psc.model.StudyClassModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_Teacher extends BorderLayoutContainer implements InterfaceGridOperate {

	private Page_Teacher pageTeacher = new Page_Teacher(); 
	
	private StudyClassModelProperties properties = GWT.create(StudyClassModelProperties.class);
	private Grid<StudyClassModel> grid = this.buildGrid();
	private TextField studyClassName = new TextField();
	
	public Tab_Teacher() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(studyClassName, "반 이름", 150, 50, true) ; 
		searchBarBuilder.addRetrieveButton(); 
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		vlc.add(grid, new VerticalLayoutData(1, 1));

		BorderLayoutData westLayoutData = new BorderLayoutData(500);
		westLayoutData.setMargins(new Margins(0, 2, 0, 0)); 
		westLayoutData.setSplit(true);
		westLayoutData.setMaxSize(1000);  

		this.setWestWidget(vlc, westLayoutData);
		this.setCenterWidget(this.pageTeacher);
		
		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<StudyClassModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<StudyClassModel> event) {
				if(event.getSelection().size() > 0){
					pageTeacher.setStudyClassModel(grid.getSelectionModel().getSelectedItem());
					pageTeacher.retrieve();
				}
			} 
		}); 
	}		

	@Override
	public void retrieve() {
		GridRetrieveData<StudyClassModel> service = new GridRetrieveData<StudyClassModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.retrieve("psc.StudyClass.selectByCompanyId");
	}
	
	public Grid<StudyClassModel> buildGrid(){

		GridBuilder<StudyClassModel> gridBuilder = new GridBuilder<StudyClassModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.studyClassCode(), 60, "반코드") ;
		gridBuilder.addLong(properties.companyId(), 60, "회사코드") ;
		gridBuilder.addText(properties.studyClassName(), 100, "반 이름") ;
		gridBuilder.addText(properties.studyClassTypeName(), 80, "반 구분") ;
		gridBuilder.addBoolean(properties.useYnFlag(), 50, "사용") ;
		gridBuilder.addText(properties.note(), 200, "비고" );

		return gridBuilder.getGrid(); 
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