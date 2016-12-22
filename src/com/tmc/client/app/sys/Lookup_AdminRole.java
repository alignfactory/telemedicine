package com.tmc.client.app.sys;

import java.util.List;

import com.tmc.client.app.sys.model.RoleModel;
import com.tmc.client.app.sys.model.RoleModelProperties;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.builder.AbstractLookupWindow;
import com.tmc.client.ui.builder.GridBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class Lookup_AdminRole extends AbstractLookupWindow  {

	private RoleModelProperties properties = GWT.create(RoleModelProperties.class);
	private Grid<RoleModel> grid = this.buildGrid(); 
	private InterfaceLookupResult lookupParent;
	private Long userId = null; 
	
	public Lookup_AdminRole(InterfaceLookupResult lookupParent, Long userId){
		
		this.lookupParent = lookupParent; 
		this.userId = userId;
		this.setInit("권한 선택", 600, 350); 

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(grid, new VerticalLayoutData(1, 1));
		this.add(vlc);
		
		this.retrieve();
	}
	
	private Grid<RoleModel> buildGrid(){
		GridBuilder<RoleModel> gridBuilder = new GridBuilder<RoleModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.MULTI);
		gridBuilder.addText(properties.roleName(), 150, "ROLE명") ;
		gridBuilder.addText(properties.note(), 400, "상세설명");
		return gridBuilder.getGrid(); 
	}
		
	@Override
	public void retrieve() {
		if(this.userId != null){
			GridRetrieveData<RoleModel> service = new GridRetrieveData<RoleModel>(grid.getStore());
			service.addParam("userId", userId);
			service.retrieve("sys.Role.selectByNotAssigned");
		} 
	}

	@Override
	public void confirm() {
		List<RoleModel> roleList = this.grid.getSelectionModel().getSelectedItems(); 
		if(roleList.size() < 1 ){
			Info.display("권한확인", "선택된 권한이 없습니다.");
			return ; 
		}
		else { 
			lookupParent.setLookupResult(roleList);
		} 
	}

	@Override
	public void cancel() {
	}
}
