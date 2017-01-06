package com.tmc.client.app.tmc.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CheckupModelProperties  extends PropertyAccess<CheckupModel> {

	 ModelKeyProvider<CheckupModel> keyId();

	 ValueProvider<CheckupModel, Long > checkupId();
	 ValueProvider<CheckupModel, Long > requestId();	 
	 ValueProvider<CheckupModel, String > checkupCode();
	 ValueProvider<CheckupModel, String > checkupName();
	 ValueProvider<CheckupModel, String > processCode();
	 ValueProvider<CheckupModel, String > processName();
	 ValueProvider<CheckupModel, String> checkupResult();
	 ValueProvider<CheckupModel, Date > checkupDate();
	 
	 ValueProvider<CheckupModel, Long > checkupUserId();

	 @Path("userModel.korName")
	ValueProvider<CheckupModel, String> userKorName();
}