package dbAll;

public class CustomReservation2VO {
	private String deptime;
	private String destime;
	private String flighttime;
	private String flightno;
	private String flight_state;
	private int fare;
	
	public CustomReservation2VO() {}
	
	public CustomReservation2VO(String flightno, String deptime,String destime,String flighttime,String flight_state, int fare) {

		this.deptime = deptime;
		this.destime = destime;
		this.flighttime = flighttime;
		this.flightno = flightno;
		this.flight_state = flight_state;
		this.fare = fare;
	}

	public String getDeptime() {
		return deptime;
	}

	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}

	public String getDestime() {
		return destime;
	}

	public void setDestime(String destime) {
		this.destime = destime;
	}

	public String getFlighttime() {
		return flighttime;
	}

	public void setFlighttime(String flighttime) {
		this.flighttime = flighttime;
	}

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String getFlight_state() {
		return flight_state;
	}

	public void setFlight_state(String flight_state) {
		this.flight_state = flight_state;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}
	
	

}
