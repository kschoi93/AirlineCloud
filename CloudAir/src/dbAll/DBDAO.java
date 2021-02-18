package dbAll;

import java.util.ArrayList;
import java.util.List;

public class DBDAO extends DBConn {

	public DBDAO() {}
	/////////////////////////////////customrevise///////////////////////////////////////////////
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
	
	//////////////////////////////////// customplan       //////////////////////////////////////////
	public List<CustomPlanVO> getCustomRecord() {
		List<CustomPlanVO> lst = new ArrayList<CustomPlanVO>();
		try {
			getConn();
			sql = "select flightNo,dep,des,depTime,destime,flight_state from ac_flight";
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
	//////////////////////////////////////////customframe//////////////////////////////////////////////////
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
	
	/////////////////////////////////////////airlinesignup/////////////////////////////////////////////////////
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
		////////////////////////////////////////////airlineloginDAO////////////////////////////////////
		public List<AirlineLoginVO> LoginAllSelect(){
			List<AirlineLoginVO> lst = new ArrayList<AirlineLoginVO>();
			try {
				getConn();
				sql = "select user_id, user_pwd from ac_user";
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					AirlineLoginVO vo = new AirlineLoginVO(rs.getString(0),rs.getString(1));
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return lst;
		}
		
		public int getLogin(String user_id,String user_pwd){
			List<AirlineLoginVO> lst = new ArrayList<AirlineLoginVO>();
			
			int state = 0;
			try {
				getConn();
				sql = "select user_id, user_pwd from ac_user where user_id = ? and user_pwd = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, user_pwd);
				
				rs = pstmt.executeQuery();
				if(rs.next()) state = 1;
				
			} catch(Exception e) {
				System.out.println("DB 아이디 비밀번호 확인에러"+e.getMessage());
			} finally {
				dbClose();
			}
			return state;
		}
}
