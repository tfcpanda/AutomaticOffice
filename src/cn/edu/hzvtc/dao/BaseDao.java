package cn.edu.hzvtc.dao;

import java.io.Serializable;
import java.util.List;

import cn.edu.hzvtc.util.PaginationUtil;

/**
 * dao层基类，实现增删改查
 */
public interface BaseDao<T> {

	// 添加一个对象
	void save(T entity);

	// 更新一个对象，所有属性
	void update(T entity);

	// 更新一个对象，部分属性
	void partUpdate(int id, String[] names, Object[] values);

	// 删除一个对象
	void delete(Serializable id);

	// 根据id查找一个对象
	T findById(Serializable id);

	// 根据HQL返回对象List
	List<T> findByHQL(String hql, Object... params);

	// 分页查询
	PaginationUtil<T> queryPage(String hql, int pageNo, int pageSize);
}
