package ru.lenoblgis.introduse.sergey.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.services.OwnerService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	OwnerService ownerService;

	@RequestMapping(value = "managing", method = RequestMethod.GET)
    public String showAdminPage(ModelMap model) {	
		return "admin/managing";
	}
	
	@RequestMapping(value = "/findingCompany", method = RequestMethod.GET)
    public String showFindingOranizationForm(ModelMap model) {	
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		OrganizationInfo serchingOrganization = (OrganizationInfo) session.getAttribute("serchingOrganization");
		
		if(serchingOrganization == null){
			serchingOrganization = new OrganizationInfo();
		}
		
		model.addAttribute("serchingOrganization", serchingOrganization);
		
		return "admin/findOrganization";
	}
	
	@RequestMapping(value = "/findingCompany", method = RequestMethod.POST)
    public String findOranization(OrganizationInfo serchingOrganization, ModelMap model) {	
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		List<OrganizationInfo> findingOrganizations = ownerService.findOrganizations(serchingOrganization);
		
		session.setAttribute("serchingOrganization", serchingOrganization);
		session.setAttribute("findingOrganizations", findingOrganizations);		
		
		return "redirect:/admin/findingCompany";
	}
	
	@RequestMapping(value = "/allEvents", method = RequestMethod.GET)
    public String showAllEvents(ModelMap model) {	
		
		return "admin/allevents";
	}
	
}
