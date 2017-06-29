package com.wxcloud.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wxcloud.dao.BaseDaoHibernate;
import com.wxcloud.dao.UserDao;
import com.wxcloud.pojo.User;
import com.wxcloud.util.Util;

@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoHibernate implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = new ArrayList<User>();
		// 通过 Hibernate 的Criteria查询
		Criteria criteria = session.createCriteria(User.class);
		if (id > 0) {
			criteria.add(Restrictions.eq("id", id));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByName(String User) {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = new ArrayList<User>();
		// 通过 Hibernate 的Criteria查询
		Criteria criteria = session.createCriteria(User.class);
		if (!(null == User && "".equals(User))) {
			criteria.add(Restrictions.eq("User", User));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addUser(User user) {
		if (getUserByName(user.getUser()) != null) { // 该用户名已存在
			return 0;
		}
		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			user.setId(id);
			session.save(user);
			trans.commit(); // 任何有关数据库更新的操作都是commit后进数据库的
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
			id = 0;
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public int editUser(User user) {
		int id = user.getId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(user);
			trans.commit(); // 任何有关数据库更新的操作都是commit后进数据库的
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
			id = 0;
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public int deleteUser(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM User WHERE id=?0";
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query q = session.createQuery(hql);
			q.setInteger("0", id);
			q.executeUpdate();
			t.commit();
		} catch (Exception ex) {
			if (t != null) {
				ex.printStackTrace();
				t.rollback();
			}
			ret = 0;
		} finally {
			session.close();
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		Session session = sessionFactory.getCurrentSession();
		List<User> list = new ArrayList<User>();
		Criteria criteria = session.createCriteria(User.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public int getUserCount() {
		List<User> list = findAllUser();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM User");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<User> message = query.list();
		session.close();
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByPublicname(String name, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<User> list = new ArrayList<User>();
		try {
			String hql = "FROM User WHERE Phone = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", name);
			query.setFirstResult((currentPaging - 1) * pageSize);
			query.setMaxResults(pageSize);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
		}
		if (list.iterator().hasNext()) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public int getUserByPublicnameCount(String name) {
		List<User> lst = getUserByPublicname(name, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	private int getValidId() {
		int id = 0;
		String sql = "SELECT max(id) FROM usermgt ";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Object obj = session.createSQLQuery(sql).uniqueResult();
		if (obj == null) {
			id = 1;
		} else {
			id = (Integer) obj;
			if (id == 0) {
				id = 1;
			} else {
				id++;
			}
		}
		session.close();
		return id;
	}
}