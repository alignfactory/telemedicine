package com.tmc.client.app.sys;

import java.util.List;

import com.tmc.client.app.sys.model.MenuModel;
import com.tmc.client.app.sys.model.MenuModelProperties;
import com.tmc.client.main.Tree_MenuRetrieve;
import com.tmc.client.service.TreeGridDeleteRow;
import com.tmc.client.service.TreeGridInsertRow;
import com.tmc.client.service.TreeGridUpdate;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.widget.core.client.button.ButtonBar;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class Page_TreeMenu extends VerticalLayoutContainer {
	
	private TreeGrid<MenuModel> treeGrid = this.buildTreeGrid();
	private Long roleId;
	
	public TreeGrid<MenuModel> buildTreeGrid(){
		
		MenuModelProperties properties = GWT.create(MenuModelProperties.class);
		final GridBuilder<MenuModel> gridBuilder = new GridBuilder<MenuModel>(properties.keyId());  
		gridBuilder.setChecked(SelectionMode.SINGLE);
		
		gridBuilder.addText(properties.menuName(), 300, "메뉴명", new TextField());
		gridBuilder.addText(properties.seq(), 50, "순서", new TextField()) ;
		gridBuilder.addText(properties.className(), 200, "클래스명", new TextField()) ;
		
		gridBuilder.addBoolean(properties.useYnFlag(), 50, "숨김") ;
		gridBuilder.addText(properties.note(), 300, "상세설명", new TextField());
	
		return gridBuilder.getTreeGrid();  
	}

	public Page_TreeMenu(){

		ButtonBar buttonBar = new ButtonBar();

		TextButton createRoot = new TextButton("루트등록"); 
		createRoot.setWidth(70);
		createRoot.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				addRootMenu(); 
			}
		}); 
		buttonBar.add(createRoot);

		TextButton addSubMenu = new TextButton("하위등록");
		addSubMenu.setWidth(70);
		addSubMenu.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				addSubMenu(); 
			}
		}); 
		buttonBar.add(addSubMenu);

		TextButton updateButton = new TextButton("저장");
		updateButton.setWidth(60);
		updateButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				update();  
			}
		}); 
		buttonBar.add(updateButton);

		TextButton deleteButton = new TextButton("삭제");  
		deleteButton.setWidth(60);
		deleteButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				deleteRow();  
			}
		}); 
		buttonBar.add(deleteButton);
		
//		TextButton refreshButton = new TextButton("조회");
//		
//		refreshButton.addSelectHandler(new SelectHandler(){
//			@Override
//			public void onSelect(SelectEvent event) {
//				 refresh(); 
//			}
//		}); 
//		buttonBar.add(refreshButton);
		
//		TextButton expandAll = new TextButton("펼치기"); 
//		expandAll.setWidth(60);
//		expandAll.addSelectHandler(new SelectHandler(){
//			@Override
//			public void onSelect(SelectEvent event) {
//				 treeGrid.expandAll();
//			}
//		}); 
//		buttonBar.add(expandAll);
//		
//		TextButton collapseAll = new TextButton("감추기");
//		collapseAll.setWidth(60);
//		collapseAll.addSelectHandler(new SelectHandler(){
//			@Override
//			public void onSelect(SelectEvent event) {
//				 treeGrid.collapseAll();
//			}
//		}); 
//		buttonBar.add(collapseAll);
		
		this.add(buttonBar, new VerticalLayoutData(1, 40));
		this.add(this.treeGrid, new VerticalLayoutData(1, 1));
	}

	public void refresh(){
		if(this.roleId != null){
			retrieve(this.roleId);
		}
	}
	
	public void retrieve(Long roleId){
		this.roleId = roleId;
		Tree_MenuRetrieve retrieve = new Tree_MenuRetrieve(treeGrid.getTreeStore());
		retrieve.retrieveTree(roleId);
	}

	private void addRootMenu(){
	
		if( this.roleId == null ){
			new SimpleMessage("좌측의 권한이 선택되어야 ROOT 메뉴를 생성할 수 있습니다. ");
			return ; 
		}
		MenuModel roleObject = new MenuModel(); 
		roleObject.setParentId(this.roleId);
		
		TreeGridInsertRow<MenuModel> treeGridInsert = new TreeGridInsertRow<MenuModel>(); 
		treeGridInsert.addRoot(treeGrid.getTreeStore(), roleObject);  
	}

	private void addSubMenu(){
		MenuModel parentModel = treeGrid.getSelectionModel().getSelectedItem(); // 선택된 Menu를 가져온다.
		
		if(parentModel == null){
			new SimpleMessage("선택된 상위 메뉴가 없습니다. "); 
			return ; 
		}
		
		if(parentModel.getClassName() != null){
			// objectID가 있으면 하위메뉴를 등록할 수 없다. 
			new SimpleMessage("오브젝트에는 하위 메뉴를 등록할 수 없습니다."); 
			return ; 
		}

		treeGrid.setExpanded(parentModel, true);
		MenuModel subRoleObject = new MenuModel();
		subRoleObject.setParentId(parentModel.getMenuId());
		
		TreeGridInsertRow<MenuModel> service = new TreeGridInsertRow<MenuModel>(); 
		service.addItem(treeGrid.getTreeStore(), parentModel, subRoleObject);  
	}
	
	private void update(){
		TreeGridUpdate<MenuModel> service = new TreeGridUpdate<MenuModel>(); 
		service.update(treeGrid.getTreeStore(), "sys.Menu.update"); 
	}
	
	private void deleteRow(){
		TreeGridDeleteRow<MenuModel> service = new TreeGridDeleteRow<MenuModel>();
		List<MenuModel> checkedList = treeGrid.getSelectionModel().getSelectedItems() ; 
		service.deleteRow(treeGrid.getTreeStore(), checkedList, "sys.Menu.delete");
	}
}
