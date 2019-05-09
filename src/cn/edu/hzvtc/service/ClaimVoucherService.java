package cn.edu.hzvtc.service;

import java.util.List;
import java.util.Map;

import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.util.PaginationUtil;

public interface ClaimVoucherService {



	PaginationUtil<ClaimVoucher> getClaimVoucherPage(String createSn,
			String dealSn, String status, String startDate, String endDate,
			int pageNo, int pageSize);

	Map<String, String> getAllStatusMap();

	ClaimVoucher findClaimVoucherById(Long id);

	boolean deleteClaimVoucherById(Long id);

	boolean saveClaimVoucher(ClaimVoucher claimVoucher);

	boolean updateClaimVoucher(ClaimVoucher newClaimVoucher);

	List<ClaimVoucher> getClaimVoucherByModifyDate(int year, int selectMonth,
			int departmentId);

}
