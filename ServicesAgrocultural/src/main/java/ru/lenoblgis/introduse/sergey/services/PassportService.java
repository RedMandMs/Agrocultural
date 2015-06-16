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
import ru.lenoblgis.introduse.sergey.datatransferobject.organizationinfo.OrganizationInfo;
import ru.lenoblgis.introduse.sergey.datatransferobject.passportinfo.PassportInfo;
import ru.lenoblgis.introduse.sergey.domen.owner.Owner;
import ru.lenoblgis.introduse.sergey.domen.owner.organization.Organization;
import ru.lenoblgis.introduse.sergey.domen.passport.Passport;

@Component("passportService")
public class PassportService implements Serializable {
	
	/**
	 * DAO ��� ������ � ����� ������
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * �������� ����� �������� � ��
	 * @param info - ���������� � ����������� ��������("id_organization" - id �����������, 
	 * 				region - ������, cadastr_number - ����������� �����, area - �������, 
	 * 				type_field - ��� ����, comment - �����������)
 	 * @return - id ���������� ��������� (0 - ���� �� ������� ������� ��������)
	 */
	public int createPassport(PassportInfo passportInfo){
		
		int idOrg = passportInfo.getIdOwner();
		String region = passportInfo.getRegion();
		int cadastrNum = passportInfo.getCadastrNumber();
		float area = (float) passportInfo.getArea();
		String typeField = passportInfo.getType();
		String comment = passportInfo.getComment();

		Passport passport = new Passport(idOrg, region, cadastrNum, area, typeField, comment);
		try{
			int id = dao.createPassport(passport);
			return id;
		}catch(DuplicateKeyException duplicateEx){
			System.out.println("������������!!!");
			return 0;
		}catch(DataIntegrityViolationException ex){
			System.out.println("������� ����!!!");
			return 0;
		}
	}
	
	
	/**
	 * ������������� ��������
	 * @param info - ���������� � �������������� ��������("id" - id ���������, "id_organization" - id �����������, region - ������, cadastr_number - ����������� �����, area - �������, type_field - ��� ����, comment - �����������)
 	 * @return - ���������� ������ ("success" - �������� �� ��� �������������� �������)
	 */
	public boolean editPassport(PassportInfo passportInfo){
				
		int id = passportInfo.getId();
		int idOrg = passportInfo.getIdOwner();
		String region = passportInfo.getRegion();
		int cadastrNum = passportInfo.getCadastrNumber();
		float area = (float) passportInfo.getArea();
		String typeField = passportInfo.getType();
		String comment = passportInfo.getComment();

		Passport passport = new Passport(id, idOrg, region, cadastrNum, area, typeField, comment);
		try{
			dao.editPassport(passport);;
			return true;
		}catch(DuplicateKeyException duplicateEx){
			System.out.println("������������!!!");
			return false;
		}catch(DataIntegrityViolationException ex){
			System.out.println("������� ����!!!");
			return false;
		}
	}
	
	/**
	 * ����������� ��������
	 * @param passportId - id ��������
	 * @return - ���������� � ��������������� �������� ("isExist" - ���������� �� ����� ��������, 
	 * 													"id" - id ���������, "id_organization" - id �����������, 
	 * 													region - ������, cadastr_number - ����������� �����, 
	 * 													area - �������, type_field - ��� ����, comment - �����������)
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
			System.out.println("�� ���������� ������ ��������!!!");
		}
		
		return passportInfo;
	}
	
	/**
	 * ������� ��������
	 * @param passportId -id ���������� ���������
	 * @return - true - �������� �����, false - �������� ��������
	 */
	public boolean deletePassport(int passportId){
		
		try{
			dao.deletePassport(passportId);
			return true;
		}catch(IndexOutOfBoundsException duplicateEx){
			System.out.println("�� ���������� ������ ��������!!!");
			return false;
		}
	}
	
	/**
	 * ��������� ������� ���������
	 * @return - ������ � ����������� � ������ ���������("id" - id ���������, "id_organization" - id �����������, 
	 * 													region - ������, cadastr_number - ����������� �����, 
	 * 													area - �������, type_field - ��� ����, comment - �����������)
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
	 * ����� ��������� �� ���������� � ���
	 * @param info - ���������� � ���������, ������� ����� �����("id" - id ���������, "id_organization" - id �����������, region - ������, cadastr_number - ����������� �����, area - �������, type_field - ��� ����, comment - �����������)
	 * @return - ������ � ����������� � ������ �������� ��������("id" - id ���������, "id_organization" - id �����������, region - ������, cadastr_number - ����������� �����, area - �������, type_field - ��� ����, comment - �����������)
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
