package ru.lenoblgis.introduse.sergey.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.services.OwnerService;
import ru.lenoblgis.introduse.sergey.services.UserService;

@Controller
@RequestMapping(value="/registration")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OwnerService ownerService;
	/**
	 * Показать Форму регистрации
	 * @param model - модель
	 * @return - view
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm(ModelMap model) {
		
		UserOrganization userOrganization = new UserOrganization();
		model.addAttribute(userOrganization);
		
		return "registration/registrationForm";
	}
	


	/**
	 * Зарегестрировать
	 * @param userOrganization - Информация о пользователе и его организации
	 * @param model - модель
	 * @return - view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String registration(UserOrganization userOrganization, ModelMap model){
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		
		OrganizationInfo regestratingCompany = userService.registration(userOrganization);
		
		if(regestratingCompany.getListEror().isEmpty()){
			session.removeAttribute("uncorrectRegistrationUserCompany");
			session.removeAttribute("listErorRegistration");
			return "redirect:/login";
		}
		
		session.setAttribute("uncorrectRegistrationUserCompany", userOrganization);
		session.setAttribute("listErorRegistration", getErorRegistration(regestratingCompany.getListEror()));
		
		return "redirect:/registration";
	}
	
	private List<String> getErorRegistration(List<String> listEror) {
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
					
			}
		}
		return listMessage;
	}
	
}
