package dbAll;

public class CustomMileageVO {
	private String user_id;
	private String user_name;
	private String userno;
	private String grade;
	
	private String dep;
	private String des;
	private String brddate;
	private String flightno;
	private String fare;
	private String mileage;
	private String sumMileage;
	public CustomMileageVO() {}
	
	public CustomMileageVO(String user_name, String userno, String grade) {
		this.user_name = user_name;
		this.userno = userno;
		this.grade = grade;
	}
	
	public CustomMileageVO(String dep,String des, String brddate, String flightno,
			String fare, String mileage, String sumMileage) {
		this.dep = dep;
		this.des = des;
		this.brddate = brddate;
		this.flightno =flightno;
		this.fare = fare;
		this.mileage = mileage;
		this.sumMileage = sumMileage;
	}

	
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	public String getBrddate() {
		return brddate;
	}

	public void setBrddate(String brddate) {
		this.brddate = brddate;
	}

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getSumMileage() {
		return sumMileage;
	}

	public void setSumMileage(String sumMileage) {
		this.sumMileage = sumMileage;
	}


}
