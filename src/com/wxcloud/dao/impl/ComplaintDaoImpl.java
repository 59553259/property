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
import com.wxcloud.dao.ComplaintDao;
import com.wxcloud.pojo.Complaint;
import com.wxcloud.util.Util;

@Repository(value = "ComplaintDao")
public class ComplaintDaoImpl extends BaseDaoHibernate implements ComplaintDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Complaint getComplaintById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Complaint> list = new ArrayList<Complaint>();

		Criteria criteria = session.createCriteria(Complaint.class);
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
	public Complaint getComplaintByUser(String User) {
		Session session = sessionFactory.getCurrentSession();
		List<Complaint> list = new ArrayList<Complaint>();

		Criteria criteria = session.createCriteria(Complaint.class);
		if (!(null == User && "".equals(User))) {
			criteria.add(Restrictions.eq("user", User));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Complaint getComplaintByState(String State) {
		Session session = sessionFactory.getCurrentSession();
		List<Complaint> list = new ArrayList<Complaint>();

		Criteria criteria = session.createCriteria(Complaint.class);
		if (!(null == State && "".equals(State))) {
			criteria.add(Restrictions.eq("state", State));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> findAllComplaint() {
		Session session = sessionFactory.getCurrentSession();
		List<Complaint> list = new ArrayList<Complaint>();
		Criteria criteria = session.createCriteria(Complaint.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> getComplaint(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM Complaint");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<Complaint> message = query.list();
		session.close();
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> getComplaintByUser(String User, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Complaint> list = new ArrayList<Complaint>();
		try {
			String hql = "FROM Complaint WHERE user = ?0";
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
	public List<Complaint> getComplaintByState(String State, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Complaint> list = new ArrayList<Complaint>();
		try {
			String hql = "FROM Complaint WHERE state = ?0";
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
	public int getComplaintCount() {
		List<Complaint> list = findAllComplaint();
		return list.size();
	}

	@Override
	public int getComplaintByUserCount(String User) {
		List<Complaint> lst = getComplaintByUser(User, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int getComplaintByStateCount(String State) {
		List<Complaint> lst = getComplaintByState(State, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int addComplaint(Complaint Complaint) {
		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Complaint.setId(id);
			session.save(Complaint);
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
	public int editComplaint(Complaint Complaint) {
		int id = Complaint.getId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(Complaint);
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
	public int deleteComplaint(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM Complaint WHERE id=?0";
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
		String sql = "SELECT max(id) FROM complaintmgt";
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
