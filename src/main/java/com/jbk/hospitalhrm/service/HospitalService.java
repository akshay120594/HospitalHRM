package com.jbk.hospitalhrm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.hospitalhrm.entity.Doctor;
import com.jbk.hospitalhrm.entity.Patient;

public interface HospitalService {
	public boolean savePatient(Patient patient);
	public Map<String,String> uploadDoctorExcelSheet(CommonsMultipartFile file,HttpSession session);
    public List<Doctor> getListOfDoctors();
    public List<Doctor> getPatientsDoctorSuggestion(String idPatient);
    public List<Doctor> sortByFees(String department);
    public List<Doctor> sortByExperience(String department);
    public List<Doctor> listByDepartment(String department);
    public List<Patient> listOfPatients();
    public Patient loadPatientDetails(String idPatient);
    public Doctor loadDoctorDetails(int idDoctor);
    public boolean deleteDoctorDetails(int idDoctor);
    public boolean updateDoctorDetails(Doctor doctor);
    public boolean deletePatientDetails(int idPatient);
    public boolean updatePatientDetails(Patient patient);
    public List<Doctor> nurseList();
    public List<Doctor> compounderList();
    
}
