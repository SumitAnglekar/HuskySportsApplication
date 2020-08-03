package com.neu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.exception.ApplyException;
import com.neu.exception.PlayerException;
import com.neu.exception.ScoutException;
import com.neu.exception.AppPostException;
import com.neu.exception.MessageException;
import com.neu.pojo.Apply;
import com.neu.pojo.AppPost;
import com.neu.pojo.Message;
import com.neu.pojo.Person;

public class PlayerDAO extends DAO{

	public PlayerDAO(){
	}
	
	public List<AppPost> list() throws PlayerException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from AppPost");
            System.out.println("Printing player list");
            List<AppPost> appPost = q.list();
            commit();
            return appPost;
        } catch (HibernateException e) {
            rollback();
            throw new PlayerException("Could not find person", e);
        }
    	
    }
	
	public Apply applyApp(Apply apply)
            throws AppPostException {
        try {
            begin();     
            getSession().save(apply);  
            
            commit();
            return apply;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create applicationPost", e);
            throw new AppPostException("Exception while creating new application post: " + e.getMessage());
        }
    }
	
public List<Apply> listAppiedApps(Person person) throws ApplyException{
    	
    	try {
            begin();
            System.out.println("list DAO");
            long personid=person.getPersonID();
            Query q = getSession().createQuery("from Apply where person_id = '" + personid + "' ");
            List<Apply> apply = q.list();
            commit();
            System.out.println("Executed");
            return apply;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find application post", e);
        }
	}
	
	public void delete(long newid)
	        throws ApplyException {
	    try {
	        begin();
	        Query q = getSession().createQuery("delete from Apply where ID = '" + newid + "' ");
	        q.executeUpdate();
	        commit();
	        } catch (HibernateException e) {
	        rollback();
	        throw new ApplyException("Could not delete application", e);
	    }
	}
	
	public Apply updateApplication(long applyid) throws ApplyException{
    	
    	try {
            begin();
            //System.out.println("list DAO for player applied");
            Query q = getSession().createQuery("from Apply where ID = '" + applyid + "' ");
            //System.out.println("hello inside player applied list DAO below query");
            Apply apply = (Apply) q.uniqueResult();
            commit();
            System.out.println("Executed here");
            return apply;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find applied player", e);
        }

    }
	
	public Apply updateExistingApplication(Apply apply, long newid)
            throws ApplyException {
        try {
            begin();     
            String resume=apply.getResume();
            Query q = getSession().createQuery("update Apply set resume ='" + resume + "' where ID = '" + newid + "' ");
            
            q.executeUpdate();
            
            commit();
            return apply;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create applicationPost", e);
            throw new ApplyException("Exception while creating new application post: " + e.getMessage());
        }
    }

	public List<Person> listEmployer() throws PlayerException{
    	
    	try {
            begin();
            /*Query q = getSession().createQuery("from Person where role = :role");
            //System.out.println("hello");
            q.setString("role", "employer");
            List<Person> person = q.list();
            
            commit();
            return person;*/
            
            Criteria cr=getSession().createCriteria(Person.class);
            cr.add(Restrictions.eq("role","scout"));
            List<Person> person = cr.list();
            commit();
            return person;
        } catch (HibernateException e) {
            rollback();
            throw new PlayerException("Could not find person", e);
        }
    	
    }
	
public List<Message> listMessage(Person person) throws MessageException{
    	
    	try {
            begin();
            System.out.println("list DAO");
            long messageTo=person.getPersonID();
            Query q = getSession().createQuery("from Message where messageTo = '" + messageTo + "' ");
            List<Message> message = q.list();
            System.out.println("Size" + message.size());
            
            commit();
            System.out.println("Executed");
            return message;
        } catch (HibernateException e) {
            rollback();
            throw new MessageException("Could not find application post", e);
        }
	}
	
}
