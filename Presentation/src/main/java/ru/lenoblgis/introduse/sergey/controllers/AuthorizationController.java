package ru.lenoblgis.introduse.sergey.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.services.UserService;

@Controller
@RequestMapping("/login")
public class AuthorizationController {

	@Autowired
	private static UserService userService;
	
	/**
	 * Показать форму авторизации
	 * @param model - модель
	 * @return - отображение
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String showAuthorizationForm(ModelMap model) {
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true); // true == allow create
		if(session.getAttribute("invalidateAuthorization")!=null){
			session.removeAttribute("invalidateAuthorization");
			String message = "Вы введи некоректные данные входа!";
			model.addAttribute("message", message);
		}else{
			String message = "Введите логин и пароль для выполнения авторизации:";
			model.addAttribute("message", message);
		}
		
		UserOrganization userOrganization = new UserOrganization();
		model.addAttribute("userOrganization", userOrganization);
		
		return "login";
	}
	
}
