package com.jbk.hospitalhrm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Entity
public class Patient {
	@Id
	String idPatient;
	@NotNull(message = "name required")
	String namePatient;
	@Min(1)
	int agePatient;
	@NotNull(message = "illness couldn't be empty")
	String illnessDeptartmentPatient;
	@Min(50)
	int feesBudgetPatient;
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Patient(String idPatient,String namePatient,int agePatient,String illnessDeptartmentPatient,int feesBudgetPatient) {
		super();
		this.idPatient = idPatient;
		this.namePatient = namePatient;
		this.agePatient = agePatient;
		this.illnessDeptartmentPatient = illnessDeptartmentPatient;
		this.feesBudgetPatient = feesBudgetPatient;
	}

	public String getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public String getNamePatient() {
		return namePatient;
	}
	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}
	public int getAgePatient() {
		return agePatient;
	}
	public void setAgePatient(int agePatient) {
		this.agePatient = agePatient;
	}
	public String getIllnessDeptartmentPatient() {
		return illnessDeptartmentPatient;
	}
	public void setIllnessDeptartmentPatient(String illnessDeptartmentPatient) {
		this.illnessDeptartmentPatient = illnessDeptartmentPatient;
	}
	public int getFeesBudgetPatient() {
		return feesBudgetPatient;
	}
	public void setFeesBudgetPatient(int feesBudgetPatient) {
		this.feesBudgetPatient = feesBudgetPatient;
	}
	@Override
	public String toString() {
		return "Patient [idPatient=" + idPatient + ", namePatient=" + namePatient + ", agePatient=" + agePatient
				+ ", illnessDeptartmentPatient=" + illnessDeptartmentPatient + ", feesBudgetPatient="
				+ feesBudgetPatient + "]";
	}
	
	

}
