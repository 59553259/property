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
import com.wxcloud.dao.CostDao;
import com.wxcloud.pojo.Cost;
import com.wxcloud.util.Util;

@Repository(value = "costDao")
public class CostDaoImpl extends BaseDaoHibernate implements CostDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Cost getCostById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Cost> list = new ArrayList<Cost>();

		Criteria criteria = session.createCriteria(Cost.class);
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
	public Cost getCostByUser(String User) {
		Session session = sessionFactory.getCurrentSession();
		List<Cost> list = new ArrayList<Cost>();

		Criteria criteria = session.createCriteria(Cost.class);
		if (!(null == User && "".equals(User))) {
			criteria.add(Restrictions.eq("User", User));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cost getCostByProject(String Project) {
		Session session = sessionFactory.getCurrentSession();
		List<Cost> list = new ArrayList<Cost>();

		Criteria criteria = session.createCriteria(Cost.class);
		if (!(null == Project && "".equals(Project))) {
			criteria.add(Restrictions.eq("Project", Project));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cost getCostByState(String State) {
		Session session = sessionFactory.getCurrentSession();
		List<Cost> list = new ArrayList<Cost>();

		Criteria criteria = session.createCriteria(Cost.class);
		if (!(null == State && "".equals(State))) {
			criteria.add(Restrictions.eq("State", State));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Cost> getCost(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM Cost");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Cost> message = query.list();
		session.close();
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCostByUser(String User, int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Cost> list = new ArrayList<Cost>();
		try {
			String hql = "FROM Cost WHERE User = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", User);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCostByProject(String Project, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Cost> list = new ArrayList<Cost>();
		try {
			String hql = "FROM Cost WHERE Project = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", Project);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> getCostByState(String State, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Cost> list = new ArrayList<Cost>();
		try {
			String hql = "FROM Cost WHERE State = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", State);
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
	public int getCostCount() {
		List<Cost> list = findAllCost();
		return list.size();
	}

	@Override
	public int getCostByUserCount(String User) {
		List<Cost> lst = getCostByUser(User, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int getCostByProjectCount(String Project) {
		List<Cost> lst = getCostByProject(Project, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int getCostByStateCount(String State) {
		List<Cost> lst = getCostByState(State, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int addCost(Cost Cost) {
		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Cost.setId(id);
			session.save(Cost);
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
	public int editCost(Cost Cost) {
		int id = Cost.getId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(Cost);
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
	public int deleteCost(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM Cost WHERE id=?0";
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

	private int getValidId() {
		int id = 0;
		String sql = "SELECT max(id) FROM costmgt";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Cost> findAllCost() {
		Session session = sessionFactory.getCurrentSession();
		List<Cost> list = new ArrayList<Cost>();
		Criteria criteria = session.createCriteria(Cost.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
