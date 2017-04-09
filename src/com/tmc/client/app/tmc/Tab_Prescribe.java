package com.tmc.client.app.tmc;

import java.util.Date;
import com.tmc.client.app.tmc.model.RequestModel;
import com.tmc.client.app.tmc.model.RequestModelProperties;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.InterfaceCallback;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class Tab_Prescribe extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private RequestModelProperties properties = GWT.create(RequestModelProperties.class);
	private Grid<RequestModel> grid = this.buildGrid();
	private Grid<RequestModel> gridHistory = this.buildGridHistory();
	

	private Page_Treat pageTreat = new Page_Treat(this); 
	
	private TextField patientNameField = new TextField();
	private DateField dateField = new DateField(); 
	//private CompanyModel companyModel = LoginUser.getLoginUser().getCompanyModel(); 
	private LookupCompanyField lookupCompanyField = new LookupCompanyField();
	
	public Tab_Prescribe() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		
		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "진료기관", 250, 58);
		searchBarBuilder.addDateField(dateField, "진료예정일", 180, 70, true);
		dateField.setValue(new Date());
		
		searchBarBuilder.addTextField(patientNameField, "환자명", 150, 46, true); 
		searchBarBuilder.addRetrieveButton(); 

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		vlc.add(grid, new VerticalLayoutData(1, 1));
		
		grid.addRowClickHandler(new RowClickHandler(){
			@Override
			public void onRowClick(RowClickEvent event) {
				RequestModel requestModel = grid.getSelectionModel().getSelectedItem(); 
				if(requestModel != null){
					retrieveHistory(requestModel); 
				}
			}
		}); 

		gridHistory.addRowClickHandler(new RowClickHandler(){
			@Override
			public void onRowClick(RowClickEvent event) {
				RequestModel requestModel = gridHistory.getSelectionModel().getSelectedItem(); 
				editRequestModel(requestModel); 
			}
		}); 
		
		BorderLayoutData northLayoutData = new BorderLayoutData(250);
		northLayoutData.setMargins(new Margins(0,0,2,0));
		northLayoutData.setSplit(true);
		northLayoutData.setMaxSize(1000);
		this.setNorthWidget(vlc, northLayoutData); 

		BorderLayoutData westLayoutData = new BorderLayoutData(500);
		westLayoutData.setMargins(new Margins(0,0,0,0));
		westLayoutData.setSplit(true);
		westLayoutData.setMaxSize(1000);
		this.setWestWidget(this.gridHistory, westLayoutData);

		BorderLayoutData centerLayoutData = new BorderLayoutData();
		centerLayoutData.setMargins(new Margins(0,2,0,2));
		centerLayoutData.setMaxSize(1000);
		this.setCenterWidget(pageTreat, centerLayoutData);
	}
	
	public Grid<RequestModel> buildGrid(){
		
		GridBuilder<RequestModel> gridBuilder = new GridBuilder<RequestModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addDate(properties.requestDate(), 100, "진료예정일"); //, new DateField());
		gridBuilder.addText(properties.treatStateName(), 80, "진료상태"); 
		gridBuilder.addText(properties.insNo(), 100, "보험번호"); //, new TextField()) ;
		gridBuilder.addText(properties.patientKorName(), 80, "환자명"); //, lookupPatientField) ;
		
		gridBuilder.addText(properties.korName(), 100, "보건의"); //, lookupRequestUserField);
		gridBuilder.addText(properties.requestNote(), 200, "진료요청내용"); //, new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "특기사항"); //, new TextField()) ;
		
		gridBuilder.addDate(properties.treatDate(), 100, "진료일"); //, new DateField());
		gridBuilder.addText(properties.treatKorName(), 80, "진료의"); //, lookUpTreatUserField) ;
		gridBuilder.addText(properties.treatNote(), 200, "처방내역"); //, new TextField()) ;
		gridBuilder.addText(properties.regKorName(), 80, "등록자"); //, new TextField()) ;
		gridBuilder.addDate(properties.regDate(), 100, "등록일"); //, new DateField()) ;
		return gridBuilder.getGrid(); 
	}

	public Grid<RequestModel> buildGridHistory(){

		GridBuilder<RequestModel> gridBuilder = new GridBuilder<RequestModel>(properties.keyId());  
		// gridBuilder.setChecked(null);
		gridBuilder.addText(properties.treatStateName(), 80, "진료상태"); 
		gridBuilder.addDate(properties.requestDate(), 	100, "진료예정일");
		gridBuilder.addText(properties.patientKorName(), 80, "환자명");
		//gridBuilder.addDate(properties.treatDate(), 	100, "진료일"); //, new DateField());
		gridBuilder.addText(properties.treatKorName(), 	80, "진료의"); //, lookUpTreatUserField) ;
		gridBuilder.addText(properties.korName(), 		80, "보건의");

		return gridBuilder.getGrid(); 
	}
	
	public Grid<RequestModel> getGridHistory() {
		return gridHistory;
	}

	private void editRequestModel(RequestModel requestModel){
		this.pageTreat.retrieve(requestModel);
	}
	
	private void retrieveHistory(RequestModel requestModel){
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(gridHistory.getStore());
		service.addCallback(new InterfaceCallback(){
			@Override
			public void callback() {
				if(grid.getStore().size() > 0 ){
					gridHistory.getSelectionModel().select(0, false);
					editRequestModel(gridHistory.getSelectionModel().getSelectedItem());
				}
			}
		});
		service.addParam("patientId", requestModel.getPatientId());
		service.retrieve("tmc.Request.selectByPatientId");
	}
	
	@Override
	public void retrieve() {
		
		if(lookupCompanyField.getCompanyModel().getCompanyId() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		
		// clear data 
		gridHistory.getStore().clear(); 
		this.pageTreat.reset();
		
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(grid.getStore());
		service.addCallback(new InterfaceCallback(){
			@Override
			public void callback() {
			}
		});
		
		service.addParam("companyId", lookupCompanyField.getCompanyModel().getCompanyId());
		service.addParam("requestDate", dateField.getValue());
		service.addParam("patientName", patientNameField.getText());
		service.retrieve("tmc.Request.selectByCompanyId");
	}
	
	public void update(RequestModel requestModel){
		Info.display("info", "requestModel.getTreatStateName()"); 
		this.grid.getStore().update(requestModel);
	}
	
	@Override
	public void update(){
	}
	
	@Override
	public void insertRow(){
	}
	
	@Override
	public void deleteRow(){
	}
}