package com.tmc.client.app.pay.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface PayModelProperties extends PropertyAccess<PaydayModel> {
	
	ModelKeyProvider<PayModel> keyId();
	
	ValueProvider<PayModel, Long>	payId() ;  
	ValueProvider<PayModel, Long> 	paydayId() ;
	ValueProvider<PayModel, Long> 	userId();     
	ValueProvider<PayModel, Double> baseAmt(); // 기본급
	ValueProvider<PayModel, Double>	hireAmt();  // 교원수당
	ValueProvider<PayModel, Double>	benefitAmt(); // 복리후생
	ValueProvider<PayModel, Double>	extraAmt();  // 기타수당
	ValueProvider<PayModel, Double> etcInAmt(); // 기타지급액
	ValueProvider<PayModel, Double> sumPayAmt(); // 지급액합계
	
	ValueProvider<PayModel, Double> incomeTax(); // 소득세 
	ValueProvider<PayModel, Double> ctzTax();  // 주민세
	ValueProvider<PayModel, Double> healthIns() ; // 건강보험
	ValueProvider<PayModel, Double>	privatePns() ; // 사학연금
	ValueProvider<PayModel, Double>	unEmpIns() ;  //  고용보험
	ValueProvider<PayModel, Double>	nationPns(); // 국민연금
	ValueProvider<PayModel, Double> etcOutAmt();  // 기타공제
	ValueProvider<PayModel, Double> sumDeduceAmt();  // 공제액합계
	
	ValueProvider<PayModel, Double> sumRealPayAmt();  // 실지급액
	
	ValueProvider<PayModel, String> closeYn();   	
	ValueProvider<PayModel, String>	note(); 	
	
	@Path("userModel.korName")
	ValueProvider<PayModel, String>	korName();

	@Path("userModel.bankName")
	ValueProvider<PayModel, String>	bankName();

	@Path("userModel.accountNo")
	ValueProvider<PayModel, String>	accountNo();

}
