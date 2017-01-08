package com.tmc.client.app.tmc;

import com.tmc.client.app.sys.model.FileModel;
import com.tmc.client.app.sys.model.FileModelProperties;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;

import java.util.List;
import com.google.gwt.user.client.Window;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
//import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class Lookup_File extends com.sencha.gxt.widget.core.client.Window {
	
	private FileModelProperties properties = GWT.create(FileModelProperties.class);	
	private Grid<FileModel> grid = this.buildGrid();  
	// private TextField patientName = new TextField();
	private ActionCell<String> deleteCell; 
	private FileUploadField fileUploadFiled = new FileUploadField();
	private FormPanel fileUploadForm = new FormPanel(); // file upload form.
	private Long parentId ; 
	
	public void open(Long parentId){
		
		this.parentId = parentId; 
		
		this.setModal(true);
		this.setSize("620",  "350");
		this.setHeaderVisible(true);
		this.setHeading("첨부파일");

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(grid, new VerticalLayoutData(1, 500));
		this.add(vlc);

		FieldLabel fieldLabel = new FieldLabel(fileUploadFiled, "파일찾기") ;
		fieldLabel.setLabelWidth(60);
		fieldLabel.setWidth(420);
		
		fileUploadForm.add(fieldLabel);
		fileUploadForm.addSubmitCompleteHandler(new SubmitCompleteHandler(){
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				retrieve();
			}
		});
		this.getButtonBar().add(fileUploadForm);
		
		TextButton fileUploadButton = new TextButton("등록"); 

		fileUploadButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if("".equals(fileUploadFiled.getValue().trim())) { 
					Info.display("파일확인", "먼저 업로드할 파일을 선택하여 주세요.");
				}
				else {
					fileUploadCall(); 
				}
			}
		});

		fileUploadButton.setWidth(60);
		this.addButton(fileUploadButton);
		
		TextButton buttonClose = new TextButton("닫기");
		buttonClose.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		}); 
		
		buttonClose.setWidth(60);
		this.addButton(buttonClose);
		
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.getButtonBar().setSpacing(10);
		
		this.retrieve();
	}
	
	private void fileUploadCall(){
		String actionUrl = "FileUpload?uploadType=file&"; 
		actionUrl = actionUrl + "parentId=" + this.parentId;

		fileUploadForm.setEncoding(Encoding.MULTIPART);
		fileUploadForm.setMethod(Method.POST);
		fileUploadForm.setAction(actionUrl); // File upload servelt call - web.xml 참조
		fileUploadForm.submit();
		fileUploadFiled.reset();
	}
	
	public Grid<FileModel> buildGrid(){
		
		GridBuilder<FileModel> gridBuilder = new GridBuilder<FileModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		//gridBuilder.addDateTime(properties.regDate(), 140, "등록일", null);
		gridBuilder.addText(properties.fileName(), 300, "파일명");
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
	
	public void retrieve(){
		GridRetrieveData<FileModel> service = new GridRetrieveData<FileModel>(grid.getStore());
		service.addParam("parentId", this.parentId);
		service.retrieve("sys.File.selectByParentId");
	} 
}
