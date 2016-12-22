package com.tmc.client.app.sys.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface UserCompanyModelProperties extends PropertyAccess<UserRoleModel>{

	ModelKeyProvider<UserCompanyModel> keyId();
	
	ValueProvider<UserCompanyModel, Long> userCompanyId();
	ValueProvider<UserCompanyModel, Long> userId();
	ValueProvider<UserCompanyModel, Long> companyId();
	ValueProvider<UserCompanyModel, String> note();
		
	@Path("companyModel.companyName")
	ValueProvider<UserCompanyModel,String> companyName();

	@Path("companyModel.bizNo")
	ValueProvider<UserCompanyModel,String> bizNo();
	
	@Path("companyModel.telNo01")
	ValueProvider<UserCompanyModel,String> telNo01();
	

}
