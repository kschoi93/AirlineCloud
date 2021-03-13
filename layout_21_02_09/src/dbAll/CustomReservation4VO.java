package dbAll;

public class CustomReservation4VO {
	private String resNo;
	private String res_date;
	private String flightNo;
	private String brdDate;
	private String user_passNo;
	private String user_exDate;
	private String seatNo;
	private String seatNo2;
	private String seatNo3;
	private String seatNo4;
	private String seatNo5;
	private String meal;
	
	public CustomReservation4VO() {}
	public CustomReservation4VO(String meal,String seatNo) {
		this.meal = meal;
		this.seatNo = seatNo;
	}
	public CustomReservation4VO(String meal,String seatNo, String seatNo2) {
		this.meal = meal;
		this.seatNo = seatNo;
		this.seatNo2 = seatNo2;
	}
	public CustomReservation4VO(String meal,String seatNo, String seatNo2,String seatNo3) {
		this.meal = meal;
		this.seatNo = seatNo;
		this.seatNo2 = seatNo2;
		this.seatNo3 = seatNo3;
	}
	public CustomReservation4VO(String meal,String seatNo, String seatNo2,String seatNo3,String seatNo4) {
		this.meal = meal;
		this.seatNo = seatNo;
		this.seatNo2 = seatNo2;
		this.seatNo3 = seatNo3;
		this.seatNo4 = seatNo4;
	}
	public CustomReservation4VO(String meal,String seatNo, String seatNo2,String seatNo3,String seatNo4,String seatNo5) {
		this.meal = meal;
		this.seatNo = seatNo;
		this.seatNo2 = seatNo2;
		this.seatNo3 = seatNo3;
		this.seatNo4 = seatNo4;
		this.seatNo5 = seatNo5;
	}

	public CustomReservation4VO(String resNo,String res_date, String flightNo, String brdDate, String user_passNo,String user_exDate,String seatNo, String meal) {
		this.resNo = resNo;
		this.res_date = res_date;
		this.flightNo = flightNo;
		this.brdDate = brdDate;
		this.user_passNo = user_passNo;
		this.user_exDate = user_exDate;
		this.seatNo = seatNo;
		this.meal = meal;
	}

	public String getSeatNo2() {
		return seatNo2;
	}
	public void setSeatNo2(String seatNo2) {
		this.seatNo2 = seatNo2;
	}
	public String getSeatNo3() {
		return seatNo3;
	}
	public void setSeatNo3(String seatNo3) {
		this.seatNo3 = seatNo3;
	}
	public String getSeatNo4() {
		return seatNo4;
	}
	public void setSeatNo4(String seatNo4) {
		this.seatNo4 = seatNo4;
	}
	public String getSeatNo5() {
		return seatNo5;
	}
	public void setSeatNo5(String seatNo5) {
		this.seatNo5 = seatNo5;
	}
	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getRes_date() {
		return res_date;
	}

	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getBrdDate() {
		return brdDate;
	}

	public void setBrdDate(String brdDate) {
		this.brdDate = brdDate;
	}

	public String getUser_passNo() {
		return user_passNo;
	}

	public void setUser_passNo(String user_passNo) {
		this.user_passNo = user_passNo;
	}

	public String getUser_exDate() {
		return user_exDate;
	}

	public void setUser_exDate(String user_exDate) {
		this.user_exDate = user_exDate;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
	

}
