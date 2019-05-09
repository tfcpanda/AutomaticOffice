package cn.edu.hzvtc.action;

import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.exception.JboaException;
import cn.edu.hzvtc.service.EmployeeService;
import cn.edu.hzvtc.util.ConstantUtil;

public class EmployeeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService; // 用户业务类
	private Employee employee;//登录页面传递的用户对象
	private String validationCode;//验证码
	private Employee manager;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String login() {

		//System.out.println(employeeService.getEmployeeBySN("001"));
		// 登录
		try {
			//后台生成的验证码
			String code=(String)this.getSession().get("validationCode");
			if(code.equals(validationCode)){

				employee=employeeService.login(employee);
				manager=employeeService.getManager(employee);
				//存放employee
				this.getSession().put(ConstantUtil.AUTH_EMPLOYEE,employee);
				//存放manager
				this.getSession().put(ConstantUtil.AUTH_EMPLOYEE_MANAGER,manager);
				//存放职位中文
				this.getSession().put(ConstantUtil.EMPLOYEE_POSITION,employee.getPosition().getNameCn());

			}else{
				addActionError(getText("错误:验证码输入有误！"));
			}		
		} catch (JboaException ex) {

			addActionError(getText("错误:用户名或密码输入有误！"));
		}
		if(hasActionErrors()){
			return INPUT;
		}
		return SUCCESS;
	}

	public String logout(){
		this.getSession().put(ConstantUtil.AUTH_EMPLOYEE,null);

		this.getSession().put(ConstantUtil.AUTH_EMPLOYEE_MANAGER,null);

		this.getSession().put(ConstantUtil.EMPLOYEE_POSITION,null);

		return "logoutSuccess";

	}


	public String add(Employee employee) {

		employeeService.saveEmployee(employee);

		// 添加
		return NONE;
	}

	public void delete(String de) {
		//删除
		employeeService.delete(de);
	}

	public void update(Employee employee) {
		//修改
		employeeService.update(employee);
	}

	public Employee getEmployee(String sN) {
		return employeeService.getEmployeeBySN(sN);
	}



}
