package com.tmc.client.app.sys.model;

//import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MenuModelProperties extends PropertyAccess<MenuModel> {
	
	ModelKeyProvider<MenuModel> keyId();	
	ValueProvider<MenuModel, Long> 		menuId();
	ValueProvider<MenuModel, Long> 		parentId();
	ValueProvider<MenuModel, String> 	menuName();
	ValueProvider<MenuModel, String> 	className();
	ValueProvider<MenuModel, Boolean> 	useYnFlag();
	ValueProvider<MenuModel, String> 	seq();
	ValueProvider<MenuModel, String> 	note();
		
//	@Path("objectModel.className")// RoleObjectEditor.java에서도 기술해야 한다. editor는 따로 기술한다.   
//	ValueProvider<RoleObjectModel, String> className();

//	@Path("objectModel.typeCode")
//	ValueProvider<RoleObjectModel, String> typeCode();

}



