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
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_Request extends VerticalLayoutContainer implements InterfaceGridOperate, InterfaceLookupResult  {
	
	private RequestModelProperties properties = GWT.create(RequestModelProperties.class);
	private Grid<RequestModel> grid = this.buildGrid();
	private TextField patientNameField = new TextField();
	private Lookup_Company lookupCompany = new Lookup_Company(this);
	private CompanyModel companyModel = new CompanyModel();
	private LookupTriggerField lookUpCompanyField = new LookupTriggerField() ;
	
	private String lookUpName = "none"; 

	private void setLookUpName(String name){
		this.lookUpName = name; 
	}

	private String getLookUpName(){
		return this.lookUpName ;  
	}

	
	public Tab_Request() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		
		lookUpCompanyField.setEditable(false);
		lookUpCompanyField.addTriggerClickHandler(new TriggerClickHandler(){
   	 		@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			setLookUpName("lookUpCompany"); 
   	 			lookupCompany.show();
			}
   	 	}); 

		searchBarBuilder.addLookupTriggerField(lookUpCompanyField, "기관명", 250, 48);
		this.companyModel = LoginUser.getLoginUser().getCompanyModel(); 
		lookUpCompanyField.setText(companyModel.getCompanyName());

		searchBarBuilder.addLabel(patientNameField, "환자명", 150, 46, true); 

		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	public Tab_Request getThis(){
		return this; 
	}
	
	public Grid<RequestModel> buildGrid(){

		// 환자 찾기 
		LookupTriggerField lookUpPatientField = new LookupTriggerField(); 
		lookUpPatientField.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			setLookUpName("lookUpPatient"); 
				new Lookup_Patient(getThis()).show();
			}
		}); 

		
		// 담당의사 찾기 
		LookupTriggerField lookUpReqUserField = new LookupTriggerField(); 
		lookUpReqUserField.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			setLookUpName("lookUpReqUser"); 
				new Lookup_RequestUser(getThis(), companyModel).show(); // 선택된 기관정보를 넘겨준다. 
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
		
		gridBuilder.addText(properties.insNo(), 100, "보험번호"); //, new TextField()) ;
		gridBuilder.addText(properties.patientKorName(), 80, "환자명", lookUpPatientField) ;
		
		gridBuilder.addText(properties.korName(), 100, "담당의", lookUpReqUserField);
		//gridBuilder.addLong(properties.requestUserId(), 100, "담당의사ID", new LongField()) ;

		//gridBuilder.addText(properties.requestTypeCode(), 80, "요청구분", new TextField()) ;
		gridBuilder.addDate(properties.requestDate(), 100, "요쳥일", new DateField());
		gridBuilder.addText(properties.requestNote(), 200, "요청내용", new TextField()) ;
		

		gridBuilder.addDate(properties.treatDate(), 100, "진료일"); //, new DateField());
		gridBuilder.addText(properties.treatKorName(), 80, "진료의"); //, lookUpTreatUserField) ;
		gridBuilder.addText(properties.treatNote(), 200, "처방내역"); //, new TextField()) ;
		

		gridBuilder.addText(properties.regKorName(), 80, "등록자"); //, new TextField()) ;
		gridBuilder.addDate(properties.regDate(), 100, "등록일"); //, new DateField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField()) ;
		
		return gridBuilder.getGrid(); 
		
	}

	@Override
	public void retrieve() {
		
		if(this.companyModel.getCompanyId() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(grid.getStore());
		service.addParam("companyId", this.companyModel.getCompanyId());
		service.addParam("patientName", patientNameField.getValue());
		service.retrieve("tmc.Request.selectByCompanyId");
	}
	
	@Override
	public void update(){
		GridUpdateData<RequestModel> service = new GridUpdateData<RequestModel>(); 
		service.update(grid.getStore(), "tmc.Request.update"); 
	}
	
	@Override
	public void insertRow(){
		if(this.companyModel.getCompanyId() == null){
			new SimpleMessage("기관선택", "등록하고자 하는 담당자의 기관을 먼저 선택하여 주세요"); 
			return ; 
		}
		
		GridInsertRow<RequestModel> service = new GridInsertRow<RequestModel>(); 

		RequestModel requestModel= new RequestModel();
		requestModel.setRegUserModel(LoginUser.getLoginUser());
		requestModel.setRegUserId(LoginUser.getLoginUser().getUserId());
		requestModel.setRegDate(new Date());

		requestModel.setRequestUserModel(LoginUser.getLoginUser());
		requestModel.setRequestUserId(LoginUser.getLoginUser().getUserId());
		
		requestModel.setRegDate(new Date());
		
		service.insertRow(grid, requestModel);
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<RequestModel> service = new GridDeleteData<RequestModel>();
		List<RequestModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "tmc.Request.delete");
	}

	@Override
	public void setLookupResult(Object result) {
		
		if("lookUpCompany".equals(this.getLookUpName())){
			if(result != null) {
				this.companyModel = (CompanyModel)result;
				lookUpCompanyField.setValue(this.companyModel.getCompanyName());
			}
		}
		
		if("lookUpPatient".equals(this.getLookUpName())){ // 환자 찾ㅈ기
			if(result != null) {
				PatientModel patientModel = (PatientModel)result; 
				RequestModel data = grid.getSelectionModel().getSelectedItem(); 
				
				// grid.getStore().getRecord(data).getModel().setPatientId(patientModel.getPatientId());
				grid.getStore().getRecord(data).addChange(properties.patientId(), patientModel.getPatientId());
				grid.getStore().getRecord(data).addChange(properties.patientKorName(), patientModel.getKorName());
				grid.getStore().getRecord(data).addChange(properties.insNo(), patientModel.getInsNo());
			}
		}
		
		if("lookUpReqUser".equals(this.getLookUpName())){ // 보건의 찾기 
			if(result != null) {
				UserModel userModel = (UserModel)result; 
				RequestModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.korName(), userModel.getKorName());
				grid.getStore().getRecord(data).addChange(properties.requestUserId(), userModel.getUserId());
			}
		}
		
//		if("lookUpTreatUser".equals(this.getLookUpName())){ // 전문의 찾기 
//			if(result != null) {
//				UserModel userModel = (UserModel)result; 
//				RequestModel data = grid.getSelectionModel().getSelectedItem(); 
//				grid.getStore().getRecord(data).addChange(properties.treatKorName(), userModel.getKorName());
//				grid.getStore().getRecord(data).addChange(properties.treatUserId(), userModel.getUserId());
//			}
//		}

		
	}
}