package cn.edu.hzvtc.dao;

import java.util.List;

import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.util.PaginationUtil;

public interface ClaimVoucherStatisticsDao extends
		BaseDao<ClaimVoucherStatistics> {

	void saveBySchedulerSearch();

	PaginationUtil<ClaimVoucherStatistics> getDeptClaimVoucherStatisticByPage(
			int year, int startMonth, int endMonth, int departmentId,
			int pageNo, int pageSize);

	List<ClaimVoucherStatistics> getCompClaimVoucherByMonth(int year,
			int startMonth, int endMonth);

	List<ClaimVoucherStatistics> getClaimVoucherStatisticsByDateAndDept(
			int year, int month);


}
