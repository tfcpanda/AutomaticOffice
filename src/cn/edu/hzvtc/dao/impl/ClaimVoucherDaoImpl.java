package cn.edu.hzvtc.dao.impl;

import java.util.List;

import cn.edu.hzvtc.dao.ClaimVoucherDao;
import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.entity.ClaimVoucherDetail;

import cn.edu.hzvtc.util.DateUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class ClaimVoucherDaoImpl extends BaseDaoImpl<ClaimVoucher> implements
ClaimVoucherDao {
	public PaginationUtil<ClaimVoucher> getClaimVoucherPage(String createSn,
			String dealSn, String status, String startDate, String endDate,
			Integer pageNo, Integer pageSize) {
		String hql = "FROM ClaimVoucher c "
				+ "LEFT OUTER JOIN FETCH c.creator "
				+ "LEFT OUTER JOIN FETCH c.nextDeal ";
		String strCondition = "";
		if(createSn != null && !createSn.equals("")){//是员工
			//add添加查询条件(属性名查询条件的值)，creator就是Employee类
			strCondition = strCondition + " c.creator.sn='" + createSn + "' ";

		}
		if(dealSn !=null && !dealSn.equals("")){//不是员工
			//nextDeal也是Employee类，返回的是一个集合，nextdeal字段对应的是处理人
			if(!strCondition.equals("")){
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " c.nextDeal.sn='" + dealSn+ "' ";
		}
		if(status !=null && !status.equals("")){
			if(!strCondition.equals("")){
				strCondition = strCondition + " AND ";
			}
			strCondition = strCondition + " c.status='" + dealSn +"' ";
		}
		try{
			if(startDate !=null && !startDate.equals("")){
				if(!strCondition.equals("")){
					strCondition = strCondition+ " AND ";
				}
				strCondition=strCondition+" c.createTime>='" + startDate + " 00:00:00'";
			}
			if(endDate!=null&&!endDate.equals("")){
				if(!strCondition.equals("")){
					strCondition = strCondition+" AND ";
				}
				strCondition=strCondition + " c.createTime<='" + endDate+ " 23:59:59'";
			}

			if(!strCondition.equals("")){
				hql=hql+" WHERE "+strCondition;
			}
			hql=hql+" ORDER BY c.createTime DESC";
		}catch(Exception e){

		}
		return this.queryPage(hql, pageNo, pageSize);//按条件查询


	}

	@Override
	public ClaimVoucher findClaimVoucherById(Long id) {
		// TODO Auto-generated method stub
		String hql = "FROM ClaimVoucher c "
				+"LEFT OUTER JOIN FETCH c.creator "
				+"LEFT OUTER JOIN FETCH c.nextDeal "
				+"LEFT OUTER JOIN FETCH c.claimVoucherDetails "
				+"LEFT OUTER JOIN FETCH c.checkResults "
				+"WHERE c.id=?";

		return this.findByHQL(hql, id).get(0);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClaimVoucherByModifyDate(int year,int month,int departmentId){
		try{
			String startDate = null;
			String endDate = null;
			if(month !=0){
				startDate = year + "-" + month + "-1 00:00:00";
				endDate = DateUtil.getLastDayOfMonth(year, month)+" 23:59:59";
			}else{
				startDate = year + "-1" + "-1 00:00:00";
				endDate = year + "-12" + "-31 23:59:59";
			}
			
			String hql = "SELECT cv.creator.sn, cv.creator.name, sum(cv.totalAccount)"
			+ " FROM ClaimVoucher cv"
			+ " WHERE cv.modifyTime >= '" 
			+ startDate 
			+ "' "
			+" AND cv.modifyTime <= '" 
			+endDate 
			+ "' ";
			if(departmentId != 0){
				hql = hql + " AND cv.creator.department.id = " + departmentId;
			}
			hql = hql + " AND cv.status ='已付款' ";
			hql = hql + " GROUP BY cv.creator";
			
			return this.getSession().createQuery(hql).list();
		}catch(Exception e){
			return null;
		}


	}


}
