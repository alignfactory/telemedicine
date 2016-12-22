package com.tmc.client.app.sys.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface RoleModelProperties extends PropertyAccess<RoleModel> {
	
	ModelKeyProvider<RoleModel> keyId();
	
	ValueProvider<RoleModel, Long> 		roleId();
	ValueProvider<RoleModel, String>	roleName();
	ValueProvider<RoleModel, String> 	seq();
	ValueProvider<RoleModel, Boolean> 	managerYnBoolean();
	ValueProvider<RoleModel, String> 	note();
}



