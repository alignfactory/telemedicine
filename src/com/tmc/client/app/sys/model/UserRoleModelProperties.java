package com.tmc.client.app.sys.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface UserRoleModelProperties extends PropertyAccess<UserRoleModel>{
	
	ModelKeyProvider<UserRoleModel> keyId();

	ValueProvider<UserRoleModel, Long> userRoleId();
	ValueProvider<UserRoleModel, Long> userId();
	ValueProvider<UserRoleModel, Long> roleId();
	ValueProvider<UserRoleModel, String> seq();
	ValueProvider<UserRoleModel, String> note();
	
	@Path("roleModel.roleName")
	ValueProvider<UserRoleModel, String> roleName();
}
