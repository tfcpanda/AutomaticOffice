package cn.edu.hzvtc.entity;

import java.util.Date;

/**
 * 请假实体类
 */
public class Leave implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Employee creator;// 填报人
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Double leaveDay;// 请假天数
	private String reason;// 请假原因
	private String status;// 状态
	private String leaveType;// 请假类型
	private Employee nextDeal;// 待处理人
	private String approveOpinion;// 批准意见
	private Date createTime;// 创建时间
	private Date modifyTime;// 修改时间

	public Leave() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getLeaveDay() {
		return this.leaveDay;
	}

	public void setLeaveDay(Double leaveday) {
		this.leaveDay = leaveday;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeaveType() {
		return this.leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getApproveOpinion() {
		return this.approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}

	public Employee getNextDeal() {
		return nextDeal;
	}

	public void setNextDeal(Employee nextDeal) {
		this.nextDeal = nextDeal;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", creator=" + creator + ", startTime="
				+ startTime + ", endTime=" + endTime + ", leaveDay=" + leaveDay
				+ ", reason=" + reason + ", status=" + status + ", leaveType="
				+ leaveType + ", nextDeal=" + nextDeal + ", approveOpinion="
				+ approveOpinion + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + "]";
	}

}