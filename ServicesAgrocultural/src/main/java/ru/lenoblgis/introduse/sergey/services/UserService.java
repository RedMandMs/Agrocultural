package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;

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
	
	/**
	 * DAO дл€ работы с базой данных
	 */
	@Autowired
	private DAO dao;
	
	@Autowired
	private OwnerService ownerService;
	
	public OrganizationInfo registration(UserOrganization userOrganization){
		
		User user = new User(userOrganization.getLogin(), userOrganization.getPassword(), UserRole.USER);
		Owner organization = new Organization(userOrganization.getOrganizationName(), 
				userOrganization.getInn(), userOrganization.getAddress());
		
		// одировка парол€
		String coddingPassword = new Md5PasswordEncoder().encodePassword(user.getPassword(), "");
		user.setPassword(coddingPassword);

		user = dao.registration(user, organization);
		
		organization = dao.reviewOwner(user.getOrganizationId());
		
		OrganizationInfo organizationInfo = new OrganizationInfo(organization.getId(), organization.getName(), 
								organization.getInn(), organization.getAddress());
		
		return organizationInfo;
	}
	
	public OrganizationInfo getUser(UserOrganization userOrganization){
		
		User user = new User(userOrganization.getLogin(), userOrganization.getPassword());
		
		user = dao.reviewUser(user);
		
		Owner owner = dao.reviewOwner(user.getOrganizationId());
		
		OrganizationInfo organizationInfo = new OrganizationInfo(owner.getId(), owner.getName(), owner.getInn(), owner.getAddress());
		
		return organizationInfo;
	}
	
	public User getUserByLogin(String login) {
		User user = dao.findUserByLogin(login);
		
		return user;
	}
	
	public OrganizationInfo getMyOrganizationByLogin(String login){
		User user = getUserByLogin(login);
		
		Owner myOwnerInfo = ownerService.reviewOwner(user.getOrganizationId());
		
		OrganizationInfo myOrganizationInfo = new OrganizationInfo(myOwnerInfo.getId(), myOwnerInfo.getName(), 
																		myOwnerInfo.getInn(), myOwnerInfo.getAddress());
		
		return myOrganizationInfo;
	}

}
