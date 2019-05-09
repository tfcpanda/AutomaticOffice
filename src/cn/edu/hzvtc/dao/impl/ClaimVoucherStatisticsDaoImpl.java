package cn.edu.hzvtc.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.edu.hzvtc.dao.ClaimVoucherStatisticsDao;
import cn.edu.hzvtc.dao.DepartmentDao;
import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.entity.ClaimVouyearStatistics;
import cn.edu.hzvtc.entity.Department;
import cn.edu.hzvtc.util.DateUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class ClaimVoucherStatisticsDaoImpl extends
		BaseDaoImpl<ClaimVoucherStatistics> implements
		ClaimVoucherStatisticsDao {
	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@SuppressWarnings("unchecked")
	public void saveBySchedulerSearch() {
		// 查询
		System.out.println("开始执行入库");
		try {
			// 得到上个月的起止时间
			Date firstDayOfLastMonth = DateUtil.getFirstDayOfLastMonth();
			Date lastDayOfLastMonth = DateUtil.getLastDayOfLastMonth();

			String hql = "SELECT SUM(bcv.totalAccount),bcv.creator.department.id"
					+ " FROM ClaimVoucher bcv"
					+ " WHERE bcv.modifyTime <= '"
					+ DateUtil.dateToStr(lastDayOfLastMonth,
							"yyyy-MM-dd hh:mm:ss")
					+ "' AND bcv.modifyTime >= '"
					+ DateUtil.dateToStr(firstDayOfLastMonth,
							"yyyy-MM-dd hh:mm:ss")
					+ "' AND bcv.status = '已付款'"
					+ " GROUP BY bcv.creator.department.name";

			List<Object[]> list = this.getSession().createQuery(hql).list();
			// 入库
			int year = DateUtil.getYear(firstDayOfLastMonth);// 入库的年份
			int month = DateUtil.getMonth(firstDayOfLastMonth);// 入库的月份

			Iterator<Object[]> it = list.iterator();
			while (it.hasNext()) {// 遍历上个月所有报销信息的部门
				ClaimVoucherStatistics result = new ClaimVoucherStatistics();
				Object[] o = (Object[]) it.next();
				result.setTotalCount((Double) o[0]);// 第0个元素是报销金额
				Department dept = departmentDao.findById((Integer) o[1]);// 第一个元素是报销id
				// Integer
				result.setDepartment(dept);
				result.setYear(year);
				result.setMonth(month);
				result.setModifyTime(new Date());// 设置所有的result属性
				this.save(result);// 保存
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("入库完毕");
	}

	@Override
	public PaginationUtil<ClaimVoucherStatistics> getDeptClaimVoucherStatisticByPage(
			int year, int startMonth, int endMonth, int departmentId,
			int pageNo, int pageSize) {
		String hql = "FROM ClaimVoucherStatistics cvs ";
		String strCondition = "";
		if (year != 0) {// 年份条件
			strCondition = strCondition + " cvs.year=" + year;
		}
		if (startMonth != 0) {// 起始月份条件
			if (!strCondition.equals("")) {
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " cvs.month>=" + startMonth;
		}
		if (endMonth != 0) {// 结束月份条件
			if (!strCondition.equals("")) {
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " cvs.month<=" + endMonth;
		}
		if (departmentId != 0) {// 部门条件
			if (!strCondition.equals("")) {
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " cvs.department.id=" + departmentId;
		}
		if (!strCondition.equals("")) {
			hql = hql + " WHERE " + strCondition;
		}
		hql = hql + " ORDER BY cvs.month DESC";
		return this.queryPage(hql, pageNo, pageSize);
	}

	@Override
	public void update(ClaimVoucherStatistics entity) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClaimVoucherStatistics> getCompClaimVoucherByMonth(int year,
			int startMonth, int endMonth) {
		List<ClaimVoucherStatistics> result = new ArrayList<ClaimVoucherStatistics>();
		if (endMonth == 0) {
			endMonth = 12;
		}
		String hql = "SELECT SUM(cvs.totalCount), cvs.year,cvs.month "
				+ " FROM ClaimVoucherStatistics cvs" + " WHERE cvs.month >= "
				+ startMonth + " AND cvs.month <= " + endMonth;
		if (year > 0) {
			hql = hql + " AND cvs.year = " + year;
		}
		hql = hql + " GROUP BY cvs.year, cvs.month "
				+ " ORDER BY cvs.year DESC, cvs.month DESC";
		List<Object[]> list = this.getSession().createQuery(hql).list();
		for (Object[] o : list) {
			ClaimVoucherStatistics cvs = new ClaimVoucherStatistics();
			cvs.setTotalCount((Double) o[0]);
			cvs.setYear((Integer) o[1]);
			cvs.setMonth((Integer) o[2]);
			result.add(cvs);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClaimVoucherStatistics> getClaimVoucherStatisticsByDateAndDept(int year,int month){
		try{
			String hql = "FROM ClaimVoucherStatistics cvs WHERE cvs.year=" +year;
			if(month > 0){
				hql = hql + " AND cvs.month=" + month;
			}
			hql = hql + " ORDER BY cvs.department.id";
			return this.getSession().createQuery(hql).list();
		}catch(Exception e){
			return null;
		}
	
	}
	@SuppressWarnings("unchecked")
	public List<ClaimVouyearStatistics> getCompClaimVoucherStatisticsByPage(int startYear, int endYear){
			List<ClaimVouyearStatistics> result = new ArrayList<ClaimVouyearStatistics>();
			if(endYear==0){
				endYear = 9999;
			}
			String hql = " SELECT SUM(cvs.totalCount), cvs.year  "
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
			
	
}
