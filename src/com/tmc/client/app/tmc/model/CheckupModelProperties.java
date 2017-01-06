package com.tmc.client.app.tmc.model;

import java.util.Date;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CheckupModelProperties  extends PropertyAccess<CheckupModel> {

	 ModelKeyProvider<CheckupModel> keyId();

	 ValueProvider<CheckupModel, Long > checkupId();
	 
	 ValueProvider<CheckupModel, String > checkupCode();
	 ValueProvider<CheckupModel, String > checkupName();
	 
	 ValueProvider<CheckupModel, String> checkupResult();
	 ValueProvider<CheckupModel, Date > checkupDate();
	 ValueProvider<CheckupModel, Date > regDate();
	 ValueProvider<CheckupModel, Long > regUserId();
	 
	 ValueProvider<CheckupModel, String > processCode();
	 ValueProvider<CheckupModel, String > processName();
	 
	 ValueProvider<CheckupModel, Long > checkupUserId();
	 ValueProvider<CheckupModel, Long > requestId();

}