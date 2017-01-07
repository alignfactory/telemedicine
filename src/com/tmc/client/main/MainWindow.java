package com.tmc.client.main;

import com.tmc.client.app.sys.model.MenuModel;
import com.tmc.client.ui.img.ResourceIcon;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.tree.Tree;

public class MainWindow extends ContentPanel {
	
	private PlainTabPanel tabPanel = new PlainTabPanel();	
 
	public MainWindow() {
		
		this.setHeaderVisible(true);	
		this.getHeader().setIcon(ResourceIcon.INSTANCE.gearIcon());
		
		String header = "반갑습니다. " + LoginUser.getLoginUser().getCompanyModel().getCompanyName()
				+ " " + LoginUser.getLoginUser().getKorName()+ "님!" ; ; 
				
				// .getLoginUser().getLoginId() + "님!" ; 
		SafeHtmlBuilder htmlBuilder=new SafeHtmlBuilder();
		htmlBuilder.appendHtmlConstant("<div style='color:lightgrey;'>&nbsp;" + header + "</div>"); 
		this.setHeading(htmlBuilder.toSafeHtml());
		
		ToolButton question = new ToolButton(ToolButton.QUESTION); 
		question.setPixelSize(25, 20);
		
		this.getHeader().addTool(question);
		
		ToolButton close = new ToolButton(ToolButton.CLOSE); 
		close.setPixelSize(30,  20);
		this.getHeader().addTool(close);
		
		BorderLayoutContainer mainContainer = new BorderLayoutContainer();
		mainContainer.setBorders(false);
		
		//
		AccordionLayoutAppearance accordianLayout = GWT.create(AccordionLayoutAppearance.class); 
		ContentPanel treeAccordianPanel = new ContentPanel(accordianLayout); 
		treeAccordianPanel.setHeading("Navigation");			
		treeAccordianPanel.add(this.getMenuTree()); 
		
		treeAccordianPanel.setBodyStyle("style='backgroundColor:white; color:red;'");
		
		ContentPanel newsAccordianPanel = new ContentPanel(accordianLayout); 
		newsAccordianPanel.setHeading("News & Board");
		newsAccordianPanel.add(new Label("공지사항 및 게시판입니다.")); 
		ContentPanel tempAccordianPanel = new ContentPanel(accordianLayout); 
		tempAccordianPanel.setHeading("공사 중...");
		tempAccordianPanel.add(new Label("준비중입니다."));

		AccordionLayoutContainer accordianContainer = new AccordionLayoutContainer();
		
		accordianContainer.setExpandMode(ExpandMode.SINGLE_FILL);
		accordianContainer.add(treeAccordianPanel);
		accordianContainer.setActiveWidget(treeAccordianPanel);
		accordianContainer.add(newsAccordianPanel);
		accordianContainer.add(tempAccordianPanel);
		
		VerticalLayoutContainer avlc = new VerticalLayoutContainer(); 
		avlc.add(accordianContainer, new VerticalLayoutData(1, 1, new Margins(3, 0, 0, 0)));
		
		ContentPanel treePanel = new ContentPanel();
		treePanel.setHeaderVisible(false);
		treePanel.add(avlc);
		BorderLayoutData treeLayout = new BorderLayoutData(250);

		treeLayout.setSplit(true);
		treeLayout.setMargins(new Margins(0, 2, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		mainContainer.setWestWidget(treePanel, treeLayout);

		tabPanel.setTabScroll(true);
		tabPanel.add(new Tab_Main(), "My Page"); // my page setting
		
		ContentPanel tabContent = new ContentPanel(); 
		tabContent.setHeaderVisible(false);
		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(tabPanel, new VerticalLayoutData(1, 1, new Margins(2, 0, 0, 2)));
		tabContent.add(vlc);
		
		mainContainer.setCenterWidget(tabContent);
		this.add(mainContainer);
		
		Viewport viewport = new Viewport();
		viewport.add(this);
		
		RootPanel.get().add(viewport);
	
	}
	
	public Tree<MenuModel, String> getMenuTree(){
		
		TreeStore<MenuModel> menuTreeStore = new TreeStore<MenuModel>(new ModelKeyProvider<MenuModel> () {
			@Override
			public String getKey(MenuModel roleObject) {
				return roleObject.getMenuId() + "";
			}
		});
		
		ValueProvider<MenuModel, String> treeMenuValueProvider 
		=  new ValueProvider<MenuModel, String>() {
				@Override
				public String getValue(MenuModel object) {
					return object.getMenuName();
				}
				@Override
				public void setValue(MenuModel object, String value) {
				}
				@Override
				public String getPath() {
					return "path";
				}
		} ; 

		Tree<MenuModel, String> menuTree 
		= new Tree<MenuModel, String>(menuTreeStore, treeMenuValueProvider) {
			@Override
			protected void onClick(Event event) { // onDoubleClick event도 있으나...
				TreeNode<MenuModel> node = findNode(event.getEventTarget().<Element> cast());

				if(node == null) {
					return; // 선택된 메뉴가 없다. 
				}
		        
				if(node.getModel().getMenuId() != null && node.getModel().getChildList().size() == 0 ){
					String className = node.getModel().getClassName();  
					String pageName = node.getModel().getMenuName();
					
					OpenTab openTab = new OpenTab();
					openTab.openTab(tabPanel, className, pageName);
				}
				
		        super.onDoubleClick(event); // tree node를 one-click으로 열기위해 사용한다. 
			}
		};

		retrieveMenuTree(menuTreeStore, LoginUser.getLoginUser().getUserId());
		menuTree.getStyle().setLeafIcon(ResourceIcon.INSTANCE.textButton());
		return menuTree; 
	}
	
	public void retrieveMenuTree(TreeStore<MenuModel> menuTreeStore, Long userId){
		Tree_MenuRetrieve retrieve = new Tree_MenuRetrieve(menuTreeStore);
		retrieve.retrieveByUserId(userId);
	}
}