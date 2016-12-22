package com.tmc.client.app.psc;

import java.util.Map;

import com.tmc.client.app.psc.model.StudentModel;
import com.tmc.client.ui.InterfaceTabPage;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public class TabPage_Register extends VerticalLayoutContainer implements InterfaceTabPage {

	private Page_Register gridRegister = new Page_Register(); 
	private Page_StudentClass gridStudentClass = new Page_StudentClass();
	private StudentModel studentModel;
	
	public TabPage_Register(){
		this.add(gridRegister, new VerticalLayoutData(1, 230, new Margins(10, 10, 10, 10))); 
		this.add(gridStudentClass, new VerticalLayoutData(1, 230, new Margins(10, 10, 10, 10)));
	}
	
	@Override
	public void retrieve(Map<String, Object> param) {
		
		if(param == null){
			gridRegister.getGrid().getStore().clear();
			gridStudentClass.getGrid().getStore().clear();
			return ; 
		}
		
		this.studentModel = (StudentModel)param.get("studentModel");
		gridRegister.retrieve(this.studentModel);
		gridStudentClass.retrieve(this.studentModel);
	}
}