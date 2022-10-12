package com.jbk.hospitalhrm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.hospitalhrm.entity.Doctor;
import com.jbk.hospitalhrm.entity.Patient;

@Repository
public class HospitalDaoImpl implements HospitalDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean savePatient(Patient patient) {
		Session session=null;
		Transaction transaction=null;
		boolean isAdded=false;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(patient);
		    transaction.commit();
		    isAdded=true;
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return isAdded;
	}

	@Override
	public int saveDoctor(List<Doctor> list) {
		Session session=null;
		Transaction transaction=null;
		int count=0;
		try {
			for (Doctor doctor : list) {
				session=sessionFactory.openSession();
				transaction=session.beginTransaction();
				session.save(doctor);
				transaction.commit();
				count=count+1;
				}
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return count;
	}

	@Override
	public List<Doctor> getListOfDoctors() {
		Session session=null;
		List<Doctor> list=null;
		try {
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Doctor.class);
			list=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<Patient> listOfPatients(){
		Session session=null;
		List<Patient> list=null;
		try {
			session=sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Patient.class);
			list=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
		}

	@Override
	public boolean deleteDoctorDetails(int idDoctor) {
		Session session=null;
		Transaction transaction=null;
		boolean isDeleted=false;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			Doctor doctor = session.load(Doctor.class,idDoctor);
			if(doctor!=null) {
				session.delete(doctor);
				isDeleted=true;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
			
			
		}
		return isDeleted;
	}

	@Override
	public boolean updateDoctorDetails(Doctor doctor) {
		Session session=null;
		Transaction transaction=null;
		boolean isUpdated=false;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			
			
			Doctor doc = session.get(Doctor.class,doctor.getIdDoctor());
			if(doc!=null) {
				session.evict(doc);
				session.update(doctor);
				isUpdated=true;
				
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();}
		}
		return isUpdated;
	}

	@Override
	public boolean deletePatientDetails(int idPatient) {
		Session session=null;
		Transaction transaction=null;
		boolean isDeleted=false;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			Patient patient = session.load(Patient.class,idPatient);
			if(patient!=null) {
				session.delete(patient);
				isDeleted=true;
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
			
			
		}
		return isDeleted;
	}

	@Override
	public boolean updatePatientDetails(Patient patient) {
		Session session=null;
		Transaction transaction=null;
		boolean isUpdated=false;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			
			
			Patient pat = session.load(Patient.class,patient.getIdPatient());
			if(pat!=null) {
				session.evict(pat);
				session.update(patient);
				isUpdated=true;
				
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();}
		}
		return isUpdated;
	}

	@Override
	public boolean saveDoctor(Doctor doctor) {
		Session session=null;
		Transaction transaction=null;
		boolean isAdded=false;
		try {
			session=sessionFactory.openSession();
			transaction=session.beginTransaction();
			session.save(doctor);
		    transaction.commit();
		    isAdded=true;
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return isAdded;
	}

	


	

}
