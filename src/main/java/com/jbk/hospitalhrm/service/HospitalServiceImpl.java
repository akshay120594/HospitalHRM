package com.jbk.hospitalhrm.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.jbk.hospitalhrm.dao.HospitalDao;
import com.jbk.hospitalhrm.entity.Patient;
import com.jbk.hospitalhrm.sort.ExperienceComparator;
import com.jbk.hospitalhrm.sort.FessComparator;
import com.jbk.hospitalhrm.entity.Doctor;

@Service
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	private HospitalDao dao;
	
	String excludedRows = "";
	int totalRecordCount = 0;


	@Override
	public boolean savePatient(Patient patient) {
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		patient.setIdPatient(id);
		boolean isAdded=dao.savePatient(patient);
		return isAdded;
		
	}

	public List<Doctor> readExcel(String filepath){
		Workbook workbook=null;
		Doctor doctor=null;
		List<Doctor> list=new ArrayList<>();
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(new File(filepath));
			workbook=new XSSFWorkbook(fis);
			
			Sheet sheet = workbook.getSheetAt(0);
     		totalRecordCount = sheet.getLastRowNum();
			Iterator<Row> rowIterator=sheet.rowIterator();
			int rowCount=0;
			
			while(rowIterator.hasNext()) {
				
				Row row = rowIterator.next();
				
					rowCount=rowCount+1;
					
				
				
				doctor=new Doctor();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					
					
					
					Cell cell = cellIterator.next();
					int column = cell.getColumnIndex();
					switch(column) {
					case 0:{
						doctor.setIdDoctor((int)cell.getNumericCellValue());
						break;
					}
					
					case 1:{
						doctor.setNameDoctor(cell.getStringCellValue());
						break;
					}
					case 2:{
						doctor.setDesignationDoctor(cell.getStringCellValue());
						break;
					}
					case 3:{
						doctor.setDepartmentDoctor(cell.getStringCellValue());
						break;
					}
					case 4:{
						doctor.setAgeDoctor((int)cell.getNumericCellValue());
						break;
					}
					case 5:{
						doctor.setExperienceDoctor((int)cell.getNumericCellValue());
						break;
					}
					case 6:{
						doctor.setFeesDoctor((int)cell.getNumericCellValue());
						break;
					}
					
					
					}
					
					
				}
				list.add(doctor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (workbook != null)
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
		
	}

	@Override
	public Map<String, String> uploadDoctorExcelSheet(CommonsMultipartFile file, HttpSession session) {
		String realPath = session.getServletContext().getRealPath("/");
		String originalFilename = file.getOriginalFilename();
		Map<String, String> map=new HashMap<>();
		int uploadedCount=0;
		FileOutputStream fos=null;
		byte[] data = file.getBytes();
		try {
			System.out.println(realPath);
			fos=new FileOutputStream(new File(realPath+File.separator+originalFilename));
			fos.write(data);
			List<Doctor> list = readExcel(realPath+File.separator+originalFilename);
			uploadedCount = dao.saveDoctor(list);

			map.put("Total Record In Sheet", String.valueOf(totalRecordCount));
			map.put("Uploaded Record In DB", String.valueOf(uploadedCount));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public List<Doctor> getListOfDoctors() {
	
		return dao.getListOfDoctors();
	}

	@Override
	public List<Doctor> getPatientsDoctorSuggestion(String idPatient) {
		List<Doctor> listOfDoctors = getListOfDoctors();
		Patient patient = null;
		List<Doctor> d2=null;
		try {
			d2=new ArrayList<>();
			patient=loadPatientDetails(idPatient);
			for (Doctor doctor : listOfDoctors) {
				if(patient.getIllnessDeptartmentPatient().equalsIgnoreCase(doctor.getDepartmentDoctor())) {
					if(patient.getFeesBudgetPatient()>=doctor.getFeesDoctor()){
						d2.add(doctor);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d2;
	}

	@Override
	public List<Doctor> sortByFees(String department) {
		List<Doctor> list = listByDepartment(department);
		if(list.size()>1)
	   Collections.sort(list,new FessComparator());
		return list;
	}

	@Override
	public List<Doctor> sortByExperience(String department) {
		List<Doctor> list = listByDepartment(department);
		if(list.size()>1)
	   Collections.sort(list,new ExperienceComparator());
		return list;
	}

	@Override
	public List<Doctor> listByDepartment(String department) {
		List<Doctor> listOfDoctors = getListOfDoctors();
		
		List<Doctor> d2=new ArrayList<>();
		for (Doctor doctors : listOfDoctors) {
			if(doctors.getDepartmentDoctor().equalsIgnoreCase(department)) {
				d2.add(doctors);
			}
			
		}
		return d2;
	}

	@Override
	public List<Patient> listOfPatients() {
		return dao.listOfPatients();
	}

	@Override
	public Patient loadPatientDetails(String idPatient) {
		Patient p=null;
		List<Patient> list=null;
		try {
			list = listOfPatients();
			for (Patient patient : list) {
				if(patient.getIdPatient().equalsIgnoreCase(idPatient)) {
					p=patient;
					
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Doctor loadDoctorDetails(int idDoctor) {
		Doctor d=null;
		List<Doctor> listOfDoctors=null;
		try {
			listOfDoctors = getListOfDoctors();
			for (Doctor doctor : listOfDoctors) {
				if(doctor.getIdDoctor()==idDoctor) {
					d=doctor;
					
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return d;
	}

	@Override
	public boolean deleteDoctorDetails(int idDoctor) {
		return dao.deleteDoctorDetails(idDoctor);
	}

	@Override
	public boolean updateDoctorDetails(Doctor doctor) {
		return dao.updateDoctorDetails(doctor);
	}

	@Override
	public boolean deletePatientDetails(int idPatient) {
		// TODO Auto-generated method stub
		return dao.deletePatientDetails(idPatient);
	}

	@Override
	public boolean updatePatientDetails(Patient patient) {
		// TODO Auto-generated method stub
		return dao.updatePatientDetails(patient);
	}

	@Override
	public List<Doctor> nurseList() {
        List<Doctor> listOfDoctors = getListOfDoctors();
		
		List<Doctor> d2=new ArrayList<>();
		for (Doctor doctors : listOfDoctors) {
			if(doctors.getDesignationDoctor().equalsIgnoreCase("nurse")) {
				d2.add(doctors);
			}
			
		}
		return d2;
	}

	@Override
	public List<Doctor> compounderList() {
		 List<Doctor> listOfDoctors = getListOfDoctors();
			
			List<Doctor> d2=new ArrayList<>();
			for (Doctor doctors : listOfDoctors) {
				if(doctors.getDesignationDoctor().equalsIgnoreCase("compounder")) {
					d2.add(doctors);
				}
				
			}
			return d2;
	}

}
