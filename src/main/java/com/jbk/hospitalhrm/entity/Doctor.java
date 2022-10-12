package com.jbk.hospitalhrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Doctor {
	@Id
	int idDoctor;
	String nameDoctor;
	String designationDoctor;
	String departmentDoctor;
	int ageDoctor;
	int experienceDoctor;
	int feesDoctor;
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Doctor(int idDoctor, String nameDoctor, String designationDoctor, String departmentDoctor, int ageDoctor,
			int experienceDoctor, int feesDoctor) {
		super();
		this.idDoctor = idDoctor;
		this.nameDoctor = nameDoctor;
		this.designationDoctor = designationDoctor;
		this.departmentDoctor = departmentDoctor;
		this.ageDoctor = ageDoctor;
		this.experienceDoctor = experienceDoctor;
		this.feesDoctor = feesDoctor;
	}
	public int getIdDoctor() {
		return idDoctor;
	}
	public void setIdDoctor(int d) {
		this.idDoctor = d;
	}
	public String getNameDoctor() {
		return nameDoctor;
	}
	public void setNameDoctor(String nameDoctor) {
		this.nameDoctor = nameDoctor;
	}
	public String getDesignationDoctor() {
		return designationDoctor;
	}
	public void setDesignationDoctor(String designationDoctor) {
		this.designationDoctor = designationDoctor;
	}
	public String getDepartmentDoctor() {
		return departmentDoctor;
	}
	public void setDepartmentDoctor(String departmentDoctor) {
		this.departmentDoctor = departmentDoctor;
	}
	public int getAgeDoctor() {
		return ageDoctor;
	}
	public void setAgeDoctor(int ageDoctor) {
		this.ageDoctor = ageDoctor;
	}
	public int getExperienceDoctor() {
		return experienceDoctor;
	}
	public void setExperienceDoctor(int experienceDoctor) {
		this.experienceDoctor = experienceDoctor;
	}
	public int getFeesDoctor() {
		return feesDoctor;
	}
	public void setFeesDoctor(int feesDoctor) {
		this.feesDoctor = feesDoctor;
	}
	@Override
	public String toString() {
		return "Doctor [idDoctor=" + idDoctor + ", nameDoctor=" + nameDoctor + ", designationDoctor="
				+ designationDoctor + ", departmentDoctor=" + departmentDoctor + ", ageDoctor=" + ageDoctor
				+ ", experienceDoctor=" + experienceDoctor + ", feesDoctor=" + feesDoctor + "]";
	}

}
