package dbAll;
import java.util.ArrayList;
import java.util.List;

public class EmpFrameDAO extends DBConn{

	public EmpFrameDAO() {}
	
	public List<EmpFrameVO> getName(String user_id) {
		List<EmpFrameVO> lst = new ArrayList<EmpFrameVO>();
		try {
			getConn();
			sql = "select user_name from ac_user where user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFrameVO vo = new EmpFrameVO();
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
