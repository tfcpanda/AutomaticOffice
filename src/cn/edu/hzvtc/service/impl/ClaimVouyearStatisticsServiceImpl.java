package cn.edu.hzvtc.service.impl;

import java.util.List;

import cn.edu.hzvtc.dao.ClaimVouyearStatisticsDao;
import cn.edu.hzvtc.dao.DepartmentDao;
import cn.edu.hzvtc.entity.ClaimVouyearStatistics;
import cn.edu.hzvtc.service.ClaimVouyearStatisticsService;
import cn.edu.hzvtc.util.DateUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class ClaimVouyearStatisticsServiceImpl implements
		ClaimVouyearStatisticsService {
	private ClaimVouyearStatisticsDao claimVouyearStatisticsDao;

	public ClaimVouyearStatisticsDao getClaimVouyearStatisticsDao() {
		return claimVouyearStatisticsDao;
	}

	public void setClaimVouyearStatisticsDao(
			ClaimVouyearStatisticsDao claimVouyearStatisticsDao) {
		this.claimVouyearStatisticsDao = claimVouyearStatisticsDao;
	}
	
	public void saveVoucherStatisticsByYear(){
		try{
			claimVouyearStatisticsDao.saveBySchedulerSearchOfYear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public PaginationUtil<ClaimVouyearStatistics> getDeptClaimVoucherStatisticsByPage(int startYear ,int endYear, int departmentId ,int pageNo,int pageSize){
		PaginationUtil<ClaimVouyearStatistics> list = claimVouyearStatisticsDao.getDeptClaimVoucherStatisticByPage(startYear, endYear, departmentId, pageNo, pageSize);
		return list;
	}
	
	@Override
	public List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByPage(int startYear,int endYear){
		List<ClaimVouyearStatistics> list = claimVouyearStatisticsDao.getCompClaimVoucherStatisticsByPage(startYear, endYear);
		return list;
	}
	@Override
	public List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByYear(int year){
		List<ClaimVouyearStatistics> list = claimVouyearStatisticsDao.getCompClaimVoucherStatisticsByYear(year);
		return list;
	}



}
