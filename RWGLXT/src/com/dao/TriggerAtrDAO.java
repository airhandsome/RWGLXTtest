package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.Atrribute;
import com.domain.Da;
import com.domain.Daatr;
import com.domain.DaatrId;
import com.domain.Dsatr;
import com.domain.EventTrigger;
import com.domain.TriggerAtr;
import com.domain.TriggerAtrId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TriggerAtr entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.domain.TriggerAtr
 * @author MyEclipse Persistence Tools
 */

public class TriggerAtrDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TriggerAtrDAO.class);
	// property constants
	public static final String VALUE = "value";
	public static final String SYMBOL = "symbol";

	public void save(TriggerAtr transientInstance) {
		log.debug("saving TriggerAtr instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TriggerAtr persistentInstance) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// yingtianjia null panduan
			if (persistentInstance != null) {
				session.delete(persistentInstance);
				transaction.commit();
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
		}
	}

	public TriggerAtr findById(com.domain.TriggerAtrId id) {
		log.debug("getting TriggerAtr instance with id: " + id);
		try {
			TriggerAtr instance = (TriggerAtr) getSession().get(
					"com.domain.TriggerAtr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TriggerAtr instance) {
		log.debug("finding TriggerAtr instance by example");
		try {
			List results = getSession().createCriteria("com.domain.TriggerAtr")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TriggerAtr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TriggerAtr as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findBySymbol(Object symbol) {
		return findByProperty(SYMBOL, symbol);
	}

	public List findAll() {
		log.debug("finding all TriggerAtr instances");
		try {
			String queryString = "from TriggerAtr";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TriggerAtr merge(TriggerAtr detachedInstance) {
		log.debug("merging TriggerAtr instance");
		try {
			TriggerAtr result = (TriggerAtr) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TriggerAtr instance) {
		log.debug("attaching dirty TriggerAtr instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TriggerAtr instance) {
		log.debug("attaching clean TriggerAtr instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void insert(Integer triggerId, Integer atrId, Float value, Integer symbol){			//tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		TriggerAtr triggeratr = new TriggerAtr();
		TriggerAtrId triggeratrid = new TriggerAtrId();
		try{
			EventTrigger eventtrigger = (EventTrigger)session.get(EventTrigger.class, triggerId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((eventtrigger!=null)&&(atrribute!=null)){
				
				triggeratrid.setAtrribute(atrribute);
				triggeratrid.setEventTrigger(eventtrigger);
				triggeratr.setId(triggeratrid);
				triggeratr.setValue(value);
				triggeratr.setSymbol(symbol);
				session.save(triggeratr);
				transaction.commit();
			}
			session.close();
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
		}
	}
	
	public void update(Integer triggerId, Integer atrId, Float value, Integer symbol){			//tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		TriggerAtr triggeratr = new TriggerAtr();
		TriggerAtrId triggeratrid = new TriggerAtrId();
		try{
			EventTrigger eventtrigger = (EventTrigger)session.get(EventTrigger.class, triggerId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((eventtrigger!=null)&&(atrribute!=null)){
				
				triggeratrid.setAtrribute(atrribute);
				triggeratrid.setEventTrigger(eventtrigger);
				triggeratr = (TriggerAtr)session.get(TriggerAtr.class, triggeratrid);
				if(triggeratr!=null){
					triggeratr.setId(triggeratrid);
					triggeratr.setValue(value);
					triggeratr.setSymbol(symbol);
					session.update(triggeratr);
					transaction.commit();
				}	
			}
			session.close();
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
		}
	}
	
	public void delete(Integer triggerId, Integer atrId){     //tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		TriggerAtr triggeratr = new TriggerAtr();
		TriggerAtrId triggeratrid = new TriggerAtrId();
		try{
			EventTrigger eventtrigger = (EventTrigger)session.get(EventTrigger.class, triggerId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((eventtrigger!=null)&&(atrribute!=null)){
				triggeratrid.setAtrribute(atrribute);
				triggeratrid.setEventTrigger(eventtrigger);
				triggeratr =(TriggerAtr)session.get(TriggerAtr.class, triggeratrid);
				session.delete(triggeratr);
				transaction.commit();
			}
			session.close();
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
		}
	}
	
	public List<TriggerAtr> getTriggerAtr(Integer triggerId){   //tested
		//String a[] = new String[]{"", ""};
		String hql = "from TriggerAtr as triggeratr where triggeratr.id.eventTrigger.triggerId ='"+triggerId+"'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
//			query.setParameter("triggerId", triggerId);
			List <TriggerAtr> u = query.list();
			transaction.commit();
			session.close();
			return u;
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();	
		}
		return null;	
	}
	
	public List<TriggerAtr> getTriggerByAtrId(Integer atrId){
		String hql = "from TriggerAtr as triggeratr where triggeratr.id.atrribute.atrId ='"+atrId+"'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
//			query.setParameter("atrId", atrId);
			List<TriggerAtr> u = query.list();
			transaction.commit();
			session.close();
			return u;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}
	
	public void insert(TriggerAtr triggeratr){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(triggeratr);
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
}