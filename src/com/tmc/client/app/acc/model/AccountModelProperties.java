package com.tmc.client.app.acc.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface AccountModelProperties extends PropertyAccess<AccountModel> {
	
	ModelKeyProvider<AccountModel> keyId();
	
	ValueProvider<AccountModel, Long> accountId() ;
	ValueProvider<AccountModel, String> eduOfficeCode() ; 
	ValueProvider<AccountModel, String> eduOfficeName() ;
	ValueProvider<AccountModel, Long> companyId() ;
	ValueProvider<AccountModel, String> gwonCode() ;
	ValueProvider<AccountModel, String> gwonName() ;
	ValueProvider<AccountModel, String> hangCode() ;
	ValueProvider<AccountModel, String> hangName() ;
	ValueProvider<AccountModel, String> gmokCode() ;
	ValueProvider<AccountModel, String> gmokName() ;
	ValueProvider<AccountModel, String> smokCode() ;
	ValueProvider<AccountModel, String> smokName() ;
	ValueProvider<AccountModel, String> printName() ;
	ValueProvider<AccountModel, String> sumYn() ;
	ValueProvider<AccountModel, Boolean> sumYnFlag() ;
	ValueProvider<AccountModel, String> transYn() ;
	ValueProvider<AccountModel, Boolean> transYnFlag() ;
	
	ValueProvider<AccountModel, String> inExpCode() ;
	ValueProvider<AccountModel, String> inExpName() ;
	
	ValueProvider<AccountModel, String> resolutionYn() ;
	ValueProvider<AccountModel, Boolean> resolutionYnFlag() ;
	
	ValueProvider<AccountModel, String> budgetYn() ;
	ValueProvider<AccountModel, Boolean> budgetYnFlag() ;
	
	ValueProvider<AccountModel, String> useYn() ;
	ValueProvider<AccountModel, Boolean> useYnFlag(); 
	
	ValueProvider<AccountModel, String> note() ;
	
	ValueProvider<AccountModel, String> accountType() ;
	
	
}
