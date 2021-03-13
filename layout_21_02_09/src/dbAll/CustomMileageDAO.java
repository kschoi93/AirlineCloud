package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomMileageDAO extends DBConn{

	public CustomMileageDAO() {}
	
	public List<CustomMileageVO> setMileage(String id) {
		List<CustomMileageVO> lst = new ArrayList<CustomMileageVO>();
		try {
			getConn();
			sql = "select a.dep, a.des, brddate, b.flightno, fare, fare*0.02, c.mileage*10 from ac_flight a "
					+ " join (select * from ac_reservation where user_passno=(select user_passno from ac_user "
					+ " where user_id=?)) b on a.flightno = b.flightno "
					+ " join ac_user c on c.user_passno=b.user_passno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomMileageVO vo = new CustomMileageVO();
				vo.setDep(rs.getString(1));
				vo.setDes(rs.getString(2));
				vo.setBrddate(rs.getString(3));
				vo.setFlightno(rs.getString(4));
				vo.setFare(rs.getString(5));
				vo.setMileage(rs.getString(6));
				vo.setSumMileage(rs.getString(7));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return lst;
	}
	
	public List<CustomMileageVO> setLabel(String id) {
		List<CustomMileageVO> lst = new ArrayList<CustomMileageVO>();
		try {
			getConn();
			sql = "select user_name, userno, grade from ac_user where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomMileageVO vo = new CustomMileageVO();
				vo.setUser_name(rs.getString(1));
				vo.setUserno(rs.getString(2));
				vo.setGrade(rs.getString(3));
				
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
