package ru.lenoblgis.introduse.sergey.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.services.PassportService;

@Controller
@RequestMapping(value = "/passport")
public class PassportController {

	@Autowired
	PassportService passportService;
	
	/**
	 * Метод для отображения конкретного паспорта
	 * @param passportId - id просматриваемого паспорта
	 * @param model
	 * @return - отображения
	 */
	@RequestMapping(value = "/{passportId}", method = RequestMethod.GET)
    public String reviewPassport(@PathVariable Integer passportId, ModelMap model) {
				
		PassportInfo reviewingPassport;
		
		if(passportId == null){
			return "403";
		}else{
			reviewingPassport = passportService.reviewPassport(Integer.valueOf(passportId));	
		}
		
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		List<Integer> myIdPasports = (List<Integer>) session.getAttribute("myIdPasports"); 
		
		if(myIdPasports.contains(reviewingPassport.getId())){
			model.addAttribute("isMyPassport", true);
		}else{
			model.addAttribute("isMyPassport", false);
		}
		
		model.addAttribute("reviewingPassport", reviewingPassport);
		
		return "passport/passport";
	}
	
	/**
	 * Метод для отображения формы для изменения данных о пасспорте
	 * @param passportId - id изменяемого пасспорта
	 * @param model
	 * @return - отображение
	 */
	@RequestMapping(value = "change_passport_info/{passportId}", method = RequestMethod.GET)
    public String editPassportForm(@PathVariable Integer passportId, ModelMap model) {
				
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		PassportInfo editingPassport;
		
		if(session.getAttribute("changePassportInfo") != null){
			session.removeAttribute("changePassportInfo");
			editingPassport = (PassportInfo) session.getAttribute("incorrectPassport");
			session.removeAttribute("incorrectPassport");
			model.addAttribute("message", "Не удалось изменить информацию о пасспорте!!!");
		}else{
			editingPassport = passportService.reviewPassport(Integer.valueOf(passportId));
			model.addAttribute("message", "Введите новые данные о пасспорте:");
		}
		
		List<Integer> myIdPasports = (List<Integer>) session.getAttribute("myIdPasports"); 
		
		if(myIdPasports.contains(editingPassport.getId())){
			model.addAttribute("editingPassport", editingPassport);
			return "change_info_passport";
		}else{
			return "403";
		}
	}
	
	@RequestMapping(value = "change_passport_info/{passportId}", method = RequestMethod.POST)
    public String editPassportForm(PassportInfo passportInfo, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create

		
		if(passportService.editPassport(passportInfo)){
			return "redirect:/passport/"+passportInfo.getId();
		}else{
			session.setAttribute("changePassportInfo", false);
			session.setAttribute("incorrectPassport", passportInfo);
			return "redirect:/passport/"+passportInfo.getId();
		}
	}
}
