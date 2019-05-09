package cn.edu.hzvtc.action;

import java.util.List;
import java.util.Map;

import cn.edu.hzvtc.entity.Department;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.service.DepartmentService;
import cn.edu.hzvtc.service.LeaveService;

public class LeaveAction extends BaseAction{
	private static final long serialVersionUID =1L;
	private LeaveService leaveService;
	private DepartmentService departmentService;
	private Leave leave;
	private String startDate;
	private String endDate;
	
	private Employee nextDeal;
	
	public String searchLeave() {

		//查询请假单
		String createSn= "";
		String dealSn= "";
		if(isStaff()){//如果是员工
			createSn = getCurrentSn();//获取当前员工工号
		}else{ //可能是经理 部门经理 财务
			dealSn = getCurrentSn();
		}
		pageUtil = leaveService.getLeavePage(createSn,dealSn,
				startDate,endDate,pageNo,pageSize);
		return "list";//返回请假列表
	}
	
	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public Leave getLeave() {
		return leave;
	}
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public Map<String, String> getLeaveTypeMap() {
 
		return leaveService.getLeaveTypeMap();


	}
	public List<Department> getDepartmentList(){
	
		return departmentService.getAll();
	
		
	}
	public Employee getNextDeal() {
		return nextDeal;
	}
	public void setNextDeal(Employee nextDeal) {
		this.nextDeal = nextDeal;
	}

	public String toEdit(){
		//前往编辑
		return "edit";
	}
	
	public String saveLeave(){
		//保存请假
		Employee employee=(Employee) getSession().get("manager");
		leave.setNextDeal(employee);
		leaveService.saveLeave(leave);
		return "redirectList";
	}
	//查询出来一张请假单
	public String getLeaveById(){
		//根据id查询
		leave=leaveService.findLeaveById(leave.getId());
		return "view";
	}
	
	public String toCheck(){
		//前往审批
		leave=leaveService.findLeaveById(leave.getId());//加载当前请假数据
		return "check";
		
	}
	
	public String checkLeave(){
		//审批
	leaveService.checkLeave(leave);
		return "redirectList";
		
	}
	
}
