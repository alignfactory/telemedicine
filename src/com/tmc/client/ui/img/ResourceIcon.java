package com.tmc.client.ui.img;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/** 사용예제 : ImageResource.INSTANCE.addButton();  
 * 	이미지를 처리하는 새로운 방법이다. 
 *  이미지를 자바 패키지 안에 속하게 하고 이 안에서 이미지 명으로 이미지를 설정한다. 
 *  외부에서는 logo()라는 함수명으로 이미지를 호출할 수 있으며
 *  이미지를 모아서 처리할 수 있다. - 나름 좋다. 
 *
 */
public interface ResourceIcon extends ClientBundle {

	public static final ResourceIcon INSTANCE = GWT.create(ResourceIcon.class);
	  
	  @Source("files/gearIcon.png")
	  ImageResource gearIcon();

	@Source("files/add.png")
	  ImageResource addButton();

	  @Source("files/db.png")
	  ImageResource dBButton();

	  @Source("files/next.png")
	  ImageResource itemButton();

	  @Source("files/text.png")
	  ImageResource textButton();

	  @Source("files/update.png")
	  ImageResource updateIcon();
	  
	  @Source("files/search_button_green.png")
	  ImageResource searchButton();

	  @Source("files/1462692039_stock_person.png")
	  ImageResource searchPerson();

	  @Source("files/1462692039_stock_person.png")
	  ImageResource searchImage();
}
