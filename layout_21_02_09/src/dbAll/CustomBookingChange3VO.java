package dbAll;

public class CustomBookingChange3VO {
	private String dep;
	private String des;
	private String brdDate;
	private String depTime;
	private String desTime;
	private String flightNo;
	private String seatNo;
	private String fare; //table 1
	
	private String gender;
	private String userName;
	private String userEname;
	private String userPassNo;
	private String userExdate;
	private String userNation;
	private String userBirth;
	private String userTel;
	private String userEmail;//table2
	
	private String oldFlight;
	private String oldFare;
	private String newFlight;
	private String newFare;//table3
	
	private String newFlightNum;//항공편명 업데이트
	private String resNo;
	
	private int mileageTotal;//마일리지 업데이트
	private int mileageOld;
	private int mileageNew;
	
	public CustomBookingChange3VO() {
		
	}
	public CustomBookingChange3VO(String dep, String des, String brdDate, String depTime, String desTime, String flightNo, String seatNo, String fare) {
		/////////////////////
		//table 1
		
		this.dep=dep;
		this.des=des;
		this.brdDate=brdDate;
		this.depTime=depTime;
		this.desTime=desTime;
		this.flightNo=flightNo;
		this.seatNo=seatNo;
		this.fare=fare;
	}
	public CustomBookingChange3VO(String gender, String userName, String userEname, String userPassNo, String userExdate, String userNation, String userBirth, String userTel, String userEmail) {
		//table2
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
//	public CustomBookingChange3VO(String oldFlight, String oldFare, String newFlight, String newFare) {
//		this.oldFlight=oldFlight;
//		this.oldFare=oldFare;
//		this.newFlight=newFlight;
//		this.newFare=newFare;
//	}
	public CustomBookingChange3VO(String oldFlight, String oldFare) {
		this.oldFlight=oldFlight;
		this.oldFare=oldFare;
//		this.newFlight=newFlight;
//		this.newFare=newFare;
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
	public String getResNo() {
		return resNo;
	}
	public void setResNo(String resNo) {
		this.resNo = resNo;
	}
	public String getNewFlightNum() {
		return newFlightNum;
	}
	public void setNewFlightNum(String newFlightNum) {
		this.newFlightNum = newFlightNum;
	}
	public String getOldFlight() {
		return oldFlight;
	}
	public void setOldFlight(String oldFlight) {
		this.oldFlight = oldFlight;
	}
	public String getOldFare() {
		return oldFare;
	}
	public void setOldFare(String oldFare) {
		this.oldFare = oldFare;
	}
	public String getNewFlight() {
		return newFlight;
	}
	public void setNewFlight(String newFlight) {
		this.newFlight = newFlight;
	}
	public String getNewFare() {
		return newFare;
	}
	public void setNewFare(String newFare) {
		this.newFare = newFare;
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
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	
	
}
