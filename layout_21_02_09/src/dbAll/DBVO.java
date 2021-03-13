package dbAll;

public class DBVO {

	public DBVO() {}
	//////////////////////////////////////// customrevise ////////////////////////////////////
	private String user_id;
	private String user_name;
	private String user_ename;
	private String user_pwd;
	private String user_tel;
	private String user_birth;
	private String user_nation;
	private String user_email;
	
	
	public void CustomReviseVO(String user_id,String user_name, String user_ename, String user_pwd, String user_tel, String user_birth, String user_nation, String user_email) {
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
	
////////////////////////////////////customplan////////////////////////////////////////////
	private String flightNo;
	private String dep;
	private String des;
	private String depTime;
	private String desTime;
	private String flight_state;
	public void CustomPlanVO(String flightNo, String dep, String des, String depTime, String desTime,String flight_state) {
		this.flightNo = flightNo;
		this.dep = dep;
		this.des = des;
		this.depTime = depTime;
		this.desTime = desTime;
		this.flight_state = flight_state;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getDesTime() {
		return desTime;
	}
	public void setDesTime(String desTime) {
		this.desTime = desTime;
	}
	public String getFlight_state() {
		return flight_state;
	}
	public void setFlight_state(String flight_state) {
		this.flight_state = flight_state;
	}
	
//////////////////////////////////custom frame/////////////////////////////////////////
//	private String user_id;
//	private String user_name;
//	
	public void CustomFrameVO(String user_id, String user_name) {
		this.user_id = user_id;
		this.user_name = user_name;
	}
//	public String getUser_id() {
//		return user_id;
//	}
//	public String getUser_name() {
//		return user_name;
//	}
//	public void setUser_name(String user_name) {
//		this.user_name = user_name;
//	}
//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}
	/////////////////////////////// airlingsignUp/////////////////////////////////////	
//	private String user_id;
//	private String user_pwd;
//	private String user_name;
//	private String user_ename;
//	private String user_tel;
//	private String user_email;
	private String user_gender;
	public void AirlineSignUpVO(String user_id, String user_pwd, 
			String user_name, String user_ename, String user_tel,String user_email, String user_gender) {
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_ename = user_ename;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_gender = user_gender;
	}
//	
//	public AirlineSignUpVO(String user_id) {
//		this.user_id = user_id;
//	}
//
//	public String getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}
//
//	public String getUser_pwd() {
//		return user_pwd;
//	}
//
//	public void setUser_pwd(String user_pwd) {
//		this.user_pwd = user_pwd;
//	}
//
//	public String getUser_name() {
//		return user_name;
//	}
//	public void setUser_name(String user_name) {
//		this.user_name = user_name;
//	}
//	public String getUser_ename() {
//		return user_ename;
//	}
//	public void setUser_ename(String user_ename) {
//		this.user_ename = user_ename;
//	}
//	public String getUser_tel() {
//		return user_tel;
//	}
//
//	public void setUser_tel(String user_tel) {
//		this.user_tel = user_tel;
//	}
//
//	public String getUser_email() {
//		return user_email;
//	}
//
//	public void setUser_email(String user_email) {
//		this.user_email = user_email;
//	}
	
	public String getUser_gender() {
		return user_gender;
	}
	
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	//////////////////////////////////////////// airlinelogin/////////////////////////////////////////
//	private String user_id;
//	private String user_pwd;
//	
//	public AirlineLoginVO() {}
	public void AirlineLoginVO(String user_id,String user_pwd) {
		this.user_id = user_id;
		this.user_pwd = user_pwd;
	}
//	public String getUser_id() {
//		return user_id;
//	}
//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}
//	public String getUser_pwd() {
//		return user_pwd;
//	}
//	public void setUser_pwd(String user_pwd) {
//		this.user_pwd = user_pwd;
//	}
}
