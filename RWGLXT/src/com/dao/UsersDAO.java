package com.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.domain.Atrribute;
import com.domain.Da;
import com.domain.FightPostion;
import com.domain.Users;


/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.domain.Users
 * @author MyEclipse Persistence Tools
 */

public class UsersDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UsersDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String DEPARTMENT = "department";
	public static final String ROLE = "role";
	public static final String POST = "post";

	public void save(Users transientInstance) {
		log.debug("saving Users instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users findById(java.lang.Integer id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) getSession().get("com.domain.Users", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List results = getSession().createCriteria("com.domain.Users").add(
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
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findByPost(Object post) {
		return findByProperty(POST, post);
	}

	public List findAll() {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List getUserbySearch(String strname){
		log.debug("get proper Users instances");
		try{
			String queryString = "from Users as User where User.username like '%"+strname+"%'";
			Query query = getSession().createQuery(queryString);
			System.out.println("query string is "+ query);
			List<Users> result = query.list();
			if (result.size()>0){
				return result;
			}
			else return null;
		}catch(RuntimeException re){
			log.error("no proper data", re);
			throw re;
		}
	}
	
	public List getUserbyOrder(String type){
		log.debug("get proper Users instances");
		
		try{
			String queryString = null;
			if (type.equals("id")){
				 queryString = "from Users as User Order by User.userid";
			}
			else if(type.equals("username")){
				 queryString = "from Users as User Order by User.username";
			}
			else if(type.equals("password")){
				 queryString = "from Users as User Order by User.password";
			}
			else if(type.equals("department")){
				 queryString = "from Users as User Order by User.department";
			}
			else if(type.equals("role")){
				 queryString = "from Users as User Order by User.role";
			}
			else if(type.equals("post")){
				 queryString = "from Users as User Order by User.post";
			}
			
			Query query = getSession().createQuery(queryString);
			query.setString(0, "'%"+type+"%'");
			List<Users> result = query.list();
			if (result.size()>0){
				return result;
			}
			else return null;
		}catch(RuntimeException re){
			log.error("no proper data", re);
			throw re;
		}
	}
	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Users login(String username, String password) {
		Users user;
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			Query query = session.createQuery("from Users as users where users.username=:username and users.password=:password");
			query.setString("username", username);
			query.setString("password", password);
			List<Users> u = query.list();
			if(u.size() == 1){
				user = u.get(0);
				return user;
			}
			transaction.commit();
			session.close();
		}
		catch(Exception e){
			e.printStackTrace();
			session.close();
			transaction.rollback();
		}
		return null;
	}
	
	public Users insert(String name, String password, String department, String role, String post, Integer positionId){
		Users user = new Users();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			user.setUsername(name);
			user.setPassword(password);
			user.setDepartment(department);
			user.setRole(role);
			user.setPost(post);
			if(positionId!=null){
				FightPostionDAO fpdao = new FightPostionDAO();
				FightPostion fp = fpdao.findById(positionId);
				user.setFightPostion(fp);
			}
			
			session.save(user);
			transaction.commit();
			session.close();
			return user;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
            return null;
		}		
	}
	
	public void delete(Integer userId){
		Users user = new Users();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{
			user = (Users)session.get(Users.class, userId);
			if(user!=null){
				session.delete(user);
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
	
	public Users update(Integer userId, String name, String password, String department, String role, String post, Integer positionId){ 
		Users user = new Users();
		Session session = sessfac.openSession();
		Transaction transaction = session.beginTransaction();
		try{System.out.println(userId);
			user = (Users)session.get(Users.class, userId);
			user.setUsername(name);
			user.setPassword(password);
			user.setDepartment(department);
			user.setRole(role);
			user.setPost(post);
			if(positionId!=null){
				FightPostionDAO fpdao = new FightPostionDAO();
				FightPostion fp = fpdao.findById(positionId);
				user.setFightPostion(fp);
			}
			
			session.update(user);
			transaction.commit();
			session.close();
			return user;
		}catch(Exception e){
			session.close();
			e.printStackTrace();
            transaction.rollback();
            return null;
		}		
	}
	
}