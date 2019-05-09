package cn.edu.hzvtc.entity;

import java.util.Date;

/**
 * 年份统计实体类
 */
public class ClaimVouyearStatistics implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Double totalCount;// 总金额
	private int year;// 年份
	private Date modifyTime;// 修改时间
	private Department department;// 部门

	public ClaimVouyearStatistics() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Double totalCount) {
		this.totalCount = totalCount;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "ClaimVouyearStatistics [id=" + id + ", totalCount="
				+ totalCount + ", year=" + year + ", modifyTime=" + modifyTime
				+ ", department=" + department + "]";
	}

}