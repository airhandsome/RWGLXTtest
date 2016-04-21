package com.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.Ds;
import com.domain.EventTrigger;
import com.domain.TriggerAtrId;

/**
 * A data access object (DAO) providing persistence and search support for
 * EventTrigger entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.domain.EventTrigger
 * @author MyEclipse Persistence Tools
 */

public class EventTriggerDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EventTriggerDAO.class);
	// property constants
	public static final String LOGIC = "logic";
	public static final String CODE = "code";

	public void save(EventTrigger transientInstance) {
		log.debug("saving EventTrigger instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EventTrigger persistentInstance) {
		log.debug("deleting EventTrigger instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EventTrigger findById(java.lang.Integer id) {
		log.debug("getting EventTrigger instance with id: " + id);
		try {
			EventTrigger instance = (EventTrigger) getSession().get(
					"com.domain.EventTrigger", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EventTrigger instance) {
		log.debug("finding EventTrigger instance by example");
		try {
			List results = getSession().createCriteria(
					"com.domain.EventTrigger").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EventTrigger instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EventTrigger as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLogic(Object logic) {
		return findByProperty(LOGIC, logic);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findAll() {
		log.debug("finding all EventTrigger instances");
		try {
			String queryString = "from EventTrigger";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EventTrigger merge(EventTrigger detachedInstance) {
		log.debug("merging EventTrigger instance");
		try {
			EventTrigger result = (EventTrigger) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EventTrigger instance) {
		log.debug("attaching dirty EventTrigger instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EventTrigger instance) {
		log.debug("attaching clean EventTrigger instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public EventTrigger insert(String logic, String code){
		EventTrigger eventtrigger = new EventTrigger();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		int flag = 0;
		try{
			eventtrigger.setLogic(logic);
			eventtrigger.setCode(code);
			session.save(eventtrigger);
			transaction.commit();
			session.close();
			return eventtrigger;
		}
		catch(Exception e){
			flag = 0;
			session.close();
			e.printStackTrace();
            transaction.rollback();
            return null;
		}
	}
	
		public void insert(Integer eventid,String code, String logic){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		EventTrigger eventtrigger = new EventTrigger();
		TriggerAtrId triggeratrid = new TriggerAtrId();
		try{
			eventtrigger = (EventTrigger)session.get(EventTrigger.class, eventid);
			if((logic!=null)&&(code!=null)&&(eventtrigger!=null)){
			eventtrigger.setLogic(logic);
			eventtrigger.setCode(code);
			session.save(eventtrigger);
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
	
	public void update(Integer triggerId, String logic, String code){
		EventTrigger eventtrigger = new EventTrigger();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			eventtrigger  = (EventTrigger)session.get(EventTrigger.class, triggerId);
			if(eventtrigger!=null){
				eventtrigger.setLogic(logic);
				eventtrigger.setCode(code);
				session.save(eventtrigger);
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
	
	public void delete(Integer triggerId){
		EventTrigger eventtrigger = new EventTrigger();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			eventtrigger  = (EventTrigger)session.get(EventTrigger.class, triggerId);
			if(eventtrigger!=null){
				session.delete(eventtrigger);
				transaction.commit();
			}
			session.close();
		}
		catch(Exception e){
			System.out.println("dddddd");
			session.close();
			e.printStackTrace();
            transaction.rollback();
		}
	}
	
	public EventTrigger createTrigger(String logic, String code){
		EventTrigger eventtrigger = new EventTrigger();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			eventtrigger.setLogic(logic);
			eventtrigger.setCode(code);
			session.save(eventtrigger);
			transaction.commit();
			session.close();
			return eventtrigger;
		}catch(Exception e){
			e.printStackTrace();
		    transaction.rollback();
		    session.close();
		    return null;
		}
	}
}