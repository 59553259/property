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
import com.wxcloud.dao.RepairDao;
import com.wxcloud.pojo.Repair;
import com.wxcloud.util.Util;

@Repository(value = "repairDao")
public class RepairDaoImpl extends BaseDaoHibernate implements RepairDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Repair getRepairById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Repair> list = new ArrayList<Repair>();

		Criteria criteria = session.createCriteria(Repair.class);
		if (id > 0) {
			criteria.add(Restrictions.eq("id", id));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addRepair(Repair repair) {

		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			repair.setId(id);
			session.save(repair);
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
	public int editRepair(Repair repair) {
		int id = repair.getId();
		System.out.println(repair.getRepairCondition());
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(repair);
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
	public int deleteRepair(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM Repair WHERE id=?0";
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
	public Repair getRepairType(String RepairType) {
		Session session = sessionFactory.getCurrentSession();
		List<Repair> list = new ArrayList<Repair>();

		Criteria criteria = session.createCriteria(Repair.class);
		if (!(null == RepairType && "".equals(RepairType))) {
			criteria.add(Restrictions.eq("RepairType", RepairType));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Repair> getRepair(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM Repair");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<Repair> message = query.list();
		session.close();
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Repair> getRepairByType(String RepairType, int currentPaging,
			int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Repair> list = new ArrayList<Repair>();
		try {
			String hql = "FROM Repair WHERE RepairType = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", RepairType);
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

	private int getValidId() {
		int id = 0;
		String sql = "SELECT max(id) FROM repairmgt";
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

	@Override
	public int getRepairTypeCount(String RepairType) {
		List<Repair> lst = getRepairByType(RepairType, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int getRepairCount() {
		List<Repair> list = findAllRepair();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Repair> findAllRepair() {
		Session session = sessionFactory.getCurrentSession();
		List<Repair> list = new ArrayList<Repair>();
		Criteria criteria = session.createCriteria(Repair.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
