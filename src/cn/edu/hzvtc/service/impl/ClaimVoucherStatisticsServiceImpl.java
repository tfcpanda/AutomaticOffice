package cn.edu.hzvtc.service.impl;

import java.util.List;

import cn.edu.hzvtc.dao.ClaimVoucherStatisticsDao;
import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.service.ClaimVoucherStatisticsService;
import cn.edu.hzvtc.util.PaginationUtil;

public class ClaimVoucherStatisticsServiceImpl implements
		ClaimVoucherStatisticsService {
	private ClaimVoucherStatisticsDao claimVoucherStatisticsDao;

	public ClaimVoucherStatisticsDao getClaimVoucherStatisticsDao() {
		return claimVoucherStatisticsDao;
	}

	public void setClaimVoucherStatisticsDao(
			ClaimVoucherStatisticsDao claimVoucherStatisticsDao) {
		this.claimVoucherStatisticsDao = claimVoucherStatisticsDao;
	}
	
	@Override
	public void saveVoucherStatisticsByMonth(){
		try{
			claimVoucherStatisticsDao.saveBySchedulerSearch();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public PaginationUtil<ClaimVoucherStatistics> getDeptClaimVoucherStatisticsByPage(
			int year,int startMonth,int endMonth,int departmentId,int pageNo,int pageSize){
		PaginationUtil<ClaimVoucherStatistics> list =claimVoucherStatisticsDao.getDeptClaimVoucherStatisticByPage(year, startMonth, endMonth, departmentId, pageNo, pageSize);
		return list;
		
	}
	@Override
	public List<ClaimVoucherStatistics> getComClaimVoucherStatisticsByMonth(int year,int startMonth, int endMonth){
		List<ClaimVoucherStatistics> list = claimVoucherStatisticsDao.getCompClaimVoucherByMonth(year, startMonth, endMonth);
		return list;
	}
	
	@Override
	public List<ClaimVoucherStatistics> getClaimVoucherStatisticsByDateAndDept(int year,int month){
		List<ClaimVoucherStatistics> list = claimVoucherStatisticsDao.getClaimVoucherStatisticsByDateAndDept(year, month);
		return list;
	}




}
