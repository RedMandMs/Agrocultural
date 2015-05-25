package ru.lenoblgis.introduse.sergey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;

@Controller
public class CompanyController {

	@RequestMapping(value = "/company", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		Owner myCompany = new Organization("LenOblGis", 1, "Каменноостровский проспект");
		
		session.setAttribute("myCompany", myCompany);
		
		return "company";
	}
}
