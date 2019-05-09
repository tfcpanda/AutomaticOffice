package cn.edu.hzvtc.dao;

import java.util.List;

import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.util.PaginationUtil;

public interface ClaimVoucherDao extends BaseDao<ClaimVoucher> {


	PaginationUtil<ClaimVoucher> getClaimVoucherPage(String createSn,
			String dealSn, String status, String startDate, String endDate,
			Integer pageNo, Integer pageSize);

	ClaimVoucher findClaimVoucherById(Long id);

	List<Object[]> getClaimVoucherByModifyDate(int year, int month,
			int departmentId);



}
