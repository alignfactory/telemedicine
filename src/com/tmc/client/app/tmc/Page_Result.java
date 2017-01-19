package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.RequestModel;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.service.InterfaceCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

// 보건의가 최종 처방결과를 등록하는 에디터 페이지. 
public class Page_Result extends ContentPanel implements Editor<RequestModel> {

	interface EditDriver extends SimpleBeanEditorDriver<RequestModel, Page_Result> {}
	EditDriver editDriver = GWT.create(EditDriver.class);

	private Grid<RequestModel> grid ; 
	RequestModel requestModel ; 
	
	TextArea treatNote = new TextArea(); // 진료내역
	TextArea resultNote = new TextArea(); // 조치내역
	TextField treatStateCode = new TextField(); 
	
	public Page_Result(Grid<RequestModel> grid){

		this.grid = grid ; 
		
		editDriver.initialize(this);
		this.setHeaderVisible(false);
		this.add(this.getEditor());
		
		TextButton resultUpdate = new TextButton("조치내역 저장");
		resultUpdate.setWidth(100);
		resultUpdate.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				update(); 
			}
		}); 
		
		this.addButton(resultUpdate);
		
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.getButtonBar().setPadding(new Padding(0, 0, 15, 0));
	}

	private FormPanel getEditor(){
		
	    HorizontalLayoutData rowLayout = new HorizontalLayoutData(0.4, 1); // 컬럼크기 
	    rowLayout.setMargins(new Margins(0, 20, 0, 0)); // 컬럼간의 간격조정 
	    
    	HorizontalLayoutContainer row00 = new HorizontalLayoutContainer();
    	
    	FieldLabel treatNoteField = new FieldLabel(treatNote, "진료내역"); 
    	treatNoteField.setLabelAlign(LabelAlign.TOP);
    	treatNote.setReadOnly(true);
    	row00.add(treatNoteField, rowLayout);
    	
    	
    	FieldLabel resultNoteField = new FieldLabel(resultNote, "조치내역"); 
    	resultNoteField.setLabelAlign(LabelAlign.TOP);
    	row00.add(resultNoteField, rowLayout);
    	
	    VerticalLayoutContainer layout = new VerticalLayoutContainer(); 
	    layout.setScrollMode(ScrollSupport.ScrollMode.AUTOY);
	    
	    layout.add(row00, new VerticalLayoutData(1, 1, new Margins(15)));
	    
	    // form setting 
		FormPanel form = new FormPanel(); 
    	form.setBorders(false);
	    form.setWidget(layout);
	    form.setLabelWidth(80); // 모든 field 적용 후 설정한다. 
	    return form;
	}
	
	public void retrieve(RequestModel requestModel){
		editDriver.edit(this.requestModel = requestModel);
	}
	
	public void update(){
		
		treatStateCode.setValue("60"); // 조치완료  
		grid.getStore().update(editDriver.flush());
		
		GridUpdateData<RequestModel> service = new GridUpdateData<RequestModel>();
		service.addCallback(new InterfaceCallback(){
			@Override
			public void callback() {
				requestModel = grid.getSelectionModel().getSelectedItem(); 
				editDriver.edit(requestModel);
			}
		});
		service.update(grid.getStore(), editDriver.flush(), "tmc.Request.update");
	}
}
