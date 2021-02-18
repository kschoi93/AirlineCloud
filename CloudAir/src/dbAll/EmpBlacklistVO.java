package dbAll;


public class EmpBlacklistVO {
	private int userNo;
	private String b_name_kor;
	private String b_name_eng;
	private String b_passportNo;
	private String b_expireDate;
	private String b_gender;
	private String b_reason;

	public EmpBlacklistVO() {}
	
	public EmpBlacklistVO(int userNo, String b_name_kor, String b_name_eng,
			String b_passportNo, String b_gender, String b_reason) {
		this.userNo = userNo;
		this.b_name_kor = b_name_kor;
		this.b_name_eng = b_name_eng;
		this.b_passportNo = b_passportNo;
		this.b_gender = b_gender;
		this.b_reason = b_reason;
	}
	
	public EmpBlacklistVO(String b_name_kor, String b_name_eng, String b_passportNo,
			String b_expireDate, String b_gender, String b_reason) {
		this.userNo = userNo;
		this.b_name_kor = b_name_kor;
		this.b_name_eng = b_name_eng;
		this.b_passportNo = b_passportNo;
		this.b_expireDate = b_expireDate;
		this.b_gender = b_gender;
		this.b_reason = b_reason;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getB_name_kor() {
		return b_name_kor;
	}

	public void setB_name_kor(String b_name_kor) {
		this.b_name_kor = b_name_kor;
	}

	public String getB_name_eng() {
		return b_name_eng;
	}

	public void setB_name_eng(String b_name_eng) {
		this.b_name_eng = b_name_eng;
	}

	public String getB_passportNo() {
		return b_passportNo;
	}

	public void setB_passportNo(String b_passportNo) {
		this.b_passportNo = b_passportNo;
	}

	public String getB_expireDate() {
		return b_expireDate;
	}

	public void setB_expireDate(String b_expireDate) {
		this.b_expireDate = b_expireDate;
	}

	public String getB_gender() {
		return b_gender;
	}

	public void setB_gender(String b_gender) {
		this.b_gender = b_gender;
	}

	public String getB_reason() {
		return b_reason;
	}

	public void setB_reason(String b_reason) {
		this.b_reason = b_reason;
	}

}

