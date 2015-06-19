package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.user.User;
import ru.lenoblgis.introduse.sergey.domen.user.UserRole;

@Service
public class UserService implements Serializable{
	
	Validator validator;
	
	
	/**
	 * ���������� ��-���������
	 */
	public UserService() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    	validator = validatorFactory.getValidator();
	}

	/**
	 * DAO ��� ������ � ����� ������
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * ������ ������ � �������������
	 */
	@Autowired
	private OwnerService ownerService;
	
	/**
	 * ���������������� ������������ � ��������
	 * @param userOrganization - ������ �������������� ������������ � �����������, ��������� ��� �����������
	 * @return - ���������� � ��������� �����������
	 */
	public OrganizationInfo registration(UserOrganization userOrganization){
		
		Set<ConstraintViolation<UserOrganization>> violationsUserOrganization = validator.validate(userOrganization);
		
		List<String> listEror = new ArrayList<String>();
		
		OrganizationInfo organizationInfo = new OrganizationInfo();
		
		if(violationsUserOrganization.size() != 0){
			Iterator<ConstraintViolation<UserOrganization>> violationIterator = violationsUserOrganization.iterator();
			while(violationIterator.hasNext()){
				String message = violationIterator.next().getMessage();
				listEror.add(message);
			}
		}else{
			
			User user = new User(userOrganization.getLogin(), userOrganization.getPassword(), UserRole.USER);
			
			Owner organization = new Organization(userOrganization.getOrganizationName(), 
					userOrganization.getInn(), userOrganization.getAddress());
			
			//��������� ������
			String coddingPassword = new Md5PasswordEncoder().encodePassword(user.getPassword(), "");
			user.setPassword(coddingPassword);
	
			user = dao.registration(user, organization);
			
			organization = dao.reviewOwner(user.getOrganizationId());
			
			organizationInfo = OwnerService.convertDomainToDTO(organization);
		}
		
		organizationInfo.setListEror(listEror);
		
		return organizationInfo;
	}
	
	/**
	 * �������� ������������ �� ������
	 * @param login - �����
	 * @return - ������������
	 */
	public User getUserByLogin(String login) {
		return dao.findUserByLogin(login);
	}
	
	/**
	 * �������� ����������� �� ������
	 * @param login - �����
	 * @return - ��������� �����������
	 */
	public OrganizationInfo getMyOrganizationByLogin(String login){
		User user = getUserByLogin(login);
		
		OrganizationInfo myOwnerInfo = ownerService.reviewOwner(user.getOrganizationId());
		
		return myOwnerInfo;
	}

}
