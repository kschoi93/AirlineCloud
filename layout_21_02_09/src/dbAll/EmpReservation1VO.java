package dbAll;

public class EmpReservation1VO {
	
	
	private String flightno;
	private String depTime;
	private String desTime;
	private String flightTime;
	private String state;
	private String fare;
	
	

	public EmpReservation1VO() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpReservation1VO(String flightno, String depTime, String desTime, String flightTime, String state,String fare) {
		
		this.flightno = flightno;
		this.depTime=depTime;
		this.desTime = desTime;
		this.flightTime = flightTime;
		this.state= state;
		this.fare = fare;	
	}

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	
	

}
