package com.tmc.client.app.tmc;

import java.util.Date;
import java.util.List;

import com.tmc.client.app.sys.Lookup_Company;
import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.app.tmc.model.CheckupModel;
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
import com.tmc.client.ui.builder.ComboBoxField;
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
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

/*
 * 처방 조치 결과 등록(보건의) 
 * 조치 완료 여부를 등록한다.   
 */

public class Tab_TreatResult extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private RequestModelProperties properties = GWT.create(RequestModelProperties.class);
	private Grid<RequestModel> grid = this.buildGrid();
	private TextField patientNameField = new TextField();
	private LookupCompanyField lookupCompanyField = new LookupCompanyField();
	private Page_Result pageResult = new Page_Result(this.grid); 
	
	private ComboBoxField treatStateComboBox = new ComboBoxField("TreatStateCode");  
	
	public Tab_TreatResult() {

		treatStateComboBox.setValue("처방등록");
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "기관명", 250, 48);
		searchBarBuilder.addComboBox(treatStateComboBox, "상태구분", 180, 60); 
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
					pageResult.retrieve(requestModel);
				}
			}
		}); 
		
		BorderLayoutData northLayoutData = new BorderLayoutData(600);
		northLayoutData.setMargins(new Margins(0,0,0,0));
		northLayoutData.setSplit(true);
		northLayoutData.setMaxSize(1000);
		this.setNorthWidget(vlc, northLayoutData); 

		BorderLayoutData centerLayoutData = new BorderLayoutData();
		centerLayoutData.setMargins(new Margins(2,0,0,0));
		centerLayoutData.setMaxSize(1000);
		this.setCenterWidget(this.pageResult, centerLayoutData);
	}
	
	public Grid<RequestModel> buildGrid(){
		
		GridBuilder<RequestModel> gridBuilder = new GridBuilder<RequestModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addDate(properties.requestDate(), 100, "진료예정일"); 
		gridBuilder.addText(properties.insNo(), 100, "보험번호"); 
		gridBuilder.addText(properties.patientKorName(), 80, "환자명"); 
		gridBuilder.addText(properties.korName(), 100, "보건의") ; 
		gridBuilder.addText(properties.requestNote(), 200, "진료요청내용"); 
		gridBuilder.addText(properties.treatStateName(), 80, "상태구분");
		gridBuilder.addDate(properties.treatDate(), 100, "진료일"); 
		gridBuilder.addText(properties.treatKorName(), 80, "진료의"); 
		gridBuilder.addText(properties.treatNote(), 200, "처방내역"); 
		gridBuilder.addText(properties.resultNote(), 200, "조치내역");
		gridBuilder.addText(properties.note(), 400, "특기사항"); 		
		gridBuilder.addText(properties.regKorName(), 80, "등록자"); 
		gridBuilder.addDate(properties.regDate(), 100, "등록일"); 
		return gridBuilder.getGrid(); 
	}
	
	
	@Override
	public void retrieve() {
		
		if(this.lookupCompanyField.getCompanyModel().getCompanyId() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(grid.getStore());
		service.addParam("companyId", this.lookupCompanyField.getCompanyModel().getCompanyId());
		service.addParam("treatStateCode", this.treatStateComboBox.getCode()); 
		service.addParam("patientName", patientNameField.getText());
		service.retrieve("tmc.Request.selectByCompanyId");
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