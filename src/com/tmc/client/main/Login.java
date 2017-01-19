package com.tmc.client.main;

import com.tmc.client.app.sys.model.UserModel;
import com.tmc.client.service.InterfaceServiceCall;
import com.tmc.client.service.ServiceCall;
import com.tmc.client.service.ServiceRequest;
import com.tmc.client.service.ServiceResult;
import com.tmc.client.ui.SimpleMessage;
import com.tmc.client.ui.img.ResourceIcon;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class Login implements InterfaceServiceCall {
	
	private final Dialog loginDialog = new Dialog();
	private TextField firstName = new TextField();
	private PasswordField password= new PasswordField();
	
	public Login() {
	}
	
	public void open(){
		
		firstName.setText("alignfactory@gmail.com");
		password.setText("1234");
		 
		loginDialog.setBodyBorder(false);
		loginDialog.getHeader().setIcon(ResourceIcon.INSTANCE.dBButton() ); //(+) 이미지를 가져온다. ;
		loginDialog.setResizable(false);
		loginDialog.setHeading("Telemedicine login");
		loginDialog.setHeaderVisible(true);
		loginDialog.setWidth(400);
		loginDialog.setHeight(440);
		
		loginDialog.getButton(PredefinedButton.OK).setWidth(60);
		loginDialog.getButton(PredefinedButton.OK).addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getService(); // 함수로 빼서 호출한다. 
			}
		});
		
		FormPanel panel = new FormPanel();
		panel.setHeight(260);
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		panel.add(vlc, new MarginData(30));
		
		VerticalLayoutData vld = new VerticalLayoutData(); 
		
		vlc.add(new HTML("<center><div><img src='img/Telemedicine.jpg' width='200' height='200'></center></div>"));
		panel.setLayoutData(new Margins(0, 0, 30, 30));
		loginDialog.add(panel); 
		loginDialog.show();

		FieldLabel firstNameLabel = new FieldLabel(firstName, "Login ID ");
		firstNameLabel.setLabelWidth(70);
		firstNameLabel.setWidth(280);
		vlc.add(firstNameLabel, vld);
		
		FieldLabel passwordLabel = new FieldLabel(password, "Password ");  
		passwordLabel.setLabelWidth(70); 
		passwordLabel.setWidth(280); 
		vlc.add(passwordLabel, vld);
  
		Label loginDesc = new HTML("<font size='2'><br>※ Login ID는 등록된 E-Mail ID를 사용 바랍니다. <br>※ 오류 발생시 담당자에게 문의 바랍니다.<br></font>");
		vlc.add(loginDesc);
	}

	public void getService(){
		ServiceRequest request = new ServiceRequest("sys.User.getLoginInfo");
		request.add("loginId", firstName.getText());
		request.add("passwd", password.getText());

		ServiceCall service = new ServiceCall(); 
		service.execute(request, this);
	}
	
	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() > 0){
			UserModel user = (UserModel) result.getResult(0); 
			LoginUser.setLoginUser(user); 
			
//			if(LoginUser.isAdmin()) {
//				// 관리자이다. 로그인할 회사를 선택한다. 
//				Select_Company loginCompany = new Select_Company();
//				loginCompany.show();
//			}
//			else {
				// 일반 사용자이다. 회사 선택없이 로드인한다. 
				new MainWindow();  
//			}
			
			loginDialog.hide();
		}
		else {
			new SimpleMessage("로그인 실패", result.getMessage()); 
		}
	}
}
