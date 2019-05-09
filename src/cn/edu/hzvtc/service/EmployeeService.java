package cn.edu.hzvtc.service;

import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.exception.JboaException;

public interface EmployeeService {

	public Employee getEmployeeBySN(String sn);

	public boolean saveEmployee(Employee employee);

	public Employee login(Employee employee) throws JboaException;

	public Employee getManager(Employee employee);

	public Employee getGeneralManager();

	public Employee getCashier();
  

	
	public void delete(String de);
	
	void update(Employee employee);
}
