package com.neu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.pojo.*;
import com.neu.exception.ApplyException;
import com.neu.exception.ScoutException;
import com.neu.exception.AppPostException;
import com.neu.exception.MessageException;
import com.neu.exception.UserException;
import org.hibernate.criterion.Restrictions;

public class ScoutDAO extends DAO{
	
	public ScoutDAO(){
	}
	
	public AppPost create(AppPost appPost)
            throws AppPostException {
        try {
            begin();     
             getSession().save(appPost);     
            commit();
            return appPost;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create appPost", e);
            throw new AppPostException("Exception while creating new application post: " + e.getMessage());
        }
    }
    
    public List<Person> list() throws ScoutException{
    	
    	try {
            begin();
//            Query q = getSession().createQuery("from Person where role = :role");
//            //System.out.println("hello");
//            q.setString("role", "player");
//            List<Person> person = q.list();
            Criteria cr=getSession().createCriteria(Person.class);
            cr.add(Restrictions.eq("role","player"));
            List<Person> person = cr.list();
            commit();
            return person;
        } catch (HibernateException e) {
            rollback();
            throw new ScoutException("Could not find person", e);
        }
    	
    }
    
    public List<AppPost> listAppPost(Person person) throws AppPostException{
    	
    	try {
            begin();
            //System.out.println("list DAO");
            long personid=person.getPersonID();
            Query q = getSession().createQuery("from AppPost where person = '" + personid + "' ");
            //System.out.println("hello inside app post list DAO below query");
            List<AppPost> appPost = q.list();
            commit();
            //System.out.println("Executed");
            return appPost;
        } catch (HibernateException e) {
            rollback();
            throw new AppPostException("Could not find app post", e);
        }

    }
    
    public List<Apply> listEmployeeApplied(long appid) throws ApplyException{
    	
    	try {
            begin();
            //System.out.println("list DAO for player applied");
            Query q = getSession().createQuery("from Apply where appPostApply = '" + appid + "' ");
            //System.out.println("hello inside employee applied list DAO below query");
            List<Apply> apply = q.list();
            commit();
            //System.out.println("Executed");
            return apply;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find applied player", e);
        }

    }
    
    
    public AppPost updatePosted(long appid) throws ApplyException{
    	
    	try {
            begin();
            //System.out.println("list DAO for player applied");
            Query q = getSession().createQuery("from AppPost where ID = '" + appid + "' ");
            //System.out.println("hello inside player applied list DAO below query");
            AppPost appPost = (AppPost) q.uniqueResult();
            commit();
            System.out.println("Executed here");
            return appPost;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find applied player", e);
        }

    }
    
    public void delete(long newid)
            throws AppPostException {
        try {
            begin();
            Query q = getSession().createQuery("delete from AppPost where ID = '" + newid + "' ");
            System.out.println("Inside player DAo delete method");
            q.executeUpdate();
            commit();
            } catch (HibernateException e) {
            rollback();
            throw new AppPostException("Could not delete apppost", e);
        }
    }
    
    public AppPost updateExistingAppPost(AppPost appPost, long newid)
            throws AppPostException {
        try {
            begin();     
            System.out.println("inside Scout DAO to update app post");
            String appTitle=appPost.getAppTitle();
            String teamName=appPost.getTeamName();
            String decription=appPost.getDescription();
            String playingPosition=appPost.getPlayingPosition();
            String appID=appPost.getAppID();
            String campus=appPost.getCampus();
            String noOfPositions=appPost.getNoOfPosition();
            String type=appPost.getType();
            Query q = getSession().createQuery("update AppPost set appTitle ='" + appTitle + "', teamName ='" + teamName + "', description ='" + decription + "', playingPosition ='" + playingPosition + "', appID ='" + appID + "',campus ='" + campus + "', noOfPosition ='" + noOfPositions + "', type ='" + type + "'  where ID = '" + newid + "' ");
            
            q.executeUpdate();
            
            //getSession().save(AppPost);        
            commit();
            return appPost;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create AppPost", e);
            throw new AppPostException("Exception while creating new app post: " + e.getMessage());
        }
    }
    
    
public List<Message> listMessage(Person person) throws MessageException{
    	
    	try {
            begin();
            System.out.println("list DAO");
            long messageTo=person.getPersonID();
            Query q = getSession().createQuery("from Message where messageTo = '" + messageTo + "' ");
            System.out.println("hello inside apppost list DAO below query");
            List<Message>Message = q.list();
            commit();
            System.out.println("Executed");
            return Message;
        } catch (HibernateException e) {
            rollback();
            throw new MessageException("Could not find app post", e);
        }
	}
}
