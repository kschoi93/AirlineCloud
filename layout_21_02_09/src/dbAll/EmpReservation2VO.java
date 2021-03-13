package dbAll;

public class EmpReservation2VO {

	
	private String flightNO;
	private String fare;
	private String flightNO2;
	private String changeFare; //삭제 
	
	private String dep;

	private String brdDate;
	private String depTime;
	private String desTime;
	private String flightNo;
	private String seatNo;
	private String fare2; //table 1
	
	private String gender;
	private String userName;
	private String userEname;
	private String userPassNo;
	private String userExdate;
	private String userNation;
	private String userBirth;
	private String userTel;
	private String userEmail;  //table2
	
	private String newFlightNum;//항공편명 업데이트
	private String resNo;
	
	private int mileageTotal;//마일리지 업데이트
	private int mileageOld;
	private int mileageNew;
	
	
	public EmpReservation2VO() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpReservation2VO(String flightNO, String fare) {
//	public EmpReservation2VO(String flightNO, String fare, String flightNO2,String changeFare) {	
		this.flightNO=flightNO;
		this.fare= fare;
//		this.flightNO2 = flightNO2;
//		this.changeFare = changeFare;
	}
	public EmpReservation2VO(String dep, String brdDate, String depTime, String desTime, String flightNo, String seatNo, String fare2) {
		/////////////////////
		//table 1
		
		this.dep=dep;

		this.brdDate=brdDate;
		this.depTime=depTime;
		this.desTime=desTime;
		this.flightNo=flightNo;
		this.seatNo=seatNo;
		this.fare2=fare2;
	}
	public EmpReservation2VO(String gender, String userName, String userEname, String userPassNo, String userExdate, String userNation, String userBirth, String userTel, String userEmail) {
		this.gender=gender;
		this.userName=userName;
		this.userEname=userEname;
		this.userPassNo=userPassNo;
		this.userExdate=userExdate;
		this.userNation=userNation;
		this.userBirth=userBirth;
		this.userTel=userTel;
		this.userEmail=userEmail;
	}

	public String getFlightNO() {
		return flightNO;
	}

	public void setFlightNO(String flightNO) {
		this.flightNO = flightNO;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getFlightNO2() {
		return flightNO2;
	}

	public void setFlightNO2(String flightNO2) {
		this.flightNO2 = flightNO2;
	}

	public String getChangeFare() {
		return changeFare;
	}

	public void setChangeFare(String changeFare) {
		this.changeFare = changeFare;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}



	public String getBrdDate() {
		return brdDate;
	}

	public void setBrdDate(String brdDate) {
		this.brdDate = brdDate;
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

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getFare2() {
		return fare2;
	}

	public void setFare2(String fare2) {
		this.fare2 = fare2;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEname() {
		return userEname;
	}

	public void setUserEname(String userEname) {
		this.userEname = userEname;
	}

	public String getUserPassNo() {
		return userPassNo;
	}

	public void setUserPassNo(String userPassNo) {
		this.userPassNo = userPassNo;
	}

	public String getUserExdate() {
		return userExdate;
	}

	public void setUserExdate(String userExdate) {
		this.userExdate = userExdate;
	}

	public String getUserNation() {
		return userNation;
	}

	public void setUserNation(String userNation) {
		this.userNation = userNation;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getNewFlightNum() {
		return newFlightNum;
	}

	public void setNewFlightNum(String newFlightNum) {
		this.newFlightNum = newFlightNum;
	}

	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public int getMileageTotal() {
		return mileageTotal;
	}

	public void setMileageTotal(int mileageTotal) {
		this.mileageTotal = mileageTotal;
	}

	public int getMileageOld() {
		return mileageOld;
	}

	public void setMileageOld(int mileageOld) {
		this.mileageOld = mileageOld;
	}

	public int getMileageNew() {
		return mileageNew;
	}

	public void setMileageNew(int mileageNew) {
		this.mileageNew = mileageNew;
	}
	

	
	

}
