package cn.edu.hzvtc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.edu.hzvtc.dao.BaseDao;
import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.util.PaginationUtil;

/**
 * dao层基类的实现
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	private SessionFactory sessionFactory;
	private Class<T> clazz; // T的具体类

	/**
	 * 设置当前工作的SessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取当前工作的Session
	 */
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 通过构造方法指定DAO的具体实现类
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void update(Object entity) {
		getSession().update(entity);
	}

	@Override
	public void partUpdate(int id, String[] names, Object[] values) {
		String tab = clazz.getSimpleName();
		String hql = "update " + tab + " t";
		for (int i = 0; i < names.length; i++) {
			hql += " set t." + names[i] + "=?";
		}
		hql += " where t.id=" + id;
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.executeUpdate();
	}

	@Override
	public void delete(Serializable id) {
		T obj = findById(id);
		getSession().delete(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHQL(String hql, Object... params) {
		List<T> list = null;
		Query query = getSession().createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginationUtil<T> queryPage(String hql, int pageNo, int pageSize) {
		PaginationUtil<T> pageUtil = new PaginationUtil<T>(pageNo, pageSize);
		Query query = getSession().createQuery(hql);
		List<T> items = query.list();
		if(items!=null){
		pageUtil.setTotalCount(items.size());
		items = query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();
		pageUtil.setItems(items);
	}
		return pageUtil;
	}
}