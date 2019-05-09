package cn.edu.hzvtc.dao;

import cn.edu.hzvtc.entity.ClaimVoucherDetail;

public interface ClaimVoucherDetailDao extends BaseDao<ClaimVoucherDetail>{

	void deleteDetailByClaimVoucherId(Long id);

}
