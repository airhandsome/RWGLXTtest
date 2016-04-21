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

import com.domain.Atrribute;
import com.domain.FightPostion;

/**
 * A data access object (DAO) providing persistence and search support for
 * FightPostion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.domain.FightPostion
 * @author MyEclipse Persistence Tools
 */

public class FightPostionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FightPostionDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DEPARTMENT = "department";

	public void save(FightPostion transientInstance) {
		log.debug("saving FightPostion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FightPostion persistentInstance) {
		log.debug("deleting FightPostion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FightPostion findById(java.lang.Integer id) {
		log.debug("getting FightPostion instance with id: " + id);
		try {
			FightPostion instance = (FightPostion) getSession().get(
					"com.domain.FightPostion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FightPostion instance) {
		log.debug("finding FightPostion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.domain.FightPostion").add(Example.create(instance))
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
		log.debug("finding FightPostion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FightPostion as model where model."
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

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findAll() {
		log.debug("finding all FightPostion instances");
		try {
			String queryString = "from FightPostion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List getFPbySearch(String strname){
		log.debug("finding proper FightPostion instances");
		Session session = sessfac.openSession(); 
		String queryString = "from FightPostion as FP where FP.name like '%" +strname+"%'";
		Transaction transaction = session.beginTransaction();
		try{
			Query queryObject = getSession().createQuery(queryString);
			List<FightPostion> result = (List<FightPostion>)queryObject.list();
			transaction.commit();
			session.close();
			if (result.size()>0){
					return result;
				}
				else return null;
			}catch(RuntimeException re){
				session.close();
				transaction.rollback();	
				log.error("find all failed", re);
				throw re;
			}
			
	}
	
	public List getFPbyOrder(String type){
		log.debug("finding proper FightPostion instances");
		Session session = sessfac.openSession(); 
		String queryString = "from FightPostion as FP Order by FP.name";
		Transaction transaction = session.beginTransaction();
		if(type.equals("name")){
			queryString = "from FightPostion as FP Order by FP.name";
		}else if(type.equals("department")){
			queryString = "from FightPostion as FP Order by FP.department";
		}else if(type.equals("post")){
			queryString = "from FightPostion as FP Order by FP.post";
		}
		
		try{
			Query queryObject = getSession().createQuery(queryString);
			List<FightPostion> result = (List<FightPostion>)queryObject.list();
			transaction.commit();
			session.close();
			if (result.size()>0){
					return result;
				}
				else return null;
			}catch(RuntimeException re){
				session.close();
				transaction.rollback();	
				log.error("find all failed", re);
				throw re;
			}
			
	}
	public FightPostion merge(FightPostion detachedInstance) {
		log.debug("merging FightPostion instance");
		try {
			FightPostion result = (FightPostion) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FightPostion instance) {
		log.debug("attaching dirty FightPostion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FightPostion instance) {
		log.debug("attaching clean FightPostion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public FightPostion insert(String name, String department, String post){
		FightPostion fight = new FightPostion();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			fight.setName(name);
			fight.setDepartment(department);
			fight.setPost(post);
			session.save(fight);
			transaction.commit();
			session.close();
			return fight;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
            return null;
		}		
	}
	
	public void delete(Integer postionId){
		FightPostion fight = new FightPostion();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			fight = (FightPostion)session.get(FightPostion.class, postionId);
			if(fight!=null){
				session.delete(fight);
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
	
	public void update(Integer id, String name, String department, String post){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		FightPostion fp = new FightPostion();
		fp = (FightPostion) session.get(FightPostion.class, id);
		fp.setName(name);
		fp.setDepartment(department);
		fp.setPost(post);
		try{
			session.save(fp);
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
}