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
	
	
	/**
	 * ����������� ��-���������
	 */
	public PassportService() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    	validator = validatorFactory.getValidator();
	}
	
	/**
	 * DAO ��� ������ � ����� ������
	 */
	@Autowired
	private DAO dao;
	
	/**
	 * �������� ����� �������� � ��
	 * @param passportInfo - ����������� �������� � ��
	 * @return - ��� �� ��������, �� ��� � id � ��
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
			Passport passport = converDTOtoDomain(passportInfo);
			try{
				Integer id = dao.createPassport(passport);
				passportInfo.setId(id);
				return passportInfo;
			}catch(DuplicateKeyException duplicateEx){
				System.out.println("������������!!!");
				return passportInfo;
			}catch(DataIntegrityViolationException ex){
				System.out.println("������� ����!!!");
				return passportInfo;
			}
		}
		
	}
	
	
	/**
	 * ������������� ��������
	 * @param passportInfo - ���������� ������� � ������ �����������
	 * @return - �����, ��������� ������� (���� �� ����������, �� �� ������� ������)
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
		
		//���� ����������� ����� �� ������� � ����������� ������ ������ ������� ��� 
		//����������� ������ �� ���������� � ����������� ������ ������ ����
		if((violationsPassportInfo.size() > 1 && cadastrNotChange) || (violationsPassportInfo.size() > 0 && !cadastrNotChange)){
			Iterator<ConstraintViolation<PassportInfo>> violationIterator = violationsPassportInfo.iterator();
			while(violationIterator.hasNext()){
				String message = violationIterator.next().getMessage();
				//���� ��� �� ������ ����� ������������ ������, 
				//����� �� ��������� ��� ����, �� ���������� ��������� �� ������ � ������ ������
				if(!(message.equals("CopyCadastrNumber") && cadastrNotChange )){
					listEror.add(message);
				}
			}
			passportInfo.setListEror(listEror);
			return passportInfo;
		
		}else{
			passportInfo.setListEror(null);
			
			Passport passport = converDTOtoDomain(passportInfo);
			try{
				dao.editPassport(passport);;
				return passportInfo;
			}catch(DuplicateKeyException duplicateEx){
				System.out.println("������������!!!");
				return passportInfo;
			}catch(DataIntegrityViolationException ex){
				System.out.println("������� ����!!!");
				return passportInfo;
			}
		}
	}
	
	/**
	 * ����������� �������
	 * @param passportId - id ��������
	 * @param myCompany - ��� ������������� �������
	 * @return - ������������� �������
	 */
	public PassportInfo reviewPassport(int passportId, OrganizationInfo myCompany){
		
		Owner browsing = OwnerService.convertDTOToDomain(myCompany);
		PassportInfo passportInfo = null;
		try{
			Passport passport = dao.reviewPassport(passportId, browsing);
			passportInfo = converDomainToDTO(passport);
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
			return false;
		}
	}
	
	/**
	 * ����� ��������� �� �������� ����������
	 * @param serchingPassport - ������ �������� ���������� ���������� ��������� ��� ������
	 * @return - ������, ��������� ���������
	 */
	public List<PassportInfo> findPassports(PassportInfo serchingPassport) {
		List<PassportInfo> listPasportsInfo = new ArrayList<PassportInfo>();
		
		Passport serchinDomainPassport = converDTOtoDomain(serchingPassport);
		
		List<Passport> passports = dao.findPassports(serchinDomainPassport);
		for(Passport passport : passports){
			listPasportsInfo.add(converDomainToDTO(passport));
		}
		return listPasportsInfo;
	}
	
	private static Passport converDTOtoDomain(PassportInfo passport){
		return new Passport(passport.getId(), passport.getIdOwner(), passport.getRegion(), passport.getCadastrNumber(), passport.getArea(), passport.getType(), passport.getComment());
	}
	
	private static PassportInfo converDomainToDTO(Passport passport){
		return new PassportInfo(passport.getId(), passport.getIdOwner(), passport.getRegion(), passport.getOwner().getName(), passport.getCadastrNumber(), passport.getArea(), passport.getType(), passport.getComment());
	}
}
