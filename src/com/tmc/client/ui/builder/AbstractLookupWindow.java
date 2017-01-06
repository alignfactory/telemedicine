package com.tmc.client.ui.builder;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.tmc.client.ui.InterfaceLookupResult;

abstract public class AbstractLookupWindow extends Window {
	
	private InterfaceLookupResult callback;
	private Object selectedModel; 
	
	abstract public void retrieve();
	abstract public void cancel();
	abstract public void confirm();
	
	ButtonBar searchBar = new ButtonBar(); 
	
	protected void setInit(String title, int width, int height){
		
		this.setModal(true);
		this.setHeading(title);
		this.setPixelSize(width, height);
		this.setResizable(false);

		this.addConfirmButton(); 
		this.addCancelButton();
		this.setButtonAlign(BoxLayoutPack.CENTER);
	}
	
	protected void addLabel(TextField field, String labelName, int width, int labelWidth, boolean useEnterKey){
		
		FieldLabel fieldLabel = new FieldLabel(field, labelName); 
		fieldLabel.setHeight(26); // 고정높이 버튼과 높이를 맞춘다. 
		fieldLabel.setWidth(width);
		fieldLabel.setLabelWidth(labelWidth);
	
		if(useEnterKey){
			field.addKeyDownHandler(new KeyDownHandler(){
				@Override
				public void onKeyDown(KeyDownEvent arg0) {
					if(arg0.getNativeKeyCode() == 13){ // enter key down 
						retrieve(); 
					}
				}
			}); 
		}
		searchBar.add(fieldLabel);
	}

	protected void addRetrieveButton(){
		TextButton button = new TextButton("조회");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				retrieve();
			}
		}); 
		searchBar.add(button);
	}

	protected ButtonBar getSearchBar(){
		this.addRetrieveButton(); 
		this.searchBar.setPadding(new Padding(0, 0, 0, 10));
		return this.searchBar; 
	}

	protected void addButtonBar(){
		this.addConfirmButton(); 
		this.addCancelButton();
	}

	protected void addConfirmButton(){
		TextButton button = new TextButton("확인");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				// 확인버튼을 누르면 선택된 객체(행)을 전달한다. 
				// callback.setLookupResult(selectedModel);
				getCallback().setLookupResult(selectedModel);
			}
		}); 
		this.addButton(button);
	}
	
	protected void addCancelButton(){
		TextButton button = new TextButton("취소");
		button.setWidth(50);
		button.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				// 작업을 취소하시겠습니까? 
				cancel();
			}
		}); 
		this.addButton(button);
	}

	public void setCallback(InterfaceLookupResult callback){
		this.callback = callback; 
	}
	
	public InterfaceLookupResult getCallback(){
		return this.callback ; 
	}
	
	
}
