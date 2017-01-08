package com.tmc.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmc.client.ui.AbstractDataModel;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.Change;
import com.sencha.gxt.widget.core.client.info.Info;


/*
 * 변경된 자료를 찾아 서버 모듈로 보낸다. 
 * 저장 후 처리결과를 리턴한다. 
 */
public class GridUpdateData<T> implements InterfaceServiceCall{
	
	private ListStore<T> listStore ;
	private Map<String, Object> param = new HashMap<String, Object>();
	private InterfaceCallback callBack; 
	
	public GridUpdateData(){
	} 
	
	public void update(ListStore<T> listStore, String serviceName){

		this.listStore = listStore;

		if (listStore.getModifiedRecords().size() > 0 ) {
			List<AbstractDataModel> updateList = new ArrayList<AbstractDataModel>();
			for(Store<T>.Record modified : listStore.getModifiedRecords()){
				// 형별로 변경된 자료를 찾는다. 
				T updateModel = modified.getModel(); 
				for (Change<T, ?> changes : modified.getChanges()) {
					// 컬럼별로 변경된 자료를 적용한다. 
					changes.modify(updateModel);
				}
				updateList.add((AbstractDataModel)updateModel); // 형을 상위로 변경해도 되는가? 
			}
			ServiceRequest request = new ServiceRequest(serviceName);
			request.setParam(this.param);

			request.setList(updateList);
			ServiceCall service = new ServiceCall();
			service.execute(request, this);
		} 
	}
	
	public void addParam(String key, Object data){
		param.put(key, data); 
	}
	
	public void update(ListStore<T> listStore, T updateModel, String serviceName){
		// 한건씩 저장한다. - form update (tabpage_user, form_student 등) 
		this.listStore = listStore;
			
		List<AbstractDataModel> updateList = new ArrayList<AbstractDataModel>();
		updateList.add((AbstractDataModel)updateModel);  
		
		ServiceRequest request = new ServiceRequest(serviceName);
		
		request.setParam(this.param);
		request.setList(updateList);
		
		ServiceCall service = new ServiceCall();
		
		service.execute(request, this);
	}
	
	public void addCallback(InterfaceCallback callBack){
		this.setCallBack(callBack);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("update error", result.getMessage());
			return ; 
		}
		for (AbstractDataModel model: result.getResult()) {
			listStore.update((T) model); 
		}
	}

	public InterfaceCallback getCallBack() {
		return callBack;
	}

	public void setCallBack(InterfaceCallback callBack) {
		this.callBack = callBack;
	}
	
}
