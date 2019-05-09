package cn.edu.hzvtc.service.impl;

import java.util.Date;

import cn.edu.hzvtc.dao.CheckResultDao;
import cn.edu.hzvtc.dao.ClaimVoucherDao;
import cn.edu.hzvtc.dao.EmployeeDao;
import cn.edu.hzvtc.entity.CheckResult;
import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.service.CheckResultService;
import cn.edu.hzvtc.util.ConstantUtil;

public class CheckResultServiceImpl implements CheckResultService {
	private ClaimVoucherDao claimVoucherDao;
	private CheckResultDao checkResultDao;
	private EmployeeDao  employeeDao;


	public ClaimVoucherDao getClaimVoucherDao() {
		return claimVoucherDao;
	}
	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}
	public CheckResultDao getCheckResultDao() {
		return checkResultDao;
	}
	public void setCheckResultDao(CheckResultDao checkResultDao) {
		this.checkResultDao = checkResultDao;
	}
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public boolean saveCheckResult(CheckResult checkResult ){//保存审核后的结果
		boolean bRet = false;
		try{
			long claimId=checkResult.getClaimVoucher().getId();
			ClaimVoucher claimVoucher=(ClaimVoucher)claimVoucherDao.findClaimVoucherById(claimId);
			Employee empCheck=checkResult.getCheckEmployee();//得到提交者
			claimVoucher=updateClaimVoucherStatus(empCheck.getPosition()
					.getNameCn(),checkResult.getResult(),claimVoucher);//调用私有方法更新报销单状态
			claimVoucher.setModifyTime(new Date(System.currentTimeMillis()));//设置更新时间
			claimVoucherDao.update(claimVoucher);//数据库更新报销单状态


			checkResult.setCheckTime(new  Date(System.currentTimeMillis()) );//设置审批时间
			checkResultDao.save(checkResult);//更新审批

			bRet=true;//成功
		}catch (Exception e){
			e.printStackTrace();
		}
		return bRet;

	}
	private ClaimVoucher updateClaimVoucherStatus(String position, String checkResult,
			ClaimVoucher claimVoucher) {
		if(checkResult.equals(ConstantUtil.CHECKRESULT_PASS)){
			//已通过
			if(position.equals(ConstantUtil.POSITION_FM)){//部门经理
				if(claimVoucher.getTotalAccount()>=5000){
					//待审批，并且处理人为总经理
					claimVoucher.setStatus(ConstantUtil.CLAIMVOUCHER_APPROVING);//待审批
					claimVoucher.setNextDeal(employeeDao.getGeneralManager());//处理人总经理
				}else{
					//已审批，处理人为财务
					claimVoucher.setStatus(ConstantUtil.CLAIMVOUCHER_APPROVED);//已审批
					claimVoucher.setNextDeal(employeeDao.getCashier());//处理人财务
				}
			}else if(position.equals(ConstantUtil.POSITION_GM)){//总经理
				//财务已审批，处理人为null
				claimVoucher.setStatus(ConstantUtil.CLAIMVOUCHER_APPROVED);//已审批
				claimVoucher.setNextDeal(employeeDao.getCashier());
			}else{
				claimVoucher.setStatus(ConstantUtil.CLAIMVOUCHER_PAID);//已付款
				claimVoucher.setNextDeal(null);//处理人为null
			}
		}else if(checkResult.equals(ConstantUtil.CHECKRESULT_REJECT)){
			//已拒绝
			claimVoucher.setStatus(ConstantUtil.CLAIMVOUCHER_TERMINATED);//已终止
			claimVoucher.setNextDeal(null);
		}else if(checkResult.equals(ConstantUtil.CHECKRESULT_BACK)){
			//已打回
			claimVoucher.setStatus(ConstantUtil.CLAIMVOUCHER_BACK);//打回
			claimVoucher.setNextDeal(claimVoucher.getCreator());//处理人自己
		}
		return claimVoucher;
	}



}
