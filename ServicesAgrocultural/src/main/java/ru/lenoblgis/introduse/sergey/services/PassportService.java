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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.UserOrganization;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;

@Component("passportService")
public class PassportService implements Serializable {
	
	Validator validator;
	
	
	
	public PassportService() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    	validator = validatorFactory.getValidator();
	}
	
	/**
	 * DAO для работы с базой данных
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * Добавить новый пасспорт в БД
	 * @param info - информация о добавляемом паспорте("id_organization" - id организации, 
	 * 				region - регион, cadastr_number - кадастровый номер, area - площадь, 
	 * 				type_field - тип поля, comment - комментарий)
 	 * @return - id созданного пасспорта (0 - если не удалось создать пасспорт)
	 */
	public PassportInfo createPassport(PassportInfo passportInfo){
		
		Set<ConstraintViolation<PassportInfo>> violationsPassportInfo = validator.validate(passportInfo);
		
		List<String> listEror = new ArrayList<String>();
		
		if(violationsPassportInfo.size() != 0){
			Iterator<ConstraintViolation<PassportInfo>> violationIterator = violationsPassportInfo.iterator();
			while(violationIterator.hasNext()){
				String message = violationIterator.next().getMessage();
				listEror.add(message);
			}
			passportInfo.setListEror(listEror);
			return passportInfo;
		}else{
		
			Integer idOrg = passportInfo.getIdOwner();
			String region = passportInfo.getRegion();
			Integer cadastrNum = passportInfo.getCadastrNumber();
			Float area = (Float) passportInfo.getArea();
			String typeField = passportInfo.getType();
			String comment = passportInfo.getComment();
	
			Passport passport = new Passport(idOrg, region, cadastrNum, area, typeField, comment);
			try{
				Integer id = dao.createPassport(passport);
				passportInfo.setId(id);
				return passportInfo;
			}catch(DuplicateKeyException duplicateEx){
				System.out.println("Дублирование!!!");
				return passportInfo;
			}catch(DataIntegrityViolationException ex){
				System.out.println("Внешний ключ!!!");
				return passportInfo;
			}
		}
		
	}
	
	
	/**
	 * Редактировать пасспорт
	 * @param info - информация о редактируемром паспорте("id" - id пасспорта, "id_organization" - id организации, region - регион, cadastr_number - кадастровый номер, area - площадь, type_field - тип поля, comment - комментарий)
 	 * @return - результаты работы ("success" - успешено ли был отредактирован паспорт)
	 */
	public PassportInfo editPassport(PassportInfo passportInfo){
		
		Set<ConstraintViolation<PassportInfo>> violationsPassportInfo = validator.validate(passportInfo);
		
		List<String> listEror = new ArrayList<String>();
		
		Passport oldPassport = dao.reviewPassportWithoutWrite(passportInfo.getId());
		
		boolean cadastrNotChange;
		
		if(oldPassport.getCadastrNumber() != null){
			cadastrNotChange = oldPassport.getCadastrNumber().equals(passportInfo.getCadastrNumber());
		}else{
			if(passportInfo.getCadastrNumber() ==null){
				cadastrNotChange = true;
			}else{
				cadastrNotChange = false;
			}
		}
		
		//Если кадастровый номер не менялся и колличество ошибок больше единицы или 
		//кадастровые номера не поменялись и колличество ошибок больше нуля
		if((violationsPassportInfo.size() > 1 && cadastrNotChange) || (violationsPassportInfo.size() > 0 && !cadastrNotChange)){
			Iterator<ConstraintViolation<PassportInfo>> violationIterator = violationsPassportInfo.iterator();
			while(violationIterator.hasNext()){
				String message = violationIterator.next().getMessage();
				//Если это не ошибка копии кадастрового номера, 
				//когда он дублирует сам себя, то записываем сообщение об ошибке в список ошибок
				if(!(message.equals("CopyCadastrNumber") && cadastrNotChange )){
					listEror.add(message);
				}
			}
			passportInfo.setListEror(listEror);
			return passportInfo;
		
		}else{
			passportInfo.setListEror(null);
			Integer id = passportInfo.getId();
			Integer idOrg = passportInfo.getIdOwner();
			String region = passportInfo.getRegion();
			Integer cadastrNum = passportInfo.getCadastrNumber();
			Float area = (Float) passportInfo.getArea();
			String typeField = passportInfo.getType();
			String comment = passportInfo.getComment();
	
			Passport passport = new Passport(id, idOrg, region, cadastrNum, area, typeField, comment);
			try{
				dao.editPassport(passport);;
				return passportInfo;
			}catch(DuplicateKeyException duplicateEx){
				System.out.println("Дублирование!!!");
				return passportInfo;
			}catch(DataIntegrityViolationException ex){
				System.out.println("Внешний ключ!!!");
				return passportInfo;
			}
		}
	}
	
	/**
	 * Просмотреть пасспорт
	 * @param passportId - id паспорта
	 * @return - информация о просматриваемом паспорте ("isExist" - существует ли такой пасспорт, 
	 * 													"id" - id пасспорта, "id_organization" - id организации, 
	 * 													region - регион, cadastr_number - кадастровый номер, 
	 * 													area - площадь, type_field - тип поля, comment - комментарий)
	 */
	public PassportInfo reviewPassport(int passportId, OrganizationInfo myCompany){
		
		Owner browsing = new Organization(myCompany.getId(), myCompany.getName(), 
				myCompany.getInn(), myCompany.getAddress());
		
		PassportInfo passportInfo = null;
		
		try{
			Passport passport = dao.reviewPassport(passportId, browsing);
			passportInfo = new PassportInfo(passport.getId(), passport.getIdOwner(), passport.getRegion(),
								passport.getOwner().getName(), passport.getCadastrNumber(), 
								passport.getArea(), passport.getType(), passport.getComment());
		}catch(IndexOutOfBoundsException duplicateEx){
			//TODO:
			System.out.println("Не существует такого паспорта!!!");
		}
		
		return passportInfo;
	}
	
	/**
	 * Удалить пасспорт
	 * @param passportId -id удаляемого пасспорта
	 * @return - true - пасспорт удалён, false - возникли проблемы
	 */
	public boolean deletePassport(int passportId){
		
		try{
			dao.deletePassport(passportId);
			return true;
		}catch(IndexOutOfBoundsException duplicateEx){
			System.out.println("Не существует такого паспорта!!!");
			return false;
		}
	}
	
	/**
	 * Получение реестра паспортов
	 * @return - список с информацией о каждом пасспорте("id" - id пасспорта, "id_organization" - id организации, 
	 * 													region - регион, cadastr_number - кадастровый номер, 
	 * 													area - площадь, type_field - тип поля, comment - комментарий)
	 */
	public List<PassportInfo> reviewAllPassport() {
		List<PassportInfo> listPasportsInfo = new ArrayList<PassportInfo>();
	
		List<Passport> passports = dao.reviewAllPassports();
		for(Passport passport : passports){

			PassportInfo passportInfo = new PassportInfo(passport.getId(),passport.getIdOwner(), passport.getRegion(), 
															passport.getOwner().getName(), passport.getCadastrNumber(), 
															passport.getArea(), passport.getType(), passport.getComment());
			listPasportsInfo.add(passportInfo);
		}
		
		return listPasportsInfo;
	}
	
	/**
	 * Поиск паспортов по информации о них
	 * @param info - информация о паспортах, которые нужно найти("id" - id пасспорта, "id_organization" - id организации, region - регион, cadastr_number - кадастровый номер, area - площадь, type_field - тип поля, comment - комментарий)
	 * @return - список с информацией о каждом найденом паспорте("id" - id пасспорта, "id_organization" - id организации, region - регион, cadastr_number - кадастровый номер, area - площадь, type_field - тип поля, comment - комментарий)
	 */
	public List<PassportInfo> findPassports(PassportInfo serchingPassport) {
		List<PassportInfo> listPasportsInfo = new ArrayList<PassportInfo>();
		
		Map<String, Object> info = new HashMap<>();
		
		info.put("id", serchingPassport.getId());
		info.put("id_organization", serchingPassport.getIdOwner());
		info.put("region", serchingPassport.getRegion());
		info.put("cadastr_number", serchingPassport.getCadastrNumber());
		info.put("area", serchingPassport.getArea());
		info.put("type_field", serchingPassport.getType());
		info.put("comment", serchingPassport.getComment());
		
		List<Passport> passports = dao.findPassports(info);
		for(Passport passport : passports){
			
			PassportInfo resaltPassportInfo = new PassportInfo(passport.getId(),passport.getIdOwner(), passport.getRegion(), 
																passport.getOwner().getName(), passport.getCadastrNumber(), 
																passport.getArea(), passport.getType(), passport.getComment());
			listPasportsInfo.add(resaltPassportInfo);
		}
		
		return listPasportsInfo;
	}
	
}
