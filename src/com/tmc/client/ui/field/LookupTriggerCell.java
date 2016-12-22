package com.tmc.client.ui.field;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.cell.core.client.form.TriggerFieldCell;

public class LookupTriggerCell extends TriggerFieldCell<String> {
	 
	public LookupTriggerCell() {
		// Cell Icon Setting 
		super(GWT.<LookupFieldAppearance>create(LookupFieldAppearance.class));
	}

}
