package com.tmc.client.app.pay;

import java.util.List;
import java.util.Map;

import com.tmc.client.app.pay.model.PayModel;
import com.tmc.client.app.pay.model.PayModelProperties;
import com.tmc.client.app.pay.model.PaydayModel;
import com.tmc.client.app.psc.Lookup_User;
import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.InterfaceTabPage;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.tmc.client.ui.field.LookupTriggerField;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.DoubleField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class TabPage_Pay extends ContentPanel implements InterfaceTabPage, InterfaceGridOperate, InterfaceLookupResult {

	private PayModelProperties properties = GWT.create(PayModelProperties.class);
	private Grid<PayModel> grid = this.buildGrid(); 
	private PaydayModel paydayModel; 
	
	public TabPage_Pay(){
		
		this.setHeaderVisible(false);
		this.add(this.grid);
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addDeleteButton();

		this.getButtonBar().add(searchBarBuilder.getSearchBar()); 
		this.setButtonAlign(BoxLayoutPack.CENTER);
	}
	
	public TabPage_Pay getThis(){
		return this; 
	}
	public Grid<PayModel> buildGrid(){
		
		GridBuilder<PayModel> gridBuilder = new GridBuilder<PayModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		LookupTriggerField userLookupField = new LookupTriggerField(); 
		userLookupField.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				// TODO Auto-generated method stub
				new Lookup_User(getThis()).show();
			}
		}); 
		gridBuilder.addText(properties.korName(), 80, "교원명", userLookupField) ;
		gridBuilder.addDouble(properties.baseAmt(), 90, "기본급여", new DoubleField()) ;
		gridBuilder.addDouble(properties.hireAmt(), 90, "교원수당", new DoubleField()) ;
		gridBuilder.addDouble(properties.benefitAmt(), 90, "복리후생", new DoubleField()) ;
		gridBuilder.addDouble(properties.extraAmt(), 90, "기타수당", new DoubleField()) ;
		gridBuilder.addDouble(properties.etcInAmt(), 90, "기타지급", new DoubleField()) ;
		gridBuilder.addDouble(properties.sumPayAmt(), 90, "지급합계", new DoubleField()) ;

		gridBuilder.addDouble(properties.incomeTax(), 90, "소득세", new DoubleField()) ;
		gridBuilder.addDouble(properties.ctzTax(), 80, "주민세", new DoubleField()) ;
		gridBuilder.addDouble(properties.healthIns(), 90, "건강보험", new DoubleField()) ;
		gridBuilder.addDouble(properties.privatePns(), 90, "사학연금", new DoubleField()) ;
		gridBuilder.addDouble(properties.unEmpIns(), 90, "고용보험", new DoubleField()) ;
		gridBuilder.addDouble(properties.nationPns(), 90, "국민연금", new DoubleField()) ;
		gridBuilder.addDouble(properties.etcOutAmt(), 90, "기타공제", new DoubleField()) ;
		gridBuilder.addDouble(properties.sumDeduceAmt(), 90, "공제합계", new DoubleField()) ;
		
		gridBuilder.addDouble(properties.sumRealPayAmt(), 120, "차감지급액", new DoubleField()) ;
		gridBuilder.addText(properties.bankName(), 80, "은행명") ;
		gridBuilder.addText(properties.accountNo(), 120, "계좌번호") ;

		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}
	
	@Override
	public void retrieve(Map<String, Object> param) {
		this.paydayModel = (PaydayModel) param.get("paydayModel");
		
		if(this.paydayModel != null){
			GridRetrieveData<PayModel> service = new GridRetrieveData<PayModel>(grid.getStore());
			service.addParam("paydayId", this.paydayModel.getPaydayId());
			service.retrieve("pay.Pay.selectByPaydayId");
		}
		else {
			this.grid.getStore().clear();
		}
	}

	@Override
	public void setLookupResult(Object result) {
		UserModel userModel = (UserModel)result; 
		PayModel data = grid.getSelectionModel().getSelectedItem(); 
		grid.getStore().getRecord(data).addChange(properties.korName(), userModel.getKorName());
		grid.getStore().getRecord(data).addChange(properties.userId(), userModel.getUserId());
		grid.getStore().getRecord(data).addChange(properties.bankName(), userModel.getBankName());
		grid.getStore().getRecord(data).addChange(properties.accountNo(), userModel.getAccountNo());
	}

	@Override
	public void retrieve() { //retrieve(Map param)이 대신한다. 
	}

	@Override
	public void update() {
		GridUpdateData<PayModel> service = new GridUpdateData<PayModel>(); 
		service.update(grid.getStore(), "pay.Pay.update"); 
	}

	@Override
	public void insertRow() {
		if(this.paydayModel != null){
			GridInsertRow<PayModel> service = new GridInsertRow<PayModel>(); 
			PayModel PayModel = new PayModel();
			PayModel.setPaydayId(paydayModel.getPaydayId());
			service.insertRow(grid, PayModel);
		}
		else {
			new SimpleMessage("지급일 확인", "선택된 지급일이 없습니다. "); 
		}
	}

	@Override
	public void deleteRow() {
		GridDeleteData<PayModel> service = new GridDeleteData<PayModel>();
		List<PayModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "pay.Pay.delete");
	}
	
}