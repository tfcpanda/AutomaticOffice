package cn.edu.hzvtc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hzvtc.dao.ClaimVoucherDao;
import cn.edu.hzvtc.dao.ClaimVoucherDetailDao;
import cn.edu.hzvtc.dao.EmployeeDao;
import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.entity.ClaimVoucherDetail;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.entity.Leave;
import cn.edu.hzvtc.service.ClaimVoucherService;
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.PaginationUtil;

public class ClaimVoucherServiceImpl implements ClaimVoucherService {
	private ClaimVoucherDao  claimVoucherDao;
	private ClaimVoucherDetailDao  claimVoucherDetailDao;
	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	public ClaimVoucherDao getClaimVoucherDao() {
		return claimVoucherDao;
	}
	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}
	public ClaimVoucherDetailDao getClaimVoucherDetailDao() {
		return claimVoucherDetailDao;
	}
	public void setClaimVoucherDetailDao(ClaimVoucherDetailDao claimVoucherDetailDao) {
		this.claimVoucherDetailDao = claimVoucherDetailDao;
	}
	public Map<String,String>getAllStatusMap(){
		Map<String,String>statusMap=new  LinkedHashMap<String,String>();
		statusMap.put(ConstantUtil.CLAIMVOUCHER_CREATED,
				ConstantUtil.CLAIMVOUCHER_CREATED);

		statusMap.put(ConstantUtil.CLAIMVOUCHER_SUBMITTED,
				ConstantUtil.CLAIMVOUCHER_SUBMITTED);

		statusMap.put(ConstantUtil.CLAIMVOUCHER_BACK,
				ConstantUtil.CLAIMVOUCHER_BACK);

		statusMap.put(ConstantUtil.CLAIMVOUCHER_APPROVING,
				ConstantUtil.CLAIMVOUCHER_APPROVING);

		statusMap.put(ConstantUtil.CLAIMVOUCHER_APPROVED,
				ConstantUtil.CLAIMVOUCHER_APPROVED);

		statusMap.put(ConstantUtil.CLAIMVOUCHER_PAID,
				ConstantUtil.CLAIMVOUCHER_PAID);


		statusMap.put(ConstantUtil.CLAIMVOUCHER_TERMINATED,
				ConstantUtil.CLAIMVOUCHER_TERMINATED);

		return statusMap;

	}
	public PaginationUtil<ClaimVoucher> getClaimVoucherPage(String createSn,
			String dealSn, String status,String startDate, String endDate,
			int pageNo, int pageSize) {
		//分页查询
		return    claimVoucherDao.getClaimVoucherPage(createSn,dealSn,status,
				startDate,endDate,pageNo,pageSize);
	}

	public ClaimVoucher findClaimVoucherById(Long id){
		return (ClaimVoucher)claimVoucherDao.findClaimVoucherById(id);
		//return (ClaimVoucher)claimVoucherDao.findById(id);
	}


	public boolean deleteClaimVoucherById(Long id){
		boolean bRet=false;
		try{
			//获得当前对象
			ClaimVoucher claimVoucher=(ClaimVoucher)claimVoucherDao.findById(id);
			if(claimVoucher!=null){
				claimVoucherDao.delete(claimVoucher.getId());
				//删除、级联删除one to many
			}
			bRet=true;//删除成功
		}catch(Exception e){
			e.printStackTrace();

		}
		return bRet;

	}

	public boolean saveClaimVoucher(ClaimVoucher claimVoucher){

		boolean bRet=false;//默认失败
		try{
			claimVoucher.setCreateTime(new Date());
			claimVoucherDao.save(claimVoucher);
			for(ClaimVoucherDetail detail:claimVoucher.getClaimVoucherDetails()){
				detail.setClaimVoucher(claimVoucher);
				claimVoucherDetailDao.save(detail);
			}
			bRet=true;//全部保存完整
		}catch(Exception e){
			e.printStackTrace();

		}
		return bRet;

	}
	public boolean updateClaimVoucher(ClaimVoucher claimVoucher){
		boolean bRet = false;
		try{
			claimVoucher.setModifyTime(new Date());//设置更新时间
			claimVoucherDao.update(claimVoucher);//更新
			if(claimVoucher.getClaimVoucherDetails() != null){//有详情
				claimVoucherDetailDao.deleteDetailByClaimVoucherId(claimVoucher.getId());
				for(ClaimVoucherDetail detail : claimVoucher.getClaimVoucherDetails()){//每一条detail进行更新
					detail.setClaimVoucher(claimVoucher);
					claimVoucherDetailDao.save(detail);//detail进行保存
				}
			}
			bRet = true;//成功
		}catch(Exception e){
			e.printStackTrace();
		}
		return bRet;
	}
	@Override
	public List<ClaimVoucher> getClaimVoucherByModifyDate(int year,
			int month, int departmentId) {
		List<Object[]> list = claimVoucherDao.getClaimVoucherByModifyDate(year, month, departmentId);
		Iterator<Object[]> it = list.iterator();
		
		List<ClaimVoucher> newList = new ArrayList<ClaimVoucher>();
		while(it.hasNext()){
			Object[] o = (Object[])it.next();
			ClaimVoucher cv =new ClaimVoucher();
			
			Employee emp = employeeDao.findEmployeeBySn((String)o[0]);
			cv.setCreator(emp);
			cv.setTotalAccount((Double) o[2]);
			newList.add(cv);
		}
		return newList;
	}

}
