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
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.ExportExcelUtil;
import cn.edu.hzvtc.util.JFreeChatUtil;
import cn.edu.hzvtc.util.PaginationUtil;

import com.opensymphony.xwork2.ActionSupport;

public class CompMonthStatisticsAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ClaimVoucherStatisticsService claimVoucherStatisticsService;

	private int year;
	private int startMonth;
	private int endMonth;
	private int currMonth;
	private String totalCount;
	private JFreeChart chart;
	private List<ClaimVoucherStatistics> statisticsListOfCompMonth;
	private List<ClaimVoucherStatistics> statisticsDetailOfCompMonth;

	public String getList() {
		statisticsListOfCompMonth = claimVoucherStatisticsService.getComClaimVoucherStatisticsByMonth(year, startMonth,
				endMonth);
		return "list";
	}

	public ClaimVoucherStatisticsService getClaimVoucherStatisticsService() {
		return claimVoucherStatisticsService;
	}

	public void setClaimVoucherStatisticsService(ClaimVoucherStatisticsService claimVoucherStatisticsService) {
		this.claimVoucherStatisticsService = claimVoucherStatisticsService;
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

	public int getCurrMonth() {
		return currMonth;
	}

	public void setCurrMonth(int currMonth) {
		this.currMonth = currMonth;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	public List<ClaimVoucherStatistics> getStatisticsListOfCompMonth() {
		return statisticsListOfCompMonth;
	}

	public void setStatisticsListOfCompMonth(List<ClaimVoucherStatistics> statisticsListOfCompMonth) {
		this.statisticsListOfCompMonth = statisticsListOfCompMonth;
	}

	public List<ClaimVoucherStatistics> getStatisticsDetailOfCompMonth() {
		return statisticsDetailOfCompMonth;
	}

	public void setStatisticsDetailOfCompMonth(List<ClaimVoucherStatistics> statisticsDetailOfCompMonth) {
		this.statisticsDetailOfCompMonth = statisticsDetailOfCompMonth;
	}

	public String getDetail() {
		statisticsDetailOfCompMonth = claimVoucherStatisticsService.getClaimVoucherStatisticsByDateAndDept(year,
				currMonth);
		Double count = 0.0;
		for (ClaimVoucherStatistics cv : statisticsDetailOfCompMonth) {
			count += cv.getTotalCount();
		}
		DecimalFormat dFormat = new DecimalFormat("0.00");
		totalCount = dFormat.format(count);
		return "detail";
	}
	
	public String getDetailExcel(){
		//创建Excel
		statisticsDetailOfCompMonth = claimVoucherStatisticsService.getClaimVoucherStatisticsByDateAndDept(year,
				currMonth);
		List<String[]> list = new ArrayList<String[]>();
		int i=0;
		for(ClaimVoucherStatistics cv : statisticsDetailOfCompMonth){
			i++;
			String index = new Integer(i).toString();
			String totalCell = cv.getTotalCount().toString();
			String yearCell = new Integer(year).toString();
			String monthCell = new Integer(currMonth).toString();
			String deptNameCell = cv.getDepartment().getName();
			list.add(new String[]{ index,  totalCell, yearCell, monthCell, deptNameCell });
		}
		String fileName = year + "年" + currMonth + "月" + "公司月度报销统计";
		try{
			ExportExcelUtil.createExcel(getResponse(), list, fileName, "monthly", "comp");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "detailExcel";
	}
	
	public String getDetailChart(){
		//创建图表
		statisticsDetailOfCompMonth = claimVoucherStatisticsService.getClaimVoucherStatisticsByDateAndDept(year,
				currMonth);
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(ClaimVoucherStatistics cv : statisticsDetailOfCompMonth) {
			dataset.setValue(cv.getDepartment().getName(), cv.getTotalCount());
		}
		String title = year + "年" + currMonth + "月" + "公司月度报销统计饼图";
		chart = new JFreeChatUtil().createPieChar3D(dataset, title);
		return "detailChart";
	}

}
