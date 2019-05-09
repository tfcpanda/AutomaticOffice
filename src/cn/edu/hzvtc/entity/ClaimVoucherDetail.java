package cn.edu.hzvtc.entity;

/**
 * 报销单详单实体类
 */
public class ClaimVoucherDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private ClaimVoucher claimVoucher; // 报销单编号
	private String item; // 项目
	private Double account; // 金额
	private String description; // 费用说明

	public ClaimVoucherDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}

	public void setClaimVoucher(ClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getAccount() {
		return this.account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ClaimVoucherDetail [id=" + id + ", claimVoucher="
				+ claimVoucher + ", item=" + item + ", account=" + account
				+ ", description=" + description + "]";
	}

}