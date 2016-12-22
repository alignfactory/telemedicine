package com.tmc.client.app.acc.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface ClientModelProperties extends PropertyAccess<ClientModel> {
	
	ModelKeyProvider<ClientModel> keyId();
	
	ValueProvider<ClientModel,Long> clientId();
	ValueProvider<ClientModel,Long> companyId();
	ValueProvider<ClientModel,String> bizNo();
	ValueProvider<ClientModel, String> clientName();
	ValueProvider<ClientModel, String> printName();
	ValueProvider<ClientModel, String> ceoName();
	ValueProvider<ClientModel, String> ctzNo();
	ValueProvider<ClientModel, String> tel1();
	ValueProvider<ClientModel, String> tel2();
	ValueProvider<ClientModel, String> fax1();
	ValueProvider<ClientModel, String> accountNo();
	ValueProvider<ClientModel, String> bankCode();
	ValueProvider<ClientModel, String> bankName();
	ValueProvider<ClientModel, String> accountOwner();
	ValueProvider<ClientModel, String> zipCode();
	ValueProvider<ClientModel, String> zipAddress();
	ValueProvider<ClientModel, String> zipDetail();
	ValueProvider<ClientModel, String> empName();
	ValueProvider<ClientModel, String> empTel1();
	ValueProvider<ClientModel, String> enpTel2();
	ValueProvider<ClientModel, String> empEmail();
	ValueProvider<ClientModel, Boolean> useYnBoolean();
	ValueProvider<ClientModel, String> note();
	ValueProvider<ClientModel, String> industry();
	ValueProvider<ClientModel, String> bizType();
}
