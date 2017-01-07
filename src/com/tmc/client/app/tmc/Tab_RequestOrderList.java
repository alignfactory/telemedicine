package com.tmc.client.app.tmc;

import java.util.Date;
import java.util.List;

import com.tmc.client.app.sys.Lookup_Company;
import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.tmc.model.PatientModel;
import com.tmc.client.app.tmc.model.RequestModel;
import com.tmc.client.app.tmc.model.RequestModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.service.InterfaceCallback;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public abstract class Tab_RequestOrderList extends BorderLayoutContainer implements InterfaceGridOperate, InterfaceLookupResult  {
	
	private RequestModelProperties properties = GWT.create(RequestModelProperties.class);
	private Grid<RequestModel> grid = this.buildGrid();
	private Grid<RequestModel> gridHistory = this.buildGridHistory();
	
	private DateField startDate = new DateField();
	private DateField endDate = new DateField();
	private TextField patientNameField = new TextField();
	// private Lookup_Company lookupCompany = new Lookup_Company(this);
	
	private LookupTriggerField lookUpCompanyField = new LookupTriggerField() ;
	
	private String lookUpName = "none"; 

	private void setLookUpName(String name){
		this.lookUpName = name; 
	}

	@SuppressWarnings("unused")
	private String getLookUpName(){
		return this.lookUpName ;  
	}

	
	public Tab_RequestOrderList() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		
//		lookUpCompanyField.setEditable(false);
//		lookUpCompanyField.addTriggerClickHandler(new TriggerClickHandler(){
//   	 		@Override
//			public void onTriggerClick(TriggerClickEvent event) {
//   	 			setLookUpName("lookUpCompany"); 
//   	 			lookupCompany.show();
//			}
//   	 	}); 

//		searchBarBuilder.addLookupTriggerField(lookUpCompanyField, "기관명", 250, 48);
//		this.companyModel = LoginUser.getLoginUser().getCompanyModel(); 
//		lookUpCompanyField.setText(companyModel.getCompanyName());

//		searchBarBuilder.addLookupTriggerField(lookUpCompanyField, "기관명", 250, 48);
//		searchBarBuilder.addLabel(startDate, "환자명", 150, 46, true); 
//		searchBarBuilder.addLabel(EndDate, "~", 50, 46, true); 
		searchBarBuilder.addRetrieveButton(); 
//		searchBarBuilder.addUpdateButton();
//		searchBarBuilder.addInsertButton();
//		searchBarBuilder.addDeleteButton();

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
		
		BorderLayoutData northLayoutData = new BorderLayoutData(300);
		northLayoutData.setMargins(new Margins(2,0,0,0));
		northLayoutData.setSplit(true);
		northLayoutData.setMaxSize(1000);
		
		this.setNorthWidget(vlc, northLayoutData); 

		BorderLayoutData westLayoutData = new BorderLayoutData(400);
		westLayoutData.setMargins(new Margins(2,0,0,0));
		westLayoutData.setSplit(true);
		westLayoutData.setMaxSize(1000);
		
		this.setWestWidget(this.gridHistory, westLayoutData);

		BorderLayoutData centerLayoutData = new BorderLayoutData();
		centerLayoutData.setMargins(new Margins(2,2,0,2));
		centerLayoutData.setMaxSize(1000);
		
		Page_Treat pageTreat = new Page_Treat(grid); 
		this.setCenterWidget(pageTreat, centerLayoutData);
	}
	
	public Tab_RequestOrderList getThis(){
		return this; 
	}
	
	public Grid<RequestModel> buildGrid(){

		// 환자 찾기 
		LookupTriggerField lookUpPatientField = new LookupTriggerField(); 
		lookUpPatientField.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			setLookUpName("lookUpPatient"); 
				//new Lookup_Patient(getThis()).show();
			}
		}); 

		
		// 담당의사 찾기 
		LookupTriggerField lookUpReqUserField = new LookupTriggerField(); 
		lookUpReqUserField.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			setLookUpName("lookUpReqUser"); 
				// new Lookup_RequestUser(getThis(), companyModel).show(); // 선택된 기관정보를 넘겨준다. 
			}
		}); 

		// 진료의사 찾기 
