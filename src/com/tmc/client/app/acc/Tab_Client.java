package com.tmc.client.app.acc;

import java.util.List;

import com.tmc.client.app.acc.model.ClientModel;
import com.tmc.client.app.acc.model.ClientModelProperties;
import com.tmc.client.main.LoginUser;
import com.tmc.client.service.GridDeleteData;
import com.tmc.client.service.GridInsertRow;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.service.GridUpdateData;
import com.tmc.client.ui.builder.ComboBoxField;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.HeaderGroupMap;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;

public class Tab_Client extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private ClientModelProperties properties = GWT.create(ClientModelProperties.class);
	private Grid<ClientModel> grid = this.buildGrid();
	private TextField clientName = new TextField();
	
	public Tab_Client() {
		
		this.setBorders(false); 
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addTextField(clientName, "거래처명", 200, 60, true); 
		searchBarBuilder.addRetrieveButton(); 
		searchBarBuilder.addUpdateButton();
		searchBarBuilder.addInsertButton();
		searchBarBuilder.addDeleteButton();
		
		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	@Override
	public void retrieve(){
		GridRetrieveData<ClientModel> service = new GridRetrieveData<ClientModel>(grid.getStore());
		service.addParam("companyId", LoginUser.getLoginCompany());
		service.addParam("clientName", clientName.getValue());
		service.retrieve("acc.Client.selectByCompanyId");
	}
	
	@Override
	public void update(){
		GridUpdateData<ClientModel> service = new GridUpdateData<ClientModel>(); 
		service.update(grid.getStore(), "acc.Client.update"); 
	}
	
	@Override
	public void insertRow(){
		GridInsertRow<ClientModel> service = new GridInsertRow<ClientModel>(); 
		ClientModel seasonModel = new ClientModel();
		seasonModel.setCompanyId(LoginUser.getLoginCompany());
		service.insertRow(grid, seasonModel);
	}
	
	@Override
	public void deleteRow(){
		GridDeleteData<ClientModel> service = new GridDeleteData<ClientModel>();
		List<ClientModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(grid.getStore(), checkedList, "acc.Client.delete");
	}
	
	public Grid<ClientModel> buildGrid(){
			
		GridBuilder<ClientModel> gridBuilder = new GridBuilder<ClientModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		final ComboBoxField bankCodeComboBox = new ComboBoxField("BankCode");  
		bankCodeComboBox.addCollapseHandler(new CollapseHandler(){
			@Override
			public void onCollapse(CollapseEvent event) {
				ClientModel data = grid.getSelectionModel().getSelectedItem(); 
				grid.getStore().getRecord(data).addChange(properties.bankCode(), bankCodeComboBox.getCode());
			}
		}); 
		
		gridBuilder.addHeaderGroupMap(new HeaderGroupMap(0, 0, new HeaderGroupConfig("거래처", 1, 11)));
		gridBuilder.addHeaderGroupMap(new HeaderGroupMap(0, 11, new HeaderGroupConfig("계좌정보", 1, 3)));
		gridBuilder.addHeaderGroupMap(new HeaderGroupMap(0, 14, new HeaderGroupConfig("담당자", 1, 4)));
		
		gridBuilder.addText(properties.clientName(), 150, "거래처명", new TextField());
		gridBuilder.addText(properties.bizNo(), 100, "사업자번호", new TextField());
		gridBuilder.addText(properties.ceoName(), 100, "대표자", new TextField());
		gridBuilder.addText(properties.ctzNo(), 80, "주민번호", new TextField());
		gridBuilder.addText(properties.industry(), 100, "업태", new TextField());
		gridBuilder.addText(properties.bizType(), 100, "업종", new TextField());
		gridBuilder.addText(properties.tel1(), 80, "전화(1)"); //, new TextField());
		gridBuilder.addText(properties.tel2(), 80, "전화(2)"); //, new TextField());
		gridBuilder.addText(properties.fax1(), 80, "팩스번호"); //, new TextField());
		gridBuilder.addText(properties.accountNo(), 80, "계좌번호"); //, new TextField());
		gridBuilder.addText(properties.bankName(), 100, "거래은행"); //, bankCodeComboBox);
		gridBuilder.addText(properties.accountOwner(), 80, "소유주"); //, new TextField());
		
		
		gridBuilder.addText(properties.empName(), 100, "성명"); //, new TextField());
		gridBuilder.addText(properties.empTel1(), 100, "전화(1)"); //, new TextField());
		gridBuilder.addText(properties.enpTel2(), 100, "전화(2)"); //, new TextField());
		gridBuilder.addText(properties.empEmail(), 100, "이메일"); //, new TextField());
		gridBuilder.addText(properties.zipCode(), 80, "우편번호"); //, new TextField());
		gridBuilder.addText(properties.zipAddress(), 150, "우편주소"); //, new TextField());
		gridBuilder.addText(properties.zipDetail(), 150, "상세주소"); //, new TextField());
		
		gridBuilder.addText(properties.printName(), 150, "출력명"); //, new TextField());

		gridBuilder.addBoolean(properties.useYnBoolean(), 60, "삭제");
		gridBuilder.addText(properties.note(), 400, "비고"); //, new TextField());
		
	
		
//		ColumnModel<ClientModel> cm = gridBuilder.getGrid().getColumnModel();  
//		
//		cm.addHeaderGroup(0, 0, new HeaderGroupConfig("거래처", 1, 13));
//		cm.addHeaderGroup(0, 0, new HeaderGroupConfig("담당자", 1, 4));
		
		return gridBuilder.getGrid(); 
	}

}