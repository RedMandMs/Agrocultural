package ru.lenoblgis.introduse.sergey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;

@Controller
public class PassportController {

	@RequestMapping(value = "/passport", method = RequestMethod.GET)
    public String authorization(ModelMap model) {
		
		PassportInfo reviewingPassport = new PassportInfo(34, 8, "Всеволожск", "LenOblGis", 1546, 456, "Для сельского хозяйства", "This passport hasn't comment");
		
		reviewingPassport.setNameOwner("LenOblGis");
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		session.setAttribute("reviewingPassport", reviewingPassport);
		
		return "passport";
	}
}
