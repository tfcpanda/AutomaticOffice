package cn.edu.hzvtc.dao;

import java.util.List;

import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.util.PaginationUtil;

public interface LeaveDao extends BaseDao<Leave> {

	PaginationUtil<Leave> getLeavePage(String createSn, String dealSn, String startDate,
			String endDate, int pageNo, int pageSize);

}
