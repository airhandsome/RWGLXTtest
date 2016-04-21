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

import com.domain.Ca;
import com.domain.Da;
import com.domain.EventTrigger;
import com.domain.Isp;
import com.domain.Type;
import com.domain.Users;

/**
 * A data access object (DAO) providing persistence and search support for Ca
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Ca
 * @author MyEclipse Persistence Tools
 */

public class CaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CaDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TRIGGERTYPE = "triggertype";
	public static final String PRIORITY = "priority";
	public static final String DEFBELONG = "defbelong";
	public static final String IF_AUTO = "ifAuto";
	public static final String IFDEF = "ifdef";
	public static final String STATE = "state";
	public static final String TERMINATE = "terminate";

	public void save(Ca transientInstance) {
		log.debug("saving Ca instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ca persistentInstance) {
		log.debug("deleting Ca instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ca findById(java.lang.Integer id) {
		log.debug("getting Ca instance with id: " + id);
		try {
			Ca instance = (Ca) getSession().get("com.domain.Ca", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Ca instance) {
		log.debug("finding Ca instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Ca").add(
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
		log.debug("finding Ca instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ca as model where model." + propertyName
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

	public List findByTriggertype(Object triggertype) {
		return findByProperty(TRIGGERTYPE, triggertype);
	}

	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List findByDefbelong(Object defbelong) {
		return findByProperty(DEFBELONG, defbelong);
	}

	public List findByIfAuto(Object ifAuto) {
		return findByProperty(IF_AUTO, ifAuto);
	}

	public List findByIfdef(Object ifdef) {
		return findByProperty(IFDEF, ifdef);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByTerminate(Object terminate) {
		return findByProperty(TERMINATE, terminate);
	}

	public List findAll() {
		log.debug("finding all Ca instances");
		try {
			String queryString = "from Ca";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ca merge(Ca detachedInstance) {
		log.debug("merging Ca instance");
		try {
			Ca result = (Ca) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ca instance) {
		log.debug("attaching dirty Ca instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ca instance) {
		log.debug("attaching clean Ca instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Ca> getCAbyType(Integer typeId){
		//String a[] = new String[]{"", ""};
		String hql = "from Ca as ca where ca.type.typeId =:typeId and ca.ifdef=1";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("typeId", typeId);
			List <Ca> u = query.list();
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
	
	public List<Ca> getCAbyISP(Integer ispId){
		//String a[] = new String[]{"", ""};
		String hql = "from Ca as ca where ca.isp.ispId =:ispId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("ispId", ispId);
			List <Ca> u = query.list();
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
	
	public List<Ca> getCAbyState(String state){
		//String a[] = new String[]{"", ""};
		String hql = "from Ca as ca where ca.state =:state ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setString("state", state);
			List <Ca> u = query.list();
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
	
	public void updateState(Integer caId, String state){
		Ca ca = new Ca();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			ca = (Ca)session.get(Ca.class, caId);//yingtianjia null panduan
			if(ca!=null){
				ca.setState(state);
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

	
	
	public Ca insertTo(Integer eventTriggerByEventId, Integer ispId, Integer usersId, Integer eventTriggerByEndeventId, 
						Integer typeId, String name, String description, String triggertype, Integer priority, Integer defbelong, Integer ifAuto,
						Integer ifdef, String state, Timestamp planStarttime, Timestamp planEndtime, Timestamp excuteStarttime, Timestamp excuteEndtime, String terminate){                                //tested

		Ca ca = new Ca();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			if(eventTriggerByEventId!=null){
				EventTriggerDAO dao = new EventTriggerDAO();
				ca.setEventTriggerByEvent(dao.findById(eventTriggerByEventId));
			}
			else{
				ca.setEventTriggerByEvent(null);
			}
			
			if(ispId!=null){
				IspDAO dao = new IspDAO();
				ca.setIsp(dao.findById(ispId));
			}
			else{
				ca.setIsp(null);
			}
			
			if(usersId!=null){
				UsersDAO dao = new UsersDAO();
				ca.setUsers(dao.findById(usersId));
			}
			else{
				ca.setUsers(null);
			}
			
			
			if(eventTriggerByEndeventId!=null){
				EventTriggerDAO dao = new EventTriggerDAO();
				ca.setEventTriggerByEndevent(dao.findById(eventTriggerByEndeventId));
			}
			else{
				ca.setEventTriggerByEndevent(null);
			}
			
			if(typeId!=null){
				TypeDAO dao = new TypeDAO();
				ca.setType(dao.findById(typeId));
			}
			else{
				ca.setType(null);
			}

				ca.setDefbelong(defbelong);		
				ca.setName(name);
				ca.setDescription(description);
				ca.setTriggertype(triggertype);
				ca.setPriority(priority);
				ca.setState(state);
				ca.setIfAuto(ifAuto);
				ca.setIfdef(ifdef);
				ca.setPlanStarttime(planStarttime);
				ca.setPlanEndtime(planEndtime);
				ca.setExcuteStarttime(excuteStarttime);
				ca.setExcuteEndtime(excuteEndtime);
				ca.setTerminate(terminate);	
				
				session.save(ca);
				transaction.commit();
				session.close();
				return ca;
		}
		catch(Exception e){
			e.printStackTrace();
            transaction.rollback();
            session.close();
            return null;
		}	
	}
	
	public void update(Integer caId, Integer eventTriggerByEventId, Integer ispId, Integer usersId, Integer eventTriggerByEndeventId, 
			Integer typeId, String name, String description, String triggertype, Integer priority, Integer defbelong, Integer ifAuto,
			Integer ifdef, String state, Timestamp planStarttime, Timestamp planEndtime, Timestamp excuteStarttime, Timestamp excuteEndtime, String terminate){                                //tested

		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Ca ca = (Ca)session.get(Ca.class, caId);
			if(ca!=null){
				if(eventTriggerByEventId!=null){
					EventTriggerDAO dao = new EventTriggerDAO();
					ca.setEventTriggerByEvent(dao.findById(eventTriggerByEventId));
				}
				else{
					ca.setEventTriggerByEvent(null);
				}

				if(ispId!=null){
					IspDAO dao = new IspDAO();
					ca.setIsp(dao.findById(ispId));
				}
				else{
					ca.setIsp(null);
				}

				if(usersId!=null){
					UsersDAO dao = new UsersDAO();
					ca.setUsers(dao.findById(usersId));
				}
				else{
					ca.setUsers(null);
				}


				if(eventTriggerByEndeventId!=null){
					EventTriggerDAO dao = new EventTriggerDAO();
					ca.setEventTriggerByEndevent(dao.findById(eventTriggerByEndeventId));
				}
				else{
					ca.setEventTriggerByEndevent(null);
				}

				if(typeId!=null){
					TypeDAO dao = new TypeDAO();
					ca.setType(dao.findById(typeId));
				}
				else{
					ca.setType(null);
				}

				ca.setDefbelong(defbelong);		
				ca.setName(name);
				ca.setDescription(description);
				ca.setTriggertype(triggertype);
				ca.setPriority(priority);
				ca.setState(state);
				ca.setIfAuto(ifAuto);
				ca.setIfdef(ifdef);
				ca.setPlanStarttime(planStarttime);
				ca.setPlanEndtime(planEndtime);
				ca.setExcuteStarttime(excuteStarttime);
				ca.setExcuteEndtime(excuteEndtime);
				ca.setTerminate(terminate);	
		
				session.save(ca);
				transaction.commit();
			}//if
			session.close();	
		}//try
		catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}	
	}
	
	public void delete(Integer caId){
		Ca ca = new Ca();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			ca = (Ca)session.get(Ca.class, caId);//yingtianjia null panduan
			if(ca!=null){
				session.delete(ca);
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
	
	/*public List<Ca> getCAbyType(Integer typeId){
		//String a[] = new String[]{"", ""};
		String hql = "from Ca as ca where ca.type.typeId =:typeId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("typeId", typeId);
			List <Ca> u = query.list();
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
	}*/
	public List<Ca> getCAbyTime() {
		String hql = "from Ca as ca where ca.planStarttime<now() and ca.triggertype='time' and ca.state='TriggerPending'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			List<Ca> u = (List<Ca>) query.list();
			transaction.commit();
			session.close();
			return u;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	public void changeState(Ca tpca, String state) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer caid = tpca.getCaId();
			Timestamp start = tpca.getExcuteStarttime();
			Timestamp end = tpca.getExcuteEndtime();
			tpca = (Ca) session.get(Ca.class, caid);
			if (tpca != null) {
				tpca.setState(state);
				tpca.setExcuteStarttime(start);
				tpca.setExcuteEndtime(end);
				session.save(tpca);
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void updateCaBelong(Integer ispid, Integer[] caids){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Ca as ca set ca.isp.ispId=:ispid where ca.caId=:caid";
		try{
			for(int i=0;i<caids.length;i++){
				Query query = session.createQuery(hql);
				query.setParameter("ispid", ispid);
				query.setParameter("caid", caids[i]);
				query.executeUpdate();
				if(i%5==0){
					session.flush();
					session.clear();
					transaction.commit();
					transaction = session.beginTransaction();
				}
			}
			
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public void changeStateByISP(Integer ispId, String state){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Ca as ca set ca.state=:state where ca.isp.ispId=:ispId";
		try{
			Query query = session.createQuery(hql);
			query.setString("state", state);
			query.setParameter("ispId", ispId);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public List<Ca> getPendingCA(){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Ca as ca where ca.state='CheckPending'";
		try{
			Query query = session.createQuery(hql);
			List<Ca> list = query.list();
			transaction.commit();
			session.close();
			return list;
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Ca> getCabyEvent(Integer event){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Ca as ca where ca.triggertype='state' and ca.state='TriggerPending' and ca.eventTriggerByEvent.triggerId=:event and ca.ifAuto=1";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("event", event);
			List<Ca> list = query.list();
			transaction.commit();
			session.close();
			return list;
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateCaByEvent(Integer event){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Ca as ca set ca.state='AffirmPending' where ca.triggertype='state' and ca.eventTriggerByEvent.triggerId=:event and ca.ifAuto=0";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("event", event);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
}