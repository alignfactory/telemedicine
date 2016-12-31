package com.tmc.client.app.tmc;

import java.util.List;

import com.tmc.client.app.sys.Lookup_Company;
import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.tmc.model.PatientModel;
import com.tmc.client.app.tmc.model.PatientModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_Patient extends VerticalLayoutContainer implements InterfaceGridOperate, InterfaceLookupResult  {
	
	private PatientModelProperties properties = GWT.create(PatientModelProperties.class);
	private Grid<PatientModel> grid = this.buildGrid();
	private TextField patientNameField = new TextField();
	private Lookup_Company lookupCompany = new Lookup_Company(this);
	private CompanyModel companyModel = new CompanyModel();
	
	private LookupTriggerField lookupCompanyField= new LookupTriggerField() ;
	
	public Tab_Patient() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		
		lookupCompanyField.setEditable(false);
		lookupCompanyField.addTriggerClickHandler(new TriggerClickHandler(){
   	 		@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			//Info.display("lookup", "Company");
   	 			lookupCompany.show();
			}
   	 	}); 

		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "기관명", 250, 48); 
		this.companyModel = LoginUser.getLoginUser().getCompanyModel(); 
		lookupCompanyField.setText(companyModel.getCompanyName());
		
		searchBarBuilder.addLabel(patientNameField, "환자", 150, 46, true); 

		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	public Grid<PatientModel> buildGrid(){

		GridBuilder<PatientModel> gridBuilder = new GridBuilder<PatientModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.insNo(), 80, "보험번호", new TextField()) ;
		gridBuilder.addText(properties.korName(), 70, "환자명", new TextField()) ;
		gridBuilder.addDate(properties.birthday(), 90, "생년월일", new DateField()) ;
		final ComboBoxField genderComboBox = new ComboBoxField("GenderCode");  
		genderComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				PatientModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.genderCode(), genderComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.genderName(), 60, "성별", genderComboBox) ;
		gridBuilder.addText(properties.tel1(), 90, "전화번호1", new TextField()) ;
		gridBuilder.addText(properties.tel2(), 90, "전화번호2", new TextField()) ;
		gridBuilder.addText(properties.viewPoint(), 80, "병력", new TextField()) ;
		gridBuilder.addText(properties.zipCode(), 60, "우편번호", new TextField()) ;
		gridBuilder.addText(properties.address(), 300, "주소", new TextField()) ;
		gridBuilder.addText(properties.guardianName(), 70, "보호자명", new TextField()) ;
		gridBuilder.addText(properties.guardianRelationName(), 100, "보호자관계", new TextField()) ;
		gridBuilder.addText(properties.guardianTel1(), 90, "보호자전화1", new TextField()) ;
		gridBuilder.addText(properties.guardianTel2(), 90, "보호자번호2", new TextField()) ;

//		gridBuilder.addText(properties.companyId(), 80, "관할기관", new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField()) ;

		return gridBuilder.getGrid(); 
		
	}

	@Override
	public void retrieve() {
		
		if(this.companyModel.getCompanyId() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		
		GridRetrieveData<PatientModel> service = new GridRetrieveData<PatientModel>(grid.getStore());
		service.addParam("companyId", this.companyModel.getCompanyId());
		service.addParam("patientName", patientNameField.getValue());
		service.retrieve("tmc.Patient.selectByName");
	}
	
	@Override
	public void update(){
		GridUpdateData<PatientModel> service = new GridUpdateData<PatientModel>(); 
		service.update(grid.getStore(), "tmc.Patient.update"); 
	}
	
	@Override
	public void insertRow(){
		if(this.companyModel.getCompanyId() == null){
			new SimpleMessage("기관선택", "등록하고자 하는 담당자의 기관을 먼저 선택하여 주세요"); 
			return ; 
		}
		
		GridInsertRow<PatientModel> service = new GridInsertRow<PatientModel>(); 
		PatientModel patientModel = new PatientModel();
		patientModel.setCompanyId(this.companyModel.getCompanyId());
		service.insertRow(grid, patientModel);
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<PatientModel> service = new GridDeleteData<PatientModel>();
		List<PatientModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "tmc.Patient.delete");
	}

	@Override
	public void setLookupResult(Object result) {
		if(result != null) {
//			CompanyModel companyModel =  
			this.companyModel = (CompanyModel)result;// userCompanyModel.getCompanyModel(); 
			lookupCompanyField.setValue(this.companyModel.getCompanyName());
		}
		else {
			this.companyModel = new CompanyModel();  
			lookupCompanyField.setValue(null);
		}

		
	}
}