package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomMypageDAO extends DBConn{

	public CustomMypageDAO() {}
	
	public List<CustomMypageVO> setMypage(String id) {
		List<CustomMypageVO> lst = new ArrayList<CustomMypageVO>();
		
		try {
			getConn();
			sql = "select user_name,userno,mileage from ac_user where user_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomMypageVO vo = new CustomMypageVO();
				
				vo.setUser_name(rs.getString(1));
				vo.setUserno(rs.getInt(2));
				vo.setMileage(rs.getInt(3));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}

	public List<CustomMypageVO> setCount(String id) {
		List<CustomMypageVO> lst = new ArrayList<CustomMypageVO>();
		
		try {
			getConn();
			sql = "select count(resno) from ac_reservation where user_passno=(select user_passno from ac_user where user_id=?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomMypageVO vo = new CustomMypageVO();
				
				vo.setCount(rs.getInt(1));
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
