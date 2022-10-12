package com.jbk.hospitalhrm.dao;

import java.util.List;

import com.jbk.hospitalhrm.entity.Doctor;
import com.jbk.hospitalhrm.entity.Patient;

public interface HospitalDao {
	public boolean savePatient(Patient patient);
	public boolean saveDoctor(Doctor doctor);
	public int saveDoctor(List<Doctor> list);
	public List<Doctor> getListOfDoctors();
    public List<Patient> listOfPatients();
    public boolean deleteDoctorDetails(int idDoctor);
    public boolean updateDoctorDetails(Doctor doctor);
    public boolean deletePatientDetails(int idPatient);
    public boolean updatePatientDetails(Patient patient);
    
}
