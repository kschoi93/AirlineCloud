package dbAll;

public class EmpFlightChangeVO {
	private String flightno_r;
	private String brdDate_r;
	private String dep;
	private String depTime;
	private String des;
	private String desTime;
	private String flight_state;
	
	public EmpFlightChangeVO() {
		
	}
	public EmpFlightChangeVO(String flightno_r, String brdDate_r,
			String dep, String depTime, String des, String desTime, String flight_state) {
		this.flightno_r=flightno_r;
		this.brdDate_r=brdDate_r;
		this.dep=dep;
		this.depTime=depTime;
		this.des=des;
		this.desTime=desTime;
		this.flight_state=flight_state;
	}
	public String getFlightno_r() {
		return flightno_r;
	}
	public void setFlightno_r(String flightno_r) {
		this.flightno_r = flightno_r;
	}
	public String getBrdDate_r() {
		return brdDate_r;
	}
	public void setBrdDate_r(String brdDate_r) {
		this.brdDate_r = brdDate_r;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
	
}
