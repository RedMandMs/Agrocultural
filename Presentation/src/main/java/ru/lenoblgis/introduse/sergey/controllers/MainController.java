package ru.lenoblgis.introduse.sergey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.lenoblgis.introduse.sergey.services.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
    	
        return "index";
    }

}
