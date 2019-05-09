package cn.edu.hzvtc.dao;

import java.util.List;

import cn.edu.hzvtc.entity.ClaimVouyearStatistics;
import cn.edu.hzvtc.util.PaginationUtil;

public interface ClaimVouyearStatisticsDao extends
BaseDao<ClaimVouyearStatistics> {

	void saveBySchedulerSearchOfYear();

	PaginationUtil<ClaimVouyearStatistics> getDeptClaimVoucherStatisticByPage(
			int startYear, int endYear, int departmentId, int pageNo,
			int pageSize);

	List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByPage(
			int startYear, int endYear);

	List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByYear(int year);

}
