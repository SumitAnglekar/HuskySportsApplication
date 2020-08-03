package com.neu.Dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.Pojo.PlayerApplicants;


public class PlayerApplicantsDAO extends DAO {
	public PlayerApplicants create(PlayerApplicants applicant) {
		try {
			begin();
			getSession().save(applicant);
			commit();
			
		} catch (HibernateException e) {
			rollback();
			//throw new UserException("Could not get user " + userId, e);
		}
		return applicant;
	}
}
