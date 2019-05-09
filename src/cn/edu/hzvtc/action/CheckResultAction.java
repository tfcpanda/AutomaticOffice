package cn.edu.hzvtc.action;

import java.util.List;
import java.util.Map;

import cn.edu.hzvtc.entity.CheckResult;
import cn.edu.hzvtc.entity.Department;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.service.CheckResultService;
import cn.edu.hzvtc.service.DepartmentService;
import cn.edu.hzvtc.service.LeaveService;

public class CheckResultAction extends BaseAction{
	private static final long serialVersionUID=1L;
	
	private CheckResultService checkResultService;
	private CheckResult checkResult;
	
	public CheckResultService getCheckResultService() {
		return checkResultService;
	}
	public void setCheckResultService(CheckResultService checkResultService) {
		this.checkResultService = checkResultService;
	}
	public CheckResult getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(CheckResult checkResult) {
		this.checkResult = checkResult;
	}
	
	public String checkClaimVoucher(){//保存审核结果
		//根据保存checkResult的结果，如果成功，返回success，否则，返回input
		checkResult.setCheckEmployee(getLoginEmployee());//设置审批人(经理)
		boolean bRet = checkResultService.saveCheckResult(checkResult);//保存审批信息
		if(bRet){
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
}

