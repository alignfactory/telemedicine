package com.tmc.client.app.emp.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface LicenseModelProperties extends PropertyAccess<LicenseModel> {
	
	ModelKeyProvider<LicenseModel> keyId();
	
	ValueProvider<LicenseModel, Long> 		licenseId();
	ValueProvider<LicenseModel, Long> 		userId();

	ValueProvider<LicenseModel, String> 	licenseCode();
	ValueProvider<LicenseModel, Date> 		issueDate();
	ValueProvider<LicenseModel, Date> 		expirationDate();
	ValueProvider<LicenseModel, String> 	note();
	ValueProvider<LicenseModel, String> 	findCell();

	@Path("licenseCodeModel.licenseName")
	ValueProvider<LicenseModel, String>	licenseName();

	@Path("licenseCodeModel.issueOrgName")
	ValueProvider<LicenseModel, String>	issueOrgName();

	@Path("userModel.korName")
	ValueProvider<LicenseModel, String>	korName();

	@Path("userModel.telNo01")
	ValueProvider<LicenseModel, String>	telNo01();
	
}
