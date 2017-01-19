package com.tmc.client.app.tmc.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface StatsRetrieveModelProperties  extends PropertyAccess<StatsRetrieveModel> {
	 
	ModelKeyProvider<StatsRetrieveModel> keyId();
	ValueProvider<StatsRetrieveModel, String> companyName();
	ValueProvider<StatsRetrieveModel, Long > patientCount();
	ValueProvider<StatsRetrieveModel, Long > p10Count();	 
	ValueProvider<StatsRetrieveModel, Long > p20Count();	 
	ValueProvider<StatsRetrieveModel, Long > p30Count();	 
	ValueProvider<StatsRetrieveModel, Long > p40Count();	 
	ValueProvider<StatsRetrieveModel, Long > p50Count();	 
	ValueProvider<StatsRetrieveModel, Long > p60Count();	 
	
}