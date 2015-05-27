package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.user.User;

@Component("organizationService")
public class OwnerService implements Serializable{

	/**
	 * DAO для работы с базой данных
	 */
	private DAO dao = new DAO();
	
	/**
	 * Редактировать владельца
	 * @param info - информация о редактируемром владельце("id" - id владельца, "name" - имя организации, inn - ИНН, address_org - адрес организации)
 	 * @return - результаты работы ("success" - успешено ли был отредактирован владелец)
	 */
	public Map<String, String> editOwner(Map<String, String> info){
		Map<String, String> workresults = new HashMap<String, String>();
		
		Integer id = Integer.valueOf(info.get("id"));
		String nameOrg = info.get("name");
		Integer inn = Integer.valueOf(info.get("inn"));
		String addresOrg = info.get("address_org");
		Owner editingOwner = new Organization(id, nameOrg, inn, addresOrg);
		try{
			dao.editOwner(editingOwner);
			workresults.put("success", "true");
		}catch(DuplicateKeyException duplicateEx){
			//TODO:
			System.out.println("Дубликаты!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	/**
	 * Просмотреть владельца
	 * @param ownerId - id владельца
	 * @return - информация о просматриваемом владельце ("isExist" - существует ли такой владелец, "id" - id владельца, "name" - имя организации, inn - ИНН, address_org - адрес организации)
	 */
	public Owner reviewOwner(int ownerId){
		
		try{
			Owner reviewOwner = dao.reviewOwner(ownerId);
			return reviewOwner;
		}
		catch (IndexOutOfBoundsException ex) {
			//TODO:
		}
		return null;
	}
	
	/**
	 * Удалить владельца
	 * @param ownerId - id владельца
	 * @return - результаты работы ("success" - успешено ли был удалён владелец)
	 */
	public Map<String, String> deleteOwner(int ownerId){
		Map<String, String> workresults = new HashMap<String, String>();
		try{
			dao.reviewOwner(ownerId);
			dao.deleteOwner(ownerId);
			workresults.put("success", "true");
		}
		catch (IndexOutOfBoundsException ex) {
			workresults.put("success", "false");
		}
		return workresults;
	}
	
	public OrganizationInfo registration(UserOrganization userOrganization){
		
		User user = new User(userOrganization.getLogin(), userOrganization.getPassword());
		Owner organization = new Organization(userOrganization.getOrganizationName(), 
				userOrganization.getInn(), userOrganization.getAddress());
		
		user = dao.registration(user, organization);		
		
		organization = dao.reviewOwner(user.getOrganizationId());
		
		OrganizationInfo organizationInfo = new OrganizationInfo(organization.getId(), organization.getName(), 
								organization.getInn(), organization.getAddress());
		
		return organizationInfo;
	}
		
}
