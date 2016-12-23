package com.tmc.client.app.acc;

import java.util.List;

import com.tmc.client.app.acc.model.SeasonModel;
import com.tmc.client.app.acc.model.SeasonModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_Season2 extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private SeasonModelProperties properties = GWT.create(SeasonModelProperties.class);
	private Grid<SeasonModel> grid = this.buildGrid();
	private TextField className = new TextField();
	
	public Tab_Season2() {
		
		this.setBorders(false); 
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLabel(className, "회기명", 150, 50, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	@Override
	public void retrieve(){
		GridRetrieveData<SeasonModel> service = new GridRetrieveData<SeasonModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.retrieve("acc.Season.selectByCompanyId");
	}
	
	@Override
	public void update(){
		GridUpdateData<SeasonModel> service = new GridUpdateData<SeasonModel>(); 
		service.update(grid.getStore(), "acc.Season.update"); 
	}
	
	@Override
	public void insertRow(){
		GridInsertRow<SeasonModel> service = new GridInsertRow<SeasonModel>(); 
		SeasonModel seasonModel = new SeasonModel();
		seasonModel.setCompanyId(LoginUser.getLoginCompany());
		service.insertRow(grid, seasonModel);
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<SeasonModel> service = new GridDeleteData<SeasonModel>();
		List<SeasonModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "acc.Season.delete");
	}
	
	public Grid<SeasonModel> buildGrid(){
			
		GridBuilder<SeasonModel> gridBuilder = new GridBuilder<SeasonModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		final ComboBoxField eduOfficeComboBox = new ComboBoxField("EduOfficeCode");  
		eduOfficeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				SeasonModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.eduOfficeCode(), eduOfficeComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.eduOfficeName(), 150, "교육청구분", eduOfficeComboBox) ;
		
		gridBuilder.addText(properties.seasonName(), 120, "회기명", new TextField()) ;
		gridBuilder.addDate(properties.startDate(), 150, "시작일", new DateField()) ;
		gridBuilder.addDate(properties.closeDate(), 150, "종료일", new DateField()) ;
		gridBuilder.addText(properties.seq(), 100, "순서", new TextField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}

}