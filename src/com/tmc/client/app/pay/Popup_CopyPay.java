package com.tmc.client.app.pay;

import com.tmc.client.app.pay.model.PaydayModel;
import com.tmc.client.app.pay.model.PaydayModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.CallBatch;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.InterfaceCallback;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Popup_CopyPay extends Window implements InterfaceCallback{
	
	private PaydayModelProperties properties = GWT.create(PaydayModelProperties.class);
	private Grid<PaydayModel> grid = this.buildGrid();
	private PaydayModel paydayModel; 
	
	public Popup_CopyPay(PaydayModel paydayModel){
		
		this.setPaydayModel(paydayModel); 
		
		DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
		this.setHeading("월 급여복사  : 급여지급일 ( "  + dtf.format(paydayModel.getPayDate()) + " )");
		
		this.setModal(true);
		this.setPixelSize(700, 350);
		this.setLayoutData(new MarginData(0));
		this.add(grid);
		
		TextButton copyButton = new TextButton("복사");
		copyButton.setWidth(60);
		copyButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				copyPay(); 
			}
		});
		this.addButton(copyButton);

		TextButton closeButton = new TextButton("닫기");
		closeButton.setWidth(60);
		closeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		});
		this.addButton(closeButton);
		this.getButtonBar().setBorders(false);
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.retrieve();

	}

	public void copyPay(){
		PaydayModel fromPaydayModel = grid.getSelectionModel().getSelectedItem(); 
		
		if(fromPaydayModel == null){
			new SimpleMessage("복사대상 급여월이 선택되어 있지 않습니다."); 
			return ; 
		}
		
		CallBatch batch = new CallBatch(this);
		
		batch.addParam("fromPaydayId", fromPaydayModel.getPaydayId());
		batch.addParam("toPaydayId", this.getPaydayModel().getPaydayId());
		batch.execute("pay.Pay.copyPay");
	}

	@Override
	public void callback() {
//		List<PaydayModel> list = new ArrayList<PaydayModel>(); 
//		list.add(this.paydayModel); 
//		this.grid.getSelectionModel().setSelection(list);  // reselection paydayModel
		this.hide(); // 조회하고 닫는다. 
	}

	public void retrieve(){
		GridRetrieveData<PaydayModel> service = new GridRetrieveData<PaydayModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.addParam("paydayId", this.getPaydayModel().getPaydayId());
		service.retrieve("pay.Payday.selectByCompanyId");
	}

	public PaydayModel getPaydayModel() {
		return paydayModel;
	}

	public void setPaydayModel(PaydayModel paydayModel) {
		this.paydayModel = paydayModel;
	}
	
	public Grid<PaydayModel> buildGrid(){
		
		GridBuilder<PaydayModel> gridBuilder = new GridBuilder<PaydayModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addDate(properties.payDate(), 120, "급여지급일");
		gridBuilder.addText(properties.payTypeName(), 100, "지급구분") ;
		gridBuilder.addText(properties.payName(), 200, "지급내역 상세") ;
		gridBuilder.addDate(properties.accountDate(), 120, "회계기준일") ;
		gridBuilder.addDate(properties.onDate(), 120, "기간시작일", new DateField()) ;
		gridBuilder.addDate(properties.offDate(), 120, "기간종료일", new DateField()) ;
		gridBuilder.addText(properties.note(), 400, "비고", new TextField());

		return gridBuilder.getGrid(); 
	}
}
