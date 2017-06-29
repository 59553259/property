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
import com.wxcloud.dao.ExpressDao;
import com.wxcloud.pojo.Express;
import com.wxcloud.util.Util;

@Repository(value = "expressDao")
public class ExpressDaoImpl extends BaseDaoHibernate implements ExpressDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Express getExpressById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Express> list = new ArrayList<Express>();

		Criteria criteria = session.createCriteria(Express.class);
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
	public Express getExpressByPhone(String Phone) {
		Session session = sessionFactory.getCurrentSession();
		List<Express> list = new ArrayList<Express>();

		Criteria criteria = session.createCriteria(Express.class);
		if (!(null == Phone && "".equals(Phone))) {
			criteria.add(Restrictions.eq("Phone", Phone));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Express getExpressByState(String State) {
		Session session = sessionFactory.getCurrentSession();
		List<Express> list = new ArrayList<Express>();

		Criteria criteria = session.createCriteria(Express.class);
		if (!(null == State && "".equals(State))) {
			criteria.add(Restrictions.eq("State", State));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Express> findAllExpreee() {
		Session session = sessionFactory.getCurrentSession();
		List<Express> list = new ArrayList<Express>();
		Criteria criteria = session.createCriteria(Express.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Express> getExpress(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM Express");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<Express> message = query.list();
		session.close();
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Express> getExpressByPhone(String Phone, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Express> list = new ArrayList<Express>();
		try {
			String hql = "FROM Express WHERE CustomPhone = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", Phone);
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
	public List<Express> getExpressByState(String State, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Express> list = new ArrayList<Express>();
		try {
			String hql = "FROM Express WHERE State = ?0";
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
	public int getExpressCount() {
		List<Express> list = findAllExpreee();
		return list.size();
	}

	@Override
	public int getExpressByPhoneCount(String Phone) {
		List<Express> lst = getExpressByPhone(Phone, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int getExpressByStateCount(String State) {
		List<Express> lst = getExpressByState(State, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int addExpress(Express express) {
		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			express.setId(id);
			session.save(express);
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
	public int editExpress(Express express) {
		int id = express.getId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(express);
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
	public int deleteExpress(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM Express WHERE id=?0";
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
		String sql = "SELECT max(id) FROM expressmgt";
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
