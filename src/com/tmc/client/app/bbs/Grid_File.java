package com.tmc.client.app.bbs;

import java.util.List;

import com.tmc.client.app.sys.model.FileModel;
import com.tmc.client.app.sys.model.FileModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Grid_File {

	private FileModelProperties properties = GWT.create(FileModelProperties.class);
	private Grid<FileModel> grid = this.buildGrid(); 
//	private boolean deleteFile = true ; 
	private ActionCell<String> deleteCell; 
	
	public FormPanel form = new FormPanel();
	
//	public Grid<FileModel> buildGrid(boolean downloadOnly){
//		this.downloadOnly = downloadOnly; 
//		return this.buildGrid(); 
//	}
	
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
	
	public Grid<FileModel> getGrid(){
		this.grid.setBorders(false);
		return this.grid; 
	}

	public void setReadOnly(){
		this.grid.getColumnModel().getColumn(6).setHidden(true);
	}
}
