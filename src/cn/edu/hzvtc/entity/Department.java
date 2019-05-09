package cn.edu.hzvtc.entity;

/**
 * 部门实体类
 */
public class Department implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;// 部门名称

	public Department() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}