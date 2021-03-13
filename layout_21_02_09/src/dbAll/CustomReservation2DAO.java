package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomReservation2DAO extends DBConn{

	public CustomReservation2DAO() {	}
	public List<CustomReservation2VO> getStartPlan(String start,String arrive){
		List<CustomReservation2VO> lst = new ArrayList<CustomReservation2VO>();
		try {
			getConn();
			sql = "select flightno, deptime, destime, flighttime, flight_state, fare from ac_flight where dep =? AND des = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, arrive);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReservation2VO vo = new CustomReservation2VO();
				vo.setFlightno(rs.getString(1));
				vo.setDeptime(rs.getString(2));
				vo.setDestime(rs.getString(3));
				vo.setFlighttime(rs.getString(4));
				vo.setFlight_state(rs.getString(5));
				vo.setFare(rs.getInt(6));
				
				lst.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	
	public List<CustomReservation2VO> getarrivePlan(String start,String arrive){
		List<CustomReservation2VO> lst = new ArrayList<CustomReservation2VO>();
		try {
			getConn();
			sql = "select flightno, deptime, destime, flighttime, flight_state, fare from ac_flight where dep =? AND des = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arrive);
			pstmt.setString(2, start);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReservation2VO vo = new CustomReservation2VO();
				vo.setFlightno(rs.getString(1));
				vo.setDeptime(rs.getString(2));
				vo.setDestime(rs.getString(3));
				vo.setFlighttime(rs.getString(4));
				vo.setFlight_state(rs.getString(5));
				vo.setFare(rs.getInt(6));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return lst;
	}
	
	

}
