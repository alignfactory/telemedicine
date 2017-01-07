package com.tmc.client.app.bbs;

import com.tmc.client.app.bbs.model.BoardModel;
import com.tmc.client.app.bbs.model.BoardModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_Board extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private BoardModelProperties properties = GWT.create(BoardModelProperties.class);
	private Grid<BoardModel> grid = this.buildGrid();
	private TextField searchText = new TextField();  
	private Edit_Board editBoard = new Edit_Board(this.grid);
	
	public Tab_Board() {
		
		this.setBorders(false); 
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addTextField(searchText, "제목으로 찾기", 250, 100, true); 
		searchBarBuilder.addRetrieveButton(); 
		
		BorderLayoutData northLayoutData = new BorderLayoutData(40); 
		northLayoutData.setMargins(new Margins(0, 0, 0, 0));
		this.setNorthWidget(searchBarBuilder.getSearchBar(), northLayoutData);
		this.setCenterWidget(grid);
		
		BorderLayoutData eastLayoutData = new BorderLayoutData(0.7);
		eastLayoutData.setMargins(new Margins(1,0,0,2));
		eastLayoutData.setSplit(true);
		eastLayoutData.setMaxSize(2000);
		this.setEastWidget(editBoard, eastLayoutData);
		
		this.grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<BoardModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<BoardModel> event) {
				if(event.getSelection().size() > 0){
					BoardModel boardModel = grid.getSelectionModel().getSelectedItem(); 
					editBoard.edit(boardModel); 
				}
			} 
		}); 
	}
	
	@Override
	public void retrieve(){
		GridRetrieveData<BoardModel> service = new GridRetrieveData<BoardModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.addParam("title", searchText.getValue());
		
		service.retrieve("bbs.Board.selectByCompanyId");
	}
	
	@Override
	public void update(){
	}
	
	@Override
	public void insertRow(){
	}
	
	@Override
	public void deleteRow(){
	}
	
	public Grid<BoardModel> buildGrid(){
		
		GridBuilder<BoardModel> gridBuilder = new GridBuilder<BoardModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addText(properties.boardTypeName(), 100, "구분");
		gridBuilder.addText(properties.title(), 250, "제목");
		gridBuilder.addText(properties.korName(), 100, "작성자");
		gridBuilder.addDate(properties.writeDate(), 100, "작성일");

		return gridBuilder.getGrid(); 
	}

}