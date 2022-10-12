package com.jbk.hospitalhrm.sort;

import java.util.Comparator;

import com.jbk.hospitalhrm.entity.Doctor;

public class FessComparator implements Comparator<Doctor>{

	@Override
	public int compare(Doctor d1, Doctor d2) {
		int i=(d1.getFeesDoctor())-(d2.getFeesDoctor());
		return i ;
	}

}
