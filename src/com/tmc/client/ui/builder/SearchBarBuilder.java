package com.tmc.client.ui.builder;

import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class SearchBarBuilder {

	InterfaceGridOperate target; 
	ButtonBar searchBar = new ButtonBar(); 

	public SearchBarBuilder(InterfaceGridOperate target){
		this.target = target; 
	}
	
	public ButtonBar getSearchBar(){
		this.searchBar.setPadding(new Padding(0, 0, 0, 10));
		return this.searchBar; 
	}
	public void addLookupTriggerField(LookupTriggerField field, String labelName, int width, int labelWidth){
		
		FieldLabel triggerField = new FieldLabel(field,  labelName); 
		triggerField.setHeight(26); // 고정높이 버튼과 높이를 맞춘다. 
		triggerField.setWidth(width);
		triggerField.setLabelWidth(labelWidth);
		triggerField.setLayoutData(new Margins(0, 100, 0, 0));
		triggerField.setLabelPad(0);
		
		this.searchBar.add(triggerField);
	}
	
	public void addText(String text){
		Label label = new Label(text); 
		label.setLayoutData(new Margins(0, 10, 0, 0));
		
		searchBar.add(new Label(text));
	}
	
	public FieldLabel addComboBox(ComboBoxField field, String labelName, int width, int labelWidth){
		FieldLabel fieldLabel = new FieldLabel(field, labelName); 
		fieldLabel.setHeight(26); // 고정높이 버튼과 높이를 맞춘다. 
		fieldLabel.setWidth(width);
		fieldLabel.setLabelWidth(labelWidth);
		fieldLabel.setLayoutData(new Margins(0, 10, 0, 0));
		
		searchBar.add(fieldLabel);
		return fieldLabel; 
	}
	
	
	public FieldLabel addLabel(TextField field, String labelName, int width, int labelWidth, boolean useEnterKey){
		
		FieldLabel fieldLabel = new FieldLabel(field, labelName); 
		fieldLabel.setHeight(26); // 고정높이 버튼과 높이를 맞춘다. 
		fieldLabel.setWidth(width);
		fieldLabel.setLabelWidth(labelWidth);
		fieldLabel.setLayoutData(new Margins(0, 10, 0, 0));
		fieldLabel.setLabelPad(0);
		
		if(useEnterKey){
			field.addKeyDownHandler(new KeyDownHandler(){
				@Override
				public void onKeyDown(KeyDownEvent arg0) {
					if(arg0.getNativeKeyCode() == 13){ // enter key down 
						target.retrieve(); 
					}
				}
			}); 
		}
		searchBar.add(fieldLabel);
		return fieldLabel; 
	}

	public FieldLabel addLabel(TextField textField, String labelName, int width, int labelWidth){
		return this.addLabel(textField, labelName, width, labelWidth, false);
	}
	
	
	public TextButton addRetrieveButton(){
		TextButton button = new TextButton("조회");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				target.retrieve();
			}
		}); 
		searchBar.add(button);
		return button; 
	}
	
	public TextButton addUpdateButton(){
		TextButton button = new TextButton("저장");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				target.update();
			}
		}); 
		searchBar.add(button);
		return button; 
	}
	
	public TextButton addInsertButton(){
		TextButton button = new TextButton("입력");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				target.insertRow();
			}
		}); 
		searchBar.add(button);
		
		return button; 
	}

	public TextButton addDeleteButton(){
		TextButton button = new TextButton("삭제");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				target.deleteRow();
			}
		}); 
		searchBar.add(button);
		return button; 
	}
}
