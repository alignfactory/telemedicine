package com.tmc.client.app.bbs.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface BoardModelProperties extends PropertyAccess<BoardModel> {
	
	ModelKeyProvider<BoardModel> keyId();
	
	ValueProvider<BoardModel, Long> boardId(); 
	ValueProvider<BoardModel, Long> companyId(); 
	ValueProvider<BoardModel, String> boardTypeCode(); 
	ValueProvider<BoardModel, String> boardTypeName(); 
	ValueProvider<BoardModel, String> title(); 
	ValueProvider<BoardModel, String> content(); 
	ValueProvider<BoardModel, Long> writeUserId(); 
	ValueProvider<BoardModel, Date> writeDate(); 
	ValueProvider<BoardModel, String> note(); 
	
	@Path("userModel.korName")
	ValueProvider<BoardModel, String>	korName();

	@Path("userModel.ctzNo")
	ValueProvider<BoardModel, String>	ctzNo();

	
}
