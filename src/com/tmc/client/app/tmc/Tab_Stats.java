package com.tmc.client.app.tmc;

import com.tmc.client.app.tmc.model.StatsRetrieveModel;
import com.tmc.client.app.tmc.model.StatsRetrieveModelProperties;
import com.tmc.client.service.GridRetrieveData;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;

import java.util.Calendar;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Tab_Stats extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	private StatsRetrieveModelProperties properties = GWT.create(StatsRetrieveModelProperties.class);
	private Grid<StatsRetrieveModel> grid = this.buildGrid();
	private DateField fromDateField = new DateField(); 
	private DateField toDateField = new DateField(); 
	
	public Tab_Stats() {
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addDateField(fromDateField, "조회기간", 180, 60, true);
		searchBarBuilder.addDateField(toDateField, "~", 120, 10, true).setLabelSeparator("");;
		
		Date today = new Date(); 
		toDateField.setValue(today);
		
		CalendarUtil.addMonthsToDate(today, -12);
		CalendarUtil.addDaysToDate(today, 1);
		
//		Calendar cal = Calendar.getInstance();
		
//	    cal.setTime(today);
//	    cal.add(Calendar.YEAR, -1); // 1년을 더한다.
	    fromDateField.setValue(today);
		
		searchBarBuilder.addRetrieveButton(); 

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	public Grid<StatsRetrieveModel> buildGrid(){

		GridBuilder<StatsRetrieveModel> gridBuilder = new GridBuilder<StatsRetrieveModel>(properties.keyId());  
		
		gridBuilder.addText(properties.companyName(), 150, "기관명") ;
		gridBuilder.addLong(properties.patientCount(), 100, "등록환자수") ;
		gridBuilder.addLong(properties.p10Count(), 100, "진료요청") ;
		gridBuilder.addLong(properties.p20Count(), 100, "검사요청") ;
		gridBuilder.addLong(properties.p30Count(), 100, "검사진행") ;
		gridBuilder.addLong(properties.p40Count(), 100, "검사완료") ;
		gridBuilder.addLong(properties.p50Count(), 100, "처방등록") ;
		gridBuilder.addLong(properties.p60Count(), 100, "조치완료") ;
		
		return gridBuilder.getGrid(); 
	}

	@Override
	public void retrieve() {
		
//		if(this.companyModel.getCompanyId() == null){
//			new SimpleMessage("기관명 확인", "조회조건의 기관명은 반드시 입력하세요. ");
//			return ; 
//		} 
//		
		GridRetrieveData<StatsRetrieveModel> service = new GridRetrieveData<StatsRetrieveModel>(grid.getStore());
		service.addParam("fromDate", fromDateField.getValue());
		service.addParam("toDate", toDateField.getValue());
		service.retrieve("tmc.StatsRetrieve.selectByRetrieve");
	}
	
	@Override
	public void update(){
	}
	
	@Override
	public void insertRow(){
	}
	
	@Override
	public void deleteRow(){
	}
}