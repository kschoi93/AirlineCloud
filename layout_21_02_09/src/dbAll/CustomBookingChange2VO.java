package dbAll;

public class CustomBookingChange2VO {
	private String dep;
	private String des;
	private String depTime;
	private String desTime;
	private String flightTime;
	private String flightNo;
	private String flightState;
	private String fare;
	
	public CustomBookingChange2VO() {
		
	}

	public CustomBookingChange2VO(String dep,String des, String depTime, String desTime, String flightTime, String flightNo, String flightState, String fare) {
		this.dep=dep;
		this.des=des;
		this.depTime=depTime;
		this.desTime=desTime;
		this.flightTime=flightTime;
		this.flightNo=flightNo;
		this.flightState=flightState;
		this.fare=fare;
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

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public String getFlightState() {
		return flightState;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public void setFlightState(String flightState) {
		this.flightState = flightState;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}
	
	
}
