package cn.edu.hzvtc.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 报销单实体类
 */
public class ClaimVoucher implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Employee creator; // 填报人
	private Employee nextDeal; // 待处理人
	private Date createTime; // 填写时间
	private String event; // 事由
	private Double totalAccount = 0d; // 总金额
	private String status; // 状态
	private Date modifyTime; // 修改时间

	
	private Set<ClaimVoucherDetail> claimVoucherDetails = new HashSet<ClaimVoucherDetail>();
	private Set<CheckResult> checkResults = new HashSet<CheckResult>();
	
	
	
	public Set<ClaimVoucherDetail> getClaimVoucherDetails() {
		return claimVoucherDetails;
	}

	public void setClaimVoucherDetails(Set<ClaimVoucherDetail> claimVoucherDetails) {
		this.claimVoucherDetails = claimVoucherDetails;
	}

	public Set<CheckResult> getCheckResults() {
		return checkResults;
	}

	public void setCheckResults(Set<CheckResult> checkResults) {
		this.checkResults = checkResults;
	}

	public ClaimVoucher() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Double getTotalAccount() {
		return this.totalAccount;
	}

	public void setTotalAccount(Double totalAccount) {
		this.totalAccount = totalAccount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "ClaimVoucher [id=" + id + ", creator=" + creator
				+ ", nextDeal=" + nextDeal + ", createTime=" + createTime
				+ ", event=" + event + ", totalAccount=" + totalAccount
				+ ", status=" + status + ", modifyTime=" + modifyTime + "]";
	}



}