package ru.lenoblgis.introduse.sergey.controllers;

import java.util.List;

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
		
		PassportInfo changedPassport;
		
		if(session.getAttribute("changePassportInfo") != null){
			session.removeAttribute("changePassportInfo");
			changedPassport = (PassportInfo) session.getAttribute("incorrectPassport");
			session.removeAttribute("incorrectPassport");
			model.addAttribute("message", "Не удалось изменить информацию о пасспорте!!!");
		}else{
			changedPassport = passportService.reviewPassport(Integer.valueOf(passportId));
			model.addAttribute("message", "Введите новые данные о пасспорте:");
		}
		
		List<Integer> myIdPasports = (List<Integer>) session.getAttribute("myIdPasports"); 
		
		if(myIdPasports.contains(changedPassport.getId())){
			model.addAttribute("changedPassport", changedPassport);
			return "passport/change_info_passport";
		}else{
			return "403";
		}
	}
	
	@RequestMapping(value = "change_passport_info/{passportId}", method = RequestMethod.POST)
    public String editPassport(PassportInfo changedPassport, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		
		
		if(passportService.editPassport(changedPassport)){
			return "redirect:/passport/"+changedPassport.getId();
		}else{
			session.setAttribute("changePassportInfo", false);
			session.setAttribute("incorrectPassport", changedPassport);
			return "redirect:/passport/change_passport_info/"+changedPassport.getId();
		}
	}
	
	@RequestMapping(value = "createPassport", method = RequestMethod.GET)
    public String showFormCreatePassport(ModelMap model) {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		PassportInfo createdPassport;
		
		if(session.getAttribute("isCreatePassport") == null){
			OrganizationInfo myCompany = (OrganizationInfo) session.getAttribute("myCompany");
			createdPassport = new PassportInfo();
			createdPassport.setIdOwner(myCompany.getId());
			createdPassport.setNameOwner(myCompany.getName());
			model.addAttribute("message", "Введите данные о новом пасспорте");
		}else{
			session.removeAttribute("isCreatePassport");
			createdPassport = (PassportInfo) session.getAttribute("incorrectPassport");
			session.removeAttribute("incorrectPassport");
			model.addAttribute("message", "Вы ввели некорректные данные!");
		}
		model.addAttribute("createdPassport", createdPassport);
		
		return "passport/create_passport";
	}
	
	/**
	 * Создать паспорт
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createPassport", method = RequestMethod.POST)
    public String createPassport(PassportInfo createdPassport, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		OrganizationInfo myCompany = (OrganizationInfo) session.getAttribute("myCompany");
		
		createdPassport.setIdOwner(myCompany.getId());
		createdPassport.setNameOwner(myCompany.getName());
		
		int passportId = passportService.createPassport(createdPassport);
		if(passportId != 0){
			List<Integer> myIdPasports = (List<Integer>) session.getAttribute("myIdPasports");
			myIdPasports.add(passportId);
			return "redirect:/passport/"+passportId;
		}else{
			session.setAttribute("isCreatePassport", false);
			session.setAttribute("incorrectPassport", createdPassport);
			return "redirect:/passport/createPassport";
		}
	}
	
	/**
	 * Отображение списка пасспортов, которыми владеет организация
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listpassports", method = RequestMethod.GET)
    public String showPassportsList(@RequestParam("purpose") String purpose, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		if(purpose.equals("myPassportsList")){
			
			OrganizationInfo myCompany = (OrganizationInfo) session.getAttribute("myCompany");
			PassportInfo ownPassports = new PassportInfo();
			ownPassports.setIdOwner(myCompany.getId());
			List<PassportInfo> myPassports = passportService.findPassports(ownPassports);
			
			session.setAttribute("isSerchList", false);
			session.setAttribute("purpose", "myPassportsList");
			session.setAttribute("reviewingPassportsList", myPassports);
			session.setAttribute("messageList", "Список паспартов вашей организации:");
		}
		
		if(purpose.equals("serch")){
			
			session.setAttribute("isSerchList", true);
			session.setAttribute("purpose", "serch");
			session.setAttribute("messageList", "Список найденых паспартов:");
			PassportInfo serchingPassport = (PassportInfo) session.getAttribute("serchingPassport");	
			if(serchingPassport==null){
				serchingPassport = new PassportInfo();
			}
			model.addAttribute("serchingPassport", serchingPassport);
		}
		
		return "passport/passportlist";
	}
	
	@RequestMapping(value = "/listpassports", method = RequestMethod.POST)
    public String findPassports(PassportInfo serchingPassport, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		List<PassportInfo> findingPassports = passportService.findPassports(serchingPassport);
		session.setAttribute("reviewingPassportsList", findingPassports);
		session.setAttribute("serchingPassport", serchingPassport);
		return "redirect:/passport/listpassports?purpose=serch";
	}
}
