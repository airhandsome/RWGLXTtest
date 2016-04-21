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
import com.domain.Ds;
import com.domain.Dsatr;
import com.domain.DsatrId;

/**
 * A data access object (DAO) providing persistence and search support for Dsatr
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Dsatr
 * @author MyEclipse Persistence Tools
 */

public class DsatrDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DsatrDAO.class);
	// property constants
	public static final String VALUE = "value";
	public static final String SYMBOL = "symbol";

	public void save(Dsatr transientInstance) {
		log.debug("saving Dsatr instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Dsatr persistentInstance) {
		log.debug("deleting Dsatr instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dsatr findById(com.domain.DsatrId id) {
		log.debug("getting Dsatr instance with id: " + id);
		try {
			Dsatr instance = (Dsatr) getSession().get("com.domain.Dsatr", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Dsatr instance) {
		log.debug("finding Dsatr instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Dsatr").add(
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
		log.debug("finding Dsatr instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dsatr as model where model."
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
		log.debug("finding all Dsatr instances");
		try {
			String queryString = "from Dsatr";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Dsatr merge(Dsatr detachedInstance) {
		log.debug("merging Dsatr instance");
		try {
			Dsatr result = (Dsatr) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dsatr instance) {
		log.debug("attaching dirty Dsatr instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dsatr instance) {
		log.debug("attaching clean Dsatr instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void insert(Integer dsId, Integer atrId, Integer symbol, Float value){			//tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Dsatr dsatr = new Dsatr();
		DsatrId dsatrid = new DsatrId();
		try{
			Ds ds = (Ds)session.get(Ds.class, dsId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((ds!=null)&&(atrribute!=null)){
				
				dsatrid.setAtrribute(atrribute);
				dsatrid.setDs(ds);
				dsatr.setId(dsatrid);
				dsatr.setValue(value);
				dsatr.setSymbol(symbol);
				session.save(dsatr);
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
	
	public void update(Integer dsId, Integer atrId, Integer symbol, Float value){			//tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Dsatr dsatr = new Dsatr();
		DsatrId dsatrid = new DsatrId();
		try{
			Ds ds = (Ds)session.get(Ds.class, dsId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((ds!=null)&&(atrribute!=null)){
				
				dsatrid.setAtrribute(atrribute);
				dsatrid.setDs(ds);
				dsatr = (Dsatr)session.get(Dsatr.class, dsatrid);
				if(dsatr!=null){
					dsatr.setValue(value);
					dsatr.setSymbol(symbol);
					session.update(dsatr);
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
	
	public void delete(Integer dsId, Integer atrId){     //tested
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		DsatrId dsatrid = new DsatrId();
		Dsatr dsatr = new Dsatr();
		try{
			Ds ds = (Ds)session.get(Ds.class, dsId);
			Atrribute atrribute = (Atrribute)session.get(Atrribute.class,atrId );
			if((ds!=null)&&(atrribute!=null)){
				dsatrid.setAtrribute(atrribute);
				dsatrid.setDs(ds);
				dsatr =(Dsatr)session.get(Dsatr.class, dsatrid);
				session.delete(dsatr);
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
	
	public void deleteByDS(Integer dsId){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "delete Dsatr as dsatr where dsatr.id.ds.dsId =:dsId";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("dsId", dsId);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();	
		}
		
	}
	
	public List<Dsatr> getDSAtr(Integer dsId){   //tested
		//String a[] = new String[]{"", ""};
		String hql = "from Dsatr as dsatr where dsatr.id.ds.dsId =:dsId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("dsId", dsId);
			List <Dsatr> u = query.list();
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
	
	public Dsatr getDsatrByAtr(Integer atrId){
		String hql = "from Dsatr as dsatr where dsatr.id.atrribute.atrId=:atrId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Dsatr asatr = null;
		try{
			Query query = session.createQuery(hql);
			query.setParameter("atrId", atrId);
			List<Dsatr> u = query.list();
			transaction.commit();
			session.close();
			if(u.size()>0)
				asatr = u.get(0);
			return asatr;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return null;
		}
	}
}