package cn.edu.hzvtc.dao.impl;

import cn.edu.hzvtc.dao.ClaimVoucherDetailDao;
import cn.edu.hzvtc.entity.ClaimVoucherDetail;

public class ClaimVoucherDetailDaoImpl extends BaseDaoImpl<ClaimVoucherDetail>
		implements ClaimVoucherDetailDao {

	@Override
	public void update(ClaimVoucherDetail entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDetailByClaimVoucherId(Long id) {
		// TODO Auto-generated method stub
		String hql = " DELETE FROM ClaimVoucherDetail cvd WHERE cvd.claimVoucher.id = '"+id+"'";
		this.getSession().createQuery(hql).executeUpdate();
	}


}
