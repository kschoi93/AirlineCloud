package dbAll;

public class CustomReviseVO {
	private String user_id;
	private String user_name;
	private String user_ename;
	private String user_pwd;
	private String user_tel;
	private String user_birth;
	private String user_nation;
	private String user_email;
	
	
	public CustomReviseVO() {}
	public CustomReviseVO(String user_id,String user_name, String user_ename, String user_pwd, String user_tel, String user_birth, String user_nation, String user_email) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_ename = user_ename;
		this.user_pwd = user_pwd;
		this.user_tel = user_tel;
		this.user_birth = user_birth;
		this.user_nation = user_nation;
		this.user_email = user_email;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_nation() {
		return user_nation;
	}
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	

}
