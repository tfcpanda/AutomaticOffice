package cn.edu.hzvtc.service;

import java.util.List;

import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.util.PaginationUtil;

public interface ClaimVoucherStatisticsService {




	void saveVoucherStatisticsByMonth();

	PaginationUtil<ClaimVoucherStatistics> getDeptClaimVoucherStatisticsByPage(
			int year, int startMonth, int endMonth, int departmentId, int pageNo,
			int pageSize);

	List<ClaimVoucherStatistics> getComClaimVoucherStatisticsByMonth(int year,
			int startMonth, int endMonth);

	List<ClaimVoucherStatistics> getClaimVoucherStatisticsByDateAndDept(int year,
			int month);








}
