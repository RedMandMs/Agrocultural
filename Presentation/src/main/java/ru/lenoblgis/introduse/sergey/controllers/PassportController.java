package ru.lenoblgis.introduse.sergey.controllers;

import java.util.ArrayList;
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
    public String editPassportForm(PassportInfo changedPassport, ModelMap model) {
		
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
	@RequestMapping(value = "/mypassportlist", method = RequestMethod.GET)
    public String showMyPassportsList(ModelMap model) {
		
		List<PassportInfo> reviewingPassportsList = new ArrayList<>();
		
		PassportInfo passport1 = new PassportInfo(1, 01, "Всеволожск", "LenOblGis1", 11, 111, "Для сельского хозяйства", "This passport hasn't comment");
		PassportInfo passport2 = new PassportInfo(2, 02, "Всеволожск", "LenOblGis2", 12, 122, "Не для сельского хозяйства", "This passport hasn't comment");
		PassportInfo passport3 = new PassportInfo(3, 03, "Всеволожск", "LenOblGis3", 13, 133, "Для сельского хозяйства", "This passport hasn't comment");
		PassportInfo passport4 = new PassportInfo(4, 04, "Всеволожск", "LenOblGis4", 14, 144, "Не для сельского хозяйства", "This passport hasn't comment");
		PassportInfo passport5 = new PassportInfo(5, 05, "Всеволожск", "LenOblGis5", 15, 155, "Для сельского хозяйства", "This passport hasn't comment");
		PassportInfo passport6 = new PassportInfo(6, 06, "Всеволожск", "LenOblGis6", 16, 166, "Не для сельского хозяйства", "This passport hasn't comment");
		
		reviewingPassportsList.add(passport1);
		reviewingPassportsList.add(passport2);
		reviewingPassportsList.add(passport3);
		reviewingPassportsList.add(passport4);
		reviewingPassportsList.add(passport5);
		reviewingPassportsList.add(passport6);
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		session.setAttribute("reviewingPassportsList", reviewingPassportsList);
		
		return "passportlist";
	}
}
