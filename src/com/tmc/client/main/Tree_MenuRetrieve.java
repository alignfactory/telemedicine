package com.tmc.client.main;

import java.util.List;

import com.tmc.client.app.sys.model.MenuModel;
import com.tmc.client.service.InterfaceServiceCall;
import com.tmc.client.service.ServiceCall;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class Tree_MenuRetrieve implements InterfaceServiceCall{
	
	TreeStore<MenuModel> treeStore ; 
	
	public Tree_MenuRetrieve(TreeStore<MenuModel> treeStore){
		this.treeStore = treeStore;
	} 

	public void retrieveByUserId(Long userId){
		ServiceRequest request = new ServiceRequest("sys.Menu.selectByUserId");
		request.add("userId", userId);
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}

	public void retrieveTree(Long roleId){
		// 서버에서 전체 트리를 한번에 가져온다.  
		ServiceRequest request = new ServiceRequest("sys.Menu.selectByRoleId");
		request.add("roleId", roleId);
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}
	
	private void addChild(MenuModel parentMenu) {
		if(parentMenu.getChildList() != null){
			List<AbstractDataModel> childList = parentMenu.getChildList(); 
			for(AbstractDataModel child : childList){
				MenuModel childObject = (MenuModel)child;
				treeStore.add(parentMenu, childObject);
				this.addChild(childObject);
			}
		}
	}
	
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
		}
		else { 
			treeStore.clear(); // 깨끗이 비운다. 
			
			for (AbstractDataModel model: result.getResult()) {
				// 서버에서 전체 트리를 한번에 가져온 후 트리를 구성한다.  
				MenuModel roleObject = (MenuModel)model;   
				treeStore.add(roleObject);
				this.addChild(roleObject); // child menu & object setting  
			}
		} 
	}
}
