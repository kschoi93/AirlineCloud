package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpReservation1DAO extends DBConn {

	public EmpReservation1DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public List<EmpReservation1VO> EmpReservation1Allselect(String dep2 , String FlightNO2){
		List<EmpReservation1VO> lst = new ArrayList<EmpReservation1VO>();
		try {
			getConn();
			sql =  "select flightNo,depTime, desTime, flightTime,  flight_state, fare  from ac_flight where dep= ? "
					+ "AND flightno not in( ? ) order by depTime";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dep2);
			pstmt.setString(2, FlightNO2);
			
			rs=pstmt.executeQuery();	
			
			while(rs.next()) {
				EmpReservation1VO vo = new EmpReservation1VO(
						
				rs.getString(1),
				rs.getString(2),
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6)
				);
				
				lst.add(vo);
				
				

				
			}
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
		
		
	}

}
