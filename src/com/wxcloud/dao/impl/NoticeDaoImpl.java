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
import com.wxcloud.dao.NoticeDao;
import com.wxcloud.pojo.Notice;
import com.wxcloud.util.Util;

@Repository(value = "noticeDao")
public class NoticeDaoImpl extends BaseDaoHibernate implements NoticeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public Notice getNoticeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<Notice> list = new ArrayList<Notice>();

		Criteria criteria = session.createCriteria(Notice.class);
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
	public Notice getNoticeByInfoTitle(String InfoTitle) {
		Session session = sessionFactory.getCurrentSession();
		List<Notice> list = new ArrayList<Notice>();

		Criteria criteria = session.createCriteria(Notice.class);
		if (!(null == InfoTitle && "".equals(InfoTitle))) {
			criteria.add(Restrictions.eq("InfoTitle", InfoTitle));
		}
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> findAllNotice() {
		Session session = sessionFactory.getCurrentSession();
		List<Notice> list = new ArrayList<Notice>();
		Criteria criteria = session.createCriteria(Notice.class);
		list = criteria.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> getNotice(int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session.createQuery("FROM Notice");
		int startRow = (currentPaging - 1) * pageSize;
		query.setFirstResult(startRow);
		query.setMaxResults(pageSize);
		List<Notice> message = query.list();
		session.close();
		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notice> getNoticeByInfoTitle(String InfoTitle,
			int currentPaging, int pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		List<Notice> list = new ArrayList<Notice>();
		try {
			String hql = "FROM Notice WHERE InfoTitle = ?0";
			Query query = session.createQuery(hql);
			query.setParameter("0", InfoTitle);
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
	public int getNoticeCount() {
		List<Notice> list = findAllNotice();
		return list.size();
	}

	@Override
	public int getNoticeByInfoTitleCount(String InfoTitle) {
		List<Notice> lst = getNoticeByInfoTitle(InfoTitle, 1, Util.PAGE_MAXSIZE);
		if (lst == null) {
			return 0;
		}
		return lst.size();
	}

	@Override
	public int addNotice(Notice Notice) {
		int id = getValidId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			Notice.setId(id);
			session.save(Notice);
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
	public int editNotice(Notice Notice) {
		int id = Notice.getId();
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		try {
			session.update(Notice);
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
	public int deleteNotice(int id) {
		int ret = id;
		Session session = sessionFactory.openSession();
		String hql = "DELETE FROM Notice WHERE id=?0";
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
		String sql = "SELECT max(id) FROM noticemgt ";
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
