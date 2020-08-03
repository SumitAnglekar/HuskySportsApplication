package com.neu.Dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.Exception.HuskyException;
import com.neu.Pojo.Husky;

public class HuskyDao extends DAO {

	public void saveCustomer(Husky customer) throws HuskyException {
		try {
			begin();
			getSession().save(customer);
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new HuskyException("Exception while creating user: " + e.getMessage());
		}
	}

	public Husky customerLogin(Husky customer) {
		String hql = "from husky as h where h.emailAddress =:email and h.password =:pwd ";
		
		try {
			begin();
			Query query = getSession().createQuery(hql);
			query.setParameter("email", customer.getEmailAddress());
			query.setParameter("pwd", customer.getPassword());
			customer = (Husky)query.list();
			
			getSession().getTransaction().commit();
			close();
		}
		catch(Exception e) {
			rollback();
			close();
		}
		return customer;
	}

	
	
}