//		LookupTriggerField lookUpTreatUserField = new LookupTriggerField(); 
//		lookUpTreatUserField.addTriggerClickHandler(new TriggerClickHandler(){
//			@Override
//			public void onTriggerClick(TriggerClickEvent event) {
//   	 			setLookUpName("lookUpTreatUser"); 
//				new Lookup_User(getThis()).show();
//			}
//		}); 

		
		GridBuilder<RequestModel> gridBuilder = new GridBuilder<RequestModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addDate(properties.requestDate(), 100, "진료예정일", new DateField());
		gridBuilder.addText(properties.insNo(), 100, "보험번호"); //, new TextField()) ;
		gridBuilder.addText(properties.patientKorName(), 80, "환자명", lookUpPatientField) ;
		
		gridBuilder.addText(properties.korName(), 100, "보건의", lookUpReqUserField);
		//gridBuilder.addLong(properties.requestUserId(), 100, "담당의사ID", new LongField()) ;

		//gridBuilder.addText(properties.requestTypeCode(), 80, "요청구분", new TextField()) ;

		gridBuilder.addText(properties.requestNote(), 200, "진료요청내용", new TextField()) ;
		

		gridBuilder.addDate(properties.treatDate(), 100, "진료일"); //, new DateField());
		gridBuilder.addText(properties.treatKorName(), 80, "진료의"); //, lookUpTreatUserField) ;
		gridBuilder.addText(properties.treatNote(), 200, "처방내역"); //, new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField()) ;		

		gridBuilder.addText(properties.regKorName(), 80, "등록자"); //, new TextField()) ;
		gridBuilder.addDate(properties.regDate(), 100, "등록일"); //, new DateField()) ;

		
		return gridBuilder.getGrid(); 
		
	}

	public Grid<RequestModel> buildGridHistory(){

		GridBuilder<RequestModel> gridBuilder = new GridBuilder<RequestModel>(properties.keyId());  
		// gridBuilder.setChecked(null);
		gridBuilder.addDate(properties.treatDate(), 	100, "진료일"); //, new DateField());
		gridBuilder.addText(properties.treatKorName(), 	80, "진료의"); //, lookUpTreatUserField) ;
		gridBuilder.addDate(properties.requestDate(), 	100, "진료예정일");
		gridBuilder.addText(properties.korName(), 		80, "보건의");
		
		return gridBuilder.getGrid(); 
		
	}
	
	
	private void retrieveHistory(RequestModel requestModel){
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(gridHistory.getStore());
		service.addCallback(new InterfaceCallback(){
			@Override
			public void callback() {
				Info.display("callback", "retrieve History");
			}
		});

		service.addParam("patientId", requestModel.getPatientId());
		service.retrieve("tmc.Request.selectByPatientId");
		
	}
	
	@Override
	public void retrieve() {
		
//		if(this.companyModel.getCompanyId() == null){
//			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
//			return ; 
//		} 
		
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(grid.getStore());
//		service.addParam("startDate", this.RequsetModel.getStartDate());
//		service.addParam("endDate", this.RequestModel.getEndDate());
//		service.addParam("patientName", patientNameField.getText());
		service.retrieve("tmc.Request.selectBySearchList");
	}
	
//	@Override
//	public void setLookupResult(Object result) {
//		
//		if("lookUpCompany".equals(this.getLookUpName())){
//			if(result != null) {
//				this.companyModel = (CompanyModel)result;
//				lookUpCompanyField.setValue(this.companyModel.getCompanyName());
//			}
//		}
//		
//		if("lookUpPatient".equals(this.getLookUpName())){ // 환자 찾기
//			if(result != null) {
//				PatientModel patientModel = (PatientModel)result; 
//				RequestModel data = grid.getSelectionModel().getSelectedItem(); 
//				
//				// grid.getStore().getRecord(data).getModel().setPatientId(patientModel.getPatientId());
//				grid.getStore().getRecord(data).addChange(properties.patientId(), patientModel.getPatientId());
//				grid.getStore().getRecord(data).addChange(properties.patientKorName(), patientModel.getKorName());
//				grid.getStore().getRecord(data).addChange(properties.insNo(), patientModel.getInsNo());
//			}
//		}
//		
//		if("lookUpReqUser".equals(this.getLookUpName())){ // 보건의 찾기 
//			if(result != null) {
//				UserModel userModel = (UserModel)result; 
//				RequestModel data = grid.getSelectionModel().getSelectedItem(); 
//				grid.getStore().getRecord(data).addChange(properties.korName(), userModel.getKorName());
//				grid.getStore().getRecord(data).addChange(properties.requestUserId(), userModel.getUserId());
//			}
//		}
//	}

	public DateField getEndDate() {
		return endDate;
	}

	public void setEndDate(DateField endDate) {
		this.endDate = endDate;
	}

	public DateField getStartDate() {
		return startDate;
	}

	public void setStartDate(DateField startDate) {
		this.startDate = startDate;
	}

	public TextField getPatientNameField() {
		return patientNameField;
	}

	public void setPatientNameField(TextField patientNameField) {
		this.patientNameField = patientNameField;
	}

	public Lookup_Company getLookupCompany() {
		return null; //lookupCompany;
	}

	public void setLookupCompany(Lookup_Company lookupCompany) {
		// this.lookupCompany = lookupCompany;
	}

	public LookupTriggerField getLookUpCompanyField() {
		return lookUpCompanyField;
	}

	public void setLookUpCompanyField(LookupTriggerField lookUpCompanyField) {
		this.lookUpCompanyField = lookUpCompanyField;
	}
}