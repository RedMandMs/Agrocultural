package ru.lenoblgis.introduse.sergey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.services.OwnerService;

@Controller
@RequestMapping(value="/organization")
public class CompanyController {

	@Autowired
	OwnerService ownerService;
	
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
			message = "Некорректные данные!!!";
			session.removeAttribute("changeOrganizationInfo");
			myCompany = (OrganizationInfo) session.getAttribute("incorrectCompany");
		}else{
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
		if(ownerService.editOwner(organizationInfo)){
			session.setAttribute("myCompany", organizationInfo);
			return "redirect:/organization/company/"+organizationInfo.getId();
		}else{
			session.setAttribute("changeOrganizationInfo", false);
			session.setAttribute("incorrectCompany", organizationInfo);
			return "redirect:/organization/company/change_organization_info";
		}
	}
}
