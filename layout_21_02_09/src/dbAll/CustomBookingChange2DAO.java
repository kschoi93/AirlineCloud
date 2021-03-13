package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomBookingChange2DAO extends DBConn{

	public CustomBookingChange2DAO() {
		
	}
	public List<CustomBookingChange2VO> getDepDes(String dep, String des, String flightNum){
		List<CustomBookingChange2VO> lst = new ArrayList<CustomBookingChange2VO>();
		try {
			getConn();
			sql = "select dep,des, depTime, desTime, flightTime, flightNo, flight_state, fare from ac_flight "
					+ "where flightno not in(?) AND dep=? AND des=? order by depTime";
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, flightNum);
			pstmt.setString(2, dep);
			pstmt.setString(3, des);
			System.out.println(dep+des);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담는 작업
				CustomBookingChange2VO vo = new CustomBookingChange2VO();
				vo.setDep(rs.getString(1));
				vo.setDes(rs.getString(2));
				vo.setDepTime(rs.getString(3));
				vo.setDesTime(rs.getString(4));
				vo.setFlightTime(rs.getString(5));
				vo.setFlightNo(rs.getString(6));
				vo.setFlightState(rs.getString(7));
				vo.setFare(rs.getString(8));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
}