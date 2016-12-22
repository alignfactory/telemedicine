package com.tmc.client.app.psc;

import java.util.List;

import com.tmc.client.app.psc.model.RegisterModel;
import com.tmc.client.app.psc.model.RegisterModelProperties;
import com.tmc.client.app.psc.model.StudentModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class Page_Register extends ContentPanel {

	private RegisterModelProperties properties = GWT.create(RegisterModelProperties.class);
	private Grid<RegisterModel> grid ;
	private StudentModel studentModel ;

	public Page_Register(){
		
		this.setBorders(true);
		this.setHeading("입학 및 졸업 상세정보");
		this.setHeaderVisible(true);
		this.grid = this.setGrid(); 
		
		this.add(this.grid);

		TextButton insertButton = new TextButton("등록");
		insertButton.setWidth(60);
		insertButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				insertRow(); 
			}
		}); 
		
		TextButton updateButton = new TextButton("저장");
		updateButton.setWidth(60);
		updateButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				update(); 
			}
		}); 

		TextButton deleteButton = new TextButton("삭제"); 
		deleteButton.setWidth(60);
		deleteButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				deleteRow();
			}
		}); 

		this.getButtonBar().add(insertButton);
		this.getButtonBar().add(updateButton);
		this.getButtonBar().add(deleteButton);
		this.setButtonAlign(BoxLayoutPack.CENTER);
	}
	
	 
	public void retrieve(StudentModel studentModel){
		this.studentModel = studentModel; 
		GridRetrieveData<RegisterModel> service = new GridRetrieveData<RegisterModel>(grid.getStore());
		service.addParam("studentId", studentModel.getStudentId());
		service.retrieve("psc.Register.selectByStudentId");
	}
	
	public void update(){
		GridUpdateData<RegisterModel> service = new GridUpdateData<RegisterModel>(); 
		service.update(grid.getStore(), "psc.Register.update"); 
	}
	public void insertRow(){
		GridInsertRow<RegisterModel> service = new GridInsertRow<RegisterModel>(); 
		RegisterModel registerModel = new RegisterModel();
		registerModel.setStudentId(this.studentModel.getStudentId());
		service.insertRow(grid, registerModel);
	}
	public void deleteRow(){
		GridDeleteData<RegisterModel> service = new GridDeleteData<RegisterModel>();
		List<RegisterModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "psc.Register.delete");
	}
	public Grid<RegisterModel> getGrid(){
		return this.grid; 
	}

	public Grid<RegisterModel> setGrid(){

		GridBuilder<RegisterModel> buildGrid = new GridBuilder<RegisterModel>(properties.keyId());  
		buildGrid.setChecked(SelectionMode.SINGLE);
		
		buildGrid.addDate(properties.entranceDate(), 100, "입학일", new DateField());

		final ComboBoxField entranceComboBox = new ComboBoxField("EntranceCode");  
		entranceComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				RegisterModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.entranceCode(), entranceComboBox.getCode());
			}
		}); 
		buildGrid.addText(properties.entranceName(), 100, "입학구분", entranceComboBox);
		buildGrid.addText(properties.entranceNote(), 200, "상세사유", new TextField());
		buildGrid.addDate(properties.graduateDate(), 100, "졸업일", new DateField());

		final ComboBoxField graduateComboBox= new ComboBoxField("GraduateCode");  
		graduateComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				RegisterModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.graduateCode(), graduateComboBox.getCode());
			}
		}); 
		buildGrid.addText(properties.graduateName(), 100, "졸업구분", graduateComboBox);

		LookupTriggerField lookupUserName = new LookupTriggerField(); 
		buildGrid.addText(properties.graduateNote(), 100, "상세사유", lookupUserName);
		
		ActionCell<String> actionCell = new ActionCell<String>("급여마감", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				Info.display("action Cell", "급여마감");
			}
		});
		buildGrid.addCell(properties.afterAction(), 200, "졸업후 활동", actionCell);
		buildGrid.addText(properties.note(), 200, "비고", new TextField());

		return buildGrid.getGrid();
	}
	
}
