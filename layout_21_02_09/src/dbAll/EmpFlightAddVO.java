package dbAll;

public class EmpFlightAddVO {
	private String regNo;
	private String flightno;
	private String dep;
	private String des;
	private String depTime;
	private String desTime;
	private String fare;
	
	public EmpFlightAddVO() {
		
	}
	public EmpFlightAddVO(String regNo, String flightNo, String dep, String des, String depTime, String desTime, String fare) {
		this.regNo=regNo;
		this.flightno=flightNo;
		this.dep=dep;
		this.des=des;
		this.depTime=depTime;
		this.desTime=desTime;
		this.fare=fare;
	}
	public EmpFlightAddVO(String dep, String crew) {
		this.dep=dep;
	
	}
	public EmpFlightAddVO(String regNo) {
		this.regNo = regNo;
		
	}
	
	
	
	
	
	public String getFlightno() {
		return flightno;
	}
	public void setFlightno(String flightno) {
		this.flightno = flightno;
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
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	

}
