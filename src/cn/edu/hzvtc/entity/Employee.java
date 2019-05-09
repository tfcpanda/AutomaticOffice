package cn.edu.hzvtc.entity;

/**
 * 员工实体类
 */
public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String sn; // 员工编号
	private String password;// 密码
	private String name;// 姓名
	private String status;// 状态

	private Department department;// 部门编号
	private Position position;// 职务编号

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [sn=" + sn + ", password=" + password + ", name="
				+ name + ", status=" + status + ", department=" + department
				+ ", position=" + position + "]";
	}

}
