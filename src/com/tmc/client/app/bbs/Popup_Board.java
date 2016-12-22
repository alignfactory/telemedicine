package com.tmc.client.app.bbs;

import com.tmc.client.app.bbs.model.BoardModel;
import com.tmc.client.app.sys.model.FileModel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Popup_Board extends Window {
	
	private Grid_File gridFileBuilder = new Grid_File(); 
	private Grid<FileModel> gridFile = gridFileBuilder.getGrid(); 
	
	public void open(BoardModel boardModel){
		this.setModal(true);
		this.setSize("820",  "650");
		this.setHeading(boardModel.getTitle());

		HorizontalLayoutData rowLayout = new HorizontalLayoutData(210, -1); // 한컬럼의 크기(라벨 + 필드)
		rowLayout.setMargins(new Margins(0, 20, 0, 0)); // 컬럼간의 간격조정

		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		TextField title = new TextField(); 
		title.setText(boardModel.getTitle());
		row01.add(new FieldLabel(title, "제목"), new HorizontalLayoutData(1, -1, new Margins(0, 10, 0, 0)));
		
		HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
		TextField boardTypeName = new TextField(); 
		boardTypeName.setText(boardModel.getBoardTypeName());
		row02.add(new FieldLabel(boardTypeName, "구분"), rowLayout);
		boardTypeName.setWidth(150);
		
		TextField korName = new TextField(); 
		korName.setText(boardModel.getUserModel().getKorName());
		row02.add(new FieldLabel(korName, "작성자"), rowLayout);

		DateField writeDate = new DateField(); 
		writeDate.setValue(boardModel.getWriteDate());
		row02.add(new FieldLabel(writeDate, "작성일"), rowLayout);
		writeDate.setReadOnly(true);
		writeDate.setHideTrigger(true);
		
		HtmlEditor content = new HtmlEditor();

		content.setEnableSourceEditMode(false);
		content.setEnableAlignments(false);
		content.setEnableColors(false);
		content.setEnableFont(false);
		content.setEnableFontSize(false);
		content.setEnableFormat(false);
		content.setEnableLinks(false);
		content.setEnableLists(false);

		content.setValue(boardModel.getContent());
		
		HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
		row03.add(new FieldLabel(content, "내용"), new HorizontalLayoutData(1, 1, new Margins(0, 10, 0, 0)));

		HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
		row05.add(new FieldLabel(gridFile, "첨부"), new HorizontalLayoutData(1, 1, new Margins(0, 10, 0, 0)));
		// retrieve file list 
		gridFile.setBorders(false);
		gridFileBuilder.setReadOnly();
		gridFileBuilder.retrieve(boardModel.getBoardId());
		
		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(16)));
		layout.add(row02, new VerticalLayoutData(1, -1, new Margins(16)));
		layout.add(row03, new VerticalLayoutData(1, 0.7, new Margins(16, 16, 0, 16)));
		layout.add(row05, new VerticalLayoutData(1, 0.3, new Margins(3, 16, 5, 16)));
		
		FormPanel form = new FormPanel();
		form.setWidget(layout);
		form.setLabelWidth(50); // 모든 field 적용 후 설정한다.
		
		this.add(layout);
		
		TextButton closeButton = new TextButton("닫기"); 
		closeButton.setWidth(60);
		closeButton.addSelectHandler(new SelectHandler(){

			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		}); 
		
		this.getButtonBar().add(closeButton);
		this.setButtonAlign(BoxLayoutPack.CENTER);
		
		this.show(); 
	}
	
}
