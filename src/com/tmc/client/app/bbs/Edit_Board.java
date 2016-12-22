package com.tmc.client.app.bbs;

import java.util.Date;
import java.util.List;

import com.tmc.client.app.bbs.model.BoardModel;
import com.tmc.client.app.sys.model.FileModel;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class Edit_Board extends ContentPanel implements Editor<BoardModel>, InterfaceGridOperate {

	interface EditDriver extends SimpleBeanEditorDriver<BoardModel, Edit_Board> {}
	EditDriver editDriver = GWT.create(EditDriver.class);
	Grid<BoardModel> grid;
	BoardModel boardModel = new BoardModel(); 
		
	TextField boardTypeCode = new TextField();
	ComboBoxField boardTypeName = new ComboBoxField("BoardTypeCode");
	
	TextField title  = new TextField();
	HtmlEditor content = new HtmlEditor();
	
	@Path("userModel.korName")
	TextField korName = new TextField();
	DateField writeDate = new DateField();

	FormPanel fileUploadForm = new FormPanel(); // file upload form. 
	private Grid_File gridFileBuilder = new Grid_File(); 
	private Grid<FileModel> gridFile = gridFileBuilder.getGrid(); 
	
	public Edit_Board(Grid<BoardModel> grid) {
		
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

		HorizontalLayoutData rowLayout = new HorizontalLayoutData(210, -1); // 한컬럼의 크기(라벨 + 필드)
		rowLayout.setMargins(new Margins(0, 20, 0, 0)); // 컬럼간의 간격조정

		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		row01.add(new FieldLabel(title, "제목"), new HorizontalLayoutData(1, -1, new Margins(0, 20, 0, 0)));
		
		HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
		row02.add(new FieldLabel(boardTypeName, "구분"), rowLayout);
		boardTypeName.setWidth(150);
		boardTypeName.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				boardTypeCode.setValue(boardTypeName.getCode());
			}
    	}); 

		row02.add(new FieldLabel(korName, "작성자"), rowLayout);
		korName.setReadOnly(true);
		row02.add(new FieldLabel(writeDate, "작성일"), rowLayout);
		writeDate.setReadOnly(true);
		writeDate.setHideTrigger(true);
		
		HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
		row03.add(new FieldLabel(content, "내용"), new HorizontalLayoutData(1, 1, new Margins(0, 20, 0, 0)));

		HorizontalLayoutContainer row04 = new HorizontalLayoutContainer();
		
		final FileUploadField fileUploadFiled = new FileUploadField();
		fileUploadForm.add(new FieldLabel(fileUploadFiled, "파일"));

		fileUploadForm.setEncoding(Encoding.MULTIPART);
		fileUploadForm.setMethod(Method.POST);
		fileUploadForm.addSubmitCompleteHandler(new SubmitCompleteHandler(){
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Info.display("upload", event.getResults().toString());
				gridFileBuilder.retrieve(boardModel.getBoardId());
			}
		});
		row04.add(fileUploadForm, new HorizontalLayoutData(0.9, 1, new Margins(2, 2, 0, 0)));
		
		TextButton fileUploadButton = new TextButton("등록"); 
		fileUploadButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if (!fileUploadForm.isValid()) {
					return;
				}
				
				if("".equals(fileUploadFiled.getValue().trim())) { 
					Info.display("파일확인", "먼저 업로드할 파일을 선택하여 주세요.");
				}
				else {
					String actionUrl = "FileUpload?uploadType=file&"; 
					actionUrl = actionUrl + "parentId=" + boardModel.getBoardId();
					
					fileUploadForm.setAction(actionUrl); // File upload servelt call - web.xml 참조
					fileUploadForm.submit();
					fileUploadFiled.reset();
				}
			}
		});
		row04.add(fileUploadButton, new HorizontalLayoutData(60, 28, new Margins(2, 2, 2, 1))); 
		
		HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
		
		row05.add(new FieldLabel(gridFile, "첨부"), new HorizontalLayoutData(1, 1, new Margins(0, 20, 0, 0)));
		
		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(16)));
		layout.add(row02, new VerticalLayoutData(1, -1, new Margins(16)));
		layout.add(row03, new VerticalLayoutData(1, 0.7, new Margins(16, 16, 0, 16)));
		layout.add(row04, new VerticalLayoutData(1, -1, new Margins(3, 16, 16, 16)));
		layout.add(row05, new VerticalLayoutData(1, 0.3, new Margins(20, 16, 0, 16)));
		
		layout.setScrollMode(ScrollMode.AUTOY);
 
//		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer(); 
//		hlc.add(layout, new HorizontalLayoutData(0.6, 1)); 
//		hlc.add(new SimplePanel(), new HorizontalLayoutData(1, 1, new Margins(15, 15, 15, 0)));
		
		// form setting
		FormPanel form = new FormPanel();
		form.setWidget(layout);
		form.setLabelWidth(50); // 모든 field 적용 후 설정한다.
		return form;
	}

	public void edit(BoardModel boardModel){
		this.boardModel = boardModel; 
		editDriver.edit(this.boardModel);
		gridFileBuilder.retrieve(boardModel.getBoardId());
		this.setEnabled(true);
	}
	
	@Override
	public void retrieve() {
	}
	
	@Override
	public void update(){
		// html editor에서 "'"값을 변경한다.  
		this.boardModel = editDriver.flush() ; 
		this.boardModel.setContent(this.boardModel.getContent().replaceAll("'", "''")); 
		
		GridUpdateData<BoardModel> service = new GridUpdateData<BoardModel>(); 
		service.update(this.grid.getStore(), this.boardModel, "bbs.Board.update"); 
	}
	
	@Override
	public void insertRow() {

		this.boardModel = new BoardModel();
		this.boardModel.setCompanyId(LoginUser.getLoginCompany());
		this.boardModel.setWriteUserId(LoginUser.getLoginUser().getUserId());
		this.boardModel.setUserModel(LoginUser.getLoginUser());
		this.boardModel.setWriteDate(new Date());
		
		GridInsertRow<BoardModel> service = new GridInsertRow<BoardModel>();
		service.insertRow(this.grid, boardModel);
		this.setEnabled(true);
	}
	
	@Override
	public void deleteRow() {
		GridDeleteData<BoardModel> service = new GridDeleteData<BoardModel>();
		List<BoardModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "bbs.Board.delete");
		
		this.setEnabled(false);
	}
}