package com.tmc.client.app.sys.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CodeModelProperties extends PropertyAccess<CodeModel> {
	
	ModelKeyProvider<CodeModel> keyId();
	
	ValueProvider<CodeModel, Long> 		codeId();
	ValueProvider<CodeModel, Long> 		codeKindId();
	ValueProvider<CodeModel, String> 	code();
	ValueProvider<CodeModel, String> 	name();
	ValueProvider<CodeModel, Date> 		applyDate();
	ValueProvider<CodeModel, Boolean> 	closeYnFlag();
	ValueProvider<CodeModel, String> 	seq();
	ValueProvider<CodeModel, String> 	note();
}
