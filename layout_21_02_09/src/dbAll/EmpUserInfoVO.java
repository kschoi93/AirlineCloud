package dbAll;


public class EmpUserInfoVO {
	private int userNo;
	private String user_name;
	private String user_ename;
	private String user_birth;
	private String user_gender;
	private String user_tel;
	private String user_passNo;
	private String user_email;
	private String mileage;
	private String grade;
	private String reason;
	private String regDate;

	public EmpUserInfoVO() {}
	
	
	public EmpUserInfoVO(int userNo, String user_name, String user_birth,
			String user_ename, String user_gender, String user_tel,
			String user_passNo, String user_email, String mileage, String grade) {
		this.userNo = userNo;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_ename = user_ename;
		this.user_gender = user_gender;
		this.user_tel = user_tel;
		this.user_passNo = user_passNo;
		this.user_email = user_email;
		this.mileage = mileage;		
		this.grade = grade;
	}
	
	public EmpUserInfoVO(int userNo, String user_name, String user_gender,
			String user_birth, String user_passNo, String user_tel, String user_email) {
		this.userNo = userNo;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.user_birth = user_birth;
		this.user_passNo = user_passNo;
		this.user_tel = user_tel;
		this.user_email = user_email;
	}
	public EmpUserInfoVO(String user_name, String user_ename, String user_passNo,
			String user_gender, String user_birth, String reason, int userNo, String regDate) {
		this.user_name = user_name;
		this.user_ename = user_ename;
		this.user_passNo = user_passNo;
		this.user_gender = user_gender;
		this.user_birth = user_birth;
		this.reason = reason;
		this.userNo = userNo;
		this.regDate = regDate;
	}
	

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_ename() {
		return user_ename;
	}

	public void setUser_ename(String user_ename) {
		this.user_ename = user_ename;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_passNo() {
		return user_passNo;
	}

	public void setUser_passNo(String user_passNo) {
		this.user_passNo = user_passNo;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	

}
