package ru.lenoblgis.introduse.sergey.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.services.PassportService;

@Controller
public class PassportController {

	@Autowired
	PassportService passportService;
	
	@RequestMapping(value = "/passport", method = RequestMethod.GET)
    public String authorization(@RequestParam("passportId") String passportId, ModelMap model) {
				
		PassportInfo reviewingPassport;
		
		if(passportId == null){
			reviewingPassport = new PassportInfo(34, 8, "Всеволожск", "LenOblGis", 1546, 456, "Для сельского хозяйства", "This passport hasn't comment");
			
			reviewingPassport.setNameOwner("LenOblGis");
		}else{
			reviewingPassport = passportService.reviewPassport(Integer.valueOf(passportId));	
		}
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		session.setAttribute("reviewingPassport", reviewingPassport);
		
		return "passport";
	}
}
