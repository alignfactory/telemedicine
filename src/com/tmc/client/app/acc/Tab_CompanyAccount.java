package com.tmc.client.app.acc;

import java.util.List;

import com.tmc.client.app.acc.model.AccountModelProperties;
import com.tmc.client.app.sys.Lookup_Company;
import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.main.LoginUser;
import com.tmc.client.app.acc.model.AccountModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent.BeforeStartEditHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_CompanyAccount extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private AccountModelProperties properties = GWT.create(AccountModelProperties.class);
	private Grid<AccountModel> grid = this.buildGrid();
	
	private CompanyModel companyModel = LoginUser.getLoginUser().getCompanyModel();
	private LookupTriggerField lookupCompanyField = this.getLookupCompanyField() ; 
	private ComboBoxField eduOfficeName = new ComboBoxField("EduOfficeCode");
	
	public Tab_CompanyAccount() {
		
		this.setBorders(false); 

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addLookupTriggerField(lookupCompanyField, "유치원", 250, 50); 
		searchBarBuilder.addComboBox(eduOfficeName, "회계구분", 250, 60);
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();

		TextButton copyRowButton = new TextButton("행복사"); 
		searchBarBuilder.getSearchBar().add(copyRowButton);
		copyRowButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				insertRow(); // 행복사는 함수안에서...... 
			}
		}); 

		searchBarBuilder.addDeleteButton();
		
		TextButton printButton = new TextButton("출력");
		printButton.setWidth(60);
		searchBarBuilder.getSearchBar().add(printButton);

		printButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				new PDF_AccountPrint(2015020).show();  
			}
		}); 
		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	private LookupTriggerField getLookupCompanyField(){
		
		final Lookup_Company lookupCompany = new Lookup_Company();
		lookupCompany.setCallback(new InterfaceLookupResult(){
			@Override
			public void setLookupResult(Object result) {
				companyModel = (CompanyModel)result;// userCompanyModel.getCompanyModel(); 
				lookupCompanyField.setValue(companyModel.getCompanyName());
			}
		});
		
		LookupTriggerField lookupCompanyField = new LookupTriggerField(); 
		lookupCompanyField.setEditable(false);
		lookupCompanyField.setText(this.companyModel.getCompanyName());
		lookupCompanyField.addTriggerClickHandler(new TriggerClickHandler(){
   	 		@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			lookupCompany.show();
			}
   	 	}); 
		return lookupCompanyField; 
	}
	
	@Override
	public void retrieve(){
		
		String eduOfficeCode = eduOfficeName.getCode(); 
		
		if(eduOfficeCode == null){
			new SimpleMessage("조회할 회계구분코드를 먼저 선택하여야 합니다.");
			return ; 
		}
		
		Long companyId = this.companyModel.getCompanyId();
		if(companyId  == null){
			new SimpleMessage("조회할 유치원이 먼저 선택하여야 합니다.");
			return ; 
		}
		
		GridRetrieveData<AccountModel> service = new GridRetrieveData<AccountModel>(grid.getStore());
		service.addParam("eduOfficeCode", eduOfficeCode);
		service.addParam("companyId", companyId);
		service.retrieve("acc.Account.selectByCompanyId");
	}
	
	@Override
	public void update(){
		GridUpdateData<AccountModel> service = new GridUpdateData<AccountModel>(); 
		service.update(grid.getStore(), "acc.Account.update"); 
	}
	
	@Override
	public void insertRow(){
		
		AccountModel currentModel = grid.getSelectionModel().getSelectedItem() ; 

		if(currentModel != null){
			GridInsertRow<AccountModel> service = new GridInsertRow<AccountModel>();
			
			AccountModel accountModel = new AccountModel(); 
			accountModel.setBudgetYn(currentModel.getBudgetYn());
			accountModel.setCompanyId(this.companyModel.getCompanyId()); // companyId는 0일 수 있다. 
			accountModel.setEduOfficeCode(currentModel.getEduOfficeCode());
			accountModel.setEduOfficeName(currentModel.getEduOfficeName());
			accountModel.setGmokCode(currentModel.getGmokCode());
			accountModel.setGmokName(currentModel.getGmokName());
			accountModel.setGwonCode(currentModel.getGwonCode());
			accountModel.setGwonName(currentModel.getGwonName());
			accountModel.setHangCode(currentModel.getHangCode());
			accountModel.setHangName(currentModel.getHangName());
			accountModel.setInExpCode(currentModel.getInExpCode());
			accountModel.setInExpName(currentModel.getInExpName());
			accountModel.setPrintName(currentModel.getPrintName());
			accountModel.setSmokCode(currentModel.getSmokCode());
			accountModel.setSmokName(currentModel.getSmokName());
			accountModel.setSumYn(currentModel.getSumYn());
			accountModel.setTransYn(currentModel.getTransYn());
			accountModel.setUseYn(currentModel.getUseYn());
			
			service.insertRow(grid, accountModel);
		}
		else {
			new SimpleMessage("복사할 계정정보를 먼저 선택해 주세요. "); 
			return ; 
		}
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<AccountModel> service = new GridDeleteData<AccountModel>();
		List<AccountModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "acc.Account.delete");
	}
	
	public Grid<AccountModel> buildGrid(){
			
		GridBuilder<AccountModel> gridBuilder = new GridBuilder<AccountModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.accountType(), 60, "구분", new TextField());
		gridBuilder.addText(properties.gwonCode(), 60, "관코드"); // not editable 
		gridBuilder.addText(properties.gwonName(), 100, "관이름", new TextField()); 
		gridBuilder.addText(properties.hangCode(), 60, "항코드", new TextField()); 
		gridBuilder.addText(properties.hangName(), 100, "항이름", new TextField()); 
		gridBuilder.addText(properties.gmokCode(), 60, "목코드", new TextField()); 
		gridBuilder.addText(properties.gmokName(), 100, "목이름", new TextField()); 
		gridBuilder.addText(properties.smokCode(), 80, "세목코드", new TextField()); 
		gridBuilder.addText(properties.smokName(), 100, "세목이름", new TextField()); 
		gridBuilder.addText(properties.printName(), 200, "출력명", new TextField()); 

		gridBuilder.addBoolean(properties.sumYnFlag(), 50, "합계"); 
		gridBuilder.addBoolean(properties.transYnFlag(), 50, "거래"); 
		
		final ComboBoxField inExpComboBox = new ComboBoxField("InExpCode");  
		inExpComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				AccountModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.inExpCode(), inExpComboBox.getCode());
			}
		}); 
		gridBuilder.addText(properties.inExpName(), 100, "입출구분", inExpComboBox) ;
		gridBuilder.addBoolean(properties.budgetYnFlag(), 50, "예산"); 
		gridBuilder.addBoolean(properties.useYnFlag(), 50, "제외"); 
	
		gridBuilder.addText(properties.note(), 400, "비고", new TextField()); 

		// grid를 return 하기 전에 설정해야 한다. 
		gridBuilder.setBeforeStartEditHandler(new BeforeStartEditHandler<AccountModel>(){
			@Override
			public void onBeforeStartEdit(BeforeStartEditEvent<AccountModel> event) {
				// TODO Auto-generated method stub
				
				AccountModel accountModel = grid.getSelectionModel().getSelectedItem(); 
				if(accountModel != null){
					if("공통".equals(accountModel.getAccountType())){
						event.setCancelled(true);		
					}
					else {
						event.setCancelled(false);
					}
				}
			}
		}); 
		
		return gridBuilder.getGrid();  
	}
}