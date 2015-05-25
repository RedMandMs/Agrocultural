package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizationController {

	@RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorization(ModelMap model) {
	
		return "authorization";
	}
}
