package com.neu.dao;

import org.hibernate.HibernateException;

import com.neu.exception.MessageException;
import com.neu.pojo.Message;


public class MessageDAO extends DAO{
	
	public Message createMessage(Message message)
            throws MessageException {
        try {
            begin();     
            System.out.println("inside Player DAO for apply");
            getSession().save(message);  
            
            commit();
            return message;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create appPost", e);
            throw new MessageException("Exception while creating new application post: " + e.getMessage());
        }
    }

}
