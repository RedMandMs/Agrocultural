package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(method = RequestMethod.GET)
    public String showAuthorizationForm(ModelMap model) {
		
		return "admin";
	}
	
}
