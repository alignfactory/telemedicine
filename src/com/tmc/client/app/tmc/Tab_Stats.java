package com.tmc.client.app.tmc;

import com.tmc.client.service.GridRetrieveJson;
import com.tmc.client.service.InterfaceCallback;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.builder.GridBuilder;
import com.tmc.client.ui.builder.InterfaceGridOperate;
import com.tmc.client.ui.builder.SearchBarBuilder;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

public class Tab_Stats extends VerticalLayoutContainer implements InterfaceGridOperate {

	private GridProperties properties = GWT.create(GridProperties.class);
	private Grid<JsonInterface> grid = this.buildGrid();
	private DateField fromDateField = new DateField(); 
	private DateField toDateField = new DateField(); 
	
	MyFactory factory = GWT.create(MyFactory.class);
	
	interface MyFactory extends AutoBeanFactory {
		  AutoBean<JsonInterface> jsonInterface();
	}
	
//	public interface JsonList extends List<JsonInterface>{
//		//List<JsonInterface> getList() ; 
//	}
	
	public interface JsonInterface {
//		Long getCompanyId();
		
		String getCompanyName();
		//void setCompanyName(String companyName); 
		
//		Long getPatientCount();
//		Long getP10Count();
//		Long getP20Count();
//		Long getP30Count();
//		Long getP40Count();
//		Long getP50Count();
//		Long getP60Count();
	}
	
	public interface GridProperties  extends PropertyAccess<JsonInterface> {
		ModelKeyProvider<JsonInterface> companyId();
		ValueProvider<JsonInterface, String> companyName();
		ValueProvider<JsonInterface, Long > patientCount();
		ValueProvider<JsonInterface, Long > p10Count();	 
		ValueProvider<JsonInterface, Long > p20Count();	 
		ValueProvider<JsonInterface, Long > p30Count();	 
		ValueProvider<JsonInterface, Long > p40Count();	 
		ValueProvider<JsonInterface, Long > p50Count();	 
		ValueProvider<JsonInterface, Long > p60Count();	 
	}

	
	public Tab_Stats() {
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addDateField(fromDateField, "조회기간", 180, 60, true);
		searchBarBuilder.addDateField(toDateField, "~", 120, 10, true).setLabelSeparator("");;
		
		Date today = new Date(); 
		toDateField.setValue(today);
		
		CalendarUtil.addMonthsToDate(today, -12);
		CalendarUtil.addDaysToDate(today, 1);
	    fromDateField.setValue(today);
		searchBarBuilder.addRetrieveButton(); 

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 48));
		this.add(grid, new VerticalLayoutData(1, 1));
	}
	
	public Grid<JsonInterface> buildGrid(){
		GridBuilder<JsonInterface> gridBuilder = new GridBuilder<JsonInterface>(properties.companyId());  
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

	JsonInterface deserializeFromJson(String json) {
		AutoBean<JsonInterface> bean = AutoBeanCodex.decode(factory, JsonInterface.class, json);
		return bean.as();
	}
	  
	  
	  
	@Override
	public void retrieve() {
		final GridRetrieveJson<JsonInterface> service = new GridRetrieveJson<JsonInterface>(grid.getStore());
		service.addParam("fromDate", fromDateField.getValue());
		service.addParam("toDate", toDateField.getValue());
		service.addCallback(new InterfaceCallback(){
			@Override
			public void callback() {
				
				try{

					AutoBean<JsonInterface> jsonInterface = factory.jsonInterface(); 
					
					jsonInterface = AutoBeanCodex.decode(factory, JsonInterface.class, service.getJsonResult()); 
					
					Info.display("json", service.getJsonResult());

					Info.display("data", jsonInterface.as().getCompanyName());
				}
				catch(Exception e){
					new SimpleMessage("e", e.getMessage());
				}
				
//				for(JsonInterface data : jsonList.as()){
//					Info.display("companyName", data.getCompanyName());
//				}
				
			}
			
		});
		
		
		service.retrieve("tmc.StatsRetrieve.selectByStatic");
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