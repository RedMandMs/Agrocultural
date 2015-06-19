package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;

@Component("organizationService")
public class OwnerService implements Serializable{

	
	ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	Validator validator = validatorFactory.getValidator();
	
	/**
	 * DAO для работы с базой данных
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * Редактировать владельца
	 * @param organizationInfo - информация о редактируемром владельце("id" - id владельца, "name" - имя организации, inn - ИНН, address_org - адрес организации)
 	 * @return - успешено ли был отредактирован владелец
	 */
	public OrganizationInfo editOwner(OrganizationInfo organizationInfo){
		
		Set<ConstraintViolation<OrganizationInfo>> violationsPassportInfo = validator.validate(organizationInfo);
		
		List<String> listEror = new ArrayList<String>();
		
		Owner oldOwner = dao.reviewOwner(organizationInfo.getId());
		
		boolean innNotChange = oldOwner.getInn().equals(organizationInfo.getInn());;
		boolean nameNotChange = oldOwner.getName().equals(organizationInfo.getName());
		//Если кадастровый номер не менялся и колличество ошибок больше единицы или 
		//кадастровые номера не поменялись и колличество ошибок больше нуля
		if((violationsPassportInfo.size() > 2 && innNotChange && nameNotChange) ||
						(violationsPassportInfo.size() > 1 && (innNotChange || nameNotChange)) ||
						(violationsPassportInfo.size() > 0 && !innNotChange && !nameNotChange)){
			Iterator<ConstraintViolation<OrganizationInfo>> violationIterator = violationsPassportInfo.iterator();
			while(violationIterator.hasNext()){
				String message = violationIterator.next().getMessage();
				//Если это не ошибка копии кадастрового номера, 
				//когда он дублирует сам себя, то записываем сообщение об ошибке в список ошибок
				if( !(message.equals("CopyINN") && innNotChange) && (!(message.equals("CopyNameOrganization") && nameNotChange)) ){
					listEror.add(message);
				}
			}
			organizationInfo.setListEror(listEror);
			return organizationInfo;
		}else{
			
			Owner editingOwner = convertDTOToDomain(organizationInfo);
			
			dao.editOwner(editingOwner);
			organizationInfo.setListEror(listEror);
			return organizationInfo;
		}
	}
	
	/**
	 * Просмотреть владельца
	 * @param ownerId - id владельца
	 * @return - информация о просматриваемом владельце ("isExist" - существует ли такой владелец, "id" - id владельца, "name" - имя организации, inn - ИНН, address_org - адрес организации)
	 */
	public OrganizationInfo reviewOwner(int ownerId){
		
		try{
			Owner reviewOwner = dao.reviewOwner(ownerId);
			OrganizationInfo organizationInfo = convertDomainToDTO(reviewOwner);
			return organizationInfo;
		}
		catch (IndexOutOfBoundsException ex) {
			//TODO:
		}
		return null;
	}
	
	/**
	 * Удалить владельца
	 * @param ownerId - id владельца
	 * @return - true - успешно удалён/ false - не удалён
	 */
	public boolean deleteOwner(int ownerId){
		try{
			dao.deleteOwner(ownerId);
			return true;
		}
		catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}
	
	/**
	 * Найти организации по заданным параметрам
	 * @param serchingOrganization - объект, содержащий в себе задынные для поиска параметры
	 * @return - список найденных организаций
	 */
	public List<OrganizationInfo> findOrganizations(OrganizationInfo serchingOrganization){
		List<OrganizationInfo> findInfo = new ArrayList<OrganizationInfo>();
		
		Organization findingOrganization = convertDTOToDomain(serchingOrganization);
		
		List<Organization> findOrganizations = dao.findOwners(findingOrganization);
		
		for(Organization organization : findOrganizations){
			findInfo.add(convertDomainToDTO(organization));
		}
		
		return findInfo;
	}
	
	/**
	 * Преобразовать организацию из Data Transfer Object в доменную форму
	 * @param organization - организация в форме DTO
	 * @return - организация в доменной форме
	 */
	public static Organization convertDTOToDomain(OrganizationInfo organization){
		return new Organization(organization.getId(), organization.getName(), organization.getInn(), organization.getAddress());
	}
	
	/**
	 * Преобразовать организацию из Data Transfer Object в доменную форму
	 * @param organization - организация в форме DTO
	 * @return - организация в доменной форме
	 */
	public static OrganizationInfo convertDomainToDTO(Owner organization){
		return new OrganizationInfo(organization.getId(), organization.getName(), organization.getInn(), organization.getAddress());
	}
}
