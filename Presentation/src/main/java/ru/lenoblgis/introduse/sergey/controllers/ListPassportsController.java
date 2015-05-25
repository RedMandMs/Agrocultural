package ru.lenoblgis.introduse.sergey.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;

@Controller
public class ListPassportsController {

	@RequestMapping(value = "/passportlist", method = RequestMethod.GET)
    public String showPassportsList(ModelMap model) {
		
		List<PassportInfo> reviewingPassportsList = new ArrayList<>();
		
		PassportInfo passport1 = new PassportInfo(1, 01, "����������", "LenOblGis1", 11, 111, "��� ��������� ���������", "This passport hasn't comment");
		PassportInfo passport2 = new PassportInfo(2, 02, "����������", "LenOblGis2", 12, 122, "�� ��� ��������� ���������", "This passport hasn't comment");
		PassportInfo passport3 = new PassportInfo(3, 03, "����������", "LenOblGis3", 13, 133, "��� ��������� ���������", "This passport hasn't comment");
		PassportInfo passport4 = new PassportInfo(4, 04, "����������", "LenOblGis4", 14, 144, "�� ��� ��������� ���������", "This passport hasn't comment");
		PassportInfo passport5 = new PassportInfo(5, 05, "����������", "LenOblGis5", 15, 155, "��� ��������� ���������", "This passport hasn't comment");
		PassportInfo passport6 = new PassportInfo(6, 06, "����������", "LenOblGis6", 16, 166, "�� ��� ��������� ���������", "This passport hasn't comment");
		
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
