package dbAll;

public class EmpReservationVO {
	private String resno;
	private String user_name;
	private String user_birth;
	private String user_tel;
	private String dep;  // 같은거불러와도되나?
	private String flightno;
	private String brdDate; // 같은거 불러와도되나?
	

	
	
	public EmpReservationVO() {
		
	}
	
	public EmpReservationVO(String resno, String user_name, String user_birth, 
						String user_tel, String dep, String flightno) {
		this.resno = resno;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_tel = user_tel;
		this.dep = dep;
		this.flightno = flightno;
	}
	public EmpReservationVO(String resno, String user_name, String user_birth, 
			String user_tel, String dep, String flightno, String brdDate) {

		this.resno = resno;
		this.user_name = user_name;
		this.user_birth = user_birth;
		this.user_tel = user_tel;
		this.dep = dep;
		this.flightno = flightno;
		this.brdDate=brdDate;




}
	
	

	public String getResno() {
		return resno;
	}

	public void setResno(String resno) {
		this.resno = resno;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String getBrdDate() {
		return brdDate;
	}

	public void setBrdDate(String brdDate) {
		this.brdDate = brdDate;
	}

	
	

}
