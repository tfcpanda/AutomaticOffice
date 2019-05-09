package cn.edu.hzvtc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.entity.ClaimVoucherDetail;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.service.ClaimVoucherService;
import cn.edu.hzvtc.util.ConstantUtil;
import freemarker.core.ParseException;

public class ClaimVoucherAction extends BaseAction {
		private static final long serialVersionUID =1L;
  
		private ClaimVoucher claimVoucher;
		private ClaimVoucherDetail claimVoucherDetail;
		private List<ClaimVoucherDetail> detailList;
		private ClaimVoucherService claimVoucherService;
		
		private String startDate;
		private String endDate;
		private int rowNumber;
		
		private Map<String,String>statusMap;

		public ClaimVoucher getClaimVoucher() {
			return claimVoucher;
		}

		public void setClaimVoucher(ClaimVoucher claimVoucher) {
			this.claimVoucher = claimVoucher;
		}

		public ClaimVoucherDetail getClaimVoucherDetail() {
			return claimVoucherDetail;
		}

		public void setClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) {
			this.claimVoucherDetail = claimVoucherDetail;
		}

		public List<ClaimVoucherDetail> getDetailList() {
			return detailList;
		}

		public void setDetailList(List<ClaimVoucherDetail> detailList) {
			this.detailList = detailList;
		}

		public ClaimVoucherService getClaimVoucherService() {
			return claimVoucherService;
		}

		public void setClaimVoucherService(ClaimVoucherService claimVoucherService) {
			this.claimVoucherService = claimVoucherService;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public int getRowNumber() {
			return rowNumber;
		}

		public void setRowNumber(int rowNumber) {
			this.rowNumber = rowNumber;
		}

		public Map<String, String> getStatusMap() {
			return claimVoucherService.getAllStatusMap();
		}

	
		public String searchClaimVoucher(){
			//查询发票
			String createSn="";
			String dealSn="";
			if(isStaff()){//如果是员工
				createSn = getCurrentSn();//获取当前员工工号
			}else{ //可能是经理 部门经理 财务
				dealSn = getCurrentSn();//高于用户的员工
			}
			String status="";
			if(claimVoucher!=null){//是否是已经提交查询
				status=claimVoucher.getStatus();
			}
			pageUtil = claimVoucherService.getClaimVoucherPage(createSn,dealSn,
				status,startDate,endDate,pageNo,pageSize);//分页支持
			return "list";//返回显示
		}
		
		public String getClaimVoucherById(){
			claimVoucher=claimVoucherService.findClaimVoucherById(claimVoucher.getId());
			//根据id查询
			return "view";
			
		}
		
		public String deleteClaimVoucherById(){
			//删除
			claimVoucherService.deleteClaimVoucherById(claimVoucher.getId());
			return "redirectList";
			
		}
		
		public String toAdd()throws ParseException{//前往添加
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
			String dateString = formatter.format(currentTime);
			this.getRequest().put("date", dateString);
			return "add";
		}
		
		
		
		
		public String saveClaimVoucher(){//添加报销单
			claimVoucher.setCreateTime(new Date());
			claimVoucher.setCreator(getLoginEmployee());//创建者
			if(claimVoucher.getStatus().equals(ConstantUtil.CLAIMVOUCHER_SUBMITTED)){//状态是已提交，下一处理人是经理
				claimVoucher.setNextDeal((Employee)getSession().get(ConstantUtil.AUTH_EMPLOYEE_MANAGER));
			}
			claimVoucher.setClaimVoucherDetails(new HashSet<ClaimVoucherDetail>(detailList));//设置子项集合
			boolean bSave = claimVoucherService.saveClaimVoucher(claimVoucher);//保存新报清单
			if(bSave){
				this.addActionMessage("添加报销单成功！");
			}else{
				this.addActionMessage("添加报销单失败！");
			}
			return "redirectList";
		}
		
		
		
		public String toUpdate(){//前往更新
			claimVoucher = claimVoucherService.findClaimVoucherById(claimVoucher.getId());
			return "update";
		}
		public String updateClaimVoucher(){//更新发票报销
			ClaimVoucher newClaimVoucher = claimVoucherService.findClaimVoucherById(claimVoucher.getId());
			newClaimVoucher.setCreator(getLoginEmployee());//设置更新者
			if(claimVoucher.getStatus().equals(ConstantUtil.CLAIMVOUCHER_SUBMITTED)){//状态是已经更新，下一处理人是经理
				newClaimVoucher.setNextDeal((Employee)getSession().get(ConstantUtil.AUTH_EMPLOYEE_MANAGER));
			}
			//设置属性
			newClaimVoucher.setEvent(claimVoucher.getEvent());
			newClaimVoucher.setStatus(claimVoucher.getStatus());
			newClaimVoucher.setTotalAccount(claimVoucher.getTotalAccount());
			newClaimVoucher.setClaimVoucherDetails(new HashSet<ClaimVoucherDetail>(detailList));
			boolean bSave = claimVoucherService.updateClaimVoucher(newClaimVoucher);//进行保存
			if(bSave){
				this.addActionMessage("更新报销单成功！");
			}else{
				this.addActionMessage("更新报销单失败！");
			}
			return "redirectList";
		}

		public String toCheck(){
			//前往check
			claimVoucher=claimVoucherService.findClaimVoucherById(claimVoucher.getId());
			//加载报销单信息
			return "check";
			
		}
		
		
		
		
		
		
		
		
		
}
