package com.tmc.client.ui.field;


import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.ValueProvider;
 
/**
 * Displays a hyperlink anchor in a cell and provides an interface
 * to handle onClick.
 */
public class AnchorLinkCell<T>  implements Cell<String> {
 
    public interface AnchorListener<T> {
        void onClick(String value);
    }
 
//    private final ValueProvider<T, String> vp;
    private final AnchorListener<T> listener;
 
    public AnchorLinkCell(ValueProvider<T, String> vp, AnchorListener<T> listener) {
        // consume click event
//        super("click");
        this.listener = listener;
  //      this.vp = vp;
    }
 

	@Override
	public boolean dependsOnSelection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getConsumedEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handlesSelection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEditing(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
		// super.onBrowserEvent(context, parent, value, event, valueUpdater);
      if (parent.getFirstChildElement().isOrHasChild(Element.as(event.getEventTarget()))) {
          listener.onClick(value);
      }
		
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context arg0, String obj, SafeHtmlBuilder sb) {
      sb.appendHtmlConstant("<div style='color:red; text-decoration: underline; cursor: pointer'><a>");
      sb.append(SafeHtmlUtils.fromString(obj)); //this.vp.getValue(obj)));
      sb.appendHtmlConstant("</a></div>");    
	}

	@Override
	public boolean resetFocus(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValue(com.google.gwt.cell.client.Cell.Context arg0, Element arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}



//public void render(com.google.gwt.cell.client.Cell.Context context, T obj, SafeHtmlBuilder sb) {
//  sb.appendHtmlConstant("<div style='color: -webkit-link; text-decoration: underline; cursor: pointer'><a>");
//  sb.append(SafeHtmlUtils.fromString(this.vp.getValue(obj)));
//  sb.appendHtmlConstant("</a></div>");    }

//@Override
//public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context, Element parent, T value, NativeEvent event, ValueUpdater<T> valueUpdater) {
//  super.onBrowserEvent(context, parent, value, event, valueUpdater);
//  if (parent.getFirstChildElement().isOrHasChild(Element.as(event.getEventTarget()))) {
//      listener.onClick(value);
//  }
//}
	
}