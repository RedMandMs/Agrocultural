package ru.lenoblgis.introduse.sergey.testing.beans;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ru.lenoblgis.introduse.sergey.config.WebAppConfig;
import ru.lenoblgis.introduse.sergey.services.OwnerService;

public class TestingOwnerBean {

	/**
	 * Тестирование создания и просмотра владельца
	 */
	@Test
	@Ignore
	public void createOwner(){
		ApplicationContext context = new AnnotationConfigApplicationContext(WebAppConfig.class);
        OwnerService ownerService = (OwnerService) context.getBean("organizationService");
        Map<String,String> info = new HashMap<String, String>();
        info.put("name", "LenOblGis6");
        info.put("inn", "4");
        //info.put("address_org", "Торжковская 15");
        ownerService.createOwner(info);
        Map<String,String> infoOwner = ownerService.reviewOwner(26);
        Assert.assertEquals("UNKNOWN", infoOwner.get("address_org"));
	}
	
	/**
	 * Тестирование редактирования владельца
	 */
	@Test
	@Ignore
	public void testEditOwner() {
		ApplicationContext context = new AnnotationConfigApplicationContext(WebAppConfig.class);
        OwnerService ownerService = (OwnerService) context.getBean("organizationService");
        Map<String,String> info = new HashMap<String, String>();        
        info.put("id", "24");
        info.put("name", "NewName");
        info.put("inn", "5");
        info.put("address_org", "Торжковская 16");
        ownerService.editOwner(info);
        Map<String,String> infoOwner = ownerService.reviewOwner(24);
        Assert.assertEquals("Торжковская 16", infoOwner.get("address_org"));
        Assert.assertEquals("5", infoOwner.get("inn"));
        Assert.assertEquals("NewName", infoOwner.get("name"));
	}
	
	/**
	 * Тестирование удаления владельца
	 */
	@Test
	@Ignore
	public void testDeleteOwner() {
		ApplicationContext context = new AnnotationConfigApplicationContext(WebAppConfig.class);
        OwnerService ownerService = (OwnerService) context.getBean("organizationService");
        Map<String,String> info = ownerService.deleteOwner(26);
        Assert.assertEquals("false", info.get("success"));
        Map<String,String> infoOwner = ownerService.reviewOwner(26);
        Assert.assertEquals("false", infoOwner.get("isExist"));
	}
}
