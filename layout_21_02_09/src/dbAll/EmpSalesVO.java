package dbAll;

public class EmpSalesVO {
	
	private String brdDate; // 출발일
	private String flightNo; // 항공편
	private String dep; // 출발지
	private String des; // 도착지
	private String fare; // 비용
	private String sales; // 매출
	private String	avail; // 영업이익
	
	private String startDate; // 필요한가
	private String endDate;
	private String startAge;
	private String endAge;
	private String gender;
	
	//파이차트용 변수
	private String group;
	private String count;
	
	


	public EmpSalesVO(String brdDate, String flightNo, String dep,String des,
				String fare, String sales,String avail, String startDate, String endDate,
				String startAge, String endAge , String gender) {
		
		this.brdDate = brdDate;
		this.flightNo=flightNo;
		this.dep=dep;
		this.des=des;
		this.fare=fare;
		this.sales=sales;
		this.avail=avail;
		
		this.startDate=startDate;
		this.endDate=endDate;
		this.startAge=startAge;
		this.endAge=endAge;
		this.gender=gender;
		
		
		
	}
	
	public EmpSalesVO(String brdDate, String flightNo, String dep,String des,
							String fare, String sales,String avail) {
	
		
		this.brdDate = brdDate;
		this.flightNo=flightNo;
		this.dep=dep;
		this.des=des;
		this.fare=fare;
		this.sales=sales;
		this.avail=avail;
			
		
	}
	

	//파이차트용
	public EmpSalesVO(String group, String count) {
		this.group = group;
		this.count = count;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartAge() {
		return startAge;
	}

	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}

	public String getEndAge() {
		return endAge;
	}

	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBrdDate() {
		return brdDate;
	}

	public void setBrdDate(String brdDate) {
		this.brdDate = brdDate;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
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

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getAvail() {
		return avail;
	}

	public void setAvail(String avail) {
		this.avail = avail;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	

	
	
	
	
	
	
	
}
