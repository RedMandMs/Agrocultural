package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class EventControllers {
	
	@RequestMapping(value = "/passports", method = RequestMethod.GET)
    public String printEvents(ModelMap model) {
		
		
		
        return "events";
	}

}
