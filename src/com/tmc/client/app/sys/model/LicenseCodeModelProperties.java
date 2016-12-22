package com.tmc.client.app.sys.model;

import java.util.Date;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface LicenseCodeModelProperties extends PropertyAccess<LicenseCodeModel> {
	ModelKeyProvider<LicenseCodeModel> keyId();
	ValueProvider<LicenseCodeModel, Long> 		licenseCodeId();
	ValueProvider<LicenseCodeModel, String> 	licenseCode();
	ValueProvider<LicenseCodeModel, String> 	licenseName();
	ValueProvider<LicenseCodeModel, String> 	issueOrgName();
	ValueProvider<LicenseCodeModel, Date> 		applyDate();
	ValueProvider<LicenseCodeModel, Boolean>	closeYnFlag();
	ValueProvider<LicenseCodeModel, String> 	note();
}
