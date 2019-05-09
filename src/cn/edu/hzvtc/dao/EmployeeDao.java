package cn.edu.hzvtc.dao;

import java.util.List;

import cn.edu.hzvtc.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee> {

	public List<Employee> findEmployee(Employee emp);

	public Employee findEmployeeBySn(String sn);

	public Employee getManager(Employee employee);

	public Employee getGeneralManager();

	public Employee getCashier();

}
