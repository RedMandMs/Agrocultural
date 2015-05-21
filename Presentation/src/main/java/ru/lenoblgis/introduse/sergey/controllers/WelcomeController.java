package ru.lenoblgis.introduse.sergey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class WelcomeController {
	
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		
		session.setAttribute("id_organization", 8);
        model.addAttribute("message", "Complite!!!");
        return "welcome";
	}
}
