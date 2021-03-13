package dbAll;

public class CustomReservation6VO {
	private String user_name;
	private String user_ename;
	private String user_birth;
	private String user_tel;
	private String user_email;
	private String user_nation;
	private String user_gender;
	
	private String com_passno;
	private String com_name;
	private String com_ename;
	private String com_birth;
	private String com_gender;
	private String com_exdate;
	private String com_nation;
	private String com_tel;
	private String com_email;
	
	private String resno;
	private String res_date;
	private String flightno;
	private String brdDate;
	private String user_passno;
	private String user_exdate;
	
	private String seatno;
	private String meal;
	
	
	private int mileage;
	private String user_id;
	
	public CustomReservation6VO() {	}
	// 회원 정보입력
	public CustomReservation6VO(String user_name,String user_ename, String user_passno, 
			String user_birth, String user_tel, String user_email, String user_nation, String user_gender) {
		this.user_name = user_name;
		this.user_ename = user_ename;
		this.user_passno = user_passno;
		this.user_birth = user_birth;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_nation = user_nation;
		this.user_gender = user_gender;
	}
	// 비회원 정보입력
	public CustomReservation6VO(String com_passno,String com_name, String com_ename, String com_birth, String com_gender, String com_exdate
			,String com_nation, String com_tel, String com_email, String resno) {
		this.com_passno = com_passno;
		this.com_name = com_name;
		this.com_ename = com_ename;
		this.com_birth = com_birth;
		this.com_gender = com_gender;
		this.com_exdate = com_exdate;
		this.com_nation = com_nation;
		this.com_tel = com_tel;
		this.com_email = com_email;
		this.resno = resno;
	}
	// reservation 정보 생성
	public CustomReservation6VO(String resno, String res_date, String flightno, String brdDate, String user_passno, String user_exdate) {
		this.resno = resno;
		this.res_date = res_date;
		this.flightno = flightno;
		this.brdDate = brdDate;
		this.user_passno = user_passno;
		this.user_exdate = user_exdate;
		
	}
	// 좌석 정보입력
	public CustomReservation6VO(String resno, String seatno, String meal) {
		this.resno = resno;
		this.seatno = seatno;
		this.meal = meal;
	}
	// 마일리지 입력
	public CustomReservation6VO(int mileage,String user_id) {
		this.mileage = mileage;
		this.user_id = user_id;
	}
	
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_ename() {
		return user_ename;
	}
	public void setUser_ename(String user_ename) {
		this.user_ename = user_ename;
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
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_nation() {
		return user_nation;
	}
	public void setUser_nation(String user_nation) {
		this.user_nation = user_nation;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getCom_passno() {
		return com_passno;
	}
	public void setCom_passno(String com_passno) {
		this.com_passno = com_passno;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	public String getCom_ename() {
		return com_ename;
	}
	public void setCom_ename(String com_ename) {
		this.com_ename = com_ename;
	}
	public String getCom_birth() {
		return com_birth;
	}
	public void setCom_birth(String com_birth) {
		this.com_birth = com_birth;
	}
	public String getCom_gender() {
		return com_gender;
	}
	public void setCom_gender(String com_gender) {
		this.com_gender = com_gender;
	}
	public String getCom_exdate() {
		return com_exdate;
	}
	public void setCom_exdate(String com_exdate) {
		this.com_exdate = com_exdate;
	}
	public String getCom_nation() {
		return com_nation;
	}
	public void setCom_nation(String com_nation) {
		this.com_nation = com_nation;
	}
	public String getCom_tel() {
		return com_tel;
	}
	public void setCom_tel(String com_tel) {
		this.com_tel = com_tel;
	}
	public String getCom_email() {
		return com_email;
	}
	public void setCom_email(String com_email) {
		this.com_email = com_email;
	}
	public String getResno() {
		return resno;
	}
	public void setResno(String resno) {
		this.resno = resno;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
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
	public String getUser_passno() {
		return user_passno;
	}
	public void setUser_passno(String user_passno) {
		this.user_passno = user_passno;
	}
	public String getUser_exdate() {
		return user_exdate;
	}
	public void setUser_exdate(String user_exdate) {
		this.user_exdate = user_exdate;
	}
	public String getSeatno() {
		return seatno;
	}
	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	
	

}
