package cn.edu.hzvtc.entity;

/**
 * 职位实体类
 */
public class Position implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nameCn;// 职务名称(中文)
	private String nameEn;// 职务名称(英文)

	public Position() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", nameCn=" + nameCn + ", nameEn="
				+ nameEn + "]";
	}

}