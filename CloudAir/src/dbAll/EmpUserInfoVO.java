package dbAll;


public class EmpUserInfoVO {
	private int userNo;
	private String user_name;
	private String user_ename;
	private String user_birth;
	private String user_gender;
	private String user_tel;
	private String user_passNo;
	private String user_exDate;
	private String user_email;
	private String mileage;

	public EmpUserInfoVO() {}
	
	public EmpUserInfoVO(int userNo, String user_name, String user_gender,
			String user_passNo, String user_tel, String user_email) {
		this.userNo = userNo;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.user_passNo = user_passNo;
		this.user_tel = user_tel;
		this.user_email = user_email;
	}
	
	public EmpUserInfoVO(int userNo, String user_name, String user_ename, String user_birth, String user_gender,
			String user_tel, String user_passNo, String user_exDate, String user_email, String mileage) {
		this(userNo, user_name, user_gender, user_passNo, user_tel, user_email);
		this.user_ename = user_ename;
		this.user_birth = user_birth;
		this.user_exDate = user_exDate;
		this.mileage = mileage;		
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getName_kor() {
		return user_name;
	}

	public void setName_kor(String name_kor) {
		this.user_name = name_kor;
	}

	public String getName_eng() {
		return user_ename;
	}

	public void setName_eng(String name_eng) {
		this.user_ename = name_eng;
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

	public String getPassportNo() {
		return user_passNo;
	}

	public void setPassportNo(String passportNo) {
		this.user_passNo = passportNo;
	}

	public String getExpireDate() {
		return user_exDate;
	}

	public void setExpireDate(String expireDate) {
		this.user_exDate = expireDate;
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
	

}
