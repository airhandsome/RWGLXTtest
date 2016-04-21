package com.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.Da;
import com.domain.Sequence;
import com.domain.Users;

/**
 * A data access object (DAO) providing persistence and search support for
 * Sequence entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.domain.Sequence
 * @author MyEclipse Persistence Tools
 */

public class SequenceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SequenceDAO.class);
	// property constants
	public static final String PRE_TYPE = "preType";
	public static final String PRE = "pre";
	public static final String POST_TYPE = "postType";
	public static final String POST = "post";

	public void save(Sequence transientInstance) {
		log.debug("saving Sequence instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Sequence persistentInstance) {
		log.debug("deleting Sequence instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sequence findById(java.lang.Integer id) {
		log.debug("getting Sequence instance with id: " + id);
		try {
			Sequence instance = (Sequence) getSession().get(
					"com.domain.Sequence", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Sequence instance) {
		log.debug("finding Sequence instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Sequence")
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
		log.debug("finding Sequence instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Sequence as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPreType(Object preType) {
		return findByProperty(PRE_TYPE, preType);
	}

	public List findByPre(Object pre) {
		return findByProperty(PRE, pre);
	}

	public List findByPostType(Object postType) {
		return findByProperty(POST_TYPE, postType);
	}

	public List findByPost(Object post) {
		return findByProperty(POST, post);
	}

	public List findAll() {
		log.debug("finding all Sequence instances");
		try {
			String queryString = "from Sequence";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Sequence merge(Sequence detachedInstance) {
		log.debug("merging Sequence instance");
		try {
			Sequence result = (Sequence) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sequence instance) {
		log.debug("attaching dirty Sequence instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sequence instance) {
		log.debug("attaching clean Sequence instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Object[] getDAPost(Integer daId) {
		Object[] obj = new Object[2];
		String hql = "from Sequence as seq where seq.preType =:preType and seq.pre =:daId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setString("preType", "DA");
			query.setParameter("daId", daId);
			if (query.list().size() > 0) {
				Sequence seq = (Sequence) query.list().get(0);
				obj[0] = seq.getPostType();
				obj[1] = seq.getPost();
			}
			session.close();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
		return null;

	}

	public Integer getDAPre(Integer daId) {
		String hql = "from Sequence as seq where seq.preType =:preType and seq.post =:daId and seq.postType =:postType";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		Integer result = 0;
		try {
			Query query = session.createQuery(hql);
			query.setString("preType", "DA");
			query.setParameter("daId", daId);
			query.setParameter("postType", "DA");
			if (query.list().size() > 0) {
				Sequence seq = (Sequence) query.list().get(0);
				result = seq.getPre();
			}
			session.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
		return 0;

	}

	public Object[] getCAPost(Integer caId) {
		Object[] obj = new Object[2];
		String hql = "from Sequence as seq where seq.preType =:preType and seq.pre =:caId";
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setString("preType", "CA");
			query.setParameter("daId", caId);
			if (query.list().size() > 0) {
				Sequence seq = (Sequence) query.list().get(0);
				obj[0] = seq.getPostType();
				obj[1] = seq.getPost();
			}
			transaction.commit();
			session.close();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
		return null;
	}

	public void insert(String preType, Integer pre, String postType,
			Integer post) { // tested
		Sequence seq = new Sequence();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			seq.setPreType(preType);
			seq.setPre(pre);
			seq.setPostType(postType);
			seq.setPost(post);
			session.save(seq);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public void update(Integer sequenceId, String preType, Integer pre,
			String postType, Integer post) {
		Sequence seq = new Sequence();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			seq = (Sequence) session.get(Sequence.class, sequenceId);
			seq.setPreType(preType);
			seq.setPre(pre);
			seq.setPostType(postType);
			seq.setPost(post);
			session.update(seq);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public void delete(Integer sequenceId) { // tested
		Sequence seq = new Sequence();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			seq = (Sequence) session.get(Sequence.class, sequenceId);
			if (seq != null) {
				session.delete(seq);
				transaction.commit();
			}
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

	public List<Sequence> queryByPost(String postType, Integer post) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Sequence as seq where seq.postType =:postType and seq.post =:post";
		try {
			Query query = session.createQuery(hql);
			query.setString("postType", postType);
			query.setParameter("post", post);
			List result = query.list();

			transaction.commit();

			session.close();
			return result;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	public List<Sequence> queryByPre(String preType, Integer pre) {
		Sequence seq = new Sequence();
		seq = null;
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from Sequence as seq where seq.preType =:preType and seq.pre =:pre";
		try {
			Query query = session.createQuery(hql);
			query.setString("preType", preType);
			query.setParameter("pre", pre);
			List<Sequence> list = new ArrayList<Sequence>();
			if (query.list().size() > 0) {
				list = query.list();
				transaction.commit();
			}
			session.close();
			return list;
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	// edit by tbx

	public void deleteByCa(Integer ca_id) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "delete Sequence as sequence where sequence.preType='CA' and sequence.pre=:pre";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("pre", ca_id);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

	// tbx
	public void deleteByDa(Integer da_id) {
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "delete Sequence as sequence where sequence.preType='DA' and sequence.pre=:pre";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("pre", da_id);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			transaction.rollback();
		}
	}

}