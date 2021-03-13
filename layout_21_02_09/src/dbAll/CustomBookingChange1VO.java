package dbAll;

public class CustomBookingChange1VO {
	private String loginName;
	private String resno_r;
	private String brdDate_r;
	private String flightNo_r;
	private String dep_city_d1;
	private String dep_airport_d1;
	private String depTime_f;
	private String des_city_d2;
	private String des_airport_d2;
	private String desTime_f;
	private String user_id;
	
	public CustomBookingChange1VO() {}
	public CustomBookingChange1VO(String user_id) {
		this.user_id =user_id;
	}
	public CustomBookingChange1VO(String resno_r, String brdDate_r,String flightNo_r, String dep_city_d1, String dep_airport_d1,String depTime_f, String des_city_d2, String des_airport_d2,String desTime_f) {
		this.resno_r=resno_r;
		this.brdDate_r=brdDate_r;
		this.flightNo_r=flightNo_r;
		this.dep_city_d1=dep_city_d1;
		this.dep_airport_d1=dep_airport_d1;
		this.depTime_f=depTime_f;
		this.des_city_d2=des_city_d2;
		this.des_airport_d2=des_airport_d2;
		this.desTime_f=desTime_f;
	}
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getResno_r() {
		return resno_r;
	}
	public void setResno_r(String resno_r) {
		this.resno_r = resno_r;
	}
	public String getBrdDate_r() {
		return brdDate_r;
	}
	public void setBrdDate_r(String brdDate_r) {
		this.brdDate_r = brdDate_r;
	}
	public String getFlightNo_r() {
		return flightNo_r;
	}
	public void setFlightNo_r(String flightNo_r) {
		this.flightNo_r = flightNo_r;
	}
	public String getDep_city_d1() {
		return dep_city_d1;
	}
	public void setDep_city_d1(String dep_city_d1) {
		this.dep_city_d1 = dep_city_d1;
	}
	public String getDep_airport_d1() {
		return dep_airport_d1;
	}
	public void setDep_airport_d1(String dep_airport_d1) {
		this.dep_airport_d1 = dep_airport_d1;
	}
	public String getDepTime_f() {
		return depTime_f;
	}
	public void setDepTime_f(String depTime_f) {
		this.depTime_f = depTime_f;
	}
	public String getDes_city_d2() {
		return des_city_d2;
	}
	public void setDes_city_d2(String des_city_d2) {
		this.des_city_d2 = des_city_d2;
	}
	public String getDes_airport_d2() {
		return des_airport_d2;
	}
	public void setDes_airport_d2(String des_airport_d2) {
		this.des_airport_d2 = des_airport_d2;
	}
	public String getDesTime_f() {
		return desTime_f;
	}
	public void setDesTime_f(String desTime_f) {
		this.desTime_f = desTime_f;
	}

}
