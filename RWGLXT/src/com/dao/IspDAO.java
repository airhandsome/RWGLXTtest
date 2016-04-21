package com.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.domain.Users;

/**
 * A data access object (DAO) providing persistence and search support for Isp
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Isp
 * @author MyEclipse Persistence Tools
 */

public class IspDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IspDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESRIPTION = "desription";
	public static final String PRIORITY = "priority";
	public static final String IFDEF = "ifdef";
	public static final String IF_AUTO = "ifAuto";
	public static final String STATE = "state";
	public static final String TRIGGERTYPE = "triggertype";
	public static final String TERMINATE = "terminate";

//	public void save(Isp transientInstance) {
//		log.debug("saving Isp instance");
//		try {
//			getSession().save(transientInstance);
//			getSession().flush();
//			log.debug("save successful");
//		} catch (RuntimeException re) {
//		    re.printStackTrace();
//			log.error("save failed", re);
//			throw re;
//		}
//	}
	
	public void save(Isp isp){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			session.save(isp);
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			session.close();
		}
	}

	public void delete(Isp persistentInstance) {
		log.debug("deleting Isp instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Isp findById(java.lang.Integer id) {
		log.debug("getting Isp instance with id: " + id);
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println("ispdao:"+id);
			String hql = "from Isp as isp where isp.ispId =:id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List list = query.list();
			Isp instance = null;
			transaction.commit();
			session.close();
			if(list.size()>0){
				instance = (Isp)list.get(0);
			}
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			transaction.rollback();
			session.close();
			return null;
		}
	}

	public List findByExample(Isp instance) {
		log.debug("finding Isp instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Isp").add(
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
		log.debug("finding Isp instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Isp as model where model."
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

	public List findByDesription(Object desription) {
		return findByProperty(DESRIPTION, desription);
	}

	public List findByPriority(Object priority) {
		return findByProperty(PRIORITY, priority);
	}

	public List findByIfdef(Object ifdef) {
		return findByProperty(IFDEF, ifdef);
	}

	public List findByIfAuto(Object ifAuto) {
		return findByProperty(IF_AUTO, ifAuto);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByTriggertype(Object triggertype) {
		return findByProperty(TRIGGERTYPE, triggertype);
	}
	
	public List findByTerminate(Object terminate) {
		return findByProperty(TERMINATE, terminate);
	}

	public List findAll() {
		log.debug("finding all Isp instances");
		try {
			String queryString = "from Isp";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Isp merge(Isp detachedInstance) {
		log.debug("merging Isp instance");
		try {
			Isp result = (Isp) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Isp instance) {
		log.debug("attaching dirty Isp instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Isp instance) {
		log.debug("attaching clean Isp instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Isp insertTo(Integer eventTriggerByEventId, Integer usersId, Integer eventTriggerByEndeventId, 
			String name, String description, String triggertype, Integer priority, Integer ifAuto,
			Integer ifdef, String state, Timestamp planStarttime, Timestamp planEndtime, Timestamp excuteStarttime, Timestamp excuteEndtime, String terminate, String layout){                                //tested

		Isp isp = new Isp();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
				if(eventTriggerByEventId!=null){
					EventTriggerDAO dao = new EventTriggerDAO();
					isp.setEventTriggerByEvent(dao.findById(eventTriggerByEventId));
				}
				else{
					isp.setEventTriggerByEvent(null);
				}


				if(usersId!=null){
					UsersDAO dao = new UsersDAO();
					isp.setUsers(dao.findById(usersId));
				}
				else{
					isp.setUsers(null);
				}


				if(eventTriggerByEndeventId!=null){
					EventTriggerDAO dao = new EventTriggerDAO();
					isp.setEventTriggerByEndevent(dao.findById(eventTriggerByEndeventId));
				}
				else{
					isp.setEventTriggerByEndevent(null);
				}
		
				isp.setName(name);
				isp.setDesription(description);
			//	isp.setTriggertype("event");
				isp.setTriggertype(triggertype);
			//	isp.setPriority(1);
				isp.setPriority(priority);
			//	isp.setState("CheckPending");
				isp.setState(state);
			//	isp.setIfAuto(1);
				isp.setIfAuto(ifAuto);
			//	isp.setIfdef(1);
				isp.setIfdef(ifdef);
				isp.setPlanStarttime(planStarttime);
				isp.setPlanEndtime(planEndtime);
				isp.setExcuteStarttime(excuteStarttime);
				isp.setExcuteEndtime(excuteEndtime);
				isp.setTerminate(terminate);	
				isp.setLayout(layout);
				session.save(isp);
				transaction.commit();
				session.close();
				return isp;
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();
			return null;
		}	
	}
	
	public Isp update(Integer ispId, Integer eventTriggerByEventId, Integer usersId, Integer eventTriggerByEndeventId, 
			String name, String description, String triggertype, Integer priority, Integer ifAuto,
			Integer ifdef, String state, Timestamp planStarttime, Timestamp planEndtime, Timestamp excuteStarttime, Timestamp excuteEndtime, String terminate, String layout){                                //tested

		Isp isp = new Isp();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
				isp = (Isp)session.get(Isp.class, ispId);
				if(isp!=null){
					if(eventTriggerByEventId!=null){
						EventTriggerDAO dao = new EventTriggerDAO();
						isp.setEventTriggerByEvent(dao.findById(eventTriggerByEventId));
					}
					else{
						isp.setEventTriggerByEvent(null);
					}


					if(usersId!=null){
						UsersDAO dao = new UsersDAO();
						isp.setUsers(dao.findById(usersId));
					}
					else{
						isp.setUsers(null);
					}


					if(eventTriggerByEndeventId!=null){
						EventTriggerDAO dao = new EventTriggerDAO();
						isp.setEventTriggerByEndevent(dao.findById(eventTriggerByEndeventId));
					}
					else{
						isp.setEventTriggerByEndevent(null);
					}
			
					isp.setName(name);
					isp.setDesription(description);
					isp.setTriggertype(triggertype);
					isp.setPriority(priority);
					isp.setState(state);
					isp.setIfAuto(ifAuto);
					isp.setIfdef(ifdef);
					isp.setPlanStarttime(planStarttime);
					isp.setPlanEndtime(planEndtime);
					isp.setExcuteStarttime(excuteStarttime);
					isp.setExcuteEndtime(excuteEndtime);
					isp.setTerminate(terminate);	
					isp.setLayout(layout);
					session.save(isp);
					transaction.commit();
				}//if
				session.close();
				return isp;
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
			transaction.rollback();
			return null;
		}	
	}
	
	public void delete(Integer ispId){
		Isp isp = new Isp();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			isp = (Isp)session.get(Isp.class, ispId);//yingtianjia null panduan
			if(isp!=null){
				session.delete(isp);
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
	
	public void updateState(Integer ispId, String state){
		Isp isp = new Isp();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			isp = (Isp)session.get(Isp.class, ispId);//yingtianjia null panduan
			if(isp!=null){
				isp.setState(state);
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
	
	public List<Isp> getISPbyState(String state){
		String hql = "from Isp as isp where isp.state =:state ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setString("state", state);
			List <Isp> u = query.list();
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
	
	public List<Isp> getISPdef(){
		String hql = "from Isp as isp where isp.ifdef=:ifdef";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("ifdef", 1);
			List <Isp> u = query.list();
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
	
	public List<Isp> getISPbyTime() {
		String hql = "from Isp as isp where isp.planStarttime<now() and triggertype='time' and state='TriggerPending'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			List<Isp> u = (List<Isp>) query.list();
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

	public void changeState(Isp tpisp, String state) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer ispid = tpisp.getIspId();
			Timestamp start = tpisp.getExcuteStarttime();
			Timestamp end = tpisp.getExcuteEndtime();
			tpisp = (Isp) session.get(Isp.class, ispid);
			if (tpisp != null) {
				tpisp.setState(state);
				tpisp.setExcuteStarttime(start);
				tpisp.setExcuteEndtime(end);
				session.save(tpisp);
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void checkISP(Integer ispid, String state){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Isp as isp set isp.state=:state where isp.ispId=:ispid";
		try{
			Query query = session.createQuery(hql);
			query.setString("state", state);
			query.setParameter("ispid", ispid);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public void updateLayout(String layout, Integer ispId){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Isp as isp set isp.layout=:layout where isp.ispId=:ispId";
		try{
			Query query = session.createQuery(hql);
			query.setString("layout", layout);
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
	public List<Isp> getCheckPendingIsp(){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		List<Isp> list = new ArrayList<Isp>();
		String hql = "from Isp as isp where isp.state='CheckPending'";
		try{
			Query query = session.createQuery(hql);
			 list = (List<Isp>)query.list();
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
	
	public List<Isp> getRejectIsp(){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		List<Isp> list = new ArrayList<Isp>();
		String hql = "from Isp as isp where isp.state='Reject'";
		try{
			Query query = session.createQuery(hql);
			 list = (List<Isp>)query.list();
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
	
	public List<Isp> getEventISP(Integer event){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		List<Isp> list = new ArrayList<Isp>();
		String hql = "from Isp as isp where isp.state='TriggerPending' and isp.triggertype='state' and isp.eventTriggerByEvent.triggerId=:event";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("event", event);
			list = (List<Isp>)query.list();
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
	
	public void updateEventISP(List<Isp> isplist){
		String hql = "update Isp as isp set isp.state='Executing' where isp.ispId=:ispId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			for(int i=0;i<isplist.size();i++){
				Query query = session.createQuery(hql);
				query.setParameter("ispId", isplist.get(i).getIspId());
				query.executeUpdate();
				if(i%20==0){
					session.flush();
					session.clear();
					transaction.commit();
					transaction = session.beginTransaction();
				}
				
			}
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
	}
	public void updateTime(Integer ispId,Timestamp start, Timestamp end){
		String hql = "update Isp as isp set isp.planStarttime=:start,isp.planEndtime=:end where isp.ispId=:ispId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery(hql);
			query.setParameter("ispId", ispId);
			query.setTimestamp("start", start);
			query.setTimestamp("end", end);
			query.executeUpdate();
			transaction.commit();
			session.close();
		}catch(Exception e){
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
	}
}