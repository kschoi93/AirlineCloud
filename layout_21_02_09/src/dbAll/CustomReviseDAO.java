package dbAll;

import java.util.ArrayList;
import java.util.List;

public class CustomReviseDAO extends DBConn{

	public CustomReviseDAO() {}
	
	// 회원 정보 수정(업데이트)
	public int ReviseUpdate(String user_id, String user_name,String user_ename,String user_pwd,String user_tel, String user_birth, String user_nation,String user_email) {
		int result = 0;
		
		try {
			getConn();
			sql = "update ac_user set user_name=?,user_ename=?,user_pwd=?,user_tel=?,user_birth=?,user_nation=?,user_email=? where user_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_ename);
			pstmt.setString(3, user_pwd);
			pstmt.setString(4, user_tel);
			pstmt.setString(5, user_birth);
			pstmt.setString(6, user_nation);
			pstmt.setString(7, user_email);
			pstmt.setString(8, user_id);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return result;
	}
	
	// 저장되어 있는 값이 공백인지 확인하고 공백이면 저장되어 있는 값을 넣기 위해 미리 불러오는 메소드
	public List<CustomReviseVO> blankCheck(String user_id) {
		List<CustomReviseVO> result = new ArrayList<CustomReviseVO>();
		try {
			getConn();
			// 생년월일은 처음에는 null로 되어 있어 값이 넘어가지 않는다. 고로 010101로 임의로 만들어 넘겨준다
			sql = "select user_name, user_ename, user_pwd, user_tel, nvl(user_birth,'01/01/01'), user_nation, user_email from ac_user where user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReviseVO vo = new CustomReviseVO();
				vo.setUser_name(rs.getString(1));
				vo.setUser_ename(rs.getString(2));
				vo.setUser_pwd(rs.getString(3));
				vo.setUser_tel(rs.getString(4));
				vo.setUser_birth(rs.getString(5));
				vo.setUser_nation(rs.getString(6));
				vo.setUser_email(rs.getString(7));
				
				result.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	public int ReviseDelete(String user_id) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_user where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
//	public int ReviseChildDelete(String )

}
