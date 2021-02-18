package dbAll;
import java.util.ArrayList;
import java.util.List;

public class AirlineSignUpDAO extends DBConn{

	public AirlineSignUpDAO() {}
	// 회원가입 db insert
	public int SignUpInsert(AirlineSignUpVO vo) {
		int result = 0;
		try{
			getConn();
			sql = "insert into ac_user(userNo, user_passNo, user_id, "
					+ " user_pwd,user_name,user_ename,user_tel, user_email,user_gender) "
					+ " values(user_sq.nextval,pass_sq.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getUser_pwd());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setString(4, vo.getUser_ename());
			pstmt.setString(5, vo.getUser_tel());
			pstmt.setString(6, vo.getUser_email());
			pstmt.setString(7, vo.getUser_gender());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// 회원 아이디 비밀번호 검색, 회원 유무 확인
	public List<AirlineSignUpVO> getidCheck(String user_id){
		List<AirlineSignUpVO> lst = new ArrayList<AirlineSignUpVO>();		
		try {
			getConn();
			sql = "select user_id from ac_user where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AirlineSignUpVO vo = new AirlineSignUpVO();
				vo.setUser_id(rs.getString(1));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
}
