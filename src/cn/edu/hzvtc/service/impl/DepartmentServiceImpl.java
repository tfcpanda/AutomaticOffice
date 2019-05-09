package cn.edu.hzvtc.service.impl;

import java.util.List;


import cn.edu.hzvtc.dao.DepartmentDao;
import cn.edu.hzvtc.entity.Department;
import cn.edu.hzvtc.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
private DepartmentDao departmentDao;
	
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentDao.getAll();
	}


}
