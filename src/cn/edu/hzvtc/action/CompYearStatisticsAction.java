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
import cn.edu.hzvtc.entity.ClaimVouyearStatistics;
import cn.edu.hzvtc.entity.Employee;
import cn.edu.hzvtc.service.ClaimVoucherService;
import cn.edu.hzvtc.service.ClaimVoucherStatisticsService;
import cn.edu.hzvtc.service.ClaimVouyearStatisticsService;
import cn.edu.hzvtc.util.ConstantUtil;
import cn.edu.hzvtc.util.ExportExcelUtil;
import cn.edu.hzvtc.util.JFreeChatUtil;
import cn.edu.hzvtc.util.PaginationUtil;

import com.opensymphony.xwork2.ActionSupport;

public class CompYearStatisticsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private ClaimVouyearStatisticsService claimVouyearStatisticsService;
	
	private int startYear;
	private int endYear;
	private int currYear;
	private List<ClaimVouyearStatistics> statisticsListOfCompYear;
	private List<ClaimVouyearStatistics> statisticsDetailOfCompYear;
	private String totalCount;
	private JFreeChart chart;
	
	public String getList(){
		statisticsListOfCompYear = claimVouyearStatisticsService.getCompClaimVoucherStatisticsByPage(startYear, endYear);
		return "list";
	}

	public ClaimVouyearStatisticsService getClaimVouyearStatisticsService() {
		return claimVouyearStatisticsService;
	}

	public void setClaimVouyearStatisticsService(
			ClaimVouyearStatisticsService claimVouyearStatisticsService) {
		this.claimVouyearStatisticsService = claimVouyearStatisticsService;
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

	public int getCurrYear() {
		return currYear;
	}

	public void setCurrYear(int currYear) {
		this.currYear = currYear;
	}

	public List<ClaimVouyearStatistics> getStatisticsListOfCompYear() {
		return statisticsListOfCompYear;
	}

	public void setStatisticsListOfCompYear(
			List<ClaimVouyearStatistics> statisticsListOfCompYear) {
		this.statisticsListOfCompYear = statisticsListOfCompYear;
	}

	public List<ClaimVouyearStatistics> getStatisticsDetailOfCompYear() {
		return statisticsDetailOfCompYear;
	}

	public void setStatisticsDetailOfCompYear(
			List<ClaimVouyearStatistics> statisticsDetailOfCompYear) {
		this.statisticsDetailOfCompYear = statisticsDetailOfCompYear;
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
	public String getDetail(){
		statisticsDetailOfCompYear = claimVouyearStatisticsService.getCompClaimVoucherStatisticsByYear(currYear);
		Double count = 0.0;
		for(ClaimVouyearStatistics cvs : statisticsDetailOfCompYear){
			count += cvs.getTotalCount();
		}
		DecimalFormat dFormat = new DecimalFormat("0.00");
		totalCount = dFormat.format(count);
		return "detail";
	}
	
	public String getDetailExcel(){
		//创建Excel
		statisticsDetailOfCompYear = claimVouyearStatisticsService.getCompClaimVoucherStatisticsByYear(currYear);
		List<String[]> list = new ArrayList<String[]>();
		int i=0;
		for(ClaimVouyearStatistics cvs : statisticsDetailOfCompYear){
			i++;
			String index = new Integer(i).toString();
			String totalCell = cvs.getTotalCount().toString();
			String yearCell = new Integer(currYear).toString();
			String deptNameCell = cvs.getDepartment().getName();
			list.add(new String[]{ index,  totalCell, yearCell, deptNameCell });
		}
		String fileName = currYear + "年" +  "公司月度报销统计";
		try{
			ExportExcelUtil.createExcel(getResponse(), list, fileName, "year", "comp");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "detailExcel";
	}
	
	public String getDetailChart(){
		//创建图表
		statisticsDetailOfCompYear = claimVouyearStatisticsService.getCompClaimVoucherStatisticsByYear(currYear);
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(ClaimVouyearStatistics cvs : statisticsDetailOfCompYear) {
			dataset.setValue(cvs.getDepartment().getName(), cvs.getTotalCount());
		}
		String title = currYear + "年" + "公司月度报销统计饼图";
		chart = new JFreeChatUtil().createPieChar3D(dataset, title);
		return "detailChart";
	}
}
