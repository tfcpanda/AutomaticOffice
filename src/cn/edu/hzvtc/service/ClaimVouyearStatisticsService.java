package cn.edu.hzvtc.service;

import java.util.List;

import cn.edu.hzvtc.entity.ClaimVouyearStatistics;
import cn.edu.hzvtc.util.PaginationUtil;

public interface ClaimVouyearStatisticsService {
	void saveVoucherStatisticsByYear();
	
	List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByYear(
			int currYear);

	List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByPage(
			int startYear, int endYear);

	PaginationUtil<ClaimVouyearStatistics> getDeptClaimVoucherStatisticsByPage(
			int startYear, int endYear, int departmentId, int pageNo,
			int pageSize);
}
