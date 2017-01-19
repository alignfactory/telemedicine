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
import com.sencha.gxt.widget.core.client.info.Info;

/*
 * 검사오더에 따른 검사내용 등록 
 */

public class Tab_CheckupList extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private CheckupModelProperties checkupModelProperties = GWT.create(CheckupModelProperties.class);
	private Grid<CheckupModel> gridCheckup = this.buildGridCheckup();

	private TextField patientNameField = new TextField();
	private DateField startDateField = new DateField(); 
	private DateField endDateField = new DateField(); 
	private LookupCompanyField lookupCompanyField = new LookupCompanyField(); 
	
	public Tab_CheckupList() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "기관명", 210, 48);

		searchBarBuilder.addDateField(startDateField, "조회기간", 180, 70, true);
		searchBarBuilder.addDateField(endDateField, "~", 110, 10, true).setLabelSeparator("");;

		startDateField.setValue(new Date());
		endDateField.setValue(new Date());
		
		searchBarBuilder.addTextField(patientNameField, "환자명", 130, 48, true); 
		searchBarBuilder.addRetrieveButton(); 

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		vlc.add(gridCheckup, new VerticalLayoutData(1, 1));
		
		BorderLayoutData northLayoutData = new BorderLayoutData(1);
		northLayoutData.setMargins(new Margins(0,0,1,0));
		northLayoutData.setSplit(true);
		northLayoutData.setMaxSize(1000);
		this.setNorthWidget(vlc, northLayoutData); 
		
	}
	
	public Grid<CheckupModel> buildGridCheckup(){

		GridBuilder<CheckupModel> gridBuilder = new GridBuilder<CheckupModel>(checkupModelProperties.keyId());  
//		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(checkupModelProperties.checkupName(), 100, "검사종류"); // , checkupTypeComboBox) ;
		gridBuilder.addText(checkupModelProperties.checkupOrder(), 300, "검사요청사항"); //, new TextField()) ;

		gridBuilder.addText(checkupModelProperties.processName(), 80, "상태구분");	//, checkupProcessComboBox) ;
		gridBuilder.addText(checkupModelProperties.checkupResult(), 400, "검사결과");	//, new TextField()) ;
		
		ActionCell<String> fileUploadCell = new ActionCell<String>("첨부파일", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				fileUploadOpen(); 
			}
		});
		gridBuilder.addCell(checkupModelProperties.fileUpload(), 80, "첨부파일", fileUploadCell) ;
		gridBuilder.addDate(checkupModelProperties.checkupDate(), 100, "검사일"); // , new DateField()) ;
		gridBuilder.addText(checkupModelProperties.userKorName(), 80, "검사담당");

//		gridBuilder.addDate(requestModelProperties.requestDate(), 90, "진료예정일"); //, new DateField());
//		gridBuilder.addText(requestModelProperties.treatStateName(), 80, "상태구분"); 
//		gridBuilder.addText(requestModelProperties.insNo(), 80, "보험번호"); //, new TextField()) ;
//		gridBuilder.addText(requestModelProperties.patientKorName(), 80, "환자명"); //, lookupPatientField) ;
//		gridBuilder.addText(requestModelProperties.korName(), 80, "보건의"); //, lookupRequestUserField);
//		gridBuilder.addText(requestModelProperties.requestNote(), 200, "진료요청내용"); //, new TextField()) ;
//		gridBuilder.addDate(requestModelProperties.treatDate(), 85, "진료일"); //, new DateField());
//		gridBuilder.addText(requestModelProperties.treatKorName(), 80, "진료의"); //, lookUpTreatUserField) ;
//		gridBuilder.addText(requestModelProperties.treatNote(), 200, "처방내역"); //, new TextField()) ;
//		gridBuilder.addText(requestModelProperties.note(), 400, "특기사항"); //, new TextField()) ;		
//		gridBuilder.addText(requestModelProperties.regKorName(), 80, "등록자"); //, new TextField()) ;
//		gridBuilder.addDate(requestModelProperties.regDate(), 85, "등록일"); //, new DateField()) ;

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
	
	@Override
	public void retrieve() {
		if(this.lookupCompanyField.getCompanyModel() == null){
			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
			return ; 
		} 
		
		GridRetrieveData<CheckupModel> service = new GridRetrieveData<CheckupModel>(gridCheckup.getStore());
		service.addParam("companyId", this.lookupCompanyField.getCompanyModel().getCompanyId());
		service.addParam("startDate", startDateField.getValue());
		service.addParam("endDate", endDateField.getValue());
		service.addParam("patientName", patientNameField.getText());
		
//		Info.display("start date is", ""+startDateField.getValue());
//		new SimpleMessage("start date is", ""+startDateField.getValue());
		
		service.retrieve("tmc.Request.selectByCheckupList");
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