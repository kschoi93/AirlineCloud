package dbAll;

public class CustomReservationVO {
	private String deptime;
	private String destime;
	private String flighttime;
	private String flightno;
	private String flight_state;
	
	public CustomReservationVO() {}
	
	public CustomReservationVO(String deptime,String destime,String flighttime,String flightno, String flight_state) {
		this.deptime = deptime;
		this.destime = destime;
		this.flighttime = flighttime;
		this.flightno = flightno;
		this.flight_state = flight_state;
	}
	

}
