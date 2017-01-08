package com.tmc.client.app.tmc;

import com.tmc.client.app.bbs.Grid_File;
import com.tmc.client.app.sys.model.FileModel;
import com.tmc.client.app.sys.model.FileModelProperties;
import com.tmc.client.app.tmc.model.PatientModel;
import com.tmc.client.app.tmc.model.PatientModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.AbstractLookupWindow;
import com.tmc.client.ui.builder.GridBuilder;

import java.util.List;
import com.google.gwt.user.client.Window;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
//import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Lookup_File extends com.sencha.gxt.widget.core.client.Window {
	
	
	private FileModelProperties properties = GWT.create(FileModelProperties.class);	
	private Grid<FileModel> grid = this.buildGrid();  
	// private TextField patientName = new TextField();
	private ActionCell<String> deleteCell; 

	public void open(Long parentId){

		this.setModal(true);
		this.setSize("820",  "600");
		this.setHeaderVisible(true);
		this.setHeading("첨부파일");

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
//		vlc.add(this.getSearchBar(), new VerticalLayoutData(1, 40)); // , new Margins(0, 0, 0, 5)));
		vlc.add(grid, new VerticalLayoutData(1, 500));
		this.add(vlc);

	}
	
	public Grid<FileModel> buildGrid(){
		
		GridBuilder<FileModel> gridBuilder = new GridBuilder<FileModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addDateTime(properties.regDate(), 140, "등록일", null);
		gridBuilder.addText(properties.fileName(), 350, "파일명");
		gridBuilder.addDouble(properties.size(), 60, "크기"); 
		
		ActionCell<String> downloadCell = new ActionCell<String>("다운로드", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				FileModel fileModel = grid.getSelectionModel().getSelectedItem(); 
				String url = "FileDownload?fileId=" + fileModel.getFileId();
				Window.open(url, "download File", "status=0,toolbar=0,menubar=0,location=0");
			}
		});
		gridBuilder.addCell(properties.downloadCell(), 100, "다운로드", downloadCell) ;

		deleteCell = new ActionCell<String>("삭제", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				deleteFile(); 
			}
		});
		gridBuilder.addCell(properties.downloadCell(), 80, "삭제", deleteCell) ;

		return gridBuilder.getGrid(); 
	}
	


	public void deleteFile(){
		GridDeleteData<FileModel> service = new GridDeleteData<FileModel>();
		List<FileModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(this.grid.getStore(), checkedList, "sys.File.delete");
	}
	
	public void retrieve(Long parentId){
		GridRetrieveData<FileModel> service = new GridRetrieveData<FileModel>(grid.getStore());
		service.addParam("parentId", parentId);
		service.retrieve("sys.File.selectByParentId");
	} 
	
	
}
