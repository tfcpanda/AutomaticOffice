package cn.edu.hzvtc.service.impl;



import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.edu.hzvtc.dao.LeaveDao;
import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.service.LeaveService;
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public    class LeaveServiceImpl implements LeaveService {
        private LeaveDao  leaveDao;

		public LeaveDao getLeaveDao() {
			return leaveDao;
		}

		public void setLeaveDao(LeaveDao leaveDao) {
			this.leaveDao = leaveDao;
		}

		@Override
		public PaginationUtil<Leave> getLeavePage(String createSn,
				String dealSn, String startDate, String endDate,
				Integer pageNo, Integer pageSize) {
		
			return    leaveDao.getLeavePage(createSn,dealSn,startDate,endDate,
		pageNo,pageSize);
		}


        	//新增请假单
	
		public boolean saveLeave(Leave leave){
			boolean bRet = false;
			try{
				leave.setStatus(ConstantUtil.LEAVESTATUS_APPROVING);
				leave.setCreateTime(new Date());//自动保存当前时间
				leaveDao.save(leave);
				
				bRet=true;
			}catch(Exception e){
				e.printStackTrace();
			}
			return bRet;
		}
		
		
		public Map<String, String> getLeaveTypeMap() {
			// 加载所有请假类型
			Map<String, String> leaveMap = new LinkedHashMap<String, String>();
			leaveMap.put(ConstantUtil.LEAVE_ANNUAL, ConstantUtil.LEAVE_ANNUAL);
			leaveMap.put(ConstantUtil.LEAVE_CASUAL, ConstantUtil.LEAVE_CASUAL);
			leaveMap.put(ConstantUtil.LEAVE_MARRIAGE, ConstantUtil.LEAVE_MARRIAGE);
			leaveMap.put(ConstantUtil.LEAVE_SICK, ConstantUtil.LEAVE_SICK);
			
			return leaveMap;
		}
		//根据id查请假单详情
		public Leave findLeaveById(Long id){
			return (Leave)leaveDao.findById(id);
		}

	public boolean checkLeave(Leave leave){
		boolean bRet=false;//默认失败
		try{
		Leave oldLeave=leaveDao.findById(leave.getId());//得到当前请假数据
		oldLeave.setStatus(leave.getStatus());//设置状态
		oldLeave.setApproveOpinion(leave.getApproveOpinion());//审批意见
		oldLeave.setModifyTime(new Date());//时间
		leaveDao.update(oldLeave);//保存
		bRet=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return bRet;
		
	}
		
		
}
