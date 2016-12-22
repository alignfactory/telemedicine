package com.tmc.client.ui.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tmc.client.ui.img.ResourceIcon;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.sencha.gxt.cell.core.client.NumberCell;
import com.sencha.gxt.cell.core.client.form.CheckBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.event.BeforeStartEditEvent.BeforeStartEditHandler;
import com.sencha.gxt.widget.core.client.form.IsField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupingView;
import com.sencha.gxt.widget.core.client.grid.RowNumberer;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class GridBuilder<T> {

	private boolean menuEnable = false;  // default false
	private RowNumberer<T> rowNum = new RowNumberer<T>();
	private CheckBoxSelectionModel<T> selectionModel ; 
	private ModelKeyProvider<T> keyProvider; 
	private List<ColumnConfig<T, ?>> columnList =  new ArrayList<ColumnConfig<T, ?>>();
	private GroupingView<T> groupingView = new GroupingView<T>();
	private BeforeStartEditHandler<T> beforeStartEditHandler;   
	private List<HeaderGroupMap> headerGroupList = new ArrayList<HeaderGroupMap>(); 
	
	// 컬럼별 Editor를 담기위한 Map 선언  
	private Map<ColumnConfig<T, ?>, IsField<?>> editorMap = new HashMap<ColumnConfig<T, ?>, IsField<?>>();
	
	public GridBuilder(ModelKeyProvider<T> keyProvider){
		this.keyProvider = keyProvider;
		columnList.add(rowNum); // add row no first
	}

	public void setBeforeStartEditHandler(BeforeStartEditHandler<T> handler){
		this.beforeStartEditHandler = handler; 
	}

	public void addHeaderGroupMap(HeaderGroupMap headerGroupMap){
		this.headerGroupList.add(headerGroupMap); 
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Grid<T> getGrid(){

		ListStore<T> listStore = new ListStore<T>(this.keyProvider);
		ColumnModel<T> columnModel = new ColumnModel<T>(columnList);
		
		if(!menuEnable){
			for(ColumnConfig<T, ?> columnConfig : columnModel.getColumns()){
				// 컬럼헤더의 메뉴를 사용하지 않는다. 
				columnConfig.setMenuDisabled(true);
			}
		}
		
		// header grouping setting 
		for(HeaderGroupMap config: headerGroupList){
			columnModel.addHeaderGroup(config.getRow(), config.getCol(), config.getConfig());
		}
		
		Grid<T> grid = new Grid<T>(listStore, columnModel, groupingView);
		this.rowNum.initPlugin(grid);
		this.rowNum.setWidth(40);
		
		// grid display setting 
		grid.clearSizeCache();
		grid.setColumnReordering(true);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true); 

		if(this.selectionModel != null ){
			// check box setting 여부 
			grid.setSelectionModel(this.selectionModel);
		}

		/*
		 * 넘어오는 필드가 어떤 형태인지 모르므로 일단 무시하고 에디터로 넣는다. - @SuppressWarnings
		 */
		GridEditing<T> editing = new GridInlineEditing<T>(grid);
		
		// handler 등록한다. 맞는지 모르겠다. 이후 이거 확인하고 찾아가서 코딩하기 어렵다. 
		if(beforeStartEditHandler != null){
			editing.addBeforeStartEditHandler(beforeStartEditHandler); 
		}
		
		for(ColumnConfig columnConfig : editorMap.keySet() ){
			editing.addEditor(columnConfig, editorMap.get(columnConfig));
		}
		return grid; 
	}
	
	public void setMenuEnable(boolean enable){
		this.menuEnable = enable; 
	}
	
	public void setChecked(SelectionMode selectionMode){
		IdentityValueProvider<T> identity = new IdentityValueProvider<T>();
		selectionModel = new CheckBoxSelectionModel<T>(identity);
		selectionModel.setSelectionMode(selectionMode); // parameter
		columnList.add(selectionModel.getColumn()); 
	}
	
	public ColumnConfig<T, String> addText(ValueProvider<T, String> valueProvider, int width, String header, IsField<?> field){
		ColumnConfig<T, String> column = new ColumnConfig<T, String>(valueProvider, width, header) ;
		columnList.add(column);

		if(field != null){
			editorMap.put(column, field);
		}
		return column; 
	}
	public ColumnConfig<T, String> addText(ValueProvider<T, String> valueProvider, int width, String header){
		return this.addText(valueProvider, width, header, null); 
	}

	public ColumnConfig<T, Double> addDouble(ValueProvider<T, Double> valueProvider, int width, String header, IsField<?> field){
		ColumnConfig<T, Double> column = new ColumnConfig<T, Double>(valueProvider, width, header) ;
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT); // 숫자는 우측정렬 
		columnList.add(column);
		column.setCell(new NumberCell<Double>()); // number format setting
		
		if(field != null){
			editorMap.put(column, field);
		}
		return column; 
	}
	public ColumnConfig<T, Long> addLong(ValueProvider<T, Long> valueProvider, int width, String header){
		return this.addLong(valueProvider, width, header, null); 
	}

	public ColumnConfig<T, Long> addLong(ValueProvider<T, Long> valueProvider, int width, String header, IsField<?> field){
		ColumnConfig<T, Long> column = new ColumnConfig<T, Long>(valueProvider, width, header) ;
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT); // 숫자는 우측정렬 
		columnList.add(column);

		if(field != null){
			editorMap.put(column, field);
			column.setCell(new NumberCell<Long>()); // number format setting 
		}
		return column; 
	}
	public ColumnConfig<T, Double> addDouble(ValueProvider<T, Double> valueProvider, int width, String header){
		return this.addDouble(valueProvider, width, header, null); 
	}

	
	public ColumnConfig<T, Date> addDate(ValueProvider<T, Date> valueProvider, int width, String header, IsField<?> field){
		ColumnConfig<T, Date> column = new ColumnConfig<T, Date>(valueProvider, width, header) ;
		column.setCell(new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd")));
		columnList.add(column);
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		if(field != null){
			editorMap.put(column, field);
		}
		return column; 
	}
	public ColumnConfig<T, Date> addDate(ValueProvider<T, Date> valueProvider, int width, String header){
		return this.addDate(valueProvider, width, header, null); 
	}

	public ColumnConfig<T, Date> addDateTime(ValueProvider<T, Date> valueProvider, int width, String header, IsField<?> field){
		ColumnConfig<T, Date> column = new ColumnConfig<T, Date>(valueProvider, width, header) ;
		column.setCell(new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss")));
		columnList.add(column);
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		if(field != null){
			editorMap.put(column, field);
		}
		return column; 
	}
	
	public ColumnConfig<T, Boolean> addBoolean(ValueProvider<T, Boolean> valueProvider, int width, String header){
		ColumnConfig<T, Boolean> column = new ColumnConfig<T, Boolean>(valueProvider, width, header) ;
		
		CheckBoxCell cell = new CheckBoxCell();
		column.setCell(cell);
		columnList.add(column);
		return column ; 
	}
	
	public ColumnConfig<T, String> addCell(ValueProvider<T, String> valueProvider, int width, String header, Cell<String> cell){
		// String 컬럼을 기준으로 생성한다. 
		ColumnConfig<T, String> column = new ColumnConfig<T, String>(valueProvider, width, header) ;
		columnList.add(column);
		
		column.setCell(cell);
		column.setCellPadding(false);
		column.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		return column; 
	}

	public void addGrouping(ColumnConfig<T, ?> groupingColumn){
	      groupingView.setShowGroupedColumn(false);
	      groupingView.groupBy(groupingColumn);
	      groupingView.setAutoExpandColumn(groupingColumn);
	      this.setMenuEnable(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TreeGrid<T> getTreeGrid(){
		
		TreeStore<T> treeStore = new TreeStore<T>(this.keyProvider); 
		ColumnModel<T> columnModel = new ColumnModel<T>(columnList);
		if(!menuEnable){
			for(ColumnConfig<T, ?> columnConfig : columnModel.getColumns()){
				// 컬럼헤더의 메뉴를 사용하지 않는다. 
				columnConfig.setMenuDisabled(true);
			}
		}
		
		// 첫번째 칼럼으로 트리를 구성한다. 
		TreeGrid<T> treeGrid = new TreeGrid<T>(treeStore, columnModel, columnModel.getColumns().get(2));
		treeGrid.getStyle().setLeafIcon(ResourceIcon.INSTANCE.textButton());
		
		if(this.selectionModel != null ){
			// check box setting 여부 
			treeGrid.setSelectionModel(this.selectionModel);
		}
		GridEditing<T> editing = new GridInlineEditing<T>(treeGrid);
		for(ColumnConfig columnConfig : editorMap.keySet() ){
			editing.addEditor(columnConfig, editorMap.get(columnConfig));
		}
		return treeGrid; 
	}
}
