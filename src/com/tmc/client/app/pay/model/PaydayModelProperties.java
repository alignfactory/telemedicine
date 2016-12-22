package com.tmc.client.app.pay.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PaydayModelProperties extends PropertyAccess<PaydayModel> {
	
	ModelKeyProvider<PaydayModel> keyId();
	
	ValueProvider<PaydayModel, Long>	paydayId();
	ValueProvider<PaydayModel, Long>	companyId(); 
	ValueProvider<PaydayModel, Date>	payDate();
	ValueProvider<PaydayModel, String> 	payTypeCode();
	ValueProvider<PaydayModel, String>  payTypeName();
	ValueProvider<PaydayModel, String> 	payName(); 
	ValueProvider<PaydayModel, Date> 	accountDate();
	ValueProvider<PaydayModel, Date> 	onDate();
	ValueProvider<PaydayModel, Date>	offDate();
	ValueProvider<PaydayModel, String> 	note();
	ValueProvider<PaydayModel, String> 	iconButton();
	ValueProvider<PaydayModel, String> 	actionCell();
	ValueProvider<PaydayModel, String> 	closeActionCell();
	
}
