package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomBookingChange1DAO extends DBConn{
	public CustomBookingChange1DAO() {
		
		
	}
	
	public List<CustomBookingChange1VO> getName(String user_id) {
		List<CustomBookingChange1VO> lst = new ArrayList<CustomBookingChange1VO>();
		try {
			getConn();
			sql="select user_name from ac_user where user_id =?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomBookingChange1VO vo = new CustomBookingChange1VO();
				vo.setUser_id(rs.getString(1));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	public List<CustomBookingChange1VO> bookingAllSelect(String user_name){
		
		List<CustomBookingChange1VO> lst = new ArrayList<CustomBookingChange1VO>();
		try {
			getConn();
			sql = "select r.resNo, r.brdDate, r.flightNo, d1.dep_city, d1.dep, f.depTime "
					+ ", d2.des_city, d2.des, f.desTime "
					+ "from ac_reservation r, ac_dep d1, ac_flight f, ac_des d2 "
					+ "where (select u.user_passNo from ac_user u where u.user_name=?)=r.user_passNo "
					+ "AND r.flightNo=f.flightNo AND f.dep=d1.dep AND f.des=d2.des order by r.brdDate";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomBookingChange1VO vo = new CustomBookingChange1VO(rs.getString(1),
						rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
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
