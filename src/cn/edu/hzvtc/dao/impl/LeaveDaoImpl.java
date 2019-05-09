package cn.edu.hzvtc.dao.impl;

import cn.edu.hzvtc.dao.LeaveDao;
import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.util.PaginationUtil;

public class LeaveDaoImpl extends BaseDaoImpl<Leave> implements LeaveDao {
	@Override
	public PaginationUtil<Leave> getLeavePage(String createSn,String dealSn,String startDate, String endDate,int pageNo,int pageSize){
		String hql="FROM Leave l ";
		String strCondition="";
		if(createSn!=null&&!createSn.equals("")){
			strCondition=strCondition + " l.creator.sn='" + createSn + "' ";
		}
		if(dealSn !=null&&!dealSn.equals("")){
			if(!strCondition.equals("")){
				strCondition=strCondition+" AND ";
				
			}
			strCondition = strCondition + " l.nextDeal.sn='" + dealSn + "' ";
		}
		
		try{
			if(startDate!=null&&!startDate.equals("")){
				if(!strCondition.equals("")){
					strCondition = strCondition+" AND ";
				}
				strCondition=strCondition+" l.createTime>='" + startDate + " 00:00:00'";
			}
			if(endDate!=null&&!endDate.equals("")){
				if(!strCondition.equals("")){
					strCondition = strCondition+" AND ";
				}
				strCondition=strCondition + " l.createTime<='" + endDate+ " 23:59:59'";
			}
			
			if(!strCondition.equals("")){
				hql=hql+" WHERE "+strCondition;
			}
			hql=hql+" ORDER BY l.createTime DESC";
		}catch(Exception e){
			
		}
		return this.queryPage(hql, pageNo, pageSize);
	}
}
