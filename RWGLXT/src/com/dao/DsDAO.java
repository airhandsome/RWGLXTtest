package com.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.Da;
import com.domain.Ds;
import com.domain.Dstype;

/**
 * A data access object (DAO) providing persistence and search support for Ds
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Ds
 * @author MyEclipse Persistence Tools
 */

public class DsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DsDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CODE = "code";
	public static final String IFDEF = "ifdef";
	public static final String DSBELONG = "dsbelong";
	public static final String LOGIC = "logic";

	public void save(Ds transientInstance) {
		log.debug("saving Ds instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ds persistentInstance) {
		log.debug("deleting Ds instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ds findById(java.lang.Integer id) {
		log.debug("getting Ds instance with id: " + id);
		try {
			Session session = sessfac.openSession();
			Ds instance = (Ds) session.get("com.domain.Ds", id);
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Ds instance) {
		log.debug("finding Ds instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Ds").add(
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
		log.debug("finding Ds instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ds as model where model." + propertyName
					+ "= ?";
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

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByIfdef(Object ifdef) {
		return findByProperty(IFDEF, ifdef);
	}

	public List findByDsbelong(Object dsbelong) {
		return findByProperty(DSBELONG, dsbelong);
	}

	public List findByLogic(Object logic) {
		return findByProperty(LOGIC, logic);
	}

	public List findAll() {
		log.debug("finding all Ds instances");
		try {
			String queryString = "from Ds";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ds merge(Ds detachedInstance) {
		log.debug("merging Ds instance");
		try {
			Ds result = (Ds) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ds instance) {
		log.debug("attaching dirty Ds instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ds instance) {
		log.debug("attaching clean Ds instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void saveDs(Ds ds){
		Session session = sessfac.openSession();
		Transaction transaction  = session.beginTransaction();
		session.save(ds);
		transaction.commit();
		session.close();
	}
	public Ds insertTo(Integer daByTrueExitId, Integer daByFalseExitId, Integer daByPreDaId, Integer caId, Integer dstypeId, String name, String description, String code, Integer ifdef, Integer dsbelong, String logic){                                //tested

		Ds ds = new Ds();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		int flag = 0;
		try{
			if(daByTrueExitId!=null){
				DaDAO dao = new DaDAO();
				ds.setDaByTrueExit(dao.findById(daByTrueExitId));
			}
			else{
				ds.setDaByTrueExit(null);
			}
			
			if(daByFalseExitId!=null){
				DaDAO dao = new DaDAO();
				ds.setDaByFalseExit(dao.findById(daByFalseExitId));
			}
			else{
				ds.setDaByFalseExit(null);
			}
			
			if(daByPreDaId!=null){
				DaDAO dao = new DaDAO();
				ds.setDaByPreDa(dao.findById(daByPreDaId));
			}
			else{
				ds.setDaByPreDa(null);
			}
			
			if(caId!=null){
				CaDAO dao = new CaDAO();
				ds.setCa(dao.findById(caId));
			}
			else{
				ds.setCa(null);
			}
			DstypeDAO dstypedao = new DstypeDAO();
			ds.setDstype(dstypedao.findById(dstypeId));
			ds.setName(name);
			ds.setDescription(description);
			ds.setCode(code);
			ds.setIfdef(ifdef);
			ds.setDsbelong(dsbelong);
			ds.setLogic(logic);
			session.save(ds);
			transaction.commit();
			session.close();
			return ds;
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
            return ds;
		}	
	}
	
	public int update(Integer dsId, Integer daByTrueExitId, Integer daByFalseExitId, Integer daByPreDaId, Integer caId, Integer dstypeId, String name, String description, String code, Integer ifdef, Integer dsbelong, String logic){                                //tested

		Ds ds = new Ds();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		int flag = 0;
		try{
			ds = (Ds)session.get(Ds.class, dsId);
			if(ds!=null){
				if(daByTrueExitId!=null){
					DaDAO dao = new DaDAO();
					ds.setDaByTrueExit(dao.findById(daByTrueExitId));
				}
				else{
					ds.setDaByTrueExit(null);
				}
				
				if(daByFalseExitId!=null){
					DaDAO dao = new DaDAO();
					ds.setDaByFalseExit(dao.findById(daByFalseExitId));
				}
				else{
					ds.setDaByFalseExit(null);
				}
				
				if(daByPreDaId!=null){
					DaDAO dao = new DaDAO();
					ds.setDaByPreDa(dao.findById(daByPreDaId));
				}
				else{
					ds.setDaByPreDa(null);
				}
				
				if(caId!=null){
					CaDAO dao = new CaDAO();
					ds.setCa(dao.findById(caId));
				}
				else{
					ds.setCa(null);
				}
				DstypeDAO dstypedao = new DstypeDAO();
				ds.setDstype(dstypedao.findById(dstypeId));
				ds.setName(name);
				ds.setDescription(description);
				ds.setCode(code);
				ds.setIfdef(ifdef);
				ds.setDsbelong(dsbelong);
				ds.setLogic(logic);
				session.save(ds);
				transaction.commit();
				flag = 1;
			}
			session.close();	
		}
		catch(Exception e){
			//flag = 0;
			session.close();
			e.printStackTrace();
            transaction.rollback();
		}
		return flag;	
	}
	
	public void delete(Integer dsId){   //tested
		Ds ds = new Ds();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			ds = (Ds)session.get(Ds.class, dsId);//yingtianjia null panduan
			if(ds!=null){
				session.delete(ds);
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
	
	public void updatePTF(Integer dsId, Integer pre, Integer trueexit, Integer falseexit, Integer caId, String dsname){
		String hql = "update Ds as ds set ds.ca.caId=:caId,ds.daByPreDa.daId=:pre,ds.daByTrueExit.daId="+trueexit+",daByFalseExit="+falseexit+",ds.name=:dsname where ds.dsId=:dsId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("caId", caId);
			query.setParameter("pre", pre);
//			query.setParameter("trueexit", trueexit);
//			query.setParameter("falseexit", falseexit);
			query.setParameter("dsId", dsId);
			query.setString("dsname", dsname);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public List<Ds> getDSbyCa(Integer caId){
		//String a[] = new String[]{"", ""};
		String hql = "from Ds as ds where ds.ca.caId =:caId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("caId", caId);
			List <Ds> u = query.list();
			transaction.commit();
			session.close();
			return u;
		}
		catch(Exception e){
			e.printStackTrace();
			transaction.rollback();	
		}
		return null;	
	}
	
	public List<Ds> getDSbyType(Integer dsTypeId){
		//String a[] = new String[]{"", ""};
		String hql = "from Ds as ds where ds.dstype.dsTypeId =:dsTypeId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("dsTypeId", dsTypeId);
			List <Ds> u = query.list();
			transaction.commit();
			session.close();
			return u;
		}
		catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
		return null;	
	}
	
	//edit by tbx
	
	public List<Ds> findByDaId(java.lang.Integer da_id){
		log.debug("getting ds by da_id:"+da_id);
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			String queryString = "from Ds as ds where ds.daByPreDa.daId=:daId";
			Query query = session.createQuery(queryString);
			query.setParameter("daId", da_id);
			List<Ds> ds = query.list();
			transaction.commit();
			session.close();
			if(ds.size()>0)
			    return ds;
			else
				return null;
		}catch(RuntimeException e){
			log.error("get filed",e);
			throw e;
		}
	}

	
}