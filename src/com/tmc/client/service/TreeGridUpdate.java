package com.tmc.client.service;

import java.util.ArrayList;
import java.util.List;

import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.Change;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.info.Info;

public class TreeGridUpdate<T> implements InterfaceServiceCall{
	
	TreeStore<T> treeStore ; 
	
	public TreeGridUpdate(){
	} 
	
	public void update(TreeStore<T> treeStore, String serviceName){

		this.treeStore = treeStore;

		if (treeStore.getModifiedRecords().size() > 0 ) {
			
			List<AbstractDataModel> updateList = new ArrayList<AbstractDataModel>();
			
			for(Store<T>.Record modified : treeStore.getModifiedRecords()){
				// 형별로 변경된 자료를 찾는다. 
				T updateModel = modified.getModel(); 
				
				 for (Change<T, ?> changes : modified.getChanges()) {
					 // 컬럼별로 변경된 자료를 적용한다. 
					 changes.modify(updateModel);
				 }
				 updateList.add((AbstractDataModel)updateModel); // 형을 상위로 변경해도 되는가? 
			}

			ServiceRequest request = new ServiceRequest(serviceName);
			request.setList(updateList);
			ServiceCall service = new ServiceCall();
			service.execute(request, this);
		} 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}

		for (AbstractDataModel model: result.getResult()) {
			
			if(treeStore.hasRecord((T) model)){
				treeStore.update((T) model); 
			}
			else {
				// 신규등록건은 인식못한다. 다시 Add 해주어야만 한다.  
				treeStore.add((T) model);
			}; 
		}
	}
}
