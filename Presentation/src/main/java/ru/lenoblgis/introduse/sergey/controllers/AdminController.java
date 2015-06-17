package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/managing")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET)
    public String showAdminPage(ModelMap model) {	
		return "admin/manage";
	}
	
	@RequestMapping(value = "/findOrganizations", method = RequestMethod.GET)
    public String showFindingOranizationForm(ModelMap model) {	
		
		return "admin/findOrganization";
	}
	
}
