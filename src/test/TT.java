package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.hzvtc.action.EmployeeAction;
import cn.edu.hzvtc.entity.Department;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.entity.Position;
import cn.edu.hzvtc.service.EmployeeService;

public class TT {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		EmployeeAction empAction = (EmployeeAction) ctx
				.getBean("employeeAction");

		empAction.login();

	}

	@Test
	public void addEmployee(){
		try{
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"applicationContext.xml");

			EmployeeAction empAction = (EmployeeAction) ctx
					.getBean("employeeAction");

			//EmployeeService  employeeService=(EmployeeAction)ctx.getBean("employeeService");
			//	Employee emp=employeeService.getEmployeeBySN("001");
			//	System.out.println(emp);

			Employee employee=new Employee();
			employee.setSn("023");
			employee.setPassword("111");
			employee.setName("小志");
			employee.setStatus("在职");
			Department department=new  Department();
			Position position=new Position();

			department.setId(3);
			position.setId(2);
			employee.setPosition(position);
			employee.setDepartment(department);
			empAction.add(employee);
			//employeeService.saveEmployee(employee);
			//System.out.println(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void deleteEmployee(){
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			EmployeeAction empAction = (EmployeeAction) ctx.getBean("employeeAction");

			String de = "019";

			empAction.delete(de);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void updateEmployee(){
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			EmployeeAction empAction = (EmployeeAction) ctx.getBean("employeeAction");

			String SN = "020";
			Employee employee = empAction.getEmployee(SN);
			employee.setPassword("321");
			employee.setName("号号");
			employee.setStatus("离职");

			empAction.update(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
