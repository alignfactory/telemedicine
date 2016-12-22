package com.tmc.client.app.sys.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CompanyModelProperties extends PropertyAccess<CompanyModel> {
	
	ModelKeyProvider<CompanyModel> keyId();
	
	ValueProvider<CompanyModel, Long >	companyId() ; 	
	ValueProvider<CompanyModel, String> companyName() ;
	ValueProvider<CompanyModel, String>	bizNo() ;
	ValueProvider<CompanyModel, String>	telNo01() ;
	ValueProvider<CompanyModel, String>	telNo02() ;
	ValueProvider<CompanyModel, String>	faxNo01() ;
	ValueProvider<CompanyModel, String>	zipCode() ;
	ValueProvider<CompanyModel, String>	zipAddress() ;
	ValueProvider<CompanyModel, String>	zipDetail() ;
	ValueProvider<CompanyModel, String>	locationName() ;
	ValueProvider<CompanyModel, Date>	annvDate() ;
	ValueProvider<CompanyModel, String>	ceoPersonId() ;
	ValueProvider<CompanyModel, String>	managerPersonId() ;
	ValueProvider<CompanyModel, String>	note() ;
}
