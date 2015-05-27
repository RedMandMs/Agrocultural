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
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.services.OwnerService;

@Controller
@RequestMapping(value="/organization")
public class CompanyController {

	@Autowired
	OwnerService ownerService;
	
	@RequestMapping(value = "/company", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		Owner myCompany = new Organization("LenOblGis", 1, "Каменноостровский проспект");
		
		session.setAttribute("myCompany", myCompany);
		
		return "organization/company";
	}
	
	/**
	 * Метод отображающий данные о конкретной компании
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company/{organizationId}", method = RequestMethod.GET)
    public String showOrganization(@PathVariable Integer organizationId, ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		Owner owner = ownerService.reviewOwner(organizationId);
		OrganizationInfo myCompany = new OrganizationInfo(owner.getId(), owner.getName(), owner.getInn(), owner.getAddress());
		
		session.setAttribute("myCompany", myCompany);
		
		return "organization/company";
	}
}
