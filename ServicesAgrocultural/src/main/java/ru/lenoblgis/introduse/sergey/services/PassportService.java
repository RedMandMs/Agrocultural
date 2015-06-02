package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;

@Component("passportService")
public class PassportService implements Serializable {
	
	/**
	 * DAO дл€ работы с базой данных
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * ƒобавить новый пасспорт в Ѕƒ
	 * @param info - информаци€ о добавл€емом паспорте("id_organization" - id организации, 
	 * 				region - регион, cadastr_number - кадастровый номер, area - площадь, 
	 * 				type_field - тип пол€, comment - комментарий)
 	 * @return - результаты работы ("success" - успешено ли был добавлен паспорт)
	 */
	public Map<String, String> createPassport(Map<String, String> info){
		Map<String, String> workresults = new HashMap<String, String>();
		
		int idOrg = Integer.valueOf(info.get("id_organization"));
		String region = info.get("region");
		String cadastrNum = info.get("cadastr_number");
		Integer area = Integer.valueOf(info.get("area"));
		String typeField = info.get("type_field");
		String comment = info.get("comment");
		Passport passport = new Passport(idOrg, region, cadastrNum, area, typeField, comment);
		try{
			dao.createPassport(passport);
			workresults.put("success", "true");
		}catch(DuplicateKeyException duplicateEx){
			System.out.println("ƒублирование!!!");
			workresults.put("success", "false");
		}catch(DataIntegrityViolationException ex){
			System.out.println("¬нешний ключ!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	
	/**
	 * –едактировать пасспорт
	 * @param info - информаци€ о редактируемром паспорте("id" - id пасспорта, "id_organization" - id организации, region - регион, cadastr_number - кадастровый номер, area - площадь, type_field - тип пол€, comment - комментарий)
 	 * @return - результаты работы ("success" - успешено ли был отредактирован паспорт)
	 */
	public Map<String, String> editPassport(Map<String, String> info){
		Map<String, String> workresults = new HashMap<String, String>();
				
		int id = Integer.valueOf(info.get("id"));
		int idOrg = Integer.valueOf(info.get("id_organization"));
		String region = info.get("region");
		String cadastrNum = info.get("cadastr_number");
		Integer area = Integer.valueOf(info.get("area"));
		String typeField = info.get("type_field");
		String comment = info.get("comment");
		Passport passport = new Passport(id, idOrg, region, cadastrNum, area, typeField, comment);
		try{
			dao.editPassport(passport);;
			workresults.put("success", "true");
		}catch(DuplicateKeyException duplicateEx){
			System.out.println("ƒублирование!!!");
			workresults.put("success", "false");
		}catch(DataIntegrityViolationException ex){
			System.out.println("¬нешний ключ!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	/**
	 * ѕросмотреть пасспорт
	 * @param passportId - id паспорта
	 * @return - информаци€ о просматриваемом паспорте ("isExist" - существует ли такой пасспорт, 
	 * 													"id" - id пасспорта, "id_organization" - id организации, 
	 * 													region - регион, cadastr_number - кадастровый номер, 
	 * 													area - площадь, type_field - тип пол€, comment - комментарий)
	 */
	public PassportInfo reviewPassport(int passportId){
		
		PassportInfo passportInfo = null;
		
		try{
			Passport passport = dao.reviewPassport(passportId);
			passportInfo = new PassportInfo(passport.getId(), passport.getIdOwner(), passport.getRegion(),
								passport.getOwner().getName(), passport.getCadastrNumber(), 
								passport.getArea(), passport.getType(), passport.getComment());
		}catch(IndexOutOfBoundsException duplicateEx){
			//TODO:
			System.out.println("Ќе существует такого паспорта!!!");
		}
		
		return passportInfo;
	}
	
	/**
	 * ”далить пасспорт
	 * @param passportId - id паспорта
	 * @return - результаты работы ("success" - успешено ли был удалЄн паспорт)
	 */
	public Map<String, String> deletePassport(int passportId){
		Map<String, String> workresults = new HashMap<String, String>();
		
		try{
			dao.deletePassport(passportId);
			workresults.put("success", "true");
		}catch(IndexOutOfBoundsException duplicateEx){
			System.out.println("Ќе существует такого паспорта!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	/**
	 * ѕолучение реестра паспортов
	 * @return - список с информацией о каждом пасспорте("id" - id пасспорта, "id_organization" - id организации, 
	 * 													region - регион, cadastr_number - кадастровый номер, 
	 * 													area - площадь, type_field - тип пол€, comment - комментарий)
	 */
	public List<Map<String, String>> reviewAllPassport() {
		List<Map<String,String>> listPasportsInfo = new ArrayList<Map<String,String>>();
	
		List<Passport> passports = dao.reviewAllPassports();
		for(Passport passport : passports){
			Map<String, String> passportInfo = new HashMap<String, String>();
			passportInfo.put("id", String.valueOf(passport.getId()));
			passportInfo.put("id_organization", String.valueOf(passport.getId()));
			passportInfo.put("region", String.valueOf(passport.getRegion()));
			passportInfo.put("cadastr_number", String.valueOf(passport.getCadastrNumber()));
			passportInfo.put("area", String.valueOf(passport.getArea()));
			passportInfo.put("type_field", String.valueOf(passport.getType()));
			passportInfo.put("comment", String.valueOf(passport.getComment()));
			listPasportsInfo.add(passportInfo);
		}
		
		return listPasportsInfo;
	}
	
	/**
	 * ѕоиск паспортов по информации о них
	 * @param info - информаци€ о паспортах, которые нужно найти("id" - id пасспорта, "id_organization" - id организации, region - регион, cadastr_number - кадастровый номер, area - площадь, type_field - тип пол€, comment - комментарий)
	 * @return - список с информацией о каждом найденом паспорте("id" - id пасспорта, "id_organization" - id организации, region - регион, cadastr_number - кадастровый номер, area - площадь, type_field - тип пол€, comment - комментарий)
	 */
	public List<Map<String, String>> findPassports(Map<String, String> info) {
		List<Map<String,String>> listPasportsInfo = new ArrayList<Map<String,String>>();
		
		List<Passport> passports = dao.findPassports(info);
		for(Passport passport : passports){
			Map<String, String> passportInfo = new HashMap<String, String>();
			passportInfo.put("id", String.valueOf(passport.getId()));
			passportInfo.put("id_organization", String.valueOf(passport.getId()));
			passportInfo.put("region", String.valueOf(passport.getRegion()));
			passportInfo.put("cadastr_number", String.valueOf(passport.getCadastrNumber()));
			passportInfo.put("area", String.valueOf(passport.getArea()));
			passportInfo.put("type_field", String.valueOf(passport.getType()));
			passportInfo.put("comment", String.valueOf(passport.getComment()));
			listPasportsInfo.add(passportInfo);
		}
		
		return listPasportsInfo;
	}
	
}
