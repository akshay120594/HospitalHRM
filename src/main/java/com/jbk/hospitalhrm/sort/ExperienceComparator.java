package com.jbk.hospitalhrm.sort;

import java.util.Comparator;

import com.jbk.hospitalhrm.entity.Doctor;

public class ExperienceComparator implements Comparator<Doctor>{

	@Override
	public int compare(Doctor d1, Doctor d2) {
		int i=(d2.getExperienceDoctor())-(d1.getExperienceDoctor());
		return i;
	}

}
