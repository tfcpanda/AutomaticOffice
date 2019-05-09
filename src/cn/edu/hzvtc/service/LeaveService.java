package cn.edu.hzvtc.service;

import java.util.Map;

import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.util.PaginationUtil;

public interface LeaveService {

	PaginationUtil<Leave>  getLeavePage(String createSn, String dealSn,
			String startDate, String endDate, Integer pageNo, Integer pageSize);

	boolean  saveLeave(Leave leave);

	Leave findLeaveById(Long id);

	

	Map<String, String> getLeaveTypeMap();

	boolean checkLeave(Leave leave);

	


}
