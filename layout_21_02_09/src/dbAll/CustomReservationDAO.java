package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomReservationDAO extends DBConn{

	public CustomReservationDAO() {	}
	public List<CustomReservationVO> getStartPlan(String start){
		List<CustomReservationVO> lst = new ArrayList<CustomReservationVO>();
		try {
			getConn();
			sql = "select flightno,deptime,destime,flighttime,flight_state,fare from ac_flight "
					+ " where deptime>to_char(sysdate,'HH24mi') order by deptime";
			
			pstmt = conn.prepareStatement(sql);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return lst;
	}
	
	public List<CustomReservationVO> setDate(String arrive) {
		List<CustomReservationVO> lst = new ArrayList<CustomReservationVO>();
		CustomReservationVO vo = new CustomReservationVO();
		
		
		
		return lst;
	}

}
