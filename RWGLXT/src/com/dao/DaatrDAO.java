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

/**
 * A data access object (DAO) providing persistence and search support for Daatr
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Daatr
 * @author MyEclipse Persistence Tools
 */

public class DaatrDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DaatrDAO.class);
	// property constants
	public static final String VALUE = "value";

	public void save(Daatr transientInstance) {
		log.debug("saving Daatr instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Daatr persistentInstance) {
		log.debug("deleting Daatr instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Daatr findById(com.domain.DaatrId id) {
		log.debug("getting Daatr instance with id: " + id);
		try {
			Daatr instance = (Daatr) getSession().get("com.domain.Daatr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Daatr instance) {
		log.debug("finding Daatr instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Daatr").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Daatr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Daatr as model where model."
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

	public List findAll() {
		log.debug("finding all Daatr instances");
		try {
			String queryString = "from Daatr";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Daatr merge(Daatr detachedInstance) {
		log.debug("merging Daatr instance");
		try {
			Daatr result = (Daatr) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Daatr instance) {
		log.debug("attaching dirty Daatr instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Daatr instance) {
		log.debug("attaching clean Daatr instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void insert(Integer daId, Integer atrId, Float value){			//tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Daatr daatr = new Daatr();
		DaatrId daatrid = new DaatrId();
		try{
			Da da = (Da)session.get(Da.class, daId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((da!=null)&&(atrribute!=null)){
				
				daatrid.setAtrribute(atrribute);
				daatrid.setDa(da);
				daatr.setId(daatrid);
				daatr.setValue(value);
				session.save(daatr);
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
	
	public void delete(Integer daId, Integer atrId){     //tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		DaatrId daatrid = new DaatrId();
		Daatr daatr = new Daatr();
		try{
			Da da = (Da)session.get(Da.class, daId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((da!=null)&&(atrribute!=null)){
				daatrid.setAtrribute(atrribute);
				daatrid.setDa(da);
				daatr =(Daatr)session.get(Daatr.class, daatrid);
				session.delete(daatr);
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
	
	public void deleteByDa(Integer daId){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "delete Daatr as daatr where daatr.id.da.daId =:daId";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("daId", daId);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			session.close();
		}
	}
	
	public List<Daatr> getDAAtr(Integer daId){   //tested
		//String a[] = new String[]{"", ""};
		String hql = "from Daatr as daatr where daatr.id.da.daId =:daId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("daId", daId);
			List <Daatr> u = query.list();
			transaction.commit();
			session.close();
			return u;
		}
		catch(Exception e){
			
			e.printStackTrace();
			transaction.rollback();
			session.close();
		}
		return null;	
	}
}