package cn.edu.hzvtc.service.impl;

import cn.edu.hzvtc.dao.EmployeeDao;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.exception.JboaException;
import cn.edu.hzvtc.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public EmployeeDao getemployeeDao() {
		return employeeDao;
	}

	public void setemployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public Employee getEmployeeBySN(String sn) {

		return employeeDao.findEmployeeBySn(sn);
	}

	@Override
	public boolean saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
       employeeDao.save(employee);
       
		return false;
	}

	@Override
	public Employee login(Employee emp) throws JboaException {
		Employee employee=employeeDao.findEmployeeBySn(emp.getSn());
		if(employee != null && employee.getPassword().equals(emp.getPassword())){
			return employee;
		}else  {
			throw new JboaException("Invalid sn or password");
		}
	
	}

	@Override
	public Employee getManager(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDao.getManager(employee);
	}

	@Override
	public Employee getGeneralManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getCashier() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(String de) {
		employeeDao.delete(de);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}
	
	
	
	
	
}
