package dbAll;
import java.util.ArrayList;
import java.util.List;

public class CustomFrameDAO extends DBConn{

	public CustomFrameDAO() {}
	
	public List<CustomFrameVO> getName(String user_id) {
		List<CustomFrameVO> lst = new ArrayList<CustomFrameVO>();
		try {
			getConn();
			sql = "select user_name from ac_user where user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomFrameVO vo = new CustomFrameVO();
				vo.setUser_name(rs.getString(1));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		return lst;
	}

}
