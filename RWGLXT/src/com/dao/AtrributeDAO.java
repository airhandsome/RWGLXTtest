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
import com.domain.Da;
import com.domain.Systems;
import com.domain.execute.AttributeDaoResult;

/**
 * A data access object (DAO) providing persistence and search support for
 * Atrribute entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.domain.Atrribute
 * @author MyEclipse Persistence Tools
 */

public class AtrributeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AtrributeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String TAG = "tag";

	public void save(Atrribute attr){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(attr);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			session.close();
		}
	}
	public void delete(Atrribute persistentInstance) {
		log.debug("deleting Atrribute instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

//	public Atrribute findById(java.lang.Integer id) {
//		log.debug("getting Atrribute instance with id: " + id);
//		try {
//			Atrribute instance = (Atrribute) getSession().get(
//					"com.domain.Atrribute", id);
//			return instance;
//		} catch (RuntimeException re) {
//			log.error("get failed", re);
//			throw re;
//		}
//	}

	public List findByExample(Atrribute instance) {
		log.debug("finding Atrribute instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Atrribute")
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
		log.debug("finding Atrribute instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Atrribute as model where model."
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

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findByTag(Object tag){
		return findByProperty(TAG, tag);
	}
	public List findAll() {
		log.debug("finding all Atrribute instances");
		try {
			String queryString = "from Atrribute";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Atrribute merge(Atrribute detachedInstance) {
		log.debug("merging Atrribute instance");
		try {
			Atrribute result = (Atrribute) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Atrribute instance) {
		log.debug("attaching dirty Atrribute instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Atrribute instance) {
		log.debug("attaching clean Atrribute instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Atrribute> getAtrBySystem(Integer systemId){
			String hql = "from Atrribute as atr where atr.systems.systemId =:systemId";
			Session session = sessfac.openSession();
			Transaction transaction = session.beginTransaction();
			try{
				Query query = session.createQuery(hql);
				query.setParameter("systemId", systemId);
				List <Atrribute> u = query.list();
				transaction.commit();
				session.close();
				return u;
			}
			catch(Exception e){
				session.close();
				e.printStackTrace();
				transaction.rollback();	
				return null;
			}		
		}
	
	public Atrribute insertAtr(Integer systemId, String name, String tag){
		Atrribute atr = new Atrribute();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			if(systemId!=null){
				SystemsDAO sysdao = new SystemsDAO();
				atr.setSystems(sysdao.findById(systemId));
			}
			else
				atr.setSystems(null);
			atr.setName(name);
			atr.setTag(tag);
			session.save(atr);
			transaction.commit();
			session.close();
			return atr;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
            return null;
		}		
	}
	
	public void delete(Integer atrId){
		Atrribute atr = new Atrribute();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			atr = (Atrribute)session.get(Atrribute.class, atrId);
			if(atr!=null){
				session.delete(atr);
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
	

	public AttributeDaoResult updateById(Integer atrId, Float value, Float changes){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Integer count = null;
		AttributeDaoResult adr = new AttributeDaoResult();
		adr.setAtrId(atrId);
		String hql = "update Atrribute as atr set atr.value=:value,atr.changes=:changes where atr.atrId=:atrId";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("value", value);
			query.setParameter("changes", changes);
			query.setParameter("atrId", atrId);
			count = (Integer)query.executeUpdate();
			adr.setCount(count);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
		return adr;
	}
	
	public void update(Integer id, String name, String tag, Systems sys){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Atrribute atr = (Atrribute)session.get(Atrribute.class, id);
		try{
			atr.setName(name);
			atr.setTag(tag);
			atr.setSystems(sys);
			session.save(atr);
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public List<Atrribute> getAtrByTag(String tag){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Atrribute as atrribute where atrribute.tag=:tag";
		try{
			Query query = session.createQuery(hql);
			query.setString("tag", tag);
			List<Atrribute> list = query.list();
			transaction.commit();
			session.close();
			return list;
		}catch(Exception e){
			transaction.rollback();
			session.close();
			return null;
		}
	}
	
	public Atrribute findById(Integer id){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Atrribute as atrribute where atrribute.atrId=:id";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List<Atrribute> list = query.list();
			transaction.commit();
			session.close();
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
			return null;
		}catch(Exception e){
			transaction.rollback();
			session.close();
			return null;
		}
	}
	public List<Atrribute> findBySearch(String strname){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Atrribute as atrribute where attribute.name like '%"+strname+"%'";
		try{
			Query query = session.createQuery(hql);
			List<Atrribute> list = query.list();
			transaction.commit();
			session.close();
			if(list != null && list.size()>0){
				return list;
			}
			else return null;
		}catch(Exception e){
			transaction.rollback();
			session.close();
			return null;
		}
		
		
	}
}