package cn.edu.hzvtc.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.service.ClaimVoucherService;
import cn.edu.hzvtc.service.ClaimVoucherStatisticsService;
import cn.edu.hzvtc.service.ClaimVouyearStatisticsService;
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.ExportExcelUtil;
import cn.edu.hzvtc.util.JFreeChatUtil;
import cn.edu.hzvtc.util.PaginationUtil;

import com.opensymphony.xwork2.ActionSupport;

public class DeptYearStatisticsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private ClaimVouyearStatisticsService claimVouyearStatisticsService;
	private ClaimVoucherService claimVoucherService;
	
	private int startYear;
	private int endYear;
	private int departmentId;
	private int currYear;
	private String deptName;
	private List<ClaimVoucher> statisticsDetailOfDeptYear;
	private String detailCount;
	
	private JFreeChart chart;
	
	public String getDeptStatisticsByYear(){
		Employee emp = (Employee) getSession().get(ConstantUtil.AUTH_EMPLOYEE);
		
		departmentId = emp.getDepartment().getId();
		pageUtil = claimVouyearStatisticsService.getDeptClaimVoucherStatisticsByPage(startYear, endYear, departmentId, pageNo, pageSize);
		return "list";
	}

	public ClaimVouyearStatisticsService getClaimVouyearStatisticsService() {
		return claimVouyearStatisticsService;
	}

	public void setClaimVouyearStatisticsService(
			ClaimVouyearStatisticsService claimVouyearStatisticsService) {
		this.claimVouyearStatisticsService = claimVouyearStatisticsService;
	}

	public ClaimVoucherService getClaimVoucherService() {
		return claimVoucherService;
	}

	public void setClaimVoucherService(ClaimVoucherService claimVoucherService) {
		this.claimVoucherService = claimVoucherService;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getCurrYear() {
		return currYear;
	}

	public void setCurrYear(int currYear) {
		this.currYear = currYear;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<ClaimVoucher> getStatisticsDetailOfDeptYear() {
		return statisticsDetailOfDeptYear;
	}

	public void setStatisticsDetailOfDeptYear(
			List<ClaimVoucher> statisticsDetailOfDeptYear) {
		this.statisticsDetailOfDeptYear = statisticsDetailOfDeptYear;
	}

	public String getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(String detailCount) {
		this.detailCount = detailCount;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	public String getDeptVoucherDetailByYear(){
		statisticsDetailOfDeptYear = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, departmentId);
		Double count = 0.0;
		for(ClaimVoucher cv : statisticsDetailOfDeptYear){
			count += cv.getTotalAccount();
		}
		DecimalFormat dFormat = new DecimalFormat("0.00");
		detailCount = dFormat.format(count);
		return "detail";
	}
	
	public String createDetailExcel(){
		//创建Excel
		statisticsDetailOfDeptYear = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, departmentId);
		List<String[]> list = new ArrayList<String[]>();
		int i=0;
		for(ClaimVoucher cv : statisticsDetailOfDeptYear){
			i++;
			String index = new Integer(i).toString();
			String nameCell = cv.getCreator().getName();
			String totalCell = cv.getTotalAccount().toString();
			String yearCell = new Integer(currYear).toString();
			String deptNameCell = cv.getCreator().getDepartment().getName();
			deptName = deptNameCell;
			list.add(new String[]{ index, nameCell, totalCell, yearCell, deptNameCell });
		}
		String fileName = currYear + "年"  + deptName + "年度报销统计";
		try{
			ExportExcelUtil.createExcel(getResponse(), list, fileName, "year", "comp");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "detailExcel";
	}
	
	public String createDetailChart(){
		//创建图表
		statisticsDetailOfDeptYear = claimVoucherService.getClaimVoucherByModifyDate(currYear, 0, departmentId);
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(ClaimVoucher cv : statisticsDetailOfDeptYear) {
			dataset.setValue(cv.getCreator().getName(), cv.getTotalAccount());
			deptName = cv.getCreator().getDepartment().getName();
		}
		String title = currYear + "年" + deptName + "年度报销统计饼图";
		chart = new JFreeChatUtil().createPieChar3D(dataset, title);
		return "detailChart";
	}
}
