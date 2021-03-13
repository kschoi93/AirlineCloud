package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomReservation4DAO extends DBConn{

	public CustomReservation4DAO() {}
	
	public List<CustomReservation4VO> startSeat(String startFlightno, String startDate) {
		
		List<CustomReservation4VO> lst = new ArrayList<CustomReservation4VO>();
		try {
			getConn();
			sql = "select seatno from (select * from ac_reservation where flightno = ? and brddate = ?) r, ac_seat s where r.resno=s.resno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startFlightno);
			pstmt.setString(2, startDate);
			

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomReservation4VO vo = new CustomReservation4VO();
				vo.setSeatNo(rs.getString(1));
				
				lst.add(vo);
			} 
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	
	public List<CustomReservation4VO> arriveSeat(String arriveFlightno, String arriveDate) {
		
		List<CustomReservation4VO> lst = new ArrayList<CustomReservation4VO>();
		try {
			getConn();
			sql = "select seatno from (select * from ac_reservation where flightno = ? and brddate = ?) r, ac_seat s where r.resno=s.resno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arriveFlightno);
			pstmt.setString(2, arriveDate);
			

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomReservation4VO vo = new CustomReservation4VO();
				vo.setSeatNo(rs.getString(1));
				
				lst.add(vo);
			}
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
}
