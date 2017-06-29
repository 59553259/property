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
import com.wxcloud.dao.HouseDao;
import com.wxcloud.pojo.House;
import com.wxcloud.util.Util;

@Repository(value = "houseDao")
public class HouseDaoImpl extends BaseDaoHibernate implements HouseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public House getHouseNumber(int HouseNumber) {
		Session session = sessionFactory.getCurrentSession();
		List<House> list = new ArrayList<House>();

		Criteria criteria = session.createCriteria(House.class);
		if (HouseNumber > 0) {
			criteria.add(Restrictions.eq("HouseNumber", HouseNumber));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<House> findAllHouse() {
		Session session = sessionFactory.getCurrentSession();
		List<House> list = new ArrayList<House>();
		Criteria criteria = session.createCriteria(House.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<House> getHouse(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM House");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<House> message = query.list();
		session.close();
		return message;
	}

	@Override
	public int getHouseCount() {
		List<House> list = findAllHouse();
		return list.size();
	}

	@Override
	public int addHouse(House house) {

		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			house.setId(id);
			session.save(house);
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
	public int editHouse(House House) {
		int id = House.getId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(House);
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
	public int deleteHouse(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM House WHERE id=?0";
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
	public House getHouseResidential(String Residential) {
		Session session = sessionFactory.getCurrentSession();
		List<House> list = new ArrayList<House>();

		Criteria criteria = session.createCriteria(House.class);
		if (!(null == Residential && "".equals(Residential))) {
			criteria.add(Restrictions.eq("Residential", Residential));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<House> getHouseByResidential(String Residential,
			int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<House> list = new ArrayList<House>();
		try {
			String hql = "FROM House WHERE Residential = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", Residential);
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
	public int getHouseByResidentialCount(String Residential) {
		List<House> lst = getHouseByResidential(Residential, 1,
				Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	private int getValidId() {
		int id = 0;
		String sql = "SELECT max(id) FROM housemgt";
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
	public House getHouseById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<House> list = new ArrayList<House>();

		Criteria criteria = session.createCriteria(House.class);
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
	public void insertHouses(List<Object> ls) {
		for (Object object : ls) {
			sessionFactory.getCurrentSession().save(object);
		}
	}

}
