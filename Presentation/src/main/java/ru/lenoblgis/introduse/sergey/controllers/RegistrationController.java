package ru.lenoblgis.introduse.sergey.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.services.OwnerService;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	
	@Autowired
	private OwnerService ownerService;
	
	/**
	 * �������� ����� �����������
	 * @param model - ������
	 * @return - view
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(ModelMap model) {
		
		UserOrganization userOrganization = new UserOrganization();
		
		model.addAttribute(userOrganization);
		
		return "registration/registrationForm";
	}
	
	/**
	 * ����������������
	 * @param userOrganization - ���������� � ������������ � ��� �����������
	 * @param model - ������
	 * @return - view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String registration(UserOrganization userOrganization, ModelMap model){
		
		OrganizationInfo organizationInfo = ownerService.registration(userOrganization);
		
		Owner myCompany = ownerService.reviewOwner(organizationInfo.getId());
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		session.setAttribute("myCompany", myCompany);
		
		return "redirect:organization/company/"+organizationInfo.getId();
	}
	
}
