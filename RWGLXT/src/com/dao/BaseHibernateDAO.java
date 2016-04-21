package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.domain.*;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public static SessionFactory sessfac = new Configuration().configure().buildSessionFactory();
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}