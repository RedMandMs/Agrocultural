package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.services.OwnerService;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	
	@Autowired
	private OwnerService ownerService;
	
	/**
	 * Показать Форму регистрации
	 * @param model - модель
	 * @return - view
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(ModelMap model) {
		
		UserOrganization userOrganization = new UserOrganization();
		
		model.addAttribute(userOrganization);
		
		return "registration/registrationForm";
	}
	
	/**
	 * Зарегестрировать
	 * @param userOrganization - Информация о пользователе и его организации
	 * @param model - модель
	 * @return - view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String registration(UserOrganization userOrganization, ModelMap model){
		
		OrganizationInfo organizationInfo = ownerService.registration(userOrganization);
		
		return "redirect:organization/company/"+organizationInfo.getId();
	}
	
}
