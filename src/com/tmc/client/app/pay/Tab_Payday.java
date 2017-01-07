package com.tmc.client.app.pay;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmc.client.app.pay.model.PaydayModel;
import com.tmc.client.app.pay.model.PaydayModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class Tab_Payday extends BorderLayoutContainer implements InterfaceGridOperate{
	
	private PaydayModelProperties properties = GWT.create(PaydayModelProperties.class);
	private Grid<PaydayModel> grid = this.buildGrid();
	private TextField payYear = new TextField(); 
	private PlainTabPanel tabPanel = new PlainTabPanel();
	
	
	public Tab_Payday() {

		this.setBorders(false); 
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addTextField(payYear, "지급년도", 150, 60, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		this.setBorders(false);
		this.setNorthWidget(searchBarBuilder.getSearchBar(), new BorderLayoutData(40)); 
		this.setCenterWidget(this.grid, new BorderLayoutData(0.4));

		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<PaydayModel>(){
			@Override
			public void onSelectionChanged(SelectionChangedEvent<PaydayModel> event) {
				retrieveTabpage(); 
			}
		});

		tabPanel.add(new TabPage_Pay(), "월지급내역"); // tabPay.setTitle("tabPay");
		tabPanel.addSelectionHandler(new SelectionHandler<Widget>(){
			@Override
			public void onSelection(SelectionEvent<Widget> arg0) {
				// TODO Auto-generated method stub
				if(arg0 != null) {
					retrieveTabpage();
				} 
			}
		}); 

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(tabPanel, new VerticalLayoutData(1, 1, new Margins(5, 0, 10, 1)));
		
		ContentPanel panel = new ContentPanel(); //setMargins이 적용되지 않아 한번 더 감싼다. 
		panel.setHeaderVisible(false);
		panel.add(vlc);
		
		BorderLayoutData southLayoutData = new BorderLayoutData(0.6);
		southLayoutData.setMargins(new Margins(2,0,0,0));
		southLayoutData.setSplit(true);
		southLayoutData.setMaxSize(1000);
		
		this.setSouthWidget(panel, southLayoutData);
		
		Date today = new Date();
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy");
		payYear.setValue(fmt.format(today));
	}

	public Grid<PaydayModel> buildGrid(){
		
		GridBuilder<PaydayModel> gridBuilder = new GridBuilder<PaydayModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		gridBuilder.addDate(properties.payDate(), 100, "급여지급일", new DateField()) ;
		
		final ComboBoxField payTypeComboBox = new ComboBoxField("StudyClassTypeCode");
		payTypeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				PaydayModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.payTypeCode(), payTypeComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.payTypeName(), 100, "지급구분", payTypeComboBox) ;
		gridBuilder.addText(properties.payName(), 200, "지급내역 상세", new TextField()) ;
		
		ActionCell<String> copyPayButtonCell = new ActionCell<String>("급여복사", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				PaydayModel paydayModel = grid.getSelectionModel().getSelectedItem(); 
				new Popup_CopyPay(paydayModel).show();
			}
		});
		gridBuilder.addCell(properties.actionCell(), 80, "급여복사", copyPayButtonCell) ;
		gridBuilder.addDate(properties.accountDate(), 100, "회계기준일", new DateField()) ;

		ActionCell<String> closePayCell = new ActionCell<String>("급여마감", new ActionCell.Delegate<String>(){
			@Override
			public void execute(String arg0) {
				//copyPay() ; 
			}
		});
		gridBuilder.addCell(properties.actionCell(), 80, "급여마감", closePayCell) ;
		gridBuilder.addDate(properties.onDate(), 100, "기간시작일", new DateField()) ;
		gridBuilder.addDate(properties.offDate(), 100, "기간종료일", new DateField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}
	
	
	public void retrieveTabpage(){
		InterfaceTabPage selectedPage = (InterfaceTabPage)tabPanel.getActiveWidget();
		PaydayModel paydayModel = grid.getSelectionModel().getSelectedItem() ; 
		if( paydayModel != null){
			Map<String, Object> tabPageParam = new HashMap<String, Object>();
			tabPageParam.put("paydayModel", paydayModel); 
			selectedPage.retrieve(tabPageParam);
		}
		else {
			selectedPage.retrieve(null);
		}
	}
	
	@Override
	public void retrieve(){
		String payYear = this.payYear.getValue();
		
		GridRetrieveData<PaydayModel> service = new GridRetrieveData<PaydayModel>(this.grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.addParam("payYear", payYear);
		service.retrieve("pay.Payday.selectByPayYear");
	}
	
	@Override
	public void update(){
		GridUpdateData<PaydayModel> service = new GridUpdateData<PaydayModel>(); 
		service.update(this.grid.getStore(), "pay.Payday.update"); 
	}
	
	@Override
	public void insertRow(){
		GridInsertRow<PaydayModel> service = new GridInsertRow<PaydayModel>(); 
		PaydayModel PaydayModel = new PaydayModel();
		PaydayModel.setCompanyId(LoginUser.getLoginCompany());
		service.insertRow(this.grid, PaydayModel);
	}
	@Override
	public void deleteRow(){
		GridDeleteData<PaydayModel> service = new GridDeleteData<PaydayModel>();
		List<PaydayModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(this.grid.getStore(), checkedList, "pay.Payday.delete");
	}

}