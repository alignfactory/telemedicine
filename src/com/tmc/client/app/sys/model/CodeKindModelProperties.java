package com.tmc.client.app.sys.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CodeKindModelProperties extends PropertyAccess<CodeKindModel> {
	
	ModelKeyProvider<CodeKindModel> keyId();
	
	ValueProvider<CodeKindModel, Long> 		codeKindId();
	ValueProvider<CodeKindModel, String> 	kindCode();
	ValueProvider<CodeKindModel, String> 	kindName();
	ValueProvider<CodeKindModel, Boolean> 	sysYnFlag();
	ValueProvider<CodeKindModel, String> 	note();
}
