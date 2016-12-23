package com.tmc.client.app.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.app.sys.model.CompanyModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

public class Grid_CompanyTest {
	
	private ListStore<CompanyModel> listStore 
	= new ListStore<CompanyModel>(new ModelKeyProvider<CompanyModel>() {
		@Override
		public String getKey(CompanyModel clientModel) {
			return String.valueOf(clientModel.getKeyId());
		}
	});
	
	private Grid<CompanyModel> grid;
	private CompanyModelProperties clientProperties = GWT.create(CompanyModelProperties.class);
	
	public Grid_CompanyTest() {
		this.setGrid();
	}
	
	public Grid<CompanyModel> getGrid(){
		// commtnet6 
		return this.grid; 
	}
	
	public void retrieve(){
		GridRetrieveData<CompanyModel> service = new GridRetrieveData<CompanyModel>(listStore); 
		service.retrieveAll("sys.Company.selectByAll");
	}

	public void insertRow(){
		GridInsertRow<CompanyModel> service = new GridInsertRow<CompanyModel>(); 
		// 초기값이 있으면 여기서 넣어주어야 한다. 
		service.insertRow(grid, new CompanyModel());
	}

	public void update(){
		GridUpdateData<CompanyModel> service = new GridUpdateData<CompanyModel>(); 
		service.update(listStore, "sys.Company.update"); 
	}

	public void deleteRow(){
		GridDeleteData<CompanyModel> service = new GridDeleteData<CompanyModel>();
		List<CompanyModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(listStore, checkedList, "sys.Company.delete");
	}

	private void setGrid(){
		
		List<ColumnConfig<CompanyModel, ?>> columnList = new ArrayList<ColumnConfig<CompanyModel, ?>>();
		
		RowNumberer<CompanyModel> rowNum = new RowNumberer<CompanyModel>();
		columnList.add(rowNum);

		// check box setting 
		IdentityValueProvider<CompanyModel> identity = new IdentityValueProvider<CompanyModel>();
		CheckBoxSelectionModel<CompanyModel> selectionModel = new CheckBoxSelectionModel<CompanyModel>(identity);
		selectionModel.setSelectionMode(SelectionMode.MULTI);
		columnList.add(selectionModel.getColumn());
		
		ColumnConfig<CompanyModel, String> companyName = new ColumnConfig<CompanyModel, String>(clientProperties.companyName(), 150, "고객명") ; 
		columnList.add(companyName);
		
		ColumnConfig<CompanyModel, String> bizNo = new ColumnConfig<CompanyModel, String>(clientProperties.bizNo(), 100, "사업자번호") ; 
		columnList.add(bizNo);
		
				
		ColumnConfig<CompanyModel, String> telNo01 = new ColumnConfig<CompanyModel, String>(clientProperties.telNo01(), 120, "전화번호1") ; 
		columnList.add(telNo01);
		
		ColumnConfig<CompanyModel, String> telNo02 = new ColumnConfig<CompanyModel, String>(clientProperties.telNo02(), 120, "전화번호2"); 
		columnList.add(telNo02);
		
		ColumnConfig<CompanyModel, String> faxNo01 = new ColumnConfig<CompanyModel, String>(clientProperties.faxNo01(), 120, "팩스번호");
		columnList.add(faxNo01);

		ColumnConfig<CompanyModel, Date> annvDate = new ColumnConfig<CompanyModel, Date>(clientProperties.annvDate(), 100, "창립기념일") ; 
		columnList.add(annvDate);
		annvDate.setCell(new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd")));
		
		ColumnConfig<CompanyModel, String> zipCode = new ColumnConfig<CompanyModel, String>(clientProperties.zipCode(), 100, "우편번호"); 
		columnList.add(zipCode);
		
		
		ColumnConfig<CompanyModel, String> zipAddress = new ColumnConfig<CompanyModel, String>(clientProperties.zipAddress(), 300, "우편주소"); 
		columnList.add(zipAddress);
		
		ColumnConfig<CompanyModel, String> zipDetail = new ColumnConfig<CompanyModel, String>(clientProperties.zipDetail(), 300, "상세주소") ; 
		columnList.add(zipDetail);
		
		ColumnConfig<CompanyModel, String> note = new ColumnConfig<CompanyModel, String>(clientProperties.note(), 200, "비고"); 
		columnList.add(note);
		
		ColumnModel<CompanyModel> columnModel = new ColumnModel<CompanyModel>(columnList);
		
		for(int i=0; i<columnModel.getColumns().size(); i++){
			// 각 컬럼별 메뉴 세팅 제거 
			columnModel.getColumns().get(i).setMenuDisabled(true);
		}
		
		// 행번호 추가 
		grid = new Grid<CompanyModel>(listStore, columnModel);
		rowNum.initPlugin(grid);
		
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true); 

		// check box setting 
		grid.setSelectionModel(selectionModel);
		 
//		grid.setColumnReordering(true);
//		grid.setStateful(false);
//		grid.setBorders(false);
//		grid.getView().setStripeRows(true);
//		grid.getView().setColumnLines(true);

//		 입력 기능 추가  
		GridEditing<CompanyModel> editing = new GridInlineEditing<CompanyModel>(grid);
		editing.addEditor(companyName, new TextField());
		editing.addEditor(bizNo, new TextField());
		editing.addEditor(telNo01, new TextField());
		editing.addEditor(telNo02, new TextField());
		editing.addEditor(faxNo01, new TextField());
		
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
		DateField dateField = new DateField(new DateTimePropertyEditor(dateFormat));
		dateField.setClearValueOnParseError(false);
		editing.addEditor(annvDate, dateField);
		
		editing.addEditor(zipCode, new TextField());
		editing.addEditor(zipCode, new LookupTriggerField());
		editing.addEditor(zipAddress, new TextField());
		editing.addEditor(zipDetail, new TextField());
		editing.addEditor(note, new TextField());
	}
}
