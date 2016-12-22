package com.tmc.client.ui;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public abstract class AbstractEditor extends Window {

//	public abstract void insert();
	public abstract void update();
	public abstract void delete();
	public abstract void edit(AbstractDataModel data);
	
	public void setInit(String title, final int width, final int height){
		
		this.setModal(true);
		this.setHeading(title);

		this.addShowHandler(new ShowHandler(){
			@Override
			public void onShow(ShowEvent event) {
				// size setting 
				// 이렇게 안하면 높이가 오픈할때마다 높아진다. 버그인거 같은데...
				setPixelSize(width, height); 
			}
		}); 
	}
	
	public void addDefaultButton(){
		
	    TextButton delete = new TextButton("삭제");
	    delete.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				delete();
				hide();
			}
	    });
		this.addButton(delete);

	    TextButton save = new TextButton("저장");
	    save.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				update(); 
			}
	    });
	    this.addButton(save);
		
	    TextButton close = new TextButton("닫기");
		close.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		});
		this.addButton(close);
	}

}
