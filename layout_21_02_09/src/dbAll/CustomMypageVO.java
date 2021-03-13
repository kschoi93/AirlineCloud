package dbAll;

public class CustomMypageVO {
	private int userno;
	private int mileage;
	private String user_name;
	private int count;
	public CustomMypageVO() {	}
	public CustomMypageVO(int count) {
		this.count = count;
	}
	public CustomMypageVO(String user_name, int userno, int mileage) {
		this.userno = userno;
		this.mileage = mileage;
		this.user_name = user_name;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	

}
