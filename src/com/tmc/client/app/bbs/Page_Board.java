package com.tmc.client.app.bbs;

import com.tmc.client.app.bbs.model.BoardModel;
import com.tmc.client.app.bbs.model.BoardModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.event.CellClickEvent.CellClickHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Page_Board extends FramedPanel implements InterfaceGridOperate {

	private BoardModelProperties properties = GWT.create(BoardModelProperties.class);
	private Grid<BoardModel> grid = this.buildGrid();
	private TextField searchText = new TextField();  
	
	public Page_Board(){
		
		this.setBorders(true);
		this.setHeading("공지 및 게시판");
		this.setHeaderVisible(true);
		this.add(grid);

		grid.addCellClickHandler(new CellClickHandler(){

			@Override
			public void onCellClick(CellClickEvent event) {
				if(event.getCellIndex() == 1){
					BoardModel boardModel = grid.getSelectionModel().getSelectedItem();
					Popup_Board popupBoard = new Popup_Board();
					popupBoard.open(boardModel); 
				}
			}
		}); 
		
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addTextField(searchText, "제목찾기", 200, 60, true) ; 
		searchBarBuilder.addRetrieveButton(); 
		
		this.getButtonBar().add(searchBarBuilder.getSearchBar());
		this.setButtonAlign(BoxLayoutPack.CENTER);
		// this.getButtonBar().add(new TextButton("닫기"));
		
		this.retrieve();
	}

	
	private Grid<BoardModel> buildGrid(){
		
		GridBuilder<BoardModel> gridBuilder = new GridBuilder<BoardModel>(properties.keyId());  

		// gridBuilder.setChecked(SelectionMode.SINGLE);

//		gridBuilder.addText(properties.boardTypeName(), 80, "구분");

		ColumnConfig<BoardModel, String> title = gridBuilder.addText(properties.title(), 400, "제목");

		/* 
		 * UNDERLINE_SETTING   
		 */
		
		SafeStylesBuilder sb = new SafeStylesBuilder(); 
		sb.textDecoration(com.google.gwt.dom.client.Style.TextDecoration.UNDERLINE );
		sb.trustedColor("DARKBLUE");
		sb.cursor(Style.Cursor.POINTER); 

		title.setColumnStyle(sb.toSafeStyles());
		
		gridBuilder.addDate(properties.writeDate(), 90, "작성일");		
		gridBuilder.addText(properties.korName(), 90, "작성자");
		
		return gridBuilder.getGrid();  
	}
	
	
	@Override
	public void retrieve() {
		GridRetrieveData<BoardModel> service = new GridRetrieveData<BoardModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginUser().getCompanyId());
		service.addParam("title", searchText.getValue());
		service.retrieve("bbs.Board.selectByCompanyId");
	}
	
	
	@Override
	public void update(){
//		GridUpdateData<RegisterModel> service = new GridUpdateData<RegisterModel>(); 
//		service.update(grid.getStore(), "psc.Register.update"); 
	}
	@Override
	public void insertRow(){
//		GridInsertRow<RegisterModel> service = new GridInsertRow<RegisterModel>(); 
//		RegisterModel registerModel = new RegisterModel();
//		registerModel.setStudentId(this.studentModel.getStudentId());
//		service.insertRow(grid, registerModel);
	}
	@Override
	public void deleteRow(){
//		GridDeleteData<RegisterModel> service = new GridDeleteData<RegisterModel>();
//		List<RegisterModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
//		service.deleteRow(grid.getStore(), checkedList, "psc.Register.delete");
	}
//	public Grid<RegisterModel> getGrid(){
//		return this.grid; 
//	}

}
