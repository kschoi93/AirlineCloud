package dbAll;


public class EmpBlacklistVO {
	private int userNo;
	private String black_name;
	private String black_ename;
	private String black_birth;
	private String black_passNo;
	private String black_gender;
	private String black_exDate;
	private String reason;
	private String regDate;

	public EmpBlacklistVO() {}
	
	public EmpBlacklistVO(String black_name, String black_ename, String black_passNo,
			String black_gender, String reason, int userNo) {
		this.userNo = userNo;
		this.black_name = black_name;
		this.black_ename = black_ename;
		this.black_passNo = black_passNo;
		this.black_gender = black_gender;
		this.reason = reason;
	}
	
	public EmpBlacklistVO(String black_name, String black_ename, String black_passNo,
			String black_gender, String black_birth, String reason, int userNo, String regDate) {
		this.black_name = black_name;
		this.black_ename = black_ename;
		this.black_passNo = black_passNo;
		this.black_gender = black_gender;
		this.black_birth = black_birth;
		this.reason = reason;
		this.userNo = userNo;
		this.regDate = regDate;
	}
	
	public EmpBlacklistVO(String black_name, String black_ename, String black_passNo,
			String black_gender, String black_birth, String reason) {
		this.black_name = black_name;
		this.black_ename = black_ename;
		this.black_passNo = black_passNo;
		this.black_gender = black_gender;
		this.black_birth = black_birth;
		this.reason = reason;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getBlack_name() {
		return black_name;
	}

	public void setBlack_name(String black_name) {
		this.black_name = black_name;
	}

	public String getBlack_ename() {
		return black_ename;
	}

	public void setBlack_ename(String black_ename) {
		this.black_ename = black_ename;
	}

	public String getBlack_birth() {
		return black_birth;
	}

	public void setBlack_birth(String black_birth) {
		this.black_birth = black_birth;
	}

	public String getBlack_passNo() {
		return black_passNo;
	}

	public void setBlack_passNo(String black_passNo) {
		this.black_passNo = black_passNo;
	}

	public String getBlack_gender() {
		return black_gender;
	}

	public void setBlack_gender(String black_gender) {
		this.black_gender = black_gender;
	}

	public String getBlack_exDate() {
		return black_exDate;
	}

	public void setBlack_exDate(String black_exDate) {
		this.black_exDate = black_exDate;
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

