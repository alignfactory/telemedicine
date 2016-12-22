package com.tmc.client.app.sys;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class TabPage_Company extends ContentPanel implements InterfaceTabPage, Editor<CompanyModel>, InterfaceGridOperate {

	interface EditDriver extends SimpleBeanEditorDriver<CompanyModel, TabPage_Company> {}
	private EditDriver editDriver = GWT.create(EditDriver.class);
	private Grid<CompanyModel> grid;
	
	private CompanyModel companyModel = new CompanyModel();

	public TextField companyName = new TextField();
	public TextField bizNo = new TextField();
	
	public TextField telNo01 = new TextField();
	public TextField telNo02 = new TextField();
	public TextField faxNo01 = new TextField();
	public LookupTriggerField zipCode = new LookupTriggerField();
	public TextField zipAddress = new TextField();
	public TextField zipDetail = new TextField();
	public TextField locationName = new TextField();
	public DateField annvDate = new DateField();
	public HtmlEditor note = new HtmlEditor();
	
	// private LongField ceoPersonId = new LongField();
	// private LongField managerPersonId = new LongField();
	
	public TabPage_Company(Grid<CompanyModel> grid) {
		
		this.setHeaderVisible(false);
		
		this.grid = grid;
		this.editDriver.initialize(this);
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addDeleteButton();
		
		this.getButtonBar().add(searchBarBuilder.getSearchBar());
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.add(this.getEditor());
		
	}

	private FormPanel getEditor() {

		HorizontalLayoutData rowLayout = new HorizontalLayoutData(220, -1); // 한컬럼의 크기(라벨 + 필드)
		rowLayout.setMargins(new Margins(0, 20, 0, 0)); // 컬럼간의 간격조정

		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		row01.add(new FieldLabel(companyName, "고객명"), rowLayout);
		row01.add(new FieldLabel(bizNo, "사업자번호"), rowLayout);
		row01.add(new FieldLabel(annvDate, "창립일"), rowLayout);
		
		HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
		row02.add(new FieldLabel(telNo01, "전화(1)"), rowLayout);
		row02.add(new FieldLabel(telNo02, "전화(2)"), rowLayout);
		row02.add(new FieldLabel(faxNo01, "팩스번호"), rowLayout);

		HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
		row03.add(new FieldLabel(zipCode, "우편번호"), rowLayout);
		zipCode.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				Info.display("msg", "is clicked");
			}
		});
		row03.add(new FieldLabel(zipAddress, "우편주소"), new HorizontalLayoutData(1, -1, new Margins(0, 30, 0, 0)));

		HorizontalLayoutContainer row04 = new HorizontalLayoutContainer();
		row04.add(new FieldLabel(zipDetail, "상세주소"), new HorizontalLayoutData(1, -1, new Margins(0, 30, 0, 0)));

		HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
		row05.add(new FieldLabel(locationName, "지역명"), rowLayout);

		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row02, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row03, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row04, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row05, new VerticalLayoutData(1, -1, new Margins(15)));

		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer(); 
		hlc.add(layout, new HorizontalLayoutData(700, 1)); // , new Margins(0, 30, 10, 0));
		
		FieldLabel noteField  = new FieldLabel(note, "기타 메모"); 
		hlc.add(noteField, new HorizontalLayoutData(1, 1, new Margins(15, 15, 15, 0)));

		// form setting
		FormPanel form = new FormPanel();
		form.setWidget(hlc);
		form.setLabelWidth(70); // 모든 field 적용 후 설정한다.
		return form;
	}

	@Override
	public void retrieve(Map<String, Object> param) {
		CompanyModel company = (CompanyModel)param.get("companyModel"); 
		
		if (company == null) {
			this.companyModel = new CompanyModel();
			this.setEnabled(false);
			
		} else {
			this.companyModel = company;
			editDriver.edit(this.companyModel);
			this.setEnabled(true);
		}
		
	}

	@Override
	public void retrieve() {
	}
	
	@Override
	public void update(){
		grid.getStore().update(editDriver.flush());
		GridUpdateData<CompanyModel> service = new GridUpdateData<CompanyModel>(); 
		service.update(this.grid.getStore(), editDriver.flush(), "sys.Company.update"); 
	}
	
	@Override
	public void insertRow() {
		GridInsertRow<CompanyModel> service = new GridInsertRow<CompanyModel>(); 
		service.insertRow(grid, new CompanyModel());
		this.setEnabled(true);
	}
	
	@Override
	public void deleteRow() {
		GridDeleteData<CompanyModel> service = new GridDeleteData<CompanyModel>();
		List<CompanyModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "sys.Company.delete");
		
		this.setEnabled(false);
		//this.editDriver.edit(new CompanyModel());
	}
}