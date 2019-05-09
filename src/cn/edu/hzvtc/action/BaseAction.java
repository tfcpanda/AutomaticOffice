package cn.edu.hzvtc.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.PaginationUtil;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware,ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	public HttpServletResponse response;
	protected Integer pageNo = 1;
	protected Integer pageSize = 5;
	@SuppressWarnings("rawtypes")
	protected PaginationUtil pageUtil;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@SuppressWarnings("rawtypes")
	public PaginationUtil getPageUtil() {
		return pageUtil;
	}

	@SuppressWarnings("rawtypes")
	public void setPageUtil(PaginationUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	protected Employee getLoginEmployee() {
		if (this.getSession().get(ConstantUtil.AUTH_EMPLOYEE) == null) {
			return null; // 如果不是员工
		} else {
			return (Employee) getSession().get(ConstantUtil.AUTH_EMPLOYEE); // AUTH_EMPLYEE
																			// "employee"
		}
	}

	protected boolean isStaff() { // 判断是否是员工
		Employee employee = getLoginEmployee();
		if (employee.getPosition().getNameCn()
				.equals(ConstantUtil.POSITION_STAFF)) { // Constants.POSITION_STAFF
														// "员工"
			return true;
		} else {
			return false;
		}
	}

	protected String getCurrentSn() { // 返回当前id
		Employee employee = getLoginEmployee();
		return employee.getSn();
	}

	protected String getCurrManagerSn() {
		Employee employee = (Employee) getSession().get(
				ConstantUtil.AUTH_EMPLOYEE_MANAGER);
		return employee.getSn();
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
		
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
}
