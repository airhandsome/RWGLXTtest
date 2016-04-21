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

import com.domain.Systems;

/**
 * A data access object (DAO) providing persistence and search support for
 * Systems entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.domain.Systems
 * @author MyEclipse Persistence Tools
 */

public class SystemsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SystemsDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PARENT = "parent";

	public void save(Systems transientInstance) {
		log.debug("saving Systems instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Systems persistentInstance) {
		log.debug("deleting Systems instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Systems findById(java.lang.Integer id) {
		log.debug("getting Systems instance with id: " + id);
		try {
			Systems instance = (Systems) getSession().get("com.domain.Systems",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Systems instance) {
		log.debug("finding Systems instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Systems")
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
		log.debug("finding Systems instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Systems as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByParent(Object parent) {
		return findByProperty(PARENT, parent);
	}

	public List findAll() {
		log.debug("finding all Systems instances");
		try {
			String queryString = "from Systems";
			
			Query queryObject = getSession().createQuery(queryString);
			System.out.println(queryObject.list().size());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Systems merge(Systems detachedInstance) {
		log.debug("merging Systems instance");
		try {
			Systems result = (Systems) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Systems instance) {
		log.debug("attaching dirty Systems instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Systems instance) {
		log.debug("attaching clean Systems instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void insertSys(String name,Integer parent, Integer level){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Systems system = new Systems();
		try{
			system.setName(name);
			system.setLevel(level);
			if(level.intValue()==0)
				system.setParent(null);
			else
				system.setParent(parent);
			session.save(system);
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public void updateSys(Integer sysId, String name, Integer parent,Integer level){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println(sysId+" "+name);
		String hql = "update Systems as systems set systems.name=:name,systems.parent=:parent,systems.level=:level where systems.systemId=:sysId";
		try{
			Query query = session.createQuery(hql);
			query.setString("name", name);
			query.setParameter("parent", parent);
			query.setParameter("level", level);
			query.setParameter("sysId", sysId);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public List<Systems> querySysbyParent(Integer parent){
		Session session = sessfac.openSession(); 
		Transaction transaction = session.beginTransaction();
		String hql = "from Systems as sys where sys.parent=:parent";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("parent", parent);
			List<Systems> result = (List<Systems>)query.list();
			transaction.commit();
			session.close();
			return result;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();	
			return null;
		}
	}
	public List<Systems> querySysbySearch(String strname){
		Session session = sessfac.openSession(); 
		Transaction transaction = session.beginTransaction();
		String hql = "from Systems as sys where sys.name like '%"+strname+"%'";
		try{
			Query query = session.createQuery(hql);
		
			List<Systems> result = (List<Systems>)query.list();
			transaction.commit();
			session.close();
			if (result.size()>0){
				return result;
			}
			else return null;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();	
			return null;
		}
	}
	
	public List<Systems> querySysbyOrder(String type){
		Session session = sessfac.openSession(); 
		Transaction transaction = session.beginTransaction();
		String hql = "from Systems as sys Order by sys.name";
		if (type.equals("name")){
			hql = "from Systems as sys Order by sys.name";
		}else if(type.equals("parent")){
			hql = "from Systems as sys Order by sys.parent";
		}
		
		
		try{
			Query query = session.createQuery(hql);
			List<Systems> result = (List<Systems>)query.list();
			transaction.commit();
			session.close();
			if (result.size()>0){
				return result;
			}
			else return null;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();	
			return null;
		}
	}

	public List<Systems> queryByName(String name){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Systems as sys where sys.name=:name";
		try{
			Query query = session.createQuery(hql);
			query.setString("name", name);
			List<Systems> result = (List<Systems>)query.list();
			transaction.commit();
			session.close();
			return result;
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
	}
}