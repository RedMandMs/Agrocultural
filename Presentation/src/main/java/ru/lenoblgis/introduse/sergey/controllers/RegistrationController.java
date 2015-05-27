package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {

	
	
	@RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(ModelMap model) {
		
		UserOrganization userOrganization = new UserOrganization();
		
		model.addAttribute(userOrganization);
		
		return "registration/registrationForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registration(UserOrganization userOrganization, ModelMap model){
		
		
		
		return "registration/success";
	}
	
}
