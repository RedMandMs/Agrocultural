package ru.lenoblgis.introduse.sergey.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	 * ћетод дл€ отображени€ конкретного паспорта
	 * @param passportId - id просматриваемого паспорта
	 * @param model
	 * @return - отображени€
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
		
		model.addAttribute("idPassport", passportId);
		model.addAttribute("reviewingPassport", reviewingPassport);
		
		return "passport/passport";
	}
	
	/**
	 * ћетод дл€ отображени€ формы дл€ изменени€ данных о пасспорте
	 * @param passportId - id измен€емого пасспорта
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
			model.addAttribute("message", "Ќе удалось изменить информацию о пасспорте!!!");
		}else{
			changedPassport = passportService.reviewPassport(Integer.valueOf(passportId));
			model.addAttribute("message", "¬ведите новые данные о пасспорте:");
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
		
		PassportInfo createdPassport = (PassportInfo) session.getAttribute("incorrectPassport");
		
		if(createdPassport == null){
			OrganizationInfo myCompany = (OrganizationInfo) session.getAttribute("myCompany");
			createdPassport = new PassportInfo();
			createdPassport.setIdOwner(myCompany.getId());
			createdPassport.setNameOwner(myCompany.getName());
			model.addAttribute("message", "¬ведите данные о новом пасспорте");
		}else{
			session.removeAttribute("incorrectPassport");
			model.addAttribute("message", "¬ы ввели некорректные данные!");
		}
		model.addAttribute("createdPassport", createdPassport);
		
		return "passport/create_passport";
	}
	
	/**
	 * —оздать паспорт
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
			PassportInfo newPassport = passportService.reviewPassport(passportId);
			List<Integer> myIdPasports = (List<Integer>) session.getAttribute("myIdPasports");
			myIdPasports.add(newPassport.getId());
			List<PassportInfo> myPassportList = (List<PassportInfo>) session.getAttribute("myPassportsList");
			myPassportList.add(newPassport);
			session.setAttribute("lastList", "mylistpassports");
			return "redirect:/passport/"+passportId;
		}else{
			session.setAttribute("incorrectPassport", createdPassport);
			return "redirect:/passport/createPassport";
		}
	}
	
	/**
	 * ќтображение списка пасспортов, которыми владеет организаци€
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mylistpassports", method = RequestMethod.GET)
    public String showMyPassportsList(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		session.setAttribute("lastList", "mylistpassports");
		
		return "passport/mypassportlist";
		
	}
	
	/**
	 * ќтображение формы поиска паспартов
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findlistpassports", method = RequestMethod.GET)
    public String findPassportsList(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		session.setAttribute("lastList", "findlistpassports");
		
		PassportInfo serchingPassport = (PassportInfo) session.getAttribute("serchingPassport");
		if(serchingPassport==null){
			serchingPassport = new PassportInfo();
		}
		model.addAttribute("serchingPassport", serchingPassport);
		
		return "passport/findpassportlist";
	}
	
	/**
	 * «апрос на поиск папартов по параметрам
	 * @param serchingPassport - образец пасспорта дл€ поиска
	 * @param model
	 * @return - перенаправление на отображение формы поиска
	 */
	@RequestMapping(value = "/findlistpassports", method = RequestMethod.POST)
    public String findPassports(PassportInfo serchingPassport, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		List<PassportInfo> findPassports = passportService.findPassports(serchingPassport);
		session.setAttribute("findPassportsList", findPassports);
		session.setAttribute("serchingPassport", serchingPassport);
		return "redirect:/passport/findlistpassports";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePassports(HttpServletRequest request, ModelMap model) {
		
		Integer idPassport = Integer.valueOf(request.getParameter("idPassport"));
		
		passportService.deletePassport(idPassport);
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		List<Integer> myIdPasports = (List<Integer>) session.getAttribute("myIdPasports");
		myIdPasports.remove(idPassport);
		List<PassportInfo> myPassportList = (List<PassportInfo>) session.getAttribute("myPassportsList");
		for(PassportInfo passportInfo : myPassportList){
			if(passportInfo.getId().equals(idPassport)){
				myPassportList.remove(passportInfo);
				break;
			}
		}
		
		String lastList = (String) session.getAttribute("lastList");
		
		return "redirect:/passport/"+ lastList;
	}
}
