package cn.edu.hzvtc.entity;

import java.util.Date;

/**
 * 审核结果实体类
 */
public class CheckResult implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private ClaimVoucher claimVoucher; // 报销单编号
	private Date checkTime; // 审核时间
	private String result; // 审核结果
	private String comment; // 审核意见
	private Employee checkEmployee; // 审核人

	public CheckResult() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}

	public void setClaimVoucher(ClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}

	public Employee getCheckEmployee() {
		return checkEmployee;
	}

	public void setCheckEmployee(Employee checkEmployee) {
		this.checkEmployee = checkEmployee;
	}

	@Override
	public String toString() {
		return "CheckResult [id=" + id + ", claimVoucher=" + claimVoucher
				+ ", checkTime=" + checkTime + ", result=" + result
				+ ", comment=" + comment + ", checkEmployee=" + checkEmployee
				+ "]";
	}

}