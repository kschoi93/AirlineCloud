package dbAll;

public class CustomReservation3FellowVO {
	private String com_name;
	private String com_ename;
	private String com_passno;
	private String com_exdate;
	private String com_birth;
	private String com_tel;
	private String com_email;
	private String com_nation;
	private String com_gender;
	
	public CustomReservation3FellowVO() {}
	public CustomReservation3FellowVO(String com_name,String com_ename, String com_passno, String com_exdate,
			String com_birth, String com_tel, String com_email, String com_nation) {
		this.com_name = com_name;
		this.com_ename = com_ename;
		this.com_passno = com_passno;
		this.com_exdate = com_exdate;
		this.com_birth = com_birth;
		this.com_tel = com_tel;
		this.com_email = com_email;
		this.com_nation = com_nation;
		
	}
	public CustomReservation3FellowVO(String com_name,String com_ename, String com_passno, String com_exdate,
			String com_birth, String com_tel, String com_email, String com_nation, String com_gender) {
		this.com_name = com_name;
		this.com_ename = com_ename;
		this.com_passno = com_passno;
		this.com_exdate = com_exdate;
		this.com_birth = com_birth;
		this.com_tel = com_tel;
		this.com_email = com_email;
		this.com_nation = com_nation;
		this.com_gender = com_gender;
		
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_ename() {
		return com_ename;
	}
	public void setCom_ename(String com_ename) {
		this.com_ename = com_ename;
	}
	public String getCom_passno() {
		return com_passno;
	}
	public void setCom_passno(String com_passno) {
		this.com_passno = com_passno;
	}
	public String getCom_exdate() {
		return com_exdate;
	}
	public void setCom_exdate(String com_exdate) {
		this.com_exdate = com_exdate;
	}
	public String getCom_birth() {
		return com_birth;
	}
	public void setCom_birth(String com_birth) {
		this.com_birth = com_birth;
	}
	public String getCom_tel() {
		return com_tel;
	}
	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}
	public String getCom_email() {
		return com_email;
	}
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	public String getCom_nation() {
		return com_nation;
	}
	public void setCom_nation(String com_nation) {
		this.com_nation = com_nation;
	}
	public String getCom_gender() {
		return com_gender;
	}
	public void setCom_gender(String com_gender) {
		this.com_gender = com_gender;
	}

}
