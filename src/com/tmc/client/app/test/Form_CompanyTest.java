package com.tmc.client.app.test;

import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class Form_CompanyTest extends ContentPanel implements Editor<CompanyModel> {

	interface EditDriver extends SimpleBeanEditorDriver<CompanyModel, Form_CompanyTest> {}
	EditDriver editDriver = GWT.create(EditDriver.class);
	
	private ListStore<CompanyModel> listStore;
	
	CompanyModel companyModel = new CompanyModel();

	TextField companyName = new TextField();
	TextField bizNo = new TextField();
	TextField telNo01 = new TextField();
	TextField telNo02 = new TextField();
	TextField faxNo01 = new TextField();
	
	LookupTriggerField zipCode = new LookupTriggerField();
	 //LookupField zipCode = new LookupField();
	
	
	TextField zipAddress = new TextField();
	TextField zipDetail = new TextField();
	TextField locationName = new TextField();
	DateField annvDate = new DateField();
	// private LongField ceoPersonId = new LongField();
	// private LongField managerPersonId = new LongField();
	TextArea note = new TextArea();

	public Form_CompanyTest(ListStore<CompanyModel> listStore) {
		
		editDriver.initialize(this);
		this.listStore = listStore;
		this.getButtonBar().setHeight(50);
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.setHeaderVisible(false);
		this.setBodyBorder(false);
		
		this.add(this.getEditor());
		
		TextButton updateButton = new TextButton("저장");
		updateButton.setWidth(60);
		updateButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				// update();
			}
		});

		this.addButton(updateButton);

		// this.retrieve(null);
	}

	private FormPanel getEditor() {

		HorizontalLayoutData rowLayout = new HorizontalLayoutData(220, -1); // 컬럼크기
		rowLayout.setMargins(new Margins(0, 10, 0, 0)); // 컬럼간의 간격조정

		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		row01.add(new FieldLabel(companyName, "고객명"), rowLayout);
		row01.add(new FieldLabel(bizNo, "사업자번호"), rowLayout);

		HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
		row02.add(new FieldLabel(telNo01, "전화(1)"), rowLayout);
		row02.add(new FieldLabel(telNo02, "전화(2)"), rowLayout);
		row02.add(new FieldLabel(faxNo01, "팩스"), rowLayout);

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
		row05.add(new FieldLabel(annvDate, "창립일"), rowLayout);

		HorizontalLayoutContainer row06 = new HorizontalLayoutContainer();
		row06.add(new FieldLabel(note, "비고"), new HorizontalLayoutData(1, 100, new Margins(0, 30, 0, 0)));

		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row02, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row03, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row04, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row05, new VerticalLayoutData(1, -1, new Margins(15)));
		layout.add(row06, new VerticalLayoutData(1, 1, new Margins(15)));

		// form setting
		FormPanel form = new FormPanel();
		form.setWidget(layout);
		form.setLabelWidth(70); // 모든 field 적용 후 설정한다.
		return form;
	}

	public void retrieve(CompanyModel company) {
		if (company == null) {
			this.companyModel = new CompanyModel();
		} else {
			this.companyModel = company;
		}
		editDriver.edit(this.companyModel);
	}

	public void update(){
		listStore.update(editDriver.flush());
		GridUpdateData<CompanyModel> service = new GridUpdateData<CompanyModel>(); 
		service.update(this.listStore, editDriver.flush(), "sys.Company.update"); 
	 }
	
}