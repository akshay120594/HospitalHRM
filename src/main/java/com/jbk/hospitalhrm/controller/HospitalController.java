package com.jbk.hospitalhrm.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.jbk.hospitalhrm.entity.Doctor;
import com.jbk.hospitalhrm.entity.Patient;
import com.jbk.hospitalhrm.exceptionhandler.DoctorNotFoundException;
import com.jbk.hospitalhrm.exceptionhandler.NotEnoughContent;
import com.jbk.hospitalhrm.exceptionhandler.PatientNotFoundException;
import com.jbk.hospitalhrm.service.HospitalService;






@RestController
public class HospitalController {
	@Autowired
	private HospitalService service;

	@PostMapping(value = "/savepatient")
	public ResponseEntity<Boolean> savePatient(@RequestBody Patient patient){
		boolean isAdded=service.savePatient(patient);
		System.out.println(patient);
		if(isAdded)
			return new ResponseEntity<Boolean>(isAdded,HttpStatus.OK);
		else
			throw new PatientNotFoundException("patient invalid");
		}
	
	@PostMapping(value = "/uploadexcel")
	public ResponseEntity<Map<String,String>> uploadDoctorExcelSheet(@RequestParam CommonsMultipartFile file,HttpSession session){	
		Map<String,String> map=service.uploadDoctorExcelSheet(file,session);
		if(map!=null)
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("doctor invalid");
		}
	
	@GetMapping(value = "/getlistofdoctor")
	public ResponseEntity<List<Doctor>> getListOfDoctors(){
		List<Doctor> list=service.getListOfDoctors();
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("doctors not found");
		}
	
	@GetMapping(value = "/getlistofpatient")
	public ResponseEntity<List<Patient>> getListOfPatients(){
		List<Patient> list=service.listOfPatients();
		if(list!=null)
			return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
		else
			throw new PatientNotFoundException("patients not found");
		}
	
	@GetMapping(value = "/listbydepartment/{department}")
	public ResponseEntity<List<Doctor>> listByDepartment(@PathVariable String department){
		List<Doctor> list=service.listByDepartment(department);
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("doctors not found");
		}
	
	@GetMapping(value = "/patientspreferencedoctor")
	public ResponseEntity<List<Doctor>> patientPreferenceDoctor(@RequestParam String idPatient){
		List<Doctor> list=service.getPatientsDoctorSuggestion(idPatient);
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("doctors not found");
	}
	
	@GetMapping(value = "/loadpatientdetail")
	public ResponseEntity<Patient> loadPatientDetails(@RequestParam String idPatient) {
		Patient patient=service.loadPatientDetails(idPatient);
		if(patient!=null)
			return new ResponseEntity<Patient>(patient,HttpStatus.OK);
		else
			throw new PatientNotFoundException("patients not found");
		}
	
	@GetMapping(value = "/loaddoctordetail")
	public ResponseEntity<Doctor> loadDoctorDetails(@RequestParam int idDoctor) {
		Doctor doctor=service.loadDoctorDetails(idDoctor);
		if(doctor!=null)
			return new ResponseEntity<Doctor>(doctor,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("doctor not found");
		}
	
	@GetMapping(value ="sortbyfee" )
	public ResponseEntity<List<Doctor>> sortByFee(@RequestParam String department) {
		List<Doctor> list=service.sortByFees(department);
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new NotEnoughContent("not enough content for sorting");
	}
	
	@GetMapping(value ="sortbyExperience" )
	public ResponseEntity<List<Doctor>> sortByExperience(@RequestParam String department) {
		List<Doctor> list=service.sortByExperience(department);
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new NotEnoughContent("not enough content for sorting");
	}
	
	@DeleteMapping(value = "deletedoctordetails")
	public ResponseEntity<Boolean> deleteDoctorDetails(@RequestParam int idDoctor){
		boolean isDeleted=service.deleteDoctorDetails(idDoctor);
		if(isDeleted)
			return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("Doctor Not Found For Update with Id :"+idDoctor);
		}
	
	@PutMapping(value = "updatedoctordetails")
	public ResponseEntity<Boolean> updateDoctorDetails(@RequestBody Doctor doctor){
		boolean isUpdated=service.updateDoctorDetails(doctor);
		if(isUpdated)
			return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("Doctor Not Found For Update with Id :"+doctor.getIdDoctor());
	}
	 
	@GetMapping(value = "/getlistofnurse")
	public ResponseEntity<List<Doctor>> getListOfNurse(){
		List<Doctor> list=service.nurseList();
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("nurse not found");
		}
	
	@GetMapping(value = "/getlistofcompounder")
    public ResponseEntity<List<Doctor>> getListOfCompounder(){
		List<Doctor> list=service.compounderList();
		if(list!=null)
			return new ResponseEntity<List<Doctor>>(list,HttpStatus.OK);
		else
			throw new DoctorNotFoundException("compounder not found");
		}
	
	@DeleteMapping(value = "deletepatientdetails")    
	public ResponseEntity<Boolean> deletePatientDetails(@RequestParam int idPatient){
			boolean isDeleted=service.deleteDoctorDetails(idPatient);
			if(isDeleted)
				return new ResponseEntity<Boolean>(isDeleted,HttpStatus.OK);
			else
				throw new PatientNotFoundException("Patient not found");
		}
	   
	@PutMapping(value = "updatepatientdetails")
    public ResponseEntity<Boolean> updatePatientDetails(@RequestBody Patient patient){
			boolean isUpdated=service.updatePatientDetails(patient);
			if(isUpdated)
				return new ResponseEntity<Boolean>(isUpdated,HttpStatus.OK);
			else
				throw new PatientNotFoundException("Patient not found");
		}
	
	

}
