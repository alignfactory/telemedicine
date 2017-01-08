package com.tmc.client.app.tmc;

import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.tmc.client.app.sys.Lookup_Company;
import com.tmc.client.app.sys.model.CompanyModel;
import com.tmc.client.main.LoginUser;
import com.tmc.client.ui.InterfaceLookupResult;
import com.tmc.client.ui.field.LookupTriggerField;

public class LookupCompanyField extends LookupTriggerField {
	
	private CompanyModel companyModel = new CompanyModel();
	
	public LookupCompanyField() {
		
		this.setEditable(false);
		
		this.addTriggerClickHandler(new TriggerClickHandler(){
   	 		@Override
			public void onTriggerClick(TriggerClickEvent event) {
   	 			
   	 			Lookup_Company lookupCompany = new Lookup_Company();
   	 			lookupCompany.setCallback(new InterfaceLookupResult(){
	   				@Override
	   				public void setLookupResult(Object result) {
	   					companyModel = (CompanyModel)result;
	   					setText(companyModel.getCompanyName());
	   				}
	   			});	
   	 			lookupCompany.show();
			}
   	 	}); 

		this.setCompanyModel(LoginUser.getLoginUser().getCompanyModel());
		this.setText(companyModel.getCompanyName());

	}

	public CompanyModel getCompanyModel(){
		return companyModel; 
	}
	
	public void setCompanyModel(CompanyModel companyModel){
		this.companyModel = companyModel; 
	}
	
}
