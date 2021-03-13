package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomPlanDAO extends DBConn{

	public CustomPlanDAO() {}
	
	public List<CustomPlanVO> getKoreaRecord() {
		List<CustomPlanVO> lst = new ArrayList<CustomPlanVO>();
		try {
			getConn();
			sql = "select flightNo,dep,des,depTime,destime,flight_state from ac_flight where dep in('ICN', 'GMP') "
					+ " and deptime > to_char(sysdate,'HH24mi') order by depTime";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomPlanVO vo = new CustomPlanVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}

	public List<CustomPlanVO> getNationRecord() {
		List<CustomPlanVO> lst = new ArrayList<CustomPlanVO>();
		try {
			getConn();
			sql = "select flightNo,dep,des,depTime,destime,flight_state from ac_flight where dep not in('ICN','GMP') "
					+ " and deptime > to_char(sysdate,'HH24mi') order by depTime";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomPlanVO vo = new CustomPlanVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
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
