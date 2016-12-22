package com.tmc.client.ui;

import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class FindIconCell implements Cell<String>{

	
	public FindIconCell(){
	}
	
	
	@Override
	public boolean dependsOnSelection() {
		return false;
	}

	@Override
	public Set<java.lang.String> getConsumedEvents() {
		return null;
	}

	@Override
	public boolean handlesSelection() {
		return false;
	}

	@Override
	public boolean isEditing(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2) {
		return false;
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2,
			NativeEvent arg3, ValueUpdater<String> arg4) {
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context arg0, String arg1, SafeHtmlBuilder arg2) {
		arg2.appendHtmlConstant("<div style='text-align:center; margin:0px; padding:0px'>"); 
		arg2.appendHtmlConstant("<img src='img/1462692428_Search.png' />"); // style='cursor: pointer'/>");
		arg2.appendHtmlConstant("</div>"); // style='cursor: pointer'/>");
	}

	@Override
	public boolean resetFocus(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2) {
		return false;
	}

	@Override
	public void setValue(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2) {
	}
}
