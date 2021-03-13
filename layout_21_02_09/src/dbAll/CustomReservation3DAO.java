package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomReservation3DAO extends DBConn{

	public CustomReservation3DAO() {}
	
	public List<CustomReservation3VO> setCustomInfo(String passno, String user_id) {
		List<CustomReservation3VO> result = new ArrayList<CustomReservation3VO>();
		try {
			getConn();
			sql = "";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passno);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
}
