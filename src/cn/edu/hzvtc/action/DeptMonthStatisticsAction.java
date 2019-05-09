package cn.edu.hzvtc.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import cn.edu.hzvtc.entity.ClaimVoucher;
import cn.edu.hzvtc.entity.ClaimVoucherStatistics;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.service.ClaimVoucherService;
import cn.edu.hzvtc.service.ClaimVoucherStatisticsService;
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.ExportExcelUtil;
import cn.edu.hzvtc.util.JFreeChatUtil;

public class DeptMonthStatisticsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private List<ClaimVoucherStatistics> result;
	private ClaimVoucherStatisticsService claimVoucherStatisticsService;
	private ClaimVoucherService claimVoucherService;
	
	private int year;
	private int startMonth;
	private int endMonth;
	private int departmentId;
	private String deptName;
	private int selectMonth;
	private List<ClaimVoucher> statisticsDetailOfDeptMonth;
	private String detailCount;
	
	private JFreeChart chart;
	
	public String getDeptStatisticsByMonth(){
		Employee emp = (Employee) getSession().get(ConstantUtil.AUTH_EMPLOYEE);
		departmentId = emp.getDepartment().getId();
		pageUtil = claimVoucherStatisticsService.getDeptClaimVoucherStatisticsByPage(year, startMonth, endMonth, departmentId, pageNo, pageSize);
		return "list";
	}

	public List<ClaimVoucherStatistics> getResult() {
		return result;
	}

	public void setResult(List<ClaimVoucherStatistics> result) {
		this.result = result;
	}

	public ClaimVoucherStatisticsService getClaimVoucherStatisticsService() {
		return claimVoucherStatisticsService;
	}

	public void setClaimVoucherStatisticsService(
			ClaimVoucherStatisticsService claimVoucherStatisticsService) {
		this.claimVoucherStatisticsService = claimVoucherStatisticsService;
	}

	public ClaimVoucherService getClaimVoucherService() {
		return claimVoucherService;
	}

	public void setClaimVoucherService(ClaimVoucherService claimVoucherService) {
		this.claimVoucherService = claimVoucherService;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(int selectMonth) {
		this.selectMonth = selectMonth;
	}

	public List<ClaimVoucher> getStatisticsDetailOfDeptMonth() {
		return statisticsDetailOfDeptMonth;
	}

	public void setStatisticsDetailOfDeptMonth(
			List<ClaimVoucher> statisticsDetailOfDeptMonth) {
		this.statisticsDetailOfDeptMonth = statisticsDetailOfDeptMonth;
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
	public String getDeptVoucherDetailByMonth(){
		statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, selectMonth, departmentId);
		Double count = 0.0;
		for(ClaimVoucher cv : statisticsDetailOfDeptMonth){
			count += cv.getTotalAccount();
		}
		DecimalFormat dFormat = new DecimalFormat("0.00");
		detailCount = dFormat.format(count);
		return "detail";
	}
	
	public String createDetailExcel(){
		//创建Excel
		statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, selectMonth, departmentId);
		List<String[]> list = new ArrayList<String[]>();
		int i=0;
		for(ClaimVoucher cv : statisticsDetailOfDeptMonth){
			i++;
			String index = new Integer(i).toString();
			String nameCell = cv.getCreator().getName();
			String totalCell = cv.getTotalAccount().toString();
			String yearCell = new Integer(year).toString();
			String monthCell = new Integer(selectMonth).toString();
			String deptNameCell = cv.getCreator().getDepartment().getName();
			deptName = deptNameCell;
			list.add(new String[]{ index, nameCell, totalCell, yearCell, monthCell, deptNameCell });
		}
		String fileName = year + "年" + selectMonth + "月" + deptName + "月度报销统计";
		try{
			ExportExcelUtil.createExcel(getResponse(), list, fileName, "monthly", "dept");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "detailExcel";
	}
	
	public String createDetailChart(){
		//创建图表
		statisticsDetailOfDeptMonth = claimVoucherService.getClaimVoucherByModifyDate(year, selectMonth, departmentId);
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(ClaimVoucher cv : statisticsDetailOfDeptMonth) {
			dataset.setValue(cv.getCreator().getName(), cv.getTotalAccount());
			deptName = cv.getCreator().getDepartment().getName();
		}
		String title = year + "年" + selectMonth + "月" + deptName + "月度报销统计饼图";
		chart = new JFreeChatUtil().createPieChar3D(dataset, title);
		return "detailChart";
	}


}
