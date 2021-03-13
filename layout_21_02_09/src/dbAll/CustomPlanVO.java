package dbAll;

public class CustomPlanVO {
	private String flightNo;
	private String dep;
	private String des;
	private String depTime;
	private String desTime;
	private String flight_state;
	public CustomPlanVO() {}
	public CustomPlanVO(String flightNo, String dep, String des, String depTime, String desTime,String flight_state) {
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
	
	
}
