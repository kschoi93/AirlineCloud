package dbAll;

public class CustomReservation3VO {
	private String user_name;
	private String user_ename;
	private String user_passno;
	private String user_exdate;
	private String user_birth;
	private String user_tel;
	private String user_email;
	private String user_nation;
	private String user_gender;
	
	public CustomReservation3VO() {}
	public CustomReservation3VO(String user_name,String user_ename, String user_passno, String user_exdate,
			String user_birth, String user_tel, String user_email, String user_nation) {
		this.user_name = user_name;
		this.user_ename = user_ename;
		this.user_passno = user_passno;
		this.user_exdate = user_exdate;
		this.user_birth = user_birth;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_nation = user_nation;
	}
	public CustomReservation3VO(String user_name,String user_ename, String user_passno, String user_exdate,
			String user_birth, String user_tel, String user_email, String user_nation, String user_gender) {
		this.user_name = user_name;
		this.user_ename = user_ename;
		this.user_passno = user_passno;
		this.user_exdate = user_exdate;
		this.user_birth = user_birth;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_nation = user_nation;
		this.user_gender = user_gender;
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
	public String getUser_passno() {
		return user_passno;
	}
	public void setUser_passno(String user_passno) {
		this.user_passno = user_passno;
	}
	public String getUser_exdate() {
		return user_exdate;
	}
	public void setUser_exdate(String user_exdate) {
		this.user_exdate = user_exdate;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_nation() {
		return user_nation;
	}
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	
	

}
