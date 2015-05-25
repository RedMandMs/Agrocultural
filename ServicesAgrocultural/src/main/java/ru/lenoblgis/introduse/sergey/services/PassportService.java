package ru.lenoblgis.introduse.sergey.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import ru.lenoblgis.introduse.sergey.data.dao.DAO;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;

@Component("passportService")
public class PassportService implements Serializable {
	
	/**
	 * DAO ��� ������ � ����� ������
	 */
	private DAO dao = new DAO();
	
	/**
	 * �������� ����� �������� � ��
	 * @param info - ���������� � ����������� ��������("id_organization" - id �����������, 
	 * 				region - ������, cadastr_number - ����������� �����, area - �������, 
	 * 				type_field - ��� ����, comment - �����������)
 	 * @return - ���������� ������ ("success" - �������� �� ��� �������� �������)
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
			System.out.println("������������!!!");
			workresults.put("success", "false");
		}catch(DataIntegrityViolationException ex){
			System.out.println("������� ����!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	
	/**
	 * ������������� ��������
	 * @param info - ���������� � �������������� ��������("id" - id ���������, "id_organization" - id �����������, region - ������, cadastr_number - ����������� �����, area - �������, type_field - ��� ����, comment - �����������)
 	 * @return - ���������� ������ ("success" - �������� �� ��� �������������� �������)
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
			System.out.println("������������!!!");
			workresults.put("success", "false");
		}catch(DataIntegrityViolationException ex){
			System.out.println("������� ����!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	/**
	 * ����������� ��������
	 * @param passportId - id ��������
	 * @return - ���������� � ��������������� �������� ("isExist" - ���������� �� ����� ��������, 
	 * 													"id" - id ���������, "id_organization" - id �����������, 
	 * 													region - ������, cadastr_number - ����������� �����, 
	 * 													area - �������, type_field - ��� ����, comment - �����������)
	 */
	public Map<String, String> reviewPassport(int passportId){
		Map<String, String> passportInfo = new HashMap<String, String>();
		
		try{
			Passport passport = dao.reviewPassport(passportId);
			passportInfo.put("isExist", "true");
			passportInfo.put("id", String.valueOf(passport.getId()));
			passportInfo.put("id_organization", String.valueOf(passport.getId()));
			passportInfo.put("region", String.valueOf(passport.getRegion()));
			passportInfo.put("cadastr_number", String.valueOf(passport.getCadastrNumber()));
			passportInfo.put("area", String.valueOf(passport.getArea()));
			passportInfo.put("type_field", String.valueOf(passport.getType()));
			passportInfo.put("comment", String.valueOf(passport.getComment()));
		}catch(IndexOutOfBoundsException duplicateEx){
			System.out.println("�� ���������� ������ ��������!!!");
			passportInfo.put("isExist", "false");
		}
		
		return passportInfo;
	}
	
	/**
	 * ������� ��������
	 * @param passportId - id ��������
	 * @return - ���������� ������ ("success" - �������� �� ��� ����� �������)
	 */
	public Map<String, String> deletePassport(int passportId){
		Map<String, String> workresults = new HashMap<String, String>();
		
		try{
			dao.deletePassport(passportId);
			workresults.put("success", "true");
		}catch(IndexOutOfBoundsException duplicateEx){
			System.out.println("�� ���������� ������ ��������!!!");
			workresults.put("success", "false");
		}
		
		return workresults;
	}
	
	/**
	 * ��������� ������� ���������
	 * @return - ������ � ����������� � ������ ���������("id" - id ���������, "id_organization" - id �����������, 
	 * 													region - ������, cadastr_number - ����������� �����, 
	 * 													area - �������, type_field - ��� ����, comment - �����������)
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
	 * ����� ��������� �� ���������� � ���
	 * @param info - ���������� � ���������, ������� ����� �����("id" - id ���������, "id_organization" - id �����������, region - ������, cadastr_number - ����������� �����, area - �������, type_field - ��� ����, comment - �����������)
	 * @return - ������ � ����������� � ������ �������� ��������("id" - id ���������, "id_organization" - id �����������, region - ������, cadastr_number - ����������� �����, area - �������, type_field - ��� ����, comment - �����������)
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
