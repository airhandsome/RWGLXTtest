package com.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.Da;
import com.domain.Isp;
import com.domain.Sequence;

/**
 * A data access object (DAO) providing persistence and search support for Da
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Da
 * @author MyEclipse Persistence Tools
 */

public class DaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(DaDAO.class);
	// property constants
	public static final String DEFBELONG = "defbelong";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TRIGGERTYPE = "triggertype";
	public static final String PRIORITY = "priority";
	public static final String STATE = "state";
	public static final String IF_AUTO = "ifAuto";
	public static final String IFDEF = "ifdef";
	public static final String TERMINATE = "terminate";
	public static final String CODE = "code";

	public void save(Da transientInstance) {
		log.debug("saving Da instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Da persistentInstance) {
		log.debug("deleting Da instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public List findByExample(Da instance) {
		log.debug("finding Da instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Da")
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
		log.debug("finding Da instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Da as model where model." + propertyName
					+ "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDefbelong(Object defbelong) {
		return findByProperty(DEFBELONG, defbelong);
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

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByIfAuto(Object ifAuto) {
		return findByProperty(IF_AUTO, ifAuto);
	}

	public List findByIfdef(Object ifdef) {
		return findByProperty(IFDEF, ifdef);
	}

	public List findByTerminate(Object terminate) {
		return findByProperty(TERMINATE, terminate);
	}

	public List findAll() {
		log.debug("finding all Da instances");
		try {
			String queryString = "from Da";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Da merge(Da detachedInstance) {
		log.debug("merging Da instance");
		try {
			Da result = (Da) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Da instance) {
		log.debug("attaching dirty Da instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Da instance) {
		log.debug("attaching clean Da instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<Da> getExeDA(Integer postionId) { // tested
		// String a[] = new String[]{"", ""};
		String hql = "from Da as da where da.fightPostion.postionId =:postionId and da.state =:state";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("postionId", postionId);
			query.setString("state", "daiqueren");
			List<Da> u = query.list();
			transaction.commit();
			session.close();
			return u;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	public List<Da> getDAbyType(Integer typeId) {
		// String a[] = new String[]{"", ""};
		String hql = "from Da as da where da.type.typeId =:typeId and da.ifdef=1";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("typeId", typeId);
			List<Da> u = query.list();
			transaction.commit();
			session.close();
			return u;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	public List<Da> getDAbyCa(Integer caId) {
		// String a[] = new String[]{"", ""};
		String hql = "from Da as da where da.ca.caId =:caId ";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("caId", caId);
			List<Da> u = query.list();
			transaction.commit();
			session.close();
			return u;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	public void updateState(Da da, String state) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {// yingtianjia null panduan
			if (da != null) {
				Timestamp start = da.getExcuteStart();
				Timestamp end = da.getExcuteEnd();
				da = (Da) session.get(Da.class, da.getDaId());
				da.setState(state);
				da.setExcuteStart(start);
				System.out.println(da.getExcuteStart());
				da.setExcuteEnd(end);
				session.save(da);
				transaction.commit();
			}
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public void saveDa(Da transientInstance){
		Session session = sessfac.openSession();
		Transaction transaction  = session.beginTransaction();
		session.save(transientInstance);
		transaction.commit();
		session.close();
	}
	public Da insertTo(Integer eventTriggerByEventId, Integer fightPostionId,
			Integer usersId, Integer caId, Integer eventTriggerByEndEventId,
			Integer typeId, Integer dsId, Integer defbelong, String name,
			String description, String triggertype, Integer priority,
			String state, Integer ifAuto, Integer ifdef, Timestamp planStart,
			Timestamp planDuration, Timestamp planEnd, String terminate,
			Timestamp excuteStart, Timestamp excuteEnd, String code,String nodename) { // tested

		Da da = new Da();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			if (eventTriggerByEventId != null) {
				EventTriggerDAO dao = new EventTriggerDAO();
				da.setEventTriggerByEvent(dao.findById(eventTriggerByEventId));
			} else {
				da.setEventTriggerByEvent(null);
			}

			if (fightPostionId != null) {
				FightPostionDAO dao = new FightPostionDAO();
				da.setFightPostion(dao.findById(fightPostionId));
			} else {
				da.setFightPostion(null);
			}

			if (usersId != null) {
				UsersDAO dao = new UsersDAO();
				da.setUsers(dao.findById(usersId));
			} else {
				da.setUsers(null);
			}

			if (caId != null) {
				CaDAO dao = new CaDAO();
				da.setCa(dao.findById(caId));
			} else {
				da.setCa(null);
			}

			if (eventTriggerByEndEventId != null) {
				EventTriggerDAO dao = new EventTriggerDAO();
				da.setEventTriggerByEndEvent(dao
						.findById(eventTriggerByEndEventId));
			} else {
				da.setEventTriggerByEndEvent(null);
			}

			if (typeId != null) {
				TypeDAO dao = new TypeDAO();
				da.setType(dao.findById(typeId));
			} else {
				da.setType(null);
			}

			if (dsId != null) {
				DsDAO dao = new DsDAO();
				da.setDs(dao.findById(dsId));
			} else {
				da.setDs(null);
			}

			da.setDefbelong(defbelong);
			da.setName(name);
			da.setDescription(description);
			da.setTriggertype(triggertype);
			da.setPriority(priority);
			da.setState(state);
			da.setIfAuto(ifAuto);
			da.setIfdef(ifdef);
			da.setPlanStart(planStart);
			da.setPlanEnd(planEnd);
			da.setPlanDuration(planDuration);
			da.setExcuteStart(excuteStart);
			da.setExcuteEnd(excuteEnd);
			da.setTerminate(terminate);
			da.setCode(code);
			da.setNodename(nodename);
			session.save(da);
			transaction.commit();
			session.close();

			return da;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	// tested
	public int update(Integer daId, Integer eventTriggerByEventId,
			Integer fightPostionId, Integer usersId, Integer caId,
			Integer eventTriggerByEndEventId, Integer typeId, Integer dsId,
			Integer defbelong, String name, String description,
			String triggertype, Integer priority, String state, Integer ifAuto,
			Integer ifdef, Timestamp planStart, Timestamp planDuration,
			Timestamp planEnd, String terminate, Timestamp excuteStart,
			Timestamp excuteEnd, String code) {

		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		int flag = 0;
		try {
			Da da = (Da) session.get(Da.class, daId);
			if (da != null) {
				if (eventTriggerByEventId != null) {
					EventTriggerDAO dao = new EventTriggerDAO();
					da.setEventTriggerByEvent(dao
							.findById(eventTriggerByEventId));
				} else {
					da.setEventTriggerByEvent(null);
				}

				if (fightPostionId != null) {
					FightPostionDAO dao = new FightPostionDAO();
					da.setFightPostion(dao.findById(fightPostionId));
				} else {
					da.setFightPostion(null);
				}

				if (usersId != null) {
					UsersDAO dao = new UsersDAO();
					da.setUsers(dao.findById(usersId));
				} else {
					da.setUsers(null);
				}

				if (caId != null) {
					CaDAO dao = new CaDAO();
					da.setCa(dao.findById(caId));
				} else {
					da.setCa(null);
				}

				if (eventTriggerByEndEventId != null) {
					EventTriggerDAO dao = new EventTriggerDAO();
					da.setEventTriggerByEndEvent(dao
							.findById(eventTriggerByEndEventId));
				} else {
					da.setEventTriggerByEndEvent(null);
				}

				if (typeId != null) {
					TypeDAO dao = new TypeDAO();
					da.setType(dao.findById(typeId));
				} else {
					da.setType(null);
				}

				if (dsId != null) {
					DsDAO dao = new DsDAO();
					da.setDs(dao.findById(dsId));
				} else {
					da.setDs(null);
				}

				da.setDefbelong(defbelong);
				da.setName(name);
				da.setDescription(description);
				da.setTriggertype(triggertype);
				da.setPriority(priority);
				da.setState(state);
				da.setIfAuto(ifAuto);
				da.setIfdef(ifdef);
				da.setPlanStart(planStart);
				da.setPlanEnd(planEnd);
				da.setPlanDuration(planDuration);
				da.setExcuteStart(excuteStart);
				da.setExcuteEnd(excuteEnd);
				da.setTerminate(terminate);
				da.setCode(code);
				session.update(da);
				transaction.commit();
				flag = 1;
			} // if
			session.close();
		}// try
		catch (Exception e) {
			flag = 0;
			e.printStackTrace();
			transaction.rollback();
		}
		return flag;
	}

	public void delete(Integer daId) { // tested
		Da da = new Da();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			da = (Da) session.get(Da.class, daId);// yingtianjia null panduan
			if (da != null) {
				session.delete(da);
				transaction.commit();
			}
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public List<Da> getDAbyTime() {
		String hql = "from Da as da where da.planStart<now() and triggertype='time' and state='TriggerPending'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			List<Da> u = (List<Da>) query.list();
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

	public void changeState(Da tpda, String state) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Integer daid = tpda.getDaId();
			Timestamp start = tpda.getExcuteStart();
			System.out.println(start);
			Timestamp end = tpda.getExcuteEnd();
			System.out.println(end);
			tpda = (Da) session.get(Da.class, daid);
			if (tpda != null) {
				tpda.setState(state);
				tpda.setExcuteStart(start);
				tpda.setExcuteEnd(end);
				session.save(tpda);
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//ÐÞ¸Ä
	public List<Da> findStopedDaByEventAndState(Integer event) {
		String hql = "from Da as da where da.eventTriggerByEvent.triggerId='"+event+"' and da.triggertype='state' and da.state='TriggerPending' and da.ifAuto=1";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			//query.setParameter("event", event);
			List<Da> list = query.list();
			transaction.commit();
			session.close();
			return list;
		} catch (Exception e) {
			session.close();
			transaction.rollback();
			e.printStackTrace();
		}
		return null;
		// StringBuffer append
	}
	public List<Da> findStartedDaByEventAndState(Integer event) {
		String hql = "from Da as da where da.eventTriggerByEndEvent.triggerId='"+event+"' and da.triggertype='state' and da.state='Executing' and da.ifAuto=1";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			//query.setParameter("event", event);
			List<Da> list = query.list();
			transaction.commit();
			session.close();
			return list;
		} catch (Exception e) {
			session.close();
			transaction.rollback();
			e.printStackTrace();
		}
		return null;
		// StringBuffer append
	}
//
	public void updateStartAutoDaByEvent(List<Da> dalist) {
	//	String hql = "update Da as da set da.state='Executing',da.excuteStart='"+new Timestamp(System.currentTimeMillis())+"' where da.daId='"+dalist.get(i).getDaId()+"'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (int i = 0; i < dalist.size(); i++) {
				Query query = session.createQuery("update Da as da set da.state='Executing',da.excuteStart='"+new Timestamp(System.currentTimeMillis())+"' where da.daId='"+dalist.get(i).getDaId()+"'");
				//query.setParameter("daid", dalist.get(i).getDaId());
				//query.setTimestamp("start",
				//		new Timestamp(System.currentTimeMillis()));
				query.executeUpdate();
				if (i % 20 == 0) {
					session.flush();
					session.clear();
					transaction.commit();
					transaction = session.beginTransaction();
				}

			}
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
	}
	public void updateStopAutoDaByEvent(List<Da> dalist) {
	//	String hql = "update Da as da set da.state='Executing',da.excuteStart='"+new Timestamp(System.currentTimeMillis())+"' where da.daId='"+dalist.get(i).getDaId()+"'";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			for (int i = 0; i < dalist.size(); i++) {
				Query query = session.createQuery("update Da as da set da.state='Finish',da.excuteEnd='"+new Timestamp(System.currentTimeMillis())+"' where da.daId='"+dalist.get(i).getDaId()+"'");
				//query.setParameter("daid", dalist.get(i).getDaId());
				//query.setTimestamp("start",
				//		new Timestamp(System.currentTimeMillis()));
				query.executeUpdate();
				if (i % 20 == 0) {
					session.flush();
					session.clear();
					transaction.commit();
					transaction = session.beginTransaction();
				}

			}
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
	}
	///ÐÞ¸Ä½áÊø


	public void updateCAbelong(Integer daId, Integer caId, String name, String nodename) {
		String hql = "update Da as da set da.ca.caId=:caId,da.name=:name,da.nodename=:nodename where da.daId=:daId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("daId", daId);
			query.setString("name", name);
			query.setString("nodename", nodename);
			query.setParameter("caId", caId);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			session.close();
		}
	}

	public void updateNotAutoDaByEvent(Integer event) {
		String hql = "update Da as da set da.state='AffirmPending' where da.eventTriggerByEvent.triggerId=:event and da.triggertype='state' and da.state='TriggerPending' and da.ifAuto=0";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("event", event);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
	}

	public void changeStateByCA(Integer caId, String state) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Da as da set da.state=:state where da.ca.caId=:caId";
		try {
			Query query = session.createQuery(hql);
			query.setString("state", state);
			query.setParameter("caId", caId);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}

	public void updateStartEvent(Integer daId, Integer eventId) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "update Da as da set da.eventTriggerByEvent.triggerId=:eventId where da.daId=:daId";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("eventId", eventId);
			query.setParameter("daId", daId);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			e.printStackTrace();
		}
	}

	public List<Da> getDaByPosition(String state, Integer positionId) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Da as da where da.state=:state and da.fightPostion.postionId=:positionId";
		try {
			Query query = session.createQuery(hql);
			query.setString("state", state);
			query.setParameter("positionId", positionId);
			List<Da> dalist = (List<Da>) query.list();
			transaction.commit();
			session.close();
			return dalist;
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
	}

	public Da findById(Integer id) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Da as da where da.daId=:id";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List<Da> dalist = (List<Da>) query.list();
			transaction.commit();
			session.close();
			return dalist.get(0);
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
	}

	public void test() {
		Session session = sessfac.openSession();
		Transaction t = session.beginTransaction();
		String hql = "update Da as da set da.priority=2,da.ifAuto=2 where da.daId=428";
		try {
			Query q = session.createQuery(hql);
			q.executeUpdate();
			t.commit();
			session.close();
		} catch (Exception e) {
			t.rollback();
			session.close();
			e.printStackTrace();
		}
	}
	
	public List getDabyNodename(String nodename){
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Da as da where da.nodename:=nodename";
		try{
			Query query = session.createQuery(hql);
			query.setParameter("nodename", nodename);
			List list = query.list();
			transaction.commit();
			session.close();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			session.close();
			return null;
		}
	}
}