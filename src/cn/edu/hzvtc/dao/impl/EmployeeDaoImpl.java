package cn.edu.hzvtc.dao.impl;

import java.util.List;

import cn.edu.hzvtc.dao.EmployeeDao;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.util.ConstantUtil;

public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements
EmployeeDao {
	@Override
	public List<Employee> findEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeeBySn(String sn) {
		String hql = "FROM Employee e WHERE e.sn='" + sn + "'";
		Object[] params = null;
		return findByHQL(hql, params).get(0);
		//		return  this.findById(sn);
	}

	@Override
	public Employee getManager(Employee employee) {
		// TODO Auto-generated method stub
		String hql = "FROM Employee e WHERE e.department.id = ? and e.position.nameCn=?";
		List<Employee> list = this.findByHQL(hql, employee.getDepartment().getId(),ConstantUtil.POSITION_FM);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public Employee getGeneralManager() {
		String hql = "FROM Employee e WHERE  e.position.nameCn=?";
		List<Employee> list = this.findByHQL(hql,ConstantUtil.POSITION_GM);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public Employee getCashier() {
		String hql = "FROM Employee e WHERE  e.position.nameCn=?";
		List<Employee> list = this.findByHQL(hql,ConstantUtil.POSITION_CASHIER);
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}

}
