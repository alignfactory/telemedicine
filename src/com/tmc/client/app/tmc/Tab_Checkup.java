package com.tmc.client.app.tmc;

import java.util.Date;
import com.tmc.client.app.tmc.model.RequestModel;
import com.tmc.client.app.tmc.model.RequestModelProperties;
import com.tmc.client.app.tmc.model.CheckupModel;
import com.tmc.client.app.tmc.model.CheckupModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

/*
 * 검사오더에 따른 검사내용 등록 
 */

public class Tab_Checkup extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private RequestModelProperties requestModelProperties = GWT.create(RequestModelProperties.class);
	private Grid<RequestModel> gridRequest = this.buildGridRequest();
	
	private CheckupModelProperties checkupModelProperties = GWT.create(CheckupModelProperties.class);
	private Grid<CheckupModel> gridCheckup = this.buildGridCheckup();

	private TextField patientNameField = new TextField();
	private DateField dateField = new DateField(); 
	private LookupCompanyField lookupCompanyField = new LookupCompanyField(); 
	
	public Tab_Checkup() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "기관명", 210, 48);

		dateField.setValue(new Date());
		searchBarBuilder.addDateField(dateField, "진료예정일", 180, 70, true);
		searchBarBuilder.addTextField(patientNameField, "환자명", 130, 48, true); 
		searchBarBuilder.addRetrieveButton(); 

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		vlc.add(gridRequest, new VerticalLayoutData(1, 1));
		
		gridRequest.addRowClickHandler(new RowClickHandler(){
			@Override
			public void onRowClick(RowClickEvent event) {
				// event.getClass
				RequestModel requestModel = gridRequest.getSelectionModel().getSelectedItem(); 
				if(requestModel != null){
					retrieveCheckup(requestModel); 
				}
			}
		}); 
		
		BorderLayoutData northLayoutData = new BorderLayoutData(300);
		northLayoutData.setMargins(new Margins(0,0,1,0));
		northLayoutData.setSplit(true);
		northLayoutData.setMaxSize(1000);
		this.setNorthWidget(vlc, northLayoutData); 
		
		ContentPanel cp = new ContentPanel(); 
		cp.setHeaderVisible(false);
		cp.add(gridCheckup);
		
		TextButton buttonUpdate = new TextButton("임시저장");  
		buttonUpdate.setWidth(100);
		buttonUpdate.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				update(); 
			}
		}); 
		cp.addButton(buttonUpdate);
		
		TextButton buttonConfirm = new TextButton("검사완료");
		buttonConfirm.setWidth(100);
		buttonConfirm.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				confirmCheckup(); 
			}
		}); 
		
		
		cp.addButton(buttonConfirm);
		
		cp.setButtonAlign(BoxLayoutPack.CENTER);
		cp.getButtonBar().setPadding(new Padding(0, 0, 15, 0)); 
		this.setCenterWidget(cp); 
	}
	
	private void confirmCheckup(){
		
		RequestModel requestModel = gridRequest.getSelectionModel().getSelectedItem();  
		
		if("50".equals(requestModel.getTreatStateCode())){
			new SimpleMessage("이미 처방완료가 된 상태이므로 검사완료 처리를 활 수 없습니다."); 
			return; 
		}
		
		if("40".equals(requestModel.getTreatStateCode())){
			new SimpleMessage("이미 검사완료 처리가 된 상태입니다."); 
			return; 
		}

		if(gridCheckup.getStore().getModifiedRecords().size() > 0 ){
			new SimpleMessage("검사내역을 먼저 저장한 후 검사완료 처리가 가능합니다.");
			return; 
		}
		
		for(CheckupModel checkupModel : gridCheckup.getStore().getAll()){
			if("010".equals(checkupModel.getProcessCode()) || "020".equals(checkupModel.getProcessCode())) {
				new SimpleMessage("검사요청 혹은 검사진행이 있는 경우에는 검사완료를 할 수 없습니다.");
				return ; 
			}
		}
		// 검사완료(40) Setting 
		gridRequest.getStore().getRecord(requestModel).addChange(requestModelProperties.treatStateCode(), "40");

		GridUpdateData<RequestModel> service = new GridUpdateData<RequestModel>();
		service.update(gridRequest.getStore(), "tmc.Request.update"); 
	}
	
	public Grid<RequestModel> buildGridRequest(){
		GridBuilder<RequestModel> gridBuilder = new GridBuilder<RequestModel>(requestModelProperties.keyId());  
		// gridBuilder.setChecked(SelectionMode.SINGLE);
		gridBuilder.addDate(requestModelProperties.requestDate(), 90, "진료예정일"); //, new DateField());
		gridBuilder.addText(requestModelProperties.treatStateName(), 80, "상태구분"); 
		gridBuilder.addText(requestModelProperties.insNo(), 80, "보험번호"); //, new TextField()) ;
		gridBuilder.addText(requestModelProperties.patientKorName(), 80, "환자명"); //, lookupPatientField) ;
		gridBuilder.addText(requestModelProperties.korName(), 80, "보건의"); //, lookupRequestUserField);
		gridBuilder.addText(requestModelProperties.requestNote(), 200, "진료요청내용"); //, new TextField()) ;
		gridBuilder.addDate(requestModelProperties.treatDate(), 85, "진료일"); //, new DateField());
		gridBuilder.addText(requestModelProperties.treatKorName(), 80, "진료의"); //, lookUpTreatUserField) ;
		gridBuilder.addText(requestModelProperties.treatNote(), 200, "처방내역"); //, new TextField()) ;
		gridBuilder.addText(requestModelProperties.note(), 400, "특기사항"); //, new TextField()) ;		
		gridBuilder.addText(requestModelProperties.regKorName(), 80, "등록자"); //, new TextField()) ;
		gridBuilder.addDate(requestModelProperties.regDate(), 85, "등록일"); //, new DateField()) ;
		return gridBuilder.getGrid(); 
	}

	public Grid<CheckupModel> buildGridCheckup(){

		GridBuilder<CheckupModel> gridBuilder = new GridBuilder<CheckupModel>(checkupModelProperties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		final ComboBoxField checkupTypeComboBox = new ComboBoxField("CheckupTypeCode");  
		checkupTypeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				CheckupModel data = gridCheckup.getSelectionModel().getSelectedItem(); 
				gridCheckup.getStore().getRecord(data).addChange(checkupModelProperties.checkupCode(), checkupTypeComboBox.getCode());
			}
		}); 
		gridBuilder.addText(checkupModelProperties.checkupName(), 100, "검사종류"); // , checkupTypeComboBox) ;
		gridBuilder.addText(checkupModelProperties.checkupOrder(), 300, "검사요청사항"); //, new TextField()) ;

		final ComboBoxField checkupProcessComboBox = new ComboBoxField("CheckupProcessCode");  
		checkupProcessComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				CheckupModel data = gridCheckup.getSelectionModel().getSelectedItem(); 
				gridCheckup.getStore().getRecord(data).addChange(checkupModelProperties.processCode(), checkupProcessComboBox.getCode());
			}
		}); 
		gridBuilder.addText(checkupModelProperties.processName(), 100, "상태구분", checkupProcessComboBox) ;
		gridBuilder.addText(checkupModelProperties.checkupResult(), 400, "검사결과", new TextField()) ;
		
		ActionCell<String> fileUploadCell = new ActionCell<String>("첨부파일", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				fileUploadOpen(); 
			}
		});
		gridBuilder.addCell(checkupModelProperties.fileUpload(), 80, "첨부파일", fileUploadCell) ;
		gridBuilder.addDate(checkupModelProperties.checkupDate(), 100, "검사일"); // , new DateField()) ;
		gridBuilder.addText(checkupModelProperties.userKorName(), 80, "검사담당") ;
		return gridBuilder.getGrid(); 
	}

	private void fileUploadOpen(){
		CheckupModel checkupModel = gridCheckup.getSelectionModel().getSelectedItem(); 
		if(checkupModel != null){
			Lookup_File lookupFile = new Lookup_File();
			lookupFile.open(checkupModel.getCheckupId());
			lookupFile.show();
		}
	}
	
	private void retrieveCheckup(RequestModel requestModel){
		GridRetrieveData<CheckupModel> service = new GridRetrieveData<CheckupModel>(gridCheckup.getStore());
		service.addParam("requestId", requestModel.getRequestId());
		service.retrieve("tmc.Checkup.selectByRequestId");
	}
	
	@Override
	public void retrieve() {
		if(this.lookupCompanyField.getCompanyModel() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		
		GridRetrieveData<RequestModel> service = new GridRetrieveData<RequestModel>(gridRequest.getStore());
		service.addParam("companyId", this.lookupCompanyField.getCompanyModel().getCompanyId());
		service.addParam("patientName", patientNameField.getText());
		service.addParam("requestDate", dateField.getValue());
		service.retrieve("tmc.Request.selectByCompanyId");
	}
	
	@Override
	public void update(){

		RequestModel requestModel = gridRequest.getSelectionModel().getSelectedItem();  
		
		if("50".equals(requestModel.getTreatStateCode())){
			new SimpleMessage("이미 처방완료가 된 상태이므로 임시저장이나 검사완료 처리를 활 수 없습니다."); 
			return; 
		}

		GridUpdateData<CheckupModel> service = new GridUpdateData<CheckupModel>();
		service.addParam("userId", LoginUser.getLoginUser().getUserId()); // 검사의사 등록. 
		service.update(gridCheckup.getStore(), "tmc.Checkup.update"); 
	}
	
	@Override
	public void insertRow(){
	}
	
	@Override
	public void deleteRow(){
	}
}