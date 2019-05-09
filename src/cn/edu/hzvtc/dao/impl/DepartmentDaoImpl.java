package cn.edu.hzvtc.dao.impl;

import java.util.List;

import cn.edu.hzvtc.dao.DepartmentDao;
import cn.edu.hzvtc.entity.Department;

public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements
		DepartmentDao {

	@Override
	public void update(Department entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		Object[]params=null;
		return this.findByHQL("FROM Department d", params);
	}
     
}
