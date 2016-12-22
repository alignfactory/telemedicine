package com.tmc.client.main;

import com.tmc.client.app.bbs.Page_Board;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class Tab_Main extends BorderLayoutContainer {
	
	public Tab_Main() {
		
		
		
		BorderLayoutData westLayoutData = new BorderLayoutData(0.4);
		//westLayoutData.setMargins(new Margins(0,1,0,0));
		//westLayoutData.setSplit(true);
		//westLayoutData.setMaxSize(1000);
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(new Page_Board(), new VerticalLayoutData(1, 0.6, new Margins(10, 10, 10, 10)));
		ContentPanel westPanel = new ContentPanel();
		westPanel.setHeaderVisible(false);
		westPanel.add(vlc);
		
		this.setWestWidget(westPanel, westLayoutData);
		
		ContentPanel centerPanel = new ContentPanel(); 
		centerPanel.setHeaderVisible(false);
		
		this.setCenterWidget(centerPanel, new BorderLayoutData(0.4));


		ContentPanel eastPanel = new ContentPanel(); 
		eastPanel.setHeaderVisible(false);

		
		BorderLayoutData eastLayoutData = new BorderLayoutData(0.2);
//		eastLayoutData.setMargins(new Margins(0,0,0,1));
//		eastLayoutData.setSplit(true);
//		eastLayoutData.setMaxSize(1000);
		this.setEastWidget(eastPanel, eastLayoutData);
	}
	
	
	

}