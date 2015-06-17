package ru.lenoblgis.introduse.sergey.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.event.EventInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.passport.RegionField;
import ru.lenoblgis.introduse.sergey.domen.passport.TypeField;
import ru.lenoblgis.introduse.sergey.domen.user.UserRole;
import ru.lenoblgis.introduse.sergey.services.EventService;
import ru.lenoblgis.introduse.sergey.services.OwnerService;
import ru.lenoblgis.introduse.sergey.services.PassportService;
import ru.lenoblgis.introduse.sergey.services.UserService;

@Controller
@RequestMapping(value="/organization")
public class CompanyController {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private EventService eventService;
	
	/**
	 * Метод отображающий данные о конкретной компании
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company/{organizationId}", method = RequestMethod.GET)
    public String showOrganization(@PathVariable Integer organizationId, ModelMap model) {
		
		Owner owner = ownerService.reviewOwner(organizationId);
		OrganizationInfo reviewingCompany = new OrganizationInfo(owner.getId(), owner.getName(), owner.getInn(), owner.getAddress());
		
		model.addAttribute("reviewingCompany", reviewingCompany);
		
		model.addAttribute("isMyCompany", false);
		
		return "organization/company";
	}
	
	/**
	 * Метод отображающий данные о конкретной компании
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company/mycompany", method = RequestMethod.GET)
    public String showMyCompany(ModelMap model) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		if(session.getAttribute("myCompany") == null){
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			RegionField[] regions = RegionField.values();
			session.setAttribute("regions", regions);
			
			TypeField[] types = TypeField.values();
			session.setAttribute("types", types);
			
			OrganizationInfo myCompany = userService.getMyOrganizationByLogin(user.getUsername());
			session.setAttribute("myCompany", myCompany);

			//Образец по которому мы ищем все паспорта организации (указано только id владельца)
			PassportInfo ownPassports = new PassportInfo();
			ownPassports.setIdOwner(myCompany.getId());
			List<PassportInfo> myPassportsList = passportService.findPassports(ownPassports);
			session.setAttribute("myPassportsList", myPassportsList);
			session.setAttribute("lastList", "mylistpassports");
			List<Integer> myIdPassports = new ArrayList<Integer>();
			for(PassportInfo passportInfo : myPassportsList){
				myIdPassports.add(passportInfo.getId());
			}
			session.setAttribute("myIdPasports", myIdPassports);
			
		}
		
		model.addAttribute("reviewingCompany", session.getAttribute("myCompany"));
		
		model.addAttribute("isMyCompany", true);
		
		return "organization/company";
	}
	
	/**
	 * Метод отображающий форму для изменения данных о вашей компании
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company/change_organization_info", method = RequestMethod.GET)
    public String showChangeInfoOrganization(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		Boolean rightTry = (Boolean) session.getAttribute("changeOrganizationInfo");
		
		OrganizationInfo myCompany;
		
		String message = "";
		if(rightTry != null){
			session.removeAttribute("changeOrganizationInfo");
			myCompany = (OrganizationInfo) session.getAttribute("incorrectCompany");
			session.setAttribute("editOrganizationErors", getErorRegistration(myCompany.getListEror()));
		}else{
			session.removeAttribute("editOrganizationErors");
			message = "Измените необходимые данные";
			myCompany = (OrganizationInfo) session.getAttribute("myCompany");
		}
		
		model.addAttribute("message", message);
		
		model.addAttribute("myCompany", myCompany);
		
		OrganizationInfo changedCompany = new OrganizationInfo();
		
		model.addAttribute("changedCompany", changedCompany);
		
		return "organization/change_info_organization";
	}
	
	/**
	 * Метод обрабатывающий изменение в информации об организации
	 * @param organizationInfo - новая информация об организации
	 * @param model - модель
	 * @return - отображение страницы после изменения (перенаправление)
	 */
	@RequestMapping(value = "/company/change_organization_info", method = RequestMethod.POST)
    public String сhangeInfoOrganization(OrganizationInfo organizationInfo, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create

		OrganizationInfo myCompany = (OrganizationInfo) session.getAttribute("myCompany");
		
		organizationInfo.setId(myCompany.getId());
		if(ownerService.editOwner(organizationInfo).getListEror().isEmpty()){
			session.setAttribute("myCompany", organizationInfo);
			return "redirect:/organization/company/mycompany";
		}else{
			session.setAttribute("changeOrganizationInfo", false);
			session.setAttribute("incorrectCompany", organizationInfo);
			return "redirect:/organization/company/change_organization_info";
		}
	}
	
	@RequestMapping(value = "/company/events", method = RequestMethod.GET)
    public String showLogEvents( ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		OrganizationInfo myCompany = (OrganizationInfo) session.getAttribute("myCompany");
		List<EventInfo> events = eventService.getAllOwnerEvents(myCompany.getId());
		
		session.setAttribute("events", events);
		
		
		return "organization/events_company";
	}
	
	public static List<String> getErorRegistration(List<String> listEror) {
		List<String> listMessage = new ArrayList<String>();
		for(String eror : listEror){
			switch(eror){
				
				case("WrongFormatLogin"):
					listMessage.add("Неверный формат логина (более 4 и менее 16 латинских символов)!");
					break;
				case("WrongFormatPassword"):
					listMessage.add("Неверный формат пароля (более 4 и менее 16 символов)!");
					break;
				
				case("CopyLogin"):
					listMessage.add("Пользователь с таким логином уже существует!");
					break;
			
				case("CopyNameOrganization"):
					listMessage.add("Организация с таким названием уже зарегистрирована!");
					break;
				
				case("CopyINN"):
					listMessage.add("Организация с таким ИНН уже зарегистрирована!");
					break;
				case("NegativINN"):
					listMessage.add("ИНН должен быть положительным значением!");
					break;
				case("wrongNameCompany"):
					listMessage.add("Имя компании должно быть от 3 до 20 символов!");
					break;
			}
		}
		return listMessage;
	}
}
