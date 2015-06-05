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
import ru.lenoblgis.introduse.sergey.services.UserService;

@Controller
@RequestMapping("/login")
public class AuthorizationController {

	@Autowired
	UserService userService;
	
	/**
	 * �������� ����� �����������
	 * @param model - ������
	 * @return - �����������
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String showAuthorizationForm(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		if(session.getAttribute("invalidateAuthorization")!=null){
			session.removeAttribute("invalidateAuthorization");
			String message = "�� ����� ����������� ������ �����!";
			model.addAttribute("message", message);
		}else{
			String message = "������� ����� � ������ ��� ���������� �����������:";
			model.addAttribute("message", message);
		}
		
		UserOrganization userOrganization = new UserOrganization();
		model.addAttribute("userOrganization", userOrganization);
		
		return "login";
	}
	
	/**
	 * ����� ��� ����������� ������������, �������� �������� ������
	 * @param userOrganization - ���������������� ������
	 * @param model - ������
	 * @return - ����������� (���������������)
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String authorization(UserOrganization userOrganization, ModelMap model){
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		OrganizationInfo organizationInfo = userService.getUser(userOrganization);
		
		if(organizationInfo != null){
			session.setAttribute("myCompany", organizationInfo);
			return "redirect:organization/company/" + organizationInfo.getId();
		}else{
			session.setAttribute("invalidateAuthorization", true);
			return "Presentation/login";
		}
	}
	
}
