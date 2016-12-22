package com.tmc.client.app.acc.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface SeasonModelProperties extends PropertyAccess<SeasonModel> {
	
	ModelKeyProvider<SeasonModel> keyId();
	
	ValueProvider<SeasonModel, Long> seasonId(); 
	ValueProvider<SeasonModel, Long> companyId(); 
	ValueProvider<SeasonModel, String> eduOfficeCode(); 
	ValueProvider<SeasonModel, String> eduOfficeName(); 
	ValueProvider<SeasonModel, String> seasonName(); 
	ValueProvider<SeasonModel, Date> startDate(); 
	ValueProvider<SeasonModel, Date> closeDate(); 
	ValueProvider<SeasonModel, String> seq(); 
	ValueProvider<SeasonModel, String> note(); 
	
}
