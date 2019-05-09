package cn.edu.hzvtc.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.edu.hzvtc.dao.ClaimVouyearStatisticsDao;
import cn.edu.hzvtc.dao.DepartmentDao;
import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.entity.ClaimVouyearStatistics;
import cn.edu.hzvtc.entity.Department;
import cn.edu.hzvtc.util.DateUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class ClaimVouyearStatisticsDaoImpl extends
		BaseDaoImpl<ClaimVouyearStatistics> implements
		ClaimVouyearStatisticsDao {
	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void saveBySchedulerSearchOfYear(){
		//查询
		System.out.println("开始执行入库");
		try{
			//得到上个年度的起止时间
			int firstYear = DateUtil.getLastYear();
			String allFirstYear = firstYear + "-01-01";
			String allLastYear = firstYear + "-12-31";
			
			String hql = " SELECT SUM(bcv.totalAccount),bcv.creator.department.id" + " FROM ClaimVoucher bcv"+ " WHERE bcv.modifyTime <= '" + allLastYear + " 23:59:59" +"' AND bcv.modifyTime >= '"+ allFirstYear+" 00:00:00"+"' AND bcv.status = '已付款'"+" GROUP BY bcv.creator.department.name";
			
			List<Object[]> list = this.getSession().createQuery(hql).list();
			//入库
			Iterator<Object[]> it = list.iterator();
			while(it.hasNext()){//遍历上个月所有的报销信息的部门
				ClaimVouyearStatistics result = new ClaimVouyearStatistics();
				Object[] o =(Object[])it.next();
				result.setTotalCount((Double) o[0]);//第0个元素是报销金额
				Department dept = departmentDao.findById((Integer) o[1]);//第一个元素是部门id
				                                                                                                               //Integer
				result.setDepartment(dept);
				result.setYear(firstYear);
				result.setModifyTime(new Date());//设置所有的result属性
				this.save(result);//保存
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("入库完毕");
	}

	

	@Override
	public PaginationUtil<ClaimVouyearStatistics> getDeptClaimVoucherStatisticByPage(
			int startYear, int endYear, int departmentId, int pageNo,
			int pageSize) {
		String hql = "FROM ClaimVouyearStatistics cvs ";
		String strCondition = "";
		if(startYear !=0){
			strCondition = strCondition + " cvs.year>=" + startYear;
		}
		if(endYear != 0){
			if(!strCondition.equals("")){
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " cvs.year<=" + endYear;
		}
		if(departmentId !=0 ){
			if(!strCondition.equals("")){
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " cvs.department.id=" + departmentId;
		}
		if(!strCondition.equals("")){
			hql = hql +" WHERE " + strCondition;
		}
		hql = hql + " ORDER BY cvs.year DESC";
		return this.queryPage(hql, pageNo, pageSize);
		
	}

	@Override
	public List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByPage(
			int startYear, int endYear) {
		List<ClaimVouyearStatistics> result = new ArrayList<ClaimVouyearStatistics>();
		if(endYear==0){
			endYear = 9999;
		}
		String hql = " SELECT SUM(cvs.totalCount), cvs.year "
				+" FROM ClaimVouyearStatistics cvs " + "WHERE cvs.year >= "
				+ startYear + " AND cvs.year <=" + endYear
				+ " GROUP BY cvs.year " + " ORDER BY cvs.year DESC";
		List<Object[]> list = this.getSession().createQuery(hql).list();
		for(Object[] o : list){
			ClaimVouyearStatistics cvs = new ClaimVouyearStatistics();
			cvs.setTotalCount((Double) o[0]);
			cvs.setYear((Integer) o[1]);
			result.add(cvs);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByYear(int year){
		try{
			String hql = "FROM ClaimVouyearStatistics cvs WHERE cvs.year=" + year + " ORDER BY cvs.department.id";
			return this.getSession().createQuery(hql).list();
		}catch(Exception e){
			return null;
		}
	}
	
}
