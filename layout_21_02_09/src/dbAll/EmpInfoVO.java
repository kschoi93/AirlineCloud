package dbAll;

public class EmpInfoVO {
	private String empNo;
	private String emp_name;
	private String emp_birth;
	private String emp_gender;
	private String emp_tel;
	private String emp_email;
	private String emp_dept;
	private String emp_job;
	private String emp_passNo;
	private String emp_addr;
	
	public EmpInfoVO() {}
	
	public EmpInfoVO(String empNo, String emp_name, String emp_birth, String emp_tel, String emp_email, String emp_dept) {
		this.empNo = empNo;
		this.emp_name = emp_name;
		this.emp_birth = emp_birth;
		this.emp_tel = emp_tel;
		this.emp_email = emp_email;
		this.emp_dept = emp_dept;
	}
	
	public EmpInfoVO(String empNo, String emp_name, String emp_birth,
			String emp_gender, String emp_tel, String emp_email, String emp_dept,
			String emp_job, String emp_passNo, String emp_addr) {
		this(empNo, emp_name, emp_birth, emp_tel, emp_email, emp_dept);
		this.emp_gender = emp_gender;
		this.emp_job = emp_job;
		this.emp_passNo = emp_passNo;
		this.emp_addr = emp_addr;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_birth() {
		return emp_birth;
	}

	public void setEmp_birth(String emp_birth) {
		this.emp_birth = emp_birth;
	}

	public String getEmp_gender() {
		return emp_gender;
	}

	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}

	public String getEmp_tel() {
		return emp_tel;
	}

	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public String getEmp_dept() {
		return emp_dept;
	}

	public void setEmp_dept(String emp_dept) {
		this.emp_dept = emp_dept;
	}

	public String getEmp_job() {
		return emp_job;
	}

	public void setEmp_job(String emp_job) {
		this.emp_job = emp_job;
	}

	public String getEmp_passNo() {
		return emp_passNo;
	}

	public void setEmp_passNo(String emp_passNo) {
		this.emp_passNo = emp_passNo;
	}

	public String getEmp_addr() {
		return emp_addr;
	}

	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}
	

}

